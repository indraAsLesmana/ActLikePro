package com.tutor93.indraaguslesmana.actlikepro.utility;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.likeaPro;

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

    /**
     * Show toast from string
     */
    public static void showToast(Context ctx, String str, boolean needLong) {
        Toast toast = Toast.makeText(ctx, str, needLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        initToast(ctx, toast);
        toast.show();
    }

    /**
     * This internal function to reduce redundancy showToast function
     */
    private static void initToast(Context context, Toast toast) {
        ViewGroup toastLayout = (ViewGroup)toast.getView();
        TextView toastTextView = (TextView)toastLayout.getChildAt(0);
        float textSize = context.getResources().getDimension(R.dimen.toast_text_size);
        toastTextView.setTypeface(likeaPro.getRegularFont());
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        toastTextView.setGravity(Gravity.CENTER);
    }

    /**
     * Show confirmation dialog with 2 buttons (positive and negative) (yes or no)
     */
    public static void showConfirmDialog(Context context, int titleRes, int messageRes,
                                         DialogInterface.OnClickListener positiveListener,
                                         DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(context)
                .setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(R.string.dialog_default_positive, positiveListener)
                .setNegativeButton(R.string.dialog_default_negative, negativeListener)
                .setCancelable(true)
                .show();
    }
}
