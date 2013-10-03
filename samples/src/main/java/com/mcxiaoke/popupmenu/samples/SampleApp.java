package com.mcxiaoke.popupmenu.samples;

import android.app.Application;

/**
 * User: mcxiaoke
 * Date: 13-10-3
 * Time: 下午3:36
 */
public class SampleApp extends Application {
    public static final int THEME_DARK = R.style.AppTheme_Dark;
    public static final int THEME_LIGHT = R.style.AppTheme_Light;
    private int theme = THEME_DARK;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setThemeId(int themeId) {
        theme = themeId;
    }

    public int getThemId() {
        return theme;
    }
}
