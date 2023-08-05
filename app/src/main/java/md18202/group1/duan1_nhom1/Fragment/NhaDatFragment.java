package md18202.group1.duan1_nhom1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.NhaDatAdapter;
import md18202.group1.duan1_nhom1.DAO.NhaDatDAO;
import md18202.group1.duan1_nhom1.Model.NhaDat;
import md18202.group1.duan1_nhom1.Model.TinhThanh;
import md18202.group1.duan1_nhom1.R;


public class NhaDatFragment extends Fragment {
    List<NhaDat> list;
    GridView gridViewNhaDat;
    NhaDatAdapter adapter;
    ImageView imgThem;
    Spinner spinnerTinhThanh,spinnerTimKiem;
    String selectedTinhThanh=null;
    List<TinhThanh> tinhThanhList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_hienthinhadat,container,false);
        gridViewNhaDat=view.findViewById(R.id.gvNhaDat);
        imgThem=view.findViewById(R.id.imgThemNhaDat);
        //load dữ liệu nhà lên gridview
        list=new NhaDatDAO(getContext()).getNhaDat();
        adapter=new NhaDatAdapter(getContext(),list);
        gridViewNhaDat.setNumColumns(2);
        gridViewNhaDat.setAdapter(adapter);


        return view;

    }

}
