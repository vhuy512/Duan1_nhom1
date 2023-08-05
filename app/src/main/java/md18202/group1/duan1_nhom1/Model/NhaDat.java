package md18202.group1.duan1_nhom1.Model;

import java.util.Date;

public class NhaDat {
    String maNhaDat;
    String tenGT;
    byte[] hinh;
    String tinhThanh;
    Date ngayDang;
    String diaChi;
    int giaTien;
    String dienTich;
    String moTa;
    int loaiNha;

    public NhaDat(String maNhaDat, String tenGT, byte[] hinh, String tinhThanh, Date ngayDang, String diaChi, int giaTien, String dienTich, String moTa, int loaiNha) {
        this.maNhaDat = maNhaDat;
        this.tenGT = tenGT;
        this.hinh = hinh;
        this.tinhThanh = tinhThanh;
        this.ngayDang = ngayDang;
        this.diaChi = diaChi;
        this.giaTien = giaTien;
        this.dienTich = dienTich;
        this.moTa = moTa;
        this.loaiNha = loaiNha;
    }

    public String getMaNhaDat() {
        return maNhaDat;
    }

    public void setMaNhaDat(String maNhaDat) {
        this.maNhaDat = maNhaDat;
    }

    public String getTenGT() {
        return tenGT;
    }

    public void setTenGT(String tenGT) {
        this.tenGT = tenGT;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getLoaiNha() {
        return loaiNha;
    }

    public void setLoaiNha(int loaiNha) {
        this.loaiNha = loaiNha;
    }
}
