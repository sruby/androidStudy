package io.github.sruby.applicationTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.github.sruby.androidStudy.R;

public class MainNew2 extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new2);
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

        textView = (TextView) findViewById(R.id.showApp);
        editText = (EditText) findViewById(R.id.editAppMsg);

        /**
         * 从application中获取信息,
         * 设置默认的显示信息
         */
        App app = (App)getApplicationContext();
        textView.setText(app.getAppData());

        findViewById(R.id.saveAppMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App app = (App)getApplicationContext();
                String text = editText.getText().toString();
                System.out.println(text);
                textView.setText(text);
                app.setAppData(text);
            }
        });
    }

}
