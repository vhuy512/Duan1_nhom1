package md18202.group1.duan1_nhom1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.DonHangAdapter;
import md18202.group1.duan1_nhom1.DAO.DonHangDAO;
import md18202.group1.duan1_nhom1.DonHangChiTietActivity;
import md18202.group1.duan1_nhom1.Model.DonHang;
import md18202.group1.duan1_nhom1.R;


public class DonHangGiaoDichThanhCongActivityFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHang;
    DonHangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_donhang,container,false);
        gridViewDonHang=view.findViewById(R.id.gvDonHang);
        //load dữ liệu đơn hàng gdtc lên gridview
        donHangList=new DonHangDAO(getContext()).getDonHangGiaoDichThanhCong();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        DonHangAdapter adapter = new DonHangAdapter(getContext(), donHangList, sharedPreferences);
        gridViewDonHang.setNumColumns(1);
        gridViewDonHang.setAdapter(adapter);
        gridViewDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang donHang= (DonHang) adapter.getItem(i);
                Intent intent = new Intent(getContext(), DonHangChiTietActivity.class);
                intent.putExtra("maDonHang",donHang.getMaDonHang());
                startActivity(intent);
            }
        });

        return view;
    }

}
