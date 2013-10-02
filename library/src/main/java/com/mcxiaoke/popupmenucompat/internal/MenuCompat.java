package com.mcxiaoke.popupmenucompat.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.mcxiaoke.popupmenucompat.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuCompat implements Menu {
    protected final Context mContext;
    protected final MenuAdapter mAdapter;

    public MenuCompat(final Context context) {
        this(context, null);
    }

    public MenuCompat(final Context context, final MenuAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @Override
    public MenuItem add(final CharSequence title) {
        return add(0, 0, 0, title);
    }

    @Override
    public MenuItem add(final int titleRes) {
        return add(0, 0, 0, titleRes);
    }

    @Override
    public MenuItem add(final int groupId, final int itemId, final int order, final CharSequence title) {
        final MenuItem item = new MenuItemCompat(mContext).setGroupId(groupId).setItemId(itemId).setOrder(order)
                .setTitle(title);
        if (order != 0) {
            mAdapter.add(order, item);
        } else {
            mAdapter.add(item);
        }
        return item;
    }

    @Override
    public MenuItem add(final int groupId, final int itemId, final int order, final int titleRes) {
        return add(groupId, itemId, order, mContext.getString(titleRes));
    }

    @Override
    public int addIntentOptions(final int groupId, final int itemId, final int order, final ComponentName caller,
                                final Intent[] specifics, final Intent intent, final int flags, final MenuItem[] outSpecificItems) {
        return 0;
    }

    @Override
    public SubMenu addSubMenu(final CharSequence title) {
        return addSubMenu(0, 0, 0, title);
    }

    @Override
    public SubMenu addSubMenu(final int titleRes) {
        return addSubMenu(0, 0, 0, titleRes);
    }

    @Override
    public SubMenu addSubMenu(final int groupId, final int itemId, final int order, final CharSequence title) {
        final MenuItem item = new MenuItemCompat(mContext).setGroupId(groupId).setItemId(itemId).setOrder(order)
                .setTitle(title);
        final SubMenu subMenu = new SubMenuCompat(mContext, item);
        ((MenuItemCompat) item).setSubMenu(subMenu);
        if (order != 0) {
            mAdapter.add(order, item);
        } else {
            mAdapter.add(item);
        }
        return subMenu;
    }

    @Override
    public SubMenu addSubMenu(final int groupId, final int itemId, final int order, final int titleRes) {
        return addSubMenu(groupId, itemId, order, mContext.getString(titleRes));
    }

    @Override
    public void clear() {
        mAdapter.clear();
    }

    @Override
    public void close() {

    }

    @Override
    public MenuItem findItem(final int id) {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.getItemId() == id)
                return item;
            else if (item.hasSubMenu()) {
                final MenuItem possibleItem = item.getSubMenu().findItem(id);

                if (possibleItem != null) return possibleItem;
            }
        }
        return null;
    }

    @Override
    public MenuItem getItem(final int index) {
        return mAdapter.getItem(index);
    }

    public List<MenuItem> getAllMenuItems() {
        return mAdapter.getAllItems();
    }

    @Override
    public boolean hasVisibleItems() {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.isVisible()) return true;
        }
        return false;
    }

    @Override
    public boolean isShortcutKey(final int keyCode, final KeyEvent event) {
        return false;
    }

    @Override
    public boolean performIdentifierAction(final int id, final int flags) {
        return false;
    }

    @Override
    public boolean performShortcut(final int keyCode, final KeyEvent event, final int flags) {
        return false;
    }

    @Override
    public void removeGroup(final int groupId) {
        final List<MenuItem> itemsToRemove = new ArrayList<MenuItem>();
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.hasSubMenu()) {
                item.getSubMenu().removeGroup(groupId);
            } else if (item.getGroupId() == groupId) {
                itemsToRemove.add(item);
            }
        }
        mAdapter.removeAll(itemsToRemove);
    }

    @Override
    public void removeItem(final int id) {
        final List<MenuItem> itemsToRemove = new ArrayList<MenuItem>();
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.hasSubMenu()) {
                item.getSubMenu().removeItem(id);
            } else if (item.getItemId() == id) {
                itemsToRemove.add(item);
            }
        }
        mAdapter.removeAll(itemsToRemove);
    }

    @Override
    public void setGroupCheckable(final int group, final boolean checkable, final boolean exclusive) {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.getGroupId() == group) {
                item.setCheckable(checkable);
                if (exclusive) {
                    break;
                }
            }
        }
    }

    @Override
    public void setGroupEnabled(final int group, final boolean enabled) {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.getGroupId() == group) {
                item.setEnabled(enabled);
            }
        }

    }

    @Override
    public void setGroupVisible(final int group, final boolean visible) {
        List<MenuItem> menuItems = mAdapter.getAllItems();
        for (final MenuItem item : menuItems) {
            if (item.getGroupId() == group) {
                item.setVisible(visible);
            }
        }
    }

    @Override
    public void setQwertyMode(final boolean isQwerty) {

    }

    @Override
    public int size() {
        return mAdapter.getCount();
    }

}
