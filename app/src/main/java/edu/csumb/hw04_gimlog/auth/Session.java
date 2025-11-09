package edu.csumb.hw04_gimlog.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private static final String PREF = "gymlog_prefs";
    private static final String KEY_USER = "user";

    public static void login(Context ctx, String username) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_USER, username).apply();
    }

    public static void logout(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().remove(KEY_USER).apply();
    }

    public static String currentUser(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getString(KEY_USER, null);
    }
}
