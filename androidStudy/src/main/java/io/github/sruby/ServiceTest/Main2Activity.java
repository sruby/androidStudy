package io.github.sruby.ServiceTest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.github.sruby.androidStudy.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    private Intent intent;
    private MyService.MyBinder myBinder ;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = (TextView) findViewById(R.id.tvShowServiceMsg);

        //intent需要在onCreate中实例化
        intent = new Intent(Main2Activity.this, MyService.class);

        /**
         * 启动服务
         */
        findViewById(R.id.startService).setOnClickListener(this);

        /**
         * 停止服务,服务被绑定的时候无法停止
         */
        findViewById(R.id.stopService).setOnClickListener(this);

        /**
         * 绑定,会启动服务
         */
        findViewById(R.id.bindService).setOnClickListener(this);
        /**
         * 解绑,会销毁服务
         */
        findViewById(R.id.unbindService).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.startService:
                /**
                 * 传入context和类的定义
                 */
                intent.putExtra("data","intent data value");
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.bindService:
                System.out.println("绑定服务");
                //this实现了服务的连接
                bindService(intent, this, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                System.out.println("解绑服务");
                unbindService(this);
                break;
        }
    }

    /**
     * 服务绑定时执行
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myBinder = (MyService.MyBinder) service;
        System.out.println("服务连接成功");

        //获取Service对象
        MyService myService = myBinder.getService();
        myService.setCallback(new MyService.Callback() {
            @Override
            public void onDataChange(String str) {
                //新创建的线程不能执行ui线程的资源,android中ui线程不允许其他辅助线程执行其资源
//                textView.setText(str);

                /**
                 * 通过message传递数据到ui线程
                 * 然后使用handler来处理
                 */
                System.out.println("str:"+str);

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data",str);
                message.setData(bundle);

                //使用sendMessage传递消息
                handler.sendMessage(message);
            }
        });


//        if(myBinder != null)
//        {
//            myBinder.setBindData("my binder data");
//        }
    }

    /**
     * 服务崩溃或者kill时
     * @param name
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    /**
     * 处理消息
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = msg.getData().getString("data");

            textView.setText(str);
        }
    };
}
