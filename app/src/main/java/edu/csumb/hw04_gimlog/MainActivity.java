package edu.csumb.hw04_gimlog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.csumb.hw04_gimlog.auth.Session;
import edu.csumb.hw04_gimlog.data.AppDatabase;
import edu.csumb.hw04_gimlog.data.GymLog;
import edu.csumb.hw04_gimlog.data.GymLogDao;

public class MainActivity extends AppCompatActivity {

    // Panels + inputs
    private View loginPanel, addPanel;
    private EditText etUsername, etLog;
    private Button btnLogin, btnAdd, btnLogout;

    // List
    private RecyclerView recycler;
    private GymLogAdapter adapter;

    // Data
    private GymLogDao dao;
    @Nullable private String user;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Find views (IDs from activity_main.xml)
        loginPanel = findViewById(R.id.loginPanel);
        addPanel   = findViewById(R.id.addPanel);
        etUsername = findViewById(R.id.etUsername);
        etLog      = findViewById(R.id.etLog);
        btnLogin   = findViewById(R.id.btnLogin);
        btnAdd     = findViewById(R.id.btnAdd);
        btnLogout  = findViewById(R.id.btnLogout);
        recycler   = findViewById(R.id.recycler);

        // 2) Recycler setup
        adapter = new GymLogAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        // 3) Room setup
        dao = AppDatabase.getInstance(this).gymLogDao();

        // 4) Restore session and update UI
        user = Session.currentUser(this);
        updateUiForUser();
        if (user != null) loadLogs();

        // 5) Clicks
        btnLogin.setOnClickListener(v -> doLogin());
        btnAdd.setOnClickListener(v -> doAdd());
        btnLogout.setOnClickListener(v -> doLogout());
    }

    private void doLogin() {
        String u = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(u)) {
            etUsername.setError("Enter a username");
            return;
        }
        Session.login(this, u);
        user = u;
        etUsername.setText("");
        updateUiForUser();
        loadLogs();
    }

    private void doAdd() {
        if (user == null) return;
        String note = etLog.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
            etLog.setError("Enter a workout note");
            return;
        }
        // Your entity uses fields: username, text, timestamp
        GymLog log = new GymLog(user, note, System.currentTimeMillis());
        dao.insert(log);
        etLog.setText("");
        loadLogs();
    }

    private void doLogout() {
        Session.logout(this);
        user = null;
        adapter.setItems(Collections.emptyList());
        updateUiForUser();
    }

    private void updateUiForUser() {
        boolean loggedIn = (user != null);
        loginPanel.setVisibility(loggedIn ? View.GONE : View.VISIBLE);
        addPanel.setVisibility(loggedIn ? View.VISIBLE : View.GONE);
        btnLogout.setVisibility(loggedIn ? View.VISIBLE : View.GONE);
    }

    private void loadLogs() {
        if (user == null) return;
        List<GymLog> logs = dao.getAllForUser(user); // DAO: SELECT * FROM gym_logs WHERE username=:u ORDER BY timestamp DESC
        adapter.setItems(logs);
    }
}
