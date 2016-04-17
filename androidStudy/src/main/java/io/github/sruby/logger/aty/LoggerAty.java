package io.github.sruby.logger.aty;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import io.github.sruby.androidStudy.R;

public class LoggerAty extends AppCompatActivity
{
    private static String TAG = "LoggerAty";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_aty);
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

        //TAG用来记录日志的类别,可以在filter中配置
        Log.v(TAG,"无用信息,吐槽作用");
        Log.d(TAG,"调试信息");
        Log.i(TAG,"info信息");
        Log.w(TAG,"警告信息");
        Log.e(TAG,"错误信息");
    }

}
