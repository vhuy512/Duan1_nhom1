package md18202.group1.duan1_nhom1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import md18202.group1.duan1_nhom1.Adapter.ThongKeTop10Adapter;
import md18202.group1.duan1_nhom1.DAO.ThongKeDao;
import md18202.group1.duan1_nhom1.Model.Top;
import md18202.group1.duan1_nhom1.R;


public class ThongKeTop10Fragment extends Fragment {
    List<Top> list;
    ThongKeTop10Adapter adapter;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongketop10_fragment,container,false);
        lv=view.findViewById(R.id.lvThongKeTop10);
        //load top 10 lên listview
        list=new ThongKeDao(getContext()).getTop();
        adapter=new ThongKeTop10Adapter(getContext(),list);
        lv.setAdapter(adapter);
        return view;
    }
}
