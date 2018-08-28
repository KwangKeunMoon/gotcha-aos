package com.seolgi.gotcha;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seolgi.gotcha.camera.CameraMainFragment;
import com.seolgi.gotcha.home.HomeMainFragment;
import com.seolgi.gotcha.magneto.MagnetoMainFragment;
import com.seolgi.gotcha.map.MapMainFragment;
import static com.seolgi.gotcha.ContainerActivity.MainPageType.*;

public class ContainerActivity extends AppCompatActivity {

    enum MainPageType {
        HOME(0), CAMERA(1), MAGNETO(2), MAP(3);

        private int position;

        MainPageType(int position) {
            this.position = position;
        }

        static MainPageType create(int position) {
            for(MainPageType type : MainPageType.values()) {
                if (type.position == position) {
                    return type;
                }
            }

            return HOME;
        }
    }

    Fragment currentFragment= new Fragment();
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    // MARK: Button OnClicked
    public void movePageToHome(View v) {
        viewPager.setCurrentItem(HOME.position);
    }

    public void movePageToCamera(View v) {
        viewPager.setCurrentItem(CAMERA.position);
    }

    public void movePageToMagneto(View v) {
        viewPager.setCurrentItem(MAGNETO.position);
    }

    public void movePageToMap(View v) {
        viewPager.setCurrentItem(MAP.position);
    }

    // MARK: FragmentPagerAdapter
    private class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            MainPageType mainPageType = MainPageType.create(position);
            switch (mainPageType) {
                case HOME:
                    currentFragment = new HomeMainFragment();
                    break;
                case CAMERA:
                    currentFragment = new CameraMainFragment();
                    break;
                case MAGNETO:
                    currentFragment = new MagnetoMainFragment();
                    break;
                case MAP:
                    currentFragment = new MapMainFragment();
                    break;
            }

            return currentFragment;
        }

        @Override
        public int getCount() {
            return MainPageType.values().length;
        }
    }
}
