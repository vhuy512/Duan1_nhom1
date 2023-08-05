package md18202.group1.duan1_nhom1.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import md18202.group1.duan1_nhom1.Fragment.DonHangDKTC_Fragment;
import md18202.group1.duan1_nhom1.Fragment.DonHangFragment;
import md18202.group1.duan1_nhom1.Fragment.DonHangGiaoDichThanhCongActivityFragment;

public class ViewDonHang_GiaoDichAdapter extends FragmentStateAdapter {
    public ViewDonHang_GiaoDichAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new DonHangFragment();
            case 1:
                return new DonHangDKTC_Fragment();
            case 2:
                return new DonHangGiaoDichThanhCongActivityFragment();

            default:
                return new DonHangFragment();
        }


        //return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
