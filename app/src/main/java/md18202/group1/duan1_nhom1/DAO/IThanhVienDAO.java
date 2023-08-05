package md18202.group1.duan1_nhom1.DAO;


import java.util.List;

import huylvph30524.fpoly.duan01_nhom01.Model.Thanhvien;

public interface IThanhVienDAO {
    List<Thanhvien>getAll();
    void insert(Thanhvien thanhvien);
    void update(Thanhvien thanhvien);
    void delete(String maTV);
    void updateMK(Thanhvien thanhvien);
}
