package com.koreait.myapplication.ch07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koreait.myapplication.R;
import com.koreait.myapplication.Utils;


public class BookPersonActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private PersonAdapter adapter;

    private EditText etName;
    private EditText etAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_person);

        rvList = findViewById(R.id.rvList);
        adapter = new PersonAdapter();

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        /*
        adapter.addItem(new Person("홍길동", 20));
        adapter.addItem(new Person("허준", 23));
        adapter.addItem(new Person("신사임당", 25));

        adapter.notifyDataSetChanged();
        */
    }
    public void clkReg(View v) {
        // 버튼이 클릭되었을대 할 일 작성.
        String name = etName.getText().toString();
        String age = etAge.getText().toString();

//        int intAge = Integer.parseInt(age); // 문자열 > 정수형
        int intAge = Utils.perseStringToInt(age); // 문자열 > 정수형

        if (intAge == 0) {
            Toast.makeText(this, "나이입력이 잘 못 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        Person p = new Person(name, intAge);
        adapter.addItem(p);
        adapter.notifyDataSetChanged();
        etName.setText("");
        etAge.setText("");
        /*
        if (intAge != 0) {
            Person p = new Person(name, intAge);
            adapter.addItem(p);
            adapter.notifyDataSetChanged();
            etName.setText("");
            etAge.setText("");
        } else {
            Toast.makeText(this, "나이입력이 잘 못 되었습니다.", Toast.LENGTH_SHORT).show();
        }
        */
    }
}