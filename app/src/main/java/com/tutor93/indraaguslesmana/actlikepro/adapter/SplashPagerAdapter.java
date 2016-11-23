package com.tutor93.indraaguslesmana.actlikepro.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Created by indraaguslesmana on 11/23/16.
 */

public class SplashPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private final int[] CONTENT = {
            R.drawable.img_intro1,
            R.drawable.img_intro2,
            R.drawable.img_intro3,
            R.drawable.img_intro4
    };

    private final int[] CONTENT_STRING = {
            R.string.be_profesional,
            R.string.get_ajob,
            R.string.one_stepahead,
            R.string.weekend
    };

    private final int CONTENT_COUNT = CONTENT.length;

    public SplashPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(CONTENT[position], CONTENT_STRING[position]);
    }

    @Override
    public int getIconResId(int index) {
        return R.drawable.selector_view_pager_indicator;
    }

    @Override
    public int getCount() {
        return CONTENT_COUNT;
    }

    /**
     * Helper fragment inner class to create pager page
     */
    public static class PagerFragment extends Fragment {
        private static final String BUNDLE_RES_IMAGE = "PagerFragment:mResImage";
        private static final String BUNDLE_RES_STRING = "PagerFragment:mResString";

        private int mResImage = 0;
        private int mResString = 0;

        public static PagerFragment newInstance(int resImage, int resString) {
            PagerFragment fragment = new PagerFragment();
            fragment.mResImage = resImage;
            fragment.mResString = resString;
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey(BUNDLE_RES_IMAGE)) {
                    mResImage = savedInstanceState.getInt(BUNDLE_RES_IMAGE);
                }
                if (savedInstanceState.containsKey(BUNDLE_RES_STRING)) {
                    mResString = savedInstanceState.getInt(BUNDLE_RES_STRING);
                }
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt(BUNDLE_RES_IMAGE, mResImage);
            outState.putInt(BUNDLE_RES_STRING, mResString);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.splash_viewpager_page, null);
            ImageView image = (ImageView) view.findViewById(R.id.splash_viewpager_page_image);
            TextView text = (TextView) view.findViewById(R.id.splash_viewpager_page_text);

            if (mResImage > 0){
                Glide.with(this).load(mResImage).crossFade().fitCenter().into(image);
            }
            if (mResString > 0){
                text.setText(mResString);
            }
            return view;
        }
    }


}
