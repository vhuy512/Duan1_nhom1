package md18202.group1.duan1_nhom1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import md18202.group1.duan1_nhom1.DAO.ThanhvienDao;
import md18202.group1.duan1_nhom1.Model.Thanhvien;
import md18202.group1.duan1_nhom1.R;


public class ThongTinCaNhanFragment extends Fragment {
    EditText edtHoTen, edtTenTk, edtNamSinh, edtSDT;
    Button btnSua, btnLuu;
    int dem=0;
    public ThongTinCaNhanFragment() {
        // Required empty public constructor
    }

    public static ThongTinCaNhanFragment newInstance() {
        ThongTinCaNhanFragment fragment = new ThongTinCaNhanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thong_tin_ca_nhan, container, false);
        edtHoTen = view.findViewById(R.id.edtHoTenCN);
        edtTenTk = view.findViewById(R.id.edtTenTaiKhoanCN);
        edtNamSinh = view.findViewById(R.id.edtNamSinhCN);
        edtSDT = view.findViewById(R.id.edtSDTCaNhan);
        btnSua = view.findViewById(R.id.btnSuaCN);
        btnLuu = view.findViewById(R.id.btnLuuCN);
        btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dem%2==0){
                        edtHoTen.setEnabled(true);
                        edtNamSinh.setEnabled(true);
                        edtSDT.setEnabled(true);
                        edtHoTen.requestFocus();
                        btnSua.setText("Đóng sửa");
                    }else{
                        ganDuLieuThongTinCaNhan();
                        edtHoTen.setEnabled(false);
                        edtNamSinh.setEnabled(false);
                        edtSDT.setEnabled(false);
                        btnSua.setText("Mở khóa để sửa");
                    }
                  dem++;
                }
            });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtHoTen.setEnabled(false);
                edtNamSinh.setEnabled(false);
                edtSDT.setEnabled(false);
                updetaThongTin();
                Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        });


        ganDuLieuThongTinCaNhan();
        return view;

    }
    private void ganDuLieuThongTinCaNhan() {
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("tenTK");
        Thanhvien thanhvien=new ThanhvienDao(getActivity()).getDuLieu(username);
        edtHoTen.setText(thanhvien.getHoTen());
        edtTenTk.setText(thanhvien.getTenTK());
        edtNamSinh.setText(thanhvien.getNamSinh());
        edtSDT.setText("0"+thanhvien.getSoDT()+"");
    }
    private void updetaThongTin(){
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("tenTK");
        Thanhvien thanhVien = new ThanhvienDao(getContext()).getDuLieu(username);
        String maThanhVien = thanhVien.getMatv();
        String matKhau = thanhVien.getMk();
        String tenTK = thanhVien.getTenTK();
        String hoTen=edtHoTen.getText().toString();
        String namSinh=edtNamSinh.getText().toString();
        String sdt=edtSDT.getText().toString();
        int SDT = Integer.parseInt(sdt);
        Thanhvien thanhvien = new Thanhvien(maThanhVien,hoTen,tenTK,matKhau,namSinh,SDT,1);
        ThanhvienDao dao = new ThanhvienDao(getActivity());
        dao.update(thanhvien);
    }
}