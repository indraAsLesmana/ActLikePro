package com.tutor93.indraaguslesmana.actlikepro.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tutor93.indraaguslesmana.actlikepro.LikeaPro;

/**
 * Created by rifqi on Apr 18, 2016.
 */
public class BTextView extends TextView {
    public BTextView(Context context) {
        this(context, null);
    }

    public BTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(!isInEditMode()) {
            int style = Typeface.NORMAL;

            if(attrs != null) {
                style = attrs.getAttributeIntValue(
                        "http://schemas.android.com/apk/res/android", "textStyle", Typeface.NORMAL);
            }

            setTypeface(LikeaPro.getRegularFont(), style);
        }
    }
}
