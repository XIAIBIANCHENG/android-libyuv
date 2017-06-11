//
// Created by Administrator on 2017/6/4.
//
#include "include/libyuv.h"
#include <jni.h>

void Java_com_example_libyuv_Test_argbtoi420(JNIEnv* env, jobject thiz,
                                             jbyteArray src_argb, int src_stride_argb,
                                             jbyteArray dst_y, int dst_stride_y,
                                             jbyteArray dst_u, int dst_stride_u,
                                             jbyteArray dst_v, int dst_stride_v,
                                             int width, int height){
    uint8_t* rgbBuffer = (uint8_t*) (*env)->GetByteArrayElements(env, src_argb, 0);
    uint8_t* yBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_y, 0);
    uint8_t* uBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_u, 0);
    uint8_t* vBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_v, 0);

    ARGBToI420(rgbBuffer,src_stride_argb,yBuffer,dst_stride_y,uBuffer,dst_stride_u,vBuffer,dst_stride_v,width,height);

    (*env)->ReleaseByteArrayElements(env, src_argb, rgbBuffer, 0);
    (*env)->ReleaseByteArrayElements(env, dst_y, yBuffer, 0);
    (*env)->ReleaseByteArrayElements(env, dst_u, uBuffer, 0);
    (*env)->ReleaseByteArrayElements(env, dst_v, vBuffer, 0);
}

void Java_com_example_libyuv_Test_convertToI420(JNIEnv* env, jobject thiz,
                                                jbyteArray src_frame, int src_size,
                                                jbyteArray dst_y, int dst_stride_y,
                                                jbyteArray dst_u, int dst_stride_u,
                                                jbyteArray dst_v, int dst_stride_v,
                                                int crop_x, int crop_y,
                                                int src_width, int src_height,
                                                int crop_width, int crop_height,
                                                int rotation,
                                                int format){
    uint8_t* yuvFrame = (uint8_t*) (*env)->GetByteArrayElements(env, src_frame, 0);
    uint8_t* yBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_y, 0);
    uint8_t* uBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_u, 0);
    uint8_t* vBuffer=(uint8_t*) (*env)->GetByteArrayElements(env, dst_v, 0);

    ConvertToI420(yuvFrame,src_size,yBuffer,dst_stride_y,uBuffer,dst_stride_u,vBuffer,dst_stride_v,crop_x,crop_y,src_width,src_height,crop_width,crop_height,kRotate0,FOURCC_IYUV);

    (*env)->ReleaseByteArrayElements(env, src_frame, yuvFrame, 0);
    (*env)->ReleaseByteArrayElements(env, dst_y, yBuffer, 0);
    (*env)->ReleaseByteArrayElements(env, dst_u, uBuffer, 0);
    (*env)->ReleaseByteArrayElements(env, dst_v, vBuffer, 0);

}

void Java_com_example_libyuv_Test_convertToArgb(JNIEnv* env, jobject thiz,
                                                jbyteArray src_frame, int src_size,
                                                jbyteArray dst_argb, int dst_stride_argb,
                                                int crop_x, int crop_y,
                                                int src_width, int src_height,
                                                int crop_width, int crop_height,
                                                int rotation,
                                                int format){

    uint8_t* yuvFrame = (uint8_t*) (*env)->GetByteArrayElements(env, src_frame, 0);
    uint8_t* rgbBuffer= (uint8_t*) (*env)->GetByteArrayElements(env, dst_argb, 0);

    ConvertToARGB(yuvFrame,src_size,rgbBuffer,dst_stride_argb,crop_x,crop_y,src_width,src_height,crop_width,crop_height,kRotate0,FOURCC_IYUV);
    (*env)->ReleaseByteArrayElements(env, src_frame, yuvFrame, 0);
    (*env)->ReleaseByteArrayElements(env, dst_argb, rgbBuffer, 0);
}