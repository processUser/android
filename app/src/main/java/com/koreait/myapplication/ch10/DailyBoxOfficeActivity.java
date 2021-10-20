package com.koreait.myapplication.ch10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.koreait.myapplication.R;
import com.koreait.myapplication.picsum.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyBoxOfficeActivity extends AppCompatActivity {

    private DatePicker dpTargetDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_box_office);
        dpTargetDt = findViewById(R.id.dpTargetDt);
    }

    private void network(String targetDt) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService service = rf.create(KobisService.class);
        final String KEY = "c368df0859f6194cec7d22fe3a4756dc";
        Call<BoxOfficeResultBodyVO> call = service.boxOfficeSearchDailyBoxOfficeList(KEY,targetDt);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                if(res.isSuccessful()) {
                    BoxOfficeResultBodyVO vo = res.body();
                    List<DailyBoxOfficeVO> list = vo.getBoxOfficeResult().getDailyBoxOfficeList();

                    Log.i("myLog", list.size()+"개");

                    for(DailyBoxOfficeVO item : list){
                        Log.i("myLog", item.getMovieNm());
                    }
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {

            }
        });

    }
    public void clkSearch(View v) {
        int day = dpTargetDt.getDayOfMonth();
        int mon = dpTargetDt.getMonth() + 1;
        int year= dpTargetDt.getYear();

        /*
        Calendar c = Calendar.getInstance();
        c.set(year,mon,day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date= sdf.format(c.getTime());
        */
        String date = String.format("%s%02d%02d",year,mon,day);
        network(date);

        Log.i("myLog", date);
    }
}