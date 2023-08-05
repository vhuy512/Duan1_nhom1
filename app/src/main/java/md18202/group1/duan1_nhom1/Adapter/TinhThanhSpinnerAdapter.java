package md18202.group1.duan1_nhom1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import huylvph30524.fpoly.duan01_nhom01.Model.TinhThanh;
import huylvph30524.fpoly.duan01_nhom01.R;

public class TinhThanhSpinnerAdapter extends BaseAdapter {
    Context context;
    List<TinhThanh> tinhThanhList;

    public TinhThanhSpinnerAdapter(Context context, List<TinhThanh> tinhThanhList) {
        this.context = context;
        this.tinhThanhList = tinhThanhList;
    }

    @Override
    public int getCount() {
        return tinhThanhList.size();
    }

    @Override
    public Object getItem(int position) {
        return tinhThanhList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view= View.inflate(viewGroup.getContext(), R.layout.layout_spinner_tinhthanh,null);

        }
        TinhThanh tinhThanh= (TinhThanh) getItem(position);
        TextView txtTinhThanh=view.findViewById(R.id.txtTenTinhThanh);
        txtTinhThanh.setText(tinhThanh.getTenTinhThanh());

        return view;
    }
}
