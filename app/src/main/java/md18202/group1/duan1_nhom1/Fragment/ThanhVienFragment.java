package md18202.group1.duan1_nhom1.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.ThanhvienAdapter;
import md18202.group1.duan1_nhom1.DAO.ThanhvienDao;
import md18202.group1.duan1_nhom1.Model.Thanhvien;
import md18202.group1.duan1_nhom1.R;


public class ThanhVienFragment extends Fragment {
    List<Thanhvien>thanhvienList;
    GridView gridViewThanhVien;
    ThanhvienAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_thanhvien,container,false);
        gridViewThanhVien = view.findViewById(R.id.gvThanhVien);
        //load dữ liệu thành viên lên gridview
        thanhvienList = new ThanhvienDao(getContext()).getAll();
        adapter = new ThanhvienAdapter(getContext(),thanhvienList);
        gridViewThanhVien.setNumColumns(1);
        gridViewThanhVien.setAdapter(adapter);
        gridViewThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //xóa thành viên
                Thanhvien thanhvien= (Thanhvien) adapter.getItem(i);
                DiaLogXoaThanhVien(thanhvien.getMatv());

                return false;
            }
        });


        return view;

    }
    public void DiaLogXoaThanhVien(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(getContext());
        dialogXoa.setMessage("Bạn có muốn xóa thành viên"+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThanhvienDao dao=new ThanhvienDao(getContext());
                dao.delete(ten);
                thanhvienList = new ThanhvienDao(getContext()).getAll();
                adapter = new ThanhvienAdapter(getContext(),thanhvienList);
                gridViewThanhVien.setNumColumns(1);
                gridViewThanhVien.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }


}
