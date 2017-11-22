//
// Created by caucse on 2017-11-01.
//

#include <jni.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <android/log.h>
#include <string.h>

typedef struct{
    unsigned char data[6];

};ioctl_hseg_data;

#define SSEG_MAGIC  0xBD
#define SSEG_SET_HSEG   _IOW(SSEG_MAGIC, 0, ioctl_hseg_data)

typedef struct {
    unsigned char data[10];
}ioctl_text_data;

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_SingleActivity_TextlcdWrite(JNIEnv *jenv, jobject self, jbyteArray data)
{
    int dev;

    if((dev = open("/dev/textlcd", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "textlcd", "failed to open /dev/textlcd\n");
        return 1;
    }
    jbyte* ss;
    ss= (*jenv)->GetByteArrayElements(jenv, data, NULL);
    write(dev, ss, (*jenv)->GetArrayLength(jenv,data));
    (*jenv)->ReleaseByteArrayElements(jenv, data, ss, 0);
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_SingleActivity_SSegmentWrite(JNIEnv *jenv, jobject self, jint data)
{
    int dev;

    if((dev = open("/dev/7segment", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "failed to open /dev/7segment\n");
        return 1;
    }

    write(dev, &data, sizeof(int));
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_SingleActivity_SSegmentIOCtlHSeg(JNIEnv *jenv, jobject self, jbyteArray arr)
{
    int dev, len;
    jbyte *hseg;

    if((dev = open("/dev/7segment", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "failed to open /dev/7segment\n");
        return 1;
    }

    if((len = (*jenv)->GetArrayLength(jenv, arr)!=6))
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "invalid length of param arr\n");
        return 1;
    }

    hseg= (*jenv)->GetByteArrayElements(jenv, arr, NULL);
    ioctl(dev, SSEG_SET_HSEG, hseg, _IOC_SIZE(SSEG_SET_HSEG));
    (*jenv)->ReleaseByteArrayElements(jenv, arr, hseg, 0);
    close(dev);

    return 0;
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_SingleActivity_DotMatrixWrite(JNIEnv *jenv, jobject self, jbyteArray data) {
    int dev;

    if((dev = open("/dev/dotmatrix", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "dotmatrix", "failed to open /dev/dotmatrix\n");
        return 1;
    }

    jbyte* bb;

    bb= (*jenv)->GetByteArrayElements(jenv, data, NULL);
    write(dev, bb, (*jenv)->GetArrayLength(jenv,data));
    (*jenv)->ReleaseByteArrayElements(jenv, data, bb, 0);
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_SingleActivity_FullcolorledWrite(JNIEnv *jenv, jobject self,jint data)
{
    int dev;
    unsigned char buf1[4][3] ={
            {255, 0, 0}, //LED1 RGB
            {255, 255, 255}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    unsigned char buf2[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {0, 255,0}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    unsigned char buf3[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {255, 255,255}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 0, 0} //LED4 RGB
    };
    unsigned char buf4[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {255, 255,255}, //LED2 RGB
            {0, 0, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    if((dev = open("/dev/fullcolorled", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "fullcolorled", "failed to open /dev/fullcolorled\n");
        return 1;
    }
    switch (data){
        case 0:
            write(dev, buf1, 12);
            break;
        case 1:
            write(dev, buf2, 12);
            break;
        case 2:
            write(dev, buf3, 12);
            break;
        case 3:
            write(dev, buf4, 12);
            break;
    };

    memset(buf2, 0x00, 12);
    close(dev);
}


JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_MultiActivity_TextlcdWrite(JNIEnv *jenv, jobject self, jbyteArray data)
{
    int dev;

    if((dev = open("/dev/textlcd", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "textlcd", "failed to open /dev/textlcd\n");
        return 1;
    }
    jbyte* ss;
    ss= (*jenv)->GetByteArrayElements(jenv, data, NULL);
    write(dev, ss, (*jenv)->GetArrayLength(jenv,data));
    (*jenv)->ReleaseByteArrayElements(jenv, data, ss, 0);
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_MultiActivity_SSegmentWrite(JNIEnv *jenv, jobject self, jint data)
{
    int dev;

    if((dev = open("/dev/7segment", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "failed to open /dev/7segment\n");
        return 1;
    }

    write(dev, &data, sizeof(int));
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_MultiActivity_SSegmentIOCtlHSeg(JNIEnv *jenv, jobject self, jbyteArray arr)
{
    int dev, len;
    jbyte *hseg;

    if((dev = open("/dev/7segment", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "failed to open /dev/7segment\n");
        return 1;
    }

    if((len = (*jenv)->GetArrayLength(jenv, arr)!=6))
    {
        __android_log_print(ANDROID_LOG_ERROR, "SSegment", "invalid length of param arr\n");
        return 1;
    }

    hseg= (*jenv)->GetByteArrayElements(jenv, arr, NULL);
    ioctl(dev, SSEG_SET_HSEG, hseg, _IOC_SIZE(SSEG_SET_HSEG));
    (*jenv)->ReleaseByteArrayElements(jenv, arr, hseg, 0);
    close(dev);

    return 0;
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_MultiActivity_DotMatrixWrite(JNIEnv *jenv, jobject self, jbyteArray data) {
    int dev;

    if((dev = open("/dev/dotmatrix", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "dotmatrix", "failed to open /dev/dotmatrix\n");
        return 1;
    }

    jbyte* bb;

    bb= (*jenv)->GetByteArrayElements(jenv, data, NULL);
    write(dev, bb, (*jenv)->GetArrayLength(jenv,data));
    (*jenv)->ReleaseByteArrayElements(jenv, data, bb, 0);
    close(dev);
}

JNIEXPORT jint JNICALL
Java_embedded_cse_cau_ac_kr_embedded_MultiActivity_FullcolorledWrite(JNIEnv *jenv, jobject self,jint data)
{
    int dev;
    unsigned char buf1[4][3] ={
            {255, 0, 0}, //LED1 RGB
            {255, 255, 255}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    unsigned char buf2[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {0, 255,0}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    unsigned char buf3[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {255, 255,255}, //LED2 RGB
            {255, 255, 255}, //LED3 RGB
            {255, 0, 0} //LED4 RGB
    };
    unsigned char buf4[4][3] ={
            {255, 255, 255}, //LED1 RGB
            {255, 255,255}, //LED2 RGB
            {0, 0, 255}, //LED3 RGB
            {255, 255, 255} //LED4 RGB
    };
    if((dev = open("/dev/fullcolorled", O_WRONLY | O_SYNC))<0)
    {
        __android_log_print(ANDROID_LOG_ERROR, "fullcolorled", "failed to open /dev/fullcolorled\n");
        return 1;
    }
    switch (data){
        case 0:
            write(dev, buf1, 12);
            break;
        case 1:
            write(dev, buf2, 12);
            break;
        case 2:
            write(dev, buf3, 12);
            break;
        case 3:
            write(dev, buf4, 12);
            break;
    };

    memset(buf2, 0x00, 12);
    close(dev);
}
