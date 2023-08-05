package md18202.group1.duan1_nhom1.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.DonHangAdapter;
import md18202.group1.duan1_nhom1.Adapter.XemDonHangAdapter;
import md18202.group1.duan1_nhom1.DAO.DonHangDAO;
import md18202.group1.duan1_nhom1.DAO.ThanhvienDao;
import md18202.group1.duan1_nhom1.DonHangChiTietActivity;
import md18202.group1.duan1_nhom1.Model.DonHang;
import md18202.group1.duan1_nhom1.Model.Thanhvien;
import md18202.group1.duan1_nhom1.R;


public class DonHangNguoiDungFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHangNguoiDung;
    XemDonHangAdapter adapter;
    public DonHangNguoiDungFragment() {
        // Required empty public constructor
    }
    public static DonHangNguoiDungFragment newInstance() {
        DonHangNguoiDungFragment fragment = new DonHangNguoiDungFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_don_hang_nguoi_dung, container, false);
        //lấy dữ liệu từ intent
        Intent intent=getActivity().getIntent();
        String username=intent.getStringExtra("tenTK");
        //hàm lấy dữ liệu của 1 thành viên
        Thanhvien thanhvien=new ThanhvienDao(getContext()).getDuLieu(username);
        gridViewDonHangNguoiDung=view.findViewById(R.id.gvDonHangNguoiDung);
        //load đơn hàng của thành viên đó lên gridview
        donHangList=new DonHangDAO(getContext()).getDonHangCaNhan(thanhvien.getMatv());
        adapter=new XemDonHangAdapter(getContext(),donHangList);
        gridViewDonHangNguoiDung.setNumColumns(1);
        gridViewDonHangNguoiDung.setAdapter(adapter);
        gridViewDonHangNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang donHang= (DonHang) adapter.getItem(i);
                //xóa đơn hàng
                DialogHuyDonHang(donHang.getMaDonHang());
                return true;
            }
        });
        gridViewDonHangNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void DialogHuyDonHang(String maDonHang) {
        AlertDialog.Builder dialogHuy=new AlertDialog.Builder(getContext());
        dialogHuy.setMessage("Bạn có muốn hủy đơn hàng này không");
        dialogHuy.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DonHangDAO donHangDAO = new DonHangDAO(getContext());
                // xóa đơn hàng
                donHangDAO.delete(maDonHang);
                donHangList=new DonHangDAO(getContext()).getDonHang();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
                DonHangAdapter adapter = new DonHangAdapter(getContext(), donHangList, sharedPreferences);
                gridViewDonHangNguoiDung.setNumColumns(1);
                gridViewDonHangNguoiDung.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        dialogHuy.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogHuy.show();

    }

}