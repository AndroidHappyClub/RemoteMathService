// Result.aidl
package com.sample.remotemathservicedemo;

//第一类AIDL文件
//这个文件的作用是引入了一个序列化对象 Result 供其他的AIDL文件使用
//注意：Result.aidl与Result.java的包名应当是一样的
//在这里声明任何非默认类型 注意parcelable是小写
parcelable Result;
