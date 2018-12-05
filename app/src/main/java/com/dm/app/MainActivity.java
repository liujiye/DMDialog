package com.dm.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show_dialog)
                .setOnClickListener(v -> showDialog());
    }

    private void showDialog()
    {
        DMDialog.builder(this, R.layout.dialog_main)
                .onDialogInitListener((helper, dialog) ->
                {
                    helper.setText(R.id.tv_hello, "哈哈");
                    helper.setText(R.id.btn_show_dialog, "代码中设置的文字");
                })
                .setGravity(Gravity.BOTTOM)
                .show();
    }
}
