package md18202.group1.duan1_nhom1.DAO;




import java.util.List;

import md18202.group1.duan1_nhom1.Model.Top;


public interface IThongKe {
    List<Top> getTop();
    int getDoanhThu(String tuNgay,String denNgay);
}
