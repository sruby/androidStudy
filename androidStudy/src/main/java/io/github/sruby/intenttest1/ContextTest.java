package io.github.sruby.intenttest1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.sruby.androidStudy.R;

public class ContextTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_context_test);
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

        /**
         * 通过context获取
         * this表示当前aty的context. 也可以写成ContextTest.this
         */
        TextView textView = (TextView) findViewById(R.id.textView3);
//        textView.setText("hello!");

        /**
         * public final void setText(@StringRes int resid) {
         *setText(getContext().getResources().getText(resid));
         *}
         */
        textView.setText(R.string.hello);

        /**
         * 获取图标
         */
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);

        /**
         *可以通过Context获取到字符串信息
         */
        System.out.println(this.getResources().getText(R.string.hello));
    }

}
