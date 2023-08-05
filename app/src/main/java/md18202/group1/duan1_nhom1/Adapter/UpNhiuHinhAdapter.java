package md18202.group1.duan1_nhom1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import md18202.group1.duan1_nhom1.DAO.HinhDAO;
import md18202.group1.duan1_nhom1.DAO.NhaDatDAO;
import md18202.group1.duan1_nhom1.Model.Hinh;
import md18202.group1.duan1_nhom1.Model.NhaDat;
import md18202.group1.duan1_nhom1.R;


public class UpNhiuHinhAdapter extends RecyclerView.Adapter<UpNhiuHinhAdapter.HinhViewHolder>{
    Context context;
    private ArrayList<Uri> arrayList;


    public UpNhiuHinhAdapter(Context context) {
        this.context = context;

    }

    public  void setData(ArrayList<Uri>  list){
        this.arrayList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hinh,parent,false);

        return new HinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HinhViewHolder holder, int position) {
        Uri uri=arrayList.get(position);
        Intent intent=((Activity)context).getIntent();
        String maNhaDat=intent.getStringExtra("maNhaDat");
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
            holder.imgHinh.setImageBitmap(bitmap);
            Random random=new Random();
            int maHinh=random.nextInt(61);
            Hinh hinh=new Hinh(maHinh+"",maNhaDat,imageViewToByte(holder.imgHinh));
            HinhDAO daohinh=new HinhDAO(context);
            daohinh.insert(hinh);

            NhaDat nhaDat = new NhaDat(maNhaDat, "tenNhaDat", imageViewToByte(holder.imgHinh), "tinhThanh", null,"diachi",10,"dientich","mota",0);
            NhaDatDAO dao = new NhaDatDAO(context);
            dao.updateHinh(nhaDat);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (arrayList==null){
            return 0;
        }else {
            return arrayList.size();
        }
    }

    public class HinhViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHinh;
        public HinhViewHolder(View itemView) {
            super(itemView);
            imgHinh=itemView.findViewById(R.id.img_uphinh);
        }
    }
    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;

    }



}
