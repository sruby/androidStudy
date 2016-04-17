package io.github.sruby.broadCR.broadcr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 *广播接收器,通信的一种方式
 *@author create at 16/4/9 22:30
 */
public class BroadCRTest extends BroadcastReceiver
{
    public static final String INTENT_RECEIVER = "io.github.sruby.broadCR.broadcr.intent.BroadCRTest";
    public BroadCRTest()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        System.out.println("接收到消息"+intent.getStringExtra("msg"));
        /**
         * 中止广播接收器的传递
         */
        abortBroadcast();
    }
}
