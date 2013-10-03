package com.mcxiaoke.popupmenu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import com.mcxiaoke.popupmenu.internal.PopupMenuAdapter;
import com.mcxiaoke.popupmenu.internal.MenuHelper;

public class ListPopupMenu implements OnDismissListener, OnItemClickListener, OnTouchListener {

    public enum Style {
        DARK, LIGHT;
    }

    private OnMenuItemClickListener mItemClickListener;
    private OnDismissListener mDismissListener;

    private Menu mMenu;
    private final Context mContext;
    private final View mView;
    private final ListPopupWindow mWindow;

    private boolean mDidAction;

    private final PopupMenuAdapter mAdapter;

    /**
     * Constructor for default vertical layout
     *
     * @param context Context
     */
    public ListPopupMenu(final Context context, final View view) {
        mContext = context;
        mView = view;
        mAdapter = new PopupMenuAdapter(context);
        mMenu = MenuHelper.createMenu(context);
        mWindow = ListPopupWindowHelper.newListPopupWindow(context);
        mWindow.setInputMethodMode(ListPopupWindowCompat.INPUT_METHOD_NOT_NEEDED);
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mWindow.setAnchorView(mView);
        mWindow.setWidth(mContext.getResources().getDimensionPixelSize(R.dimen.popup_window_width));
        mWindow.setAdapter(mAdapter);
        mWindow.setOnItemClickListener(this);
        mWindow.setModal(true);
    }

    /**
     * Dismiss the popup window.
     */
    public void dismiss() {
        if (isShowing()) {
            mWindow.dismiss();
        }
    }

    public Menu getMenu() {
        return mMenu;
    }

    public MenuInflater getMenuInflater() {
        return new MenuInflater(mContext);
    }

    public void inflate(final int menuRes) {
        new MenuInflater(mContext).inflate(menuRes, mMenu);
    }

    public boolean isShowing() {
        return mWindow != null && mWindow.isShowing();
    }

    @Override
    public void onDismiss() {
        if (!mDidAction && mDismissListener != null) {
            mDismissListener.onDismiss(this);
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> adapter, final View view, final int position, final long id) {
        mDidAction = true;
        dismiss();
        final MenuItem item = mAdapter.getItem(position);
        if (item.hasSubMenu()) {
            if (item.getSubMenu().size() == 0) return;
            showMenu(item.getSubMenu());
        } else {
            if (mItemClickListener != null) {
                mItemClickListener.onMenuItemClick(item);
            }
        }
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            mWindow.dismiss();

            return true;
        }

        return false;
    }

    public void setMenu(final Menu menu) {
        mMenu = menu;
    }

    /**
     * Set listener for window dismissed. This listener will only be fired if
     * the quickaction dialog is dismissed by clicking outside the dialog or
     * clicking on sticky item.
     */
    public void setOnDismissListener(final ListPopupMenu.OnDismissListener listener) {
        mWindow.setOnDismissListener(listener != null ? this : null);
        mDismissListener = listener;
    }

    /**
     * Set listener for action item clicked.
     *
     * @param listener Listener
     */
    public void setOnMenuItemClickListener(final OnMenuItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void show() {
        if (isShowing()) {
            dismiss();
        }
        showMenu(getMenu());
    }

    private void showMenu(final Menu menu) {
        mAdapter.setMenu(menu);
        try {
            mWindow.show();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static ListPopupMenu getInstance(final Context context, final View view) {
        return new ListPopupMenu(context, view);
    }

    /**
     * Listener for window dismiss
     */
    public static interface OnDismissListener {
        public void onDismiss(ListPopupMenu PopupMenu);
    }

    /**
     * Listener for item click
     */
    public static interface OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem item);
    }

}
