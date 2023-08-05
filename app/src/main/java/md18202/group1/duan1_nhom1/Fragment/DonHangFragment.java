package md18202.group1.duan1_nhom1.Fragment;

import static java.time.LocalDate.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.DonHangAdapter;
import md18202.group1.duan1_nhom1.DAO.DonHangDAO;
import md18202.group1.duan1_nhom1.DAO.NhaDatDAO;
import md18202.group1.duan1_nhom1.Database.Database;
import md18202.group1.duan1_nhom1.DonHangChiTietActivity;
import md18202.group1.duan1_nhom1.Model.DonHang;
import md18202.group1.duan1_nhom1.R;


public class
DonHangFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    List<DonHang> list;
    GridView gridViewDonHang;
    Database db;
    DonHangAdapter adapter;
    int trangThai;
    int vaiTro = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_donhang,container,false);
        gridViewDonHang=view.findViewById(R.id.gvDonHang);
        donHangList=new DonHangDAO(getContext()).getDonHang();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        DonHangAdapter adapter = new DonHangAdapter(this, donHangList, sharedPreferences);
        gridViewDonHang.setNumColumns(1);
        gridViewDonHang.setAdapter(adapter);
        gridViewDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang donHang= (DonHang) adapter.getItem(i);
                //xóa đơn hàng
                DialogHuyDonHang(donHang.getMaDonHang());
                return false;
            }
        });
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

    // hoàn thành giao dịch
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
                     Date ngayDang= Date.valueOf(String.valueOf(now()));
                     //update thành trạng thái giao dịch thành công
                     DonHang donHang=new DonHang(maDonHang,null,null,03103123,"null",null,"null",1231,trangThai,ngayDang);
                     DonHangDAO dao=new DonHangDAO(getContext());
                     dao.updateTrangThai(donHang);
//                     //xóa nhà đất khi giao dịch thành công
//                     DonHang donHang1=new DonHangDAO(getContext()).getMaDonHang(maDonHang);
//                     NhaDatDAO nhaDatDAO=new NhaDatDAO(getContext());
//                     nhaDatDAO.delete(donHang1.getMaNhaDat());
                     //load lại danh sách đơn hàng
                     donHangList=new DonHangDAO(getContext()).getDonHang();
                     SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
                     DonHangAdapter adapter = new DonHangAdapter(getContext(), donHangList, sharedPreferences);
                     gridViewDonHang.setNumColumns(1);
                     gridViewDonHang.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
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
                gridViewDonHang.setNumColumns(1);
                gridViewDonHang.setAdapter(adapter);
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
