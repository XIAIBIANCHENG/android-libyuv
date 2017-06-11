package com.example.libyuv;

/**
 * Created by Administrator on 2017/6/4.
 */

public class Test {
    static {
        System.loadLibrary("yuv");
    }

    //argb 转yuv i420
    public static native  void argbtoi420(byte[] src_argb, int src_stride_argb,
                                   byte[] dst_y, int dst_stride_y,
                                   byte[] dst_u, int dst_stride_u,
                                   byte[] dst_v, int dst_stride_v,
                                   int width, int height);

    //对yuv 片数据 进行处理 包括裁剪和旋转
    public static native  void convertToI420(byte[] src_frame, int src_size,
                                      byte[] dst_y, int dst_stride_y,
                                      byte[] dst_u, int dst_stride_u,
                                      byte[] dst_v, int dst_stride_v,
                                      int crop_x, int crop_y,
                                      int src_width, int src_height,
                                      int crop_width, int crop_height,
                                      int rotation,
                                      int format);

    //将yuv数据转为argb
    public static native void  convertToArgb(byte[] src_frame, int src_size,
                                      byte[] dst_argb, int dst_stride_argb,
                                      int crop_x, int crop_y,
                                      int src_width, int src_height,
                                      int crop_width, int crop_height,
                                      int rotation,
                                      int format);

}
