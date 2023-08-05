package md18202.group1.duan1_nhom1.DAO;


import java.util.List;

import huylvph30524.fpoly.duan01_nhom01.Model.NhaDat;

public interface INhaDatDAO {
    List<NhaDat> getNha();
    List<NhaDat> getDat();
    void insert(NhaDat nhaDat);
    void update(NhaDat nhaDat);
    void delete(String maNhaDat);
}
