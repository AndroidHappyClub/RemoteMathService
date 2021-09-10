package com.sample.remotemathservicedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mangoer on 2017/11/9.
 */

public class Result implements Parcelable {

    public long addResult;
    public long subResult;
    public long mulResult;
    public double divResult;

    public void setAddResult(long addResult) {
        this.addResult = addResult;
    }

    public void setSubResult(long subResult) {
        this.subResult = subResult;
    }

    public void setMulResult(long mulResult) {
        this.mulResult = mulResult;
    }

    public void setDivResult(double divResult) {
        this.divResult = divResult;
    }

    public Result() {
    }

    public Result(long addResult, long subResult, long mulResult, double divResult) {
        this.addResult = addResult;
        this.subResult = subResult;
        this.mulResult = mulResult;
        this.divResult = divResult;
    }

    //从Parcel对象得到数据，拆包函数
    public Result(Parcel parcel) {
        addResult = parcel.readLong();
        subResult = parcel.readLong();
        mulResult = parcel.readLong();
        divResult = parcel.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //顾名思义 wiiteToParcel  打包函数
    //将Result类内部的数据按照特定顺序写入Parcel对象 序列化操作
    //写入顺序必须与构造函数读取顺序一致
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(addResult);
        dest.writeLong(subResult);
        dest.writeLong(mulResult);
        dest.writeDouble(divResult);
    }

    /**
     * 默认生成的模板类的对象只支持为 in 的定向 tag,因为只有writeToParcel方法
     * 如果要支持为 out 或者 inout 的定向 tag 的话，还需要实现 readFromParcel() 方法
     * 参数是一个Parcel,用它来存储与传输数据
     * 注意，此处的读值顺序应当是和writeToParcel()方法中一致的
     * @param dest
     */
    public void readFromParcel(Parcel dest) {

        addResult = dest.readLong();
        subResult = dest.readLong();
        mulResult = dest.readLong();
        divResult = dest.readDouble();
    }


    //实现静态公共字段Creator，用来使用Parcel对象构造AllResult对象
    public static final Parcelable.Creator<Result> CREATOR = new Creator<Result>() {

        /*
        * 将序列化的数据进行反序列化操作
        * */
        @Override
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public String toString() {
        return "Result{" +
                "addResult=" + addResult +
                ", subResult=" + subResult +
                ", mulResult=" + mulResult +
                ", divResult=" + divResult +
                '}';
    }
}
