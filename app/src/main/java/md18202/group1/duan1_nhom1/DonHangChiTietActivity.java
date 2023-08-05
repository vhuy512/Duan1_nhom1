package md18202.group1.duan1_nhom1;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import md18202.group1.duan1_nhom1.DAO.DonHangDAO;
import md18202.group1.duan1_nhom1.Model.DonHang;


public class DonHangChiTietActivity extends AppCompatActivity {
    TextView txtTenGTNhaDat,txtDiaChi,txtTien,txtMaTVMuaHang,txtSDTKhachHang, txtNgayMua;
    ImageView imgHinh, imgQuayLai;
    String maDonHang;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        txtTenGTNhaDat = findViewById(R.id.txtTenGTNhaDat);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTien = findViewById(R.id.txtTien);
        txtMaTVMuaHang = findViewById(R.id.txtMaTVMuaHang);
        txtSDTKhachHang = findViewById(R.id.txtSDTKhachHang);
        txtNgayMua = findViewById(R.id.txtNgayMua);
        imgHinh = findViewById(R.id.imgHinh);
        imgQuayLai = findViewById(R.id.imgQuayLai);
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ganDuLieuDonHang();
    }
    private void ganDuLieuDonHang() {
        Intent intent=getIntent();
        maDonHang=intent.getStringExtra("maDonHang");
        DonHang donHang=new DonHangDAO(this).getMaDonHang(maDonHang);
        txtTenGTNhaDat.setText(donHang.getTenGTND());
        txtDiaChi.setText(donHang.getDiaChiND());
        txtTien.setText(donHang.getGiaTienND()+"");
        txtNgayMua.setText(sdf.format(donHang.getNgay()));
        txtMaTVMuaHang.setText(donHang.getMaTV());
        txtSDTKhachHang.setText(donHang.getSoDTNM()+"");
        byte[] imageArray=donHang.getHinhND();
        if(imageArray==null){
            imgHinh.setImageResource(R.drawable.nha1);
        }else{
            imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(imageArray,0,imageArray.length));
        }
    }

}