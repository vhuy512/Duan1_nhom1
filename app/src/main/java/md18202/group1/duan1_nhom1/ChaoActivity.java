package md18202.group1.duan1_nhom1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import md18202.group1.duan1_nhom1.Model.Photo;
import me.relex.circleindicator.CircleIndicator3;

public class ChaoActivity extends AppCompatActivity {

    private ViewPager2 vp2;
    private CircleIndicator3 ci3;
    private TextView txtTenAPP, txtXinChao;
    private List<Photo> mlist;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = vp2.getCurrentItem();
            if (currentPosition == mlist.size()-1){
                vp2.setCurrentItem(0);
            }else {
                vp2.setCurrentItem(currentPosition+1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);
        vp2 = findViewById(R.id.vp2);
        ci3 = findViewById(R.id.Ci3);
        txtTenAPP = findViewById(R.id.txtTenApp);
        txtXinChao = findViewById(R.id.txtXinChao);
        mlist = getListPhoto();

        //setting ViewPager2
        vp2.setOffscreenPageLimit(3);
        vp2.setClipToPadding(false);
        vp2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        vp2.setPageTransformer(compositePageTransformer);

        PhotoAdapter photoAdapter = new PhotoAdapter(mlist);
        vp2.setAdapter(photoAdapter);
        ci3.setViewPager(vp2);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,1500);
            }
        });
        Thread gio = new Thread(){
            public void run(){
                try {
                    sleep(7000);
                }catch (Exception e){

                }finally {
                    Intent intent = new Intent(ChaoActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        };
        gio.start();
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_1));
        list.add(new Photo(R.drawable.img_2));
        list.add(new Photo(R.drawable.img_3));
        list.add(new Photo(R.drawable.img_4));
        list.add(new Photo(R.drawable.img_5));

        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,1000);
    }
}