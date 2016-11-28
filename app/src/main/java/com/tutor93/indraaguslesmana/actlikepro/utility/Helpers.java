package com.tutor93.indraaguslesmana.actlikepro.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tutor93.indraaguslesmana.actlikepro.R;

/**
 * Created by indraaguslesmana on 11/23/16.
 */

public class Helpers {

    private static ProgressDialog sProgressDialog;

    /**
     * Show image background on activity or fragment root image
     * NOTE: you must wrap activity or fragment layout content in FrameLayout with an ImageView,
     * activity_splash.xml shows the example, otherwise this function doing nothing
     */
    public static void useBackground(View root, boolean useColor) {
        ImageView background = (ImageView) root.findViewById(R.id.background);
        int resId = useColor ? R.drawable.img_background : R.drawable.img_background_blank;

        if (root.getTag() != null && (Integer) root.getTag() == resId) {
            return;
        }

        if (background != null) {
            root.setTag(resId);
            Glide.with(background.getContext())
                    .load(resId)
                    .crossFade()
                    .fitCenter()
                    .into(background);
        }
    }

    /**
     * Show progress dialog, can only be called once per tier (show-hide)
     */
    public static void showProgressDialog(Context ctx, int bodyStringId) {
        if(sProgressDialog == null) {
            sProgressDialog = ProgressDialog.show(ctx,
                    ctx.getString(R.string.progress_title_default),
                    ctx.getString(bodyStringId), true, false, null);
        }
    }

    /**
     * Show soft keyboard for given view
     */
    public static void hideSoftKeyboard(View view) {
        if(view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
