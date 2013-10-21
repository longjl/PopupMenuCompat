package com.mcxiaoke.popupmenu.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;
import com.mcxiaoke.popupmenu.PopupMenuCompat;

/**
 *
 */
public class Samples extends Activity {
    public static final String TAG = Samples.class.getSimpleName();

    @InjectView(android.R.id.button1)
    Button button1;

    @InjectView(android.R.id.button2)
    Button button2;

    @InjectView(android.R.id.checkbox)
    CheckBox checkBox;

    @OnClick(android.R.id.button1)
    public void onButton1Click() {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "onButton1Click()");
        }
        showPopupMenu(button1);
    }

    @OnClick(android.R.id.button2)
    public void onButton2Click() {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "onButton2Click()");
        }
        showPopupMenu(button2);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Views.inject(this);

    }

    private void showPopupMenu(View view) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "showPopupMenu()");
        }
        final PopupMenuCompat.OnMenuItemClickListener onMenuItemClickListener =
                new PopupMenuCompat.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                };
        final PopupMenuCompat.OnDismissListener onDismissListener =
                new PopupMenuCompat.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenuCompat PopupMenu) {

                    }
                };
        PopupMenuCompat popupMenu = new PopupMenuCompat(this, view);
        popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        popupMenu.setOnDismissListener(onDismissListener);
        popupMenu.inflate(R.menu.menu);
        popupMenu.show();
    }
}
