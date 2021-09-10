package com.sample.remotemathservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Button btnBind;
    private Button btnUnbind;
    private Button btnMath;
    private String TAG = "MainActivity";
    public IMathInterface mi;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mi = IMathInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mi = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);
        btnMath = findViewById(R.id.btnMath);

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "bind");
                //隐式启动服务，android5.0后要设置包名
                Intent serviceIntent = new Intent();
                serviceIntent.setAction("com.sample.remotemathservicedemo.MathService");
                serviceIntent.setPackage("com.sample.remotemathservicedemo");
                bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
            }
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
                mi = null;
                Log.e(TAG, "unBind");
            }
        });

        btnMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mi == null) {
                    Toast.makeText(MainActivity.this,
                            "远程服务未绑定", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    int ir = mi.add(3, 2);
                    Log.e(TAG, "ir=" + ir);
                    Result r = new Result();
                    String sr = mi.putResult(r);
                    Log.e(TAG, "r=" + r);
                    Log.e(TAG, "sr=" + sr);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
