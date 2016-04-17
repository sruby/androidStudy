package io.github.sruby.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service
{

    private String data = "默认值";
    boolean flag;

    public AidlService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        /**
         * 返回aidl的连接对象
         */
        return new IServiceInterface.Stub()
        {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException
            {

            }

            @Override
            public void setData(String data) throws RemoteException
            {
                AidlService.this.data = data;
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        System.out.println("aidlService startCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate()
    {
        System.out.println("AidlService create");
        new Thread(){
            @Override
            public void run()
            {
                super.run();
                flag = true;
                while (flag)
                {
                    System.out.println("AIDLservice 正在运行"+data);
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy()
    {
        flag = false;
        System.out.println("AidlService onDestroy");
    }
}
