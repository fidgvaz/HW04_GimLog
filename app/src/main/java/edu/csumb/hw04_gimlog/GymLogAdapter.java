package edu.csumb.hw04_gimlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.csumb.hw04_gimlog.data.GymLog;

public class GymLogAdapter extends RecyclerView.Adapter<GymLogAdapter.VH> {

    private final List<GymLog> items = new ArrayList<>();
    private final SimpleDateFormat fmt = new SimpleDateFormat("MMM d, h:mm a", Locale.getDefault());

    public void setItems(List<GymLog> logs) {
        items.clear();
        if (logs != null) items.addAll(logs);
        notifyDataSetChanged();
    }


    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        GymLog g = items.get(position);
        h.tvNote.setText(g.text);
        h.tvTime.setText(fmt.format(new Date(g.timestamp)));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNote, tvTime;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
