package tranvuongquyenphong.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import tranvuongquyenphong.com.fragment.fm_DocGanDay;
import tranvuongquyenphong.com.fragment.fm_TinDaLuu;
import tranvuongquyenphong.com.fragment.fm_TinDaThich;

public class VP_HistoryAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Doc gan day", "Tin da luu","Tin da thich"};
    public VP_HistoryAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new fm_DocGanDay();
            case 1:
                return new fm_TinDaLuu();
            case 2:
                return new fm_TinDaThich();
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
