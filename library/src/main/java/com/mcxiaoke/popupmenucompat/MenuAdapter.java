package com.mcxiaoke.popupmenucompat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mcxiaoke.popupmenucompat.internal.MenuCompat;

import java.util.ArrayList;
import java.util.List;

public final class MenuAdapter extends ArrayAdapterCompat<MenuItem> {
    private LayoutInflater mInflater;

    public MenuAdapter(final Context context) {
        super(context, new ArrayList<MenuItem>());
        mInflater = LayoutInflater.from(context);
    }

    public MenuAdapter(final Context context, List<MenuItem> data) {
        super(context, data);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public long getItemId(final int index) {
        return getItem(index).getItemId();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item_popup_menu, parent, false);
        } else {
            view = convertView;
        }
        final TextView text = (TextView) view.findViewById(android.R.id.text1);
        final ImageView icon = (ImageView) view.findViewById(android.R.id.icon);
        final MenuItem item = getItem(position);
        text.setText(item.getTitle());
        icon.setImageDrawable(item.getIcon());
        icon.setVisibility(item.getIcon() != null ? View.VISIBLE : View.GONE);
        return view;
    }

    @Override
    public boolean isEnabled(final int position) {
        return getItem(position).isEnabled();
    }

    public void setMenu(final Menu menu) {
        if (menu instanceof MenuCompat) {
            MenuCompat mc = (MenuCompat) menu;
            clear();
            addAll(mc.getAllMenuItems());
        }
    }

    public boolean setMenuItems(List<MenuItem> items) {
        if (items == null || items.isEmpty()) {
            return false;
        }
        clear();
        for (final MenuItem item : items) {
            if (item.isVisible()) {
                add(item);
            }
        }
        return true;
    }

}
