package md18202.group1.duan1_nhom1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import md18202.group1.duan1_nhom1.Model.NhaDat;
import md18202.group1.duan1_nhom1.R;

public class NhaDatAdapter extends BaseAdapter {
    Context context;
    List<NhaDat> nhaDatList;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public NhaDatAdapter(Context context, List<NhaDat> loaiSachList) {
        this.context = context;
        this.nhaDatList = loaiSachList;
    }
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    public int getCount() {
        return nhaDatList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhaDatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view= View.inflate(viewGroup.getContext(), R.layout.layout_item_nhadat,null);

        }
        NhaDat nhaDat= (NhaDat) getItem(position);
        TextView txttenGT=view.findViewById(R.id.txttenGT);
        TextView txttinhThanh=view.findViewById(R.id.txttinhThanh);
        TextView txtdienTich=view.findViewById(R.id.txtdienTich);
        TextView txtgiaTien=view.findViewById(R.id.txtgiaTien);
        TextView txtngayDang=view.findViewById(R.id.txtngayDang);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imghinh);
        txttenGT.setText(nhaDat.getTenGT());
        txttinhThanh.setText(nhaDat.getTinhThanh());
        txtdienTich.setText(nhaDat.getDienTich());
        txtgiaTien.setText(formatter.format(nhaDat.getGiaTien()));
        txtngayDang.setText(sdf.format(nhaDat.getNgayDang()));
        byte[] imageArray=nhaDat.getHinh();
        if(imageArray==null){
            Glide.with(context)
                    .load(R.drawable.nha1)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imgHinh);
        }else{
            Glide.with(context)
                    .load(imageArray)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imgHinh);
        }
        return view;
    }


}
