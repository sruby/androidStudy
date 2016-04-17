package io.github.sruby.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sruby.github.io.intenttest2.R;

/**  
 * 跨应用启动service 
 * @author: Sruby  on 2016/4/3 0003 20:18
 */
public class AIDLAty extends AppCompatActivity implements View.OnClickListener, ServiceConnection
{
    private Intent serviceIntent ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlaty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * 初始化intent,设置跨应用的service参数
         */
        serviceIntent = new Intent();
        //设置在manifest中package名和服务的全路径名
        serviceIntent.setComponent(new ComponentName("sruby.github.io.intenttest1", "io.github.sruby.aidl.AidlService"));

        findViewById(R.id.btnStartAnotherAppService).setOnClickListener(this);
        findViewById(R.id.btnStopAnotherAppService).setOnClickListener(this);
        findViewById(R.id.btnBindAnotherAppService).setOnClickListener(this);
        findViewById(R.id.btnUnbindAnotherAppService).setOnClickListener(this);
        findViewById(R.id.btnSyncAnotherAppService).setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnStartAnotherAppService:
                startService(serviceIntent);
                break;
            case R.id.btnStopAnotherAppService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindAnotherAppService:
                bindService(serviceIntent, this, BIND_AUTO_CREATE);
                System.out.println("绑定服务点击事件");
                break;
            case R.id.btnUnbindAnotherAppService:
                unbindService(this);
                System.out.println("解绑服务点击事件");
                break;
            case R.id.btnSyncAnotherAppService:
                System.out.println("同步数据事件");
                try
                {
                    iServiceInterface.setData("远程通信成功2");
                } catch (RemoteException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 声明aidl类型变量
     */
    IServiceInterface iServiceInterface ;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        System.out.println("连接成功");
        //初始化aidl实例
        iServiceInterface = (IServiceInterface) IServiceInterface.Stub.asInterface(service);

        try
        {
            iServiceInterface.setData("远程通信成功");
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name)
    {
        iServiceInterface = null;
        System.out.println("解绑,断开连接");
    }
}
