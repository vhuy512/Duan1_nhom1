package md18202.group1.duan1_nhom1.Fragment;

import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import huylvph30524.fpoly.duan01_nhom01.Adapter.DonHangAdapter;
import huylvph30524.fpoly.duan01_nhom01.DAO.DonHangDAO;
import huylvph30524.fpoly.duan01_nhom01.DAO.NhaDatDAO;
import huylvph30524.fpoly.duan01_nhom01.Model.DonHang;

public class DonHangDKTC_Fragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHang;
    DonHangAdapter adapter;
    int trangThai;
    int vaiTro = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_donhang,container,false);
        gridViewDonHang=view.findViewById(R.id.gvDonHang);
        //load dữ liệu đơn hàng gdtc lên gridview
        donHangList=new DonHangDAO(getContext()).getDonHangDKTCThanhCong();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        DonHangAdapter adapter = new DonHangAdapter(this, donHangList, sharedPreferences);
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
    public void dialogHoanTatGD(String maDonHang) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_sua_don_hang);
        dialog.setCanceledOnTouchOutside(false);
        RadioButton chkHoaThanh = dialog.findViewById(R.id.chkHoanTatGD);
        RadioButton chkgiaodich = dialog.findViewById(R.id.chkGD);
        Button btnLuuGD=dialog.findViewById(R.id.btnLuuGD);
        Button btnCancel = dialog.findViewById(R.id.btnBo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuuGD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (chkgiaodich.isChecked()){
                    trangThai = 2;
//                    Date ngayDang= Date.valueOf(String.valueOf(now()));
//                    //update thành trạng thái giao dịch thành công
//                    DonHang donHang=new DonHang(maDonHang,null,null,03103123,"null",null,"null",1231,trangThai,ngayDang);
//                    DonHangDAO dao=new DonHangDAO(getContext());
//                    dao.updateTrangThai(donHang);
////                     //xóa nhà đất khi giao dịch thành công
////                     DonHang donHang1=new DonHangDAO(getContext()).getMaDonHang(maDonHang);
////                     NhaDatDAO nhaDatDAO=new NhaDatDAO(getContext());
////                     nhaDatDAO.delete(donHang1.getMaNhaDat());
//                    //load lại danh sách đơn hàng
//                    donHangList=new DonHangDAO(getContext()).getDonHang();
//                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
//                    DonHangAdapter adapter = new DonHangAdapter(getContext(), donHangList, sharedPreferences);
//                    gridViewDonHang.setNumColumns(1);
//                    gridViewDonHang.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Đơn hàng đang ở đã đăng ký", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if (chkHoaThanh.isChecked()){
                    trangThai=0;
                    Date ngayDang= Date.valueOf(String.valueOf(now()));
                    //update thành trạng thái giao dịch thành công
                    DonHang donHang=new DonHang(maDonHang,null,null,03103123,"null",null,"null",1231,trangThai,ngayDang);
                    DonHangDAO dao=new DonHangDAO(getContext());
                    dao.updateTrangThai(donHang);
                    //xóa nhà đất khi giao dịch thành công
                    DonHang donHang1=new DonHangDAO(getContext()).getMaDonHang(maDonHang);
                    NhaDatDAO nhaDatDAO=new NhaDatDAO(getContext());
                    nhaDatDAO.delete(donHang1.getMaNhaDat());
                    //load lại danh sách đơn hàng
                    donHangList=new DonHangDAO(getContext()).getDonHang();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
                    DonHangAdapter adapter = new DonHangAdapter(getContext(), donHangList, sharedPreferences);
                    gridViewDonHang.setNumColumns(1);
                    gridViewDonHang.setAdapter(adapter);
                    dialog.dismiss();
                }
                else {
                    trangThai=1;
                }

            }
        });
        dialog.show();
    }

}
