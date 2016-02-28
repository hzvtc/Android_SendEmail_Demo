package com.ald.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendEmailActivity extends AppCompatActivity implements EditText.OnKeyListener,View.OnClickListener {
    private EditText emailReceiver;
    private Button sendEmail_btn;
    private EditText emailCC;
    private EditText emailSubject;
    private EditText emailBody;

    private String[] strEmailReciver;
    private String[] strEmailCC;
    private String strEmailSubject;
    private String strEmailBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        findViewById();
        Init();
        setListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sendEmail_btn:
                sendEmail();
                break;

        }
    }
    //发送邮件
    private void sendEmail(){
        Intent mEmailIntent=new Intent(Intent.ACTION_SEND);
        mEmailIntent.setType("plain/text");
        strEmailReciver=new String[]{emailReceiver.getText().toString()};
        strEmailCC=new String[]{emailCC.getText().toString()};
        strEmailSubject=emailSubject.getText().toString();
        strEmailBody=emailBody.getText().toString();
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL,strEmailReciver);
        mEmailIntent.putExtra(Intent.EXTRA_CC,strEmailCC);
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT,strEmailSubject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT,strEmailBody);
        startActivity(Intent.createChooser(mEmailIntent,getResources().getString(R.string.Str_Message)));
    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        int id = v.getId();
        switch (id) {
            case R.id.emailReceiver:
                if(isEmail(emailReceiver.getText().toString())){
                     sendEmail_btn.setEnabled(true);
                }
                else {
                    sendEmail_btn.setEnabled(false);
                }
                break;
        }
        return false;
    }

    /*界面的初始化工作*/
    private void Init() {
        sendEmail_btn.setEnabled(false);
    }

    /*为控件设置事件监听*/
    private void setListener() {
        emailReceiver.setOnKeyListener(this);
        sendEmail_btn.setOnClickListener(this);
    }

    /*实例化布局文件的控件*/
    private void findViewById() {
        emailReceiver = (EditText) findViewById(R.id.emailReceiver);
        emailCC=(EditText)findViewById(R.id.emailCC);
        emailSubject=(EditText)findViewById(R.id.emailSubject);
        emailBody=(EditText)findViewById(R.id.emailBody);
        sendEmail_btn = (Button)findViewById(R.id.sendEmail_btn);
    }

    public boolean isEmail(String email){
        String strPattern="^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
        Pattern p=Pattern.compile(email);
        Matcher m=p.matcher(email);
       return m.matches();
    }
}
