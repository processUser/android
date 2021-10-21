package com.koreait.myapplication.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.koreait.myapplication.R;
import com.koreait.myapplication.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeekBoxOfficeActivity extends AppCompatActivity {

    private DailyBoxofficeAdapter adapter;
    private Spinner spinner;

    private DatePicker dpTargetDt;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_box_office);
    }

    private void network(String targetDt, String weekGb) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService service = rf.create(KobisService.class);
        final String KEY = "c368df0859f6194cec7d22fe3a4756dc";
        Call<BoxOfficeResultBodyVO> call = service.boxOfficeSearchDailyBoxOfficeList(KEY,targetDt, weekGb);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                if(res.isSuccessful()) {
                    BoxOfficeResultBodyVO vo = res.body();

                    BoxOfficeResultVO resultVo = vo.getBoxOfficeResult();
                    List<DailyBoxOfficeVO> list = resultVo.getDailyBoxOfficeList();


                    List<DailyBoxOfficeVO> list2 = vo.getBoxOfficeResult().getDailyBoxOfficeList();

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {

            }
        });

    }

    public void clkSearch1(View v) {

    }

}
//// 참고 - https://onedaycodeing.tistory.com/65
//        spinner = findViewById(R.id.wkSpinner);
//                ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.week_array, android.R.layout.simple_spinner_dropdown_item);
//                monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(monthAdapter);
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//@Override
//public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다
//        spinner.getItemAtPosition(position);
//        Log.i("myLog", (String) spinner.getItemAtPosition(position));
//        }
//
//@Override
//public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//        });