package com.sample.remotemathservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mangoer on 2017/11/6.
 */

public class MathService extends Service {

    private String TAG = "MathService";

    /*
    * 建立IMathInterface.Stub实例，并实现IMathInterface这个AIDL文件定义的远程服务接口
    *在onBind方法中将myBind返回给远程调用者
    * */
    private IMathInterface.Stub mBind = new IMathInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.e(TAG,"add");
            return a+b;
        }

        @Override
        public Result getResult(long a, long b) throws RemoteException {
            Log.e(TAG,"getResult");
            long addResult = a + b;
            long subResult = a - b;
            long mulResult = a * b;
            double divResult = a / b;
            return new Result(addResult,subResult,mulResult,divResult);
        }

        @Override
        public List<Result> getListResult(long a, long b) throws RemoteException {
            Log.e(TAG,"getListResult");
            List<Result> list = new ArrayList<>();
            long addResult = a + b;
            long subResult = a - b;
            long mulResult = a * b;
            double divResult = a / b;
            list.add(new Result(addResult,subResult,mulResult,divResult));
            list.add(new Result(addResult,subResult,mulResult,divResult));
            return list;
        }
        @Override
        public String putResult(Result result) throws RemoteException {
            result.setAddResult(1);
            result.setDivResult(1);
            Log.e(TAG,"putResult="+result);
            return "put成功";
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate process id = " + Process.myPid());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return mBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"onUnbind");
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
