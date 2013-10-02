package com.mcxiaoke.popupmenucompat.internal;

import android.content.Context;
import com.mcxiaoke.popupmenucompat.MenuAdapter;

/**
 * User: mcxiaoke
 * Date: 13-10-2
 * Time: 下午11:57
 */
public final class MenuHelper {

    private MenuHelper() {
    }

    public static MenuCompat createMenu(Context context) {
        return new MenuCompat(context);
    }

    public static MenuCompat createMenu(Context context, MenuAdapter adapter) {
        return new MenuCompat(context, adapter);
    }
}
