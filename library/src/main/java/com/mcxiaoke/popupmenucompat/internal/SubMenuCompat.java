package com.mcxiaoke.popupmenucompat.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.mcxiaoke.popupmenucompat.MenuAdapter;

import java.util.List;

final class SubMenuCompat extends MenuCompat implements SubMenu {
    private final MenuItem mMenuItem;

    SubMenuCompat(final Context context, final MenuItem menuItem) {
        super(context, new MenuAdapter(context));
        mMenuItem = menuItem;
    }

    @Override
    public void clearHeader() {
    }

    @Override
    public MenuItem findItem(final int id) {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.getItemId() == id) return item;
        }
        return null;
    }

    @Override
    public MenuItem getItem() {
        return mMenuItem;
    }

    @Override
    public SubMenu setHeaderIcon(final Drawable icon) {
        return this;
    }

    @Override
    public SubMenu setHeaderIcon(final int iconRes) {
        return this;
    }

    @Override
    public SubMenu setHeaderTitle(final CharSequence title) {

        return this;
    }

    @Override
    public SubMenu setHeaderTitle(final int titleRes) {

        return this;
    }

    @Override
    public SubMenu setHeaderView(final View view) {

        return this;
    }

    @Override
    public SubMenu setIcon(final Drawable icon) {

        return this;
    }

    @Override
    public SubMenu setIcon(final int iconRes) {

        return this;
    }

    @Override
    public void setQwertyMode(final boolean isQwerty) {

    }

}
