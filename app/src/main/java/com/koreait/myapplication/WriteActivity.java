package com.koreait.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
// p.384
public class WriteActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList; // view 영역

    private List<String> msgList; // data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write); // 객체화

        //객체의 id값을 통해 주소값을 찾는다.
        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList);

        msgList = new LinkedList<>();

//        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); // 가로
        LinearLayoutManager llm = new LinearLayoutManager(this); //위에서 아래로 vertical
        rvList.setLayoutManager(llm); // 레이아아웃 구성 세팅

        SimpleTextAdapter sta = new SimpleTextAdapter(msgList); // 연동
        rvList.setAdapter(sta);

        // 인터페이스 객체화 가능? - 불가능..
        //1. 클래스 작성 필요
        View.OnClickListener event2 = new MyOnClickLisener();
        btnSend.setOnClickListener(event2);

        //2.  변수 할당 필요
        View.OnClickListener event = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        btnSend.setOnClickListener(event);

        //3.가장 간략하게 작성.
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 콜백 메소드드
                String msg = etMsg.getText().toString();
                Log.i("myLog", msg);
                etMsg.setText("");
                msgList.add(msg);
                sta.notifyDataSetChanged();
            }
        });
    }
}

class MyOnClickLisener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

    }
}

class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.MyViewHolder> {
    private List<String> list;

    SimpleTextAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview, parent, false);
        return new SimpleTextAdapter.MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String str = list.get(position);
        holder.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMsg;

        public MyViewHolder(View v) {
            super(v);
            tvMsg = v.findViewById(R.id.tvMsg);
        }
    }
}