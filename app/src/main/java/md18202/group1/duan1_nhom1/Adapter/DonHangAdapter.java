package md18202.group1.duan1_nhom1.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import md18202.group1.duan1_nhom1.Fragment.DonHangDKTC_Fragment;
import md18202.group1.duan1_nhom1.Fragment.DonHangFragment;
import md18202.group1.duan1_nhom1.Model.DonHang;
import md18202.group1.duan1_nhom1.R;

public class DonHangAdapter extends BaseAdapter {
    DonHangFragment donHangFragment;
    Context context;
    List<DonHang> donHangList;
    String username;
    DonHangDKTC_Fragment donHangDKTC_fragment;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    int vaiTro;
    private SharedPreferences sharedPreferences;



    public DonHangAdapter(DonHangFragment donHangFragment, List<DonHang> donHangList, SharedPreferences sharedPreferences) {
        this.donHangFragment = donHangFragment;
        this.donHangList = donHangList;
        this.sharedPreferences = sharedPreferences;
    }

    public DonHangAdapter(DonHangDKTC_Fragment donHangDKTC_fragment, List<DonHang> donHangList, SharedPreferences sharedPreferences) {
        this.donHangDKTC_fragment = donHangDKTC_fragment;
        this.donHangList = donHangList;
        this.sharedPreferences = sharedPreferences;
    }


    public DonHangAdapter(Context context, List<DonHang> donHangList, SharedPreferences sharedPreferences) {
        this.context = context;
        this.donHangList = donHangList;
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public int getCount() {
        return donHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return donHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.layout_item_donhang, null);

        }

        DonHang donHang= (DonHang) getItem(position);
        // ánh xạ
        TextView txtMaDonhang = view.findViewById(R.id.txtMaDonHang);
        TextView txtTenGT=view.findViewById(R.id.txtTenGT);
        TextView txtTinhThanh=view.findViewById(R.id.txtTinhThanh);
        TextView txtGiaTien=view.findViewById(R.id.txtGiaTien);
        TextView txtTrangThai=view.findViewById(R.id.txtTrangThai);
        TextView txtngayDang=view.findViewById(R.id.txtNgay);
        ImageView imgHinhND =(ImageView) view.findViewById(R.id.imgHinhND);
        ImageView imgSua =(ImageView) view.findViewById(R.id.imgSua);

        txtMaDonhang.setText(donHang.getMaDonHang());
        txtTenGT.setText(donHang.getTenGTND());
        txtTinhThanh.setText(donHang.getDiaChiND());
        txtGiaTien.setText(formatter.format(donHang.getGiaTienND())+"");
        txtngayDang.setText(sdf.format(donHang.getNgay()));
        byte[] img=donHang.getHinhND();
        if(img == null){
            imgHinhND.setImageResource(R.drawable.nha1);
        }else{
            imgHinhND.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        }
        int vaiTro = sharedPreferences.getInt("VaiTro", 1); // Lấy giá trị vaiTro từ SharedPreferences
        if (donHang.getTrangThai() == 1) {
            txtTrangThai.setText("Đang chờ giao dịch");
            // Ẩn icon sửa nếu vaiTro != 0 (không phải admin)
            imgSua.setVisibility(vaiTro == 0 ? View.VISIBLE : View.GONE);
        } else if (donHang.getTrangThai() == 2) {
            txtTrangThai.setText("Đăng ký thành công");
            // Ẩn icon sửa nếu vaiTro != 0 (không phải admin)
            imgSua.setVisibility(vaiTro == 0 ? View.VISIBLE : View.GONE);
        } else if (donHang.getTrangThai() == 0) {
            txtTrangThai.setText("Giao dịch thành công");
            imgSua.setVisibility(View.GONE);
        }

        // hiện dialog xác nhận gia dịch thành công
        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (donHang.getTrangThai() == 1){
                    donHangFragment.dialogHoanTatGD(donHang.getMaDonHang());
                }else {
                    donHangDKTC_fragment.dialogHoanTatGD(donHang.getMaDonHang());
                }
            }
        });

        return view;
    }


}
