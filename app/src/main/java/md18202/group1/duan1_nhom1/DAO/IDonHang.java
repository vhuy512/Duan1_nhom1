package md18202.group1.duan1_nhom1.DAO;



import java.util.List;

import huylvph30524.fpoly.duan01_nhom01.Model.DonHang;

public interface IDonHang {
    List<DonHang> getDonHang();

    void updateTrangThaiDonHang(int maDonHang, int trangThai);

    void insert(DonHang donHang);
    void delete(String maDonHang);
    DonHang getMaDonHang(String maDonHang);
    void updateTrangThai(DonHang donHang);
}
