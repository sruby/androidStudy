package io.github.sruby.ServiceTest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    private boolean flag = false;
    public String serviceMsg = "默认信息";
    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        //返回binder实例
        return new MyBinder();
    }

    /**
     * 继承bander类
     */
    public class MyBinder extends Binder
    {
        private String bindData;

        /**
         * 修改Service中类变量信息
         * @param bindData
         */
        public void setBindData(String bindData) {
            serviceMsg = bindData;
        }

        public String getBindData() {

            return serviceMsg;
        }

        /**
         * 把service对象暴露给外部
         * @return
         */
        public MyService getService(){
            return MyService.this;
        }
    }

    /**
     * 外部第一次执行starService,会期执行OnCreate,然后执行onStartCommand;
     * 后续再执行,则只会执行onStartCommand
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service onStartCommand");
        /**
         * 服务运行线程
         */
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                while (true)
//                {
//                    System.out.println("服务正在运行");
//                    try {
//                        sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.run();

        /**
         * 获取intent的数据
         */
        serviceMsg = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     *服务只会被创建一次
     */
    @Override
    public void onCreate()
    {
        System.out.println("service onCreate");
        /**
         * 服务运行线程
         */
        flag = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (flag)
                {
                    String str = ++i+"服务正在运行"+serviceMsg;
                    System.out.println(str);

                    if(callback != null)
                    {
                        callback.onDataChange(str);
                    }

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
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
        System.out.println("service onDestroy");
    }

    /**
     * 定义回调接口
     * @author: Sruby  on 2016/4/3 0003 18:48
     */
    public static interface Callback
    {
        //当数据变化
        void onDataChange(String str);
    }

    /**
     *回调的局部变量
     */
    private Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
