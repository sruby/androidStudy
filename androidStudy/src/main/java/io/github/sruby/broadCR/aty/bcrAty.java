package io.github.sruby.broadCR.aty;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.sruby.broadCR.broadcr.BroadCRTest;
import io.github.sruby.androidStudy.R;

/**
 *广播接收器测试aty
 *@author create at 16/4/9 22:39
 */
public class bcrAty extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcr_aty);
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
         *
         */
        findViewById(R.id.btSendBCR).setOnClickListener(this);
        findViewById(R.id.regReciver).setOnClickListener(this);
        findViewById(R.id.unregReciver).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btSendBCR:
                /**
                 * 指定boradCastReceiver
                 */
//                Intent intent = new Intent(this,BroadCRTest.class);

                /**
                 * 动态注册和注销,使用隐式intent
                 */
                Intent intent = new Intent(BroadCRTest.INTENT_RECEIVER);
                intent.putExtra("msg","send msg to broadcastreceiver");
                System.out.println("发送消息");
                /**
                 * 发送消息给广播接收器,此方法调用的广播不能被中止
                 */
//                sendBroadcast(intent);


                /**
                 * 按顺序传递广播,在广播接收器中可以被中止
                 */
                sendOrderedBroadcast(intent,null);
                break;
            case R.id.regReciver:
                if(broadCRTest == null)
                {
                    broadCRTest = new BroadCRTest();
                    /**
                     * 注册广播接收器
                     */
                   registerReceiver(broadCRTest,new IntentFilter(BroadCRTest.INTENT_RECEIVER));
                }
                break;
            case R.id.unregReciver:
                if(broadCRTest != null)
                {
                    unregisterReceiver(broadCRTest);
                    broadCRTest = null;
                }
                break;
        }
    }

    private BroadCRTest broadCRTest = null;
}
