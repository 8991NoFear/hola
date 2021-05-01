package vn.binhld.hola.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // tra ve bien tham chieu toi fragment o vi tri position
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTab();
            case 1:
                return new FriendTab();
            case 2:
                return new GroupTab();
        }
        return null;
    }

    // so luong fragment hien co
    @Override
    public int getCount() {
        return 3;
    }

    // noi dung hien thi tren fragment o vi tri position
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Friend";
            case 2:
                return "Group";
        }
        return null;
    }
}
