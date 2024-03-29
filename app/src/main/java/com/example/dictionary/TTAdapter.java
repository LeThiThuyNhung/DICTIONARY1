package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TTAdapter extends FragmentStatePagerAdapter {
    private String listTT[] = {"ANH-VIỆT", "TRÁI NGHĨA", "NGHĨA CỦA BẠN"};
    private Fragment_AnhViet mAnhViet;
    private Fragment_TraiNghia mTraNghia;
    private Fragment_NghiaCuaBan mNghiaCuaBan;
    public TTAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mAnhViet = new Fragment_AnhViet();
        mTraNghia = new Fragment_TraiNghia();
        mNghiaCuaBan = new Fragment_NghiaCuaBan();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return  mAnhViet;
        }else if(position == 1){
            return mTraNghia;
        }else if(position == 2){
        return mNghiaCuaBan;
    }
        return null;
    }

    @Override
    public int getCount() {
        return listTT.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTT[position];
    }
}
