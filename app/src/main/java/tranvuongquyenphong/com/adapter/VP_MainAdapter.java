package tranvuongquyenphong.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import tranvuongquyenphong.com.fragment.Frament_Main.fm_CongNghe;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_GiaiTri;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_GiaoDuc;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_SucKhoe;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_TheThao;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_ThoiSu;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_Video;
import tranvuongquyenphong.com.fragment.fm_DienDan;
import tranvuongquyenphong.com.fragment.Frament_Main.fm_Home;
import tranvuongquyenphong.com.fragment.fm_TinHot;

public class VP_MainAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Trang Chu", "Dien Dan", "Tin Hot"};
    public static ArrayList<String> listFragment;
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
                return new fm_ThoiSu();
            case 2:
                return new fm_GiaiTri();
            case 3:
                return new fm_GiaoDuc();
            case 4:
                return new fm_TheThao();
            case 5:
                return new fm_SucKhoe();
            case 6:
                return new fm_CongNghe();
            case 7:
                return new fm_Video();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragment.get(position);
    }
}
