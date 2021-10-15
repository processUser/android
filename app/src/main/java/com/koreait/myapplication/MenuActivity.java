package com.koreait.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void call(View v) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1111-2222"));
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:010-1111-2222"));
        startActivity(intent);
    }
    public void moveToActivity(View v) {
        int id = v.getId();
        int B1 = R.id.menuBtn1; // R.id.[아이디명] 여기에 id 값이 저장되어있음
        int B2 = R.id.menuBtn2; // R.id.[아이디명] 여기에 id 값이
        int B3 = R.id.menuBtn3; // R.id.[아이디명] 여기에 id 값이

        Class c = null;

        if(id == B1){
            c = MainActivity.class;
        } else if(id == B2){
            c = LinearActivity.class;
        } else if(id == B3) {
            c = ConstraintActivity.class;
        }
        // Intent
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    public void moveToActivityWithText(View v) {
        TextView tv = (TextView)v;
        String text = (String)tv.getText();
        Log.i("myLog", text);

        Class c = null;
        switch (text) {
            case "메인":
                c = MainActivity.class;
                break;
            case "리니어레이아웃":
                c = LinearActivity.class;
                break;
            case "제약레이아웃":
                c = ConstraintActivity.class;
                break;
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}