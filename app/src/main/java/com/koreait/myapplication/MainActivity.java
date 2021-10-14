package com.koreait.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //이벤트 연결 (event binding) 버튼클릭시 실행될 메소드 연결
    public void clkBtn(View v) {
        Button btn = (Button)v;
        String btnText = (String)btn.getText();
        Toast.makeText(this, btnText+"를 클릭했어요.", Toast.LENGTH_SHORT).show();
    }
    public void ddd(View v) {
        // 누를 때 마다 1씩 증가
        if (v instanceof TextView){
            // v에 담겨있는 객체주소값을 TextView타입으로 저장할 수 있으면 true 없으면 false
            TextView tv = (TextView) v;
            String tvt = (String) tv.getText();
            int num = Integer.parseInt(tvt);
            num += 1;
            String tvt2 = String.valueOf(num);
            tv.setText(tvt2);
        }
    }

}