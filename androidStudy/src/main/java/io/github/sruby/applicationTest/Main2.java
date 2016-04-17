package io.github.sruby.applicationTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.github.sruby.androidStudy.R;

/**
 * 作者: Sruby on 2016/4/3 0003 14:10
 * 描述:
 */
public class Main2 extends AppCompatActivity{
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 绑定视图
         */
//        setContentView(R.layout.main2);
        textView = (TextView) findViewById(R.id.showApp);
        editText = (EditText) findViewById(R.id.editAppMsg);

        /**
         * 从application中获取信息,
         * 设置默认的显示信息
         */
        final App app = (App)getApplicationContext();
        textView.setText(app.getAppData());

        findViewById(R.id.saveAppMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                app.setAppData(text);
            }
        });
    }
}
