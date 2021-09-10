// IMathInterface.aidl
package com.sample.remotemathservicedemo;
//导入所需要使用的非默认支持数据类型的包
import com.sample.remotemathservicedemo.Result;
//第二类AIDL文件
//作用是定义方法接口
//远程服务接口的定义
interface IMathInterface {

    //所有的返回值前都不需要加任何东西，不管是什么数据类型
    int add(int a,int b);
    Result getResult(long a, long b);
    List<Result> getListResult(long a, long b);
    //Map里不支持泛型，aidl编译工具会提示编译失败
//    Map<Integer,Result> getMapResult(long a, long b);

    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    String putResult(out Result result);
}
