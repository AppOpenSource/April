package com.abt.price.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.abt.common.util.ToastUtils;
import com.abt.price.R;
import com.abt.price.base.BaseActivity;
import com.abt.price.databinding.ActivityPicBinding;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @描述： @PictureActivity
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class PictureActivity extends BaseActivity {

    public static final String IMG_URL = "img_url";
    public static final String IMG_DESC = "img_desc";
    public static final String TRANSIT_PIC = "picture";

    private ActivityPicBinding binding;
    private String img_url;
    private String img_desc;
    private ImageView iv_meizhi_pic;

/*    @BindView(R.id.iv_meizhi_pic)
    ImageView iv_meizhi_pic;
    @OnClick(R.id.save_img) void saveImg(){
        saveImage();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pic);
        iv_meizhi_pic = binding.ivMeizhiPic;
        parseIntent();
        //设置共享元素
        ViewCompat.setTransitionName(iv_meizhi_pic, TRANSIT_PIC);
        Glide.with(this).load(img_url).centerCrop().into(iv_meizhi_pic);
        new PhotoViewAttacher(iv_meizhi_pic);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.clear(iv_meizhi_pic);
    }

    public static Intent newIntent(Context context, String url,String desc){
        Intent intent = new Intent(context,PictureActivity.class);
        intent.putExtra(PictureActivity.IMG_URL,url);
        intent.putExtra(PictureActivity.IMG_DESC,desc);
        return intent;
    }

    private void parseIntent(){
        img_url = getIntent().getStringExtra(IMG_URL);
        img_desc = getIntent().getStringExtra(IMG_DESC);
    }

    private void saveImage(){
        iv_meizhi_pic.buildDrawingCache();
        Bitmap bitmap = iv_meizhi_pic.getDrawingCache();
        //将Bitmap 转换成二进制，写入本地
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG , 100 , stream);
        byte[] byteArray = stream.toByteArray();
        File dir=new File(Environment.getExternalStorageDirectory ().getAbsolutePath()+"/zhigan" );
        if(!dir.exists()) {
            dir.mkdir();
        }
            File file = new File(dir, img_desc.substring(0, 10) + ".png");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(byteArray, 0, byteArray.length);
                fos.flush();
                //用广播通知相册进行更新相册
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                PictureActivity.this.sendBroadcast(intent);
                ToastUtils.show(this, R.string.save_success);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
