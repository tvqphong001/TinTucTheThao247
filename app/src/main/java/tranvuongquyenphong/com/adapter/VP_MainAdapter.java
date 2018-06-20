package tranvuongquyenphong.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import tranvuongquyenphong.com.fragment.fm_DienDan;
import tranvuongquyenphong.com.fragment.fm_Home;
import tranvuongquyenphong.com.fragment.fm_TinHot;

public class VP_MainAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Trang Chu", "Dien Dan", "Tin Hot"};
    public VP_MainAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new fm_Home();
            case 1:
                return new fm_DienDan();
            case 2:
                return new fm_TinHot();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
