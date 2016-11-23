package com.tutor93.indraaguslesmana.actlikepro.utility;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tutor93.indraaguslesmana.actlikepro.R;

/**
 * Created by indraaguslesmana on 11/23/16.
 */

public class Helpers {

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
}
