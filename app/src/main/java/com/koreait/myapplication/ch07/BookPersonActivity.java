package com.koreait.myapplication.ch07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.koreait.myapplication.R;

public class BookPersonActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_person);

        rvList = findViewById(R.id.rvList);

        adapter = new PersonAdapter();

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        adapter.addItem(new Person("홍길동", 20));
        adapter.addItem(new Person("허준", 23));
        adapter.addItem(new Person("신사임당", 25));
        adapter.notifyDataSetChanged();
    }
}