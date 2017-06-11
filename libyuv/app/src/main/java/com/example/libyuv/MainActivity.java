package com.example.libyuv;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button      btnCrop;
    private ImageView   imgShow;
    private int w=480,h=640;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrop= (Button) findViewById(R.id.btn_crop);
        btnCrop.setOnClickListener(this);
        imgShow= (ImageView) findViewById(R.id.show_img);
    }


    @Override
    public void onClick(View v) {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try{
            //读取assert 的文图
            InputStream is = am.open("test.jpg");
            image = BitmapFactory.decodeStream(is);

            //将位图资源转为二进制数据，数据大小为w*h*4
            int bytes = image.getByteCount();
            ByteBuffer buf = ByteBuffer.allocate(bytes);
            image.copyPixelsToBuffer(buf);
            byte[] byteArray = buf.array();

            byte[]  ybuffer=new byte[w*h];//用于保存y分量数据
            byte[]  ubuffer=new byte[w*h*1/4];//用于保存u分量数据
            byte[]  vbuffer=new byte[w*h*1/4];//用于保存v分量数据
            //将位图数据argb转换为yuv I420 转换后的数据分别保存在 ybuffer、ubuffer和vbuffer里面
            Test.argbtoi420(byteArray,w*4,ybuffer,w,ubuffer,(w+1)/2,vbuffer,(w+1)/2,w,h);

            //将上面的yuv数据保存到一个数组里面组成一帧yuv I420 数据 分辨率为w*h
            byte[] frameBuffer=new byte[w*h*3/2];
            System.arraycopy(ybuffer,0,frameBuffer,0,w*h);
            System.arraycopy(ubuffer,0,frameBuffer,w*h,w*h*1/4);
            System.arraycopy(vbuffer,0,frameBuffer,w*h*5/4,w*h*1/4);

            //用于保存将yuv数据转成argb数据
            byte[] rgbbuffer=new byte[w*h*4];
            //将上面的yuv I420 还原成argb数据
            Test.convertToArgb(frameBuffer,w*h*3/2,rgbbuffer,w*4,0,0,w,h,w,h,0,0);

            //还原成位图
            Bitmap stitchBmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            stitchBmp.copyPixelsFromBuffer(ByteBuffer.wrap(rgbbuffer));

            //显示还原的位图
            imgShow.setImageBitmap(stitchBmp);


        }catch (IOException e){

        }
    }
}
