package com.koreait.myapplication.ch10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.koreait.myapplication.R;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieInfoResultVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieInfoVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvMovieNm;
    private TextView tvMovieNmEn;
    private TextView tvShowTm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        String movieCd = intent.getStringExtra("movieCd");
        Log.i("myLog", movieCd);

        getDate(movieCd);

        tvMovieNm = findViewById(R.id.tvMovieNm);
        tvMovieNmEn = findViewById(R.id.tvMovieNmEn);
        tvShowTm = findViewById(R.id.tvShowTm);
    }

    private void getDate(String movieCd) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService service = rf.create(KobisService.class);
        final String KEY = "c368df0859f6194cec7d22fe3a4756dc";
        Call<MovieInfoResultBodyVO> call = service.searchMovieInfo(KEY,movieCd);
        call.enqueue(new Callback<MovieInfoResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieInfoResultBodyVO> call, Response<MovieInfoResultBodyVO> response) {
                if(response.isSuccessful()) { //status : 200 - 통신성공
                    MovieInfoResultBodyVO result = response.body();

                    MovieInfoResultVO result2 = result.getMovieInfoResult();
                    MovieInfoVO data = result2.getMovieInfo();

                    MovieInfoVO date2 =result.getMovieInfoResult().getMovieInfo();
                    tvMovieNm.setText(data.getMovieNm());
                    tvMovieNmEn.setText(data.getMovieNmEn());
                    tvShowTm.setText(data.getShowTm());

                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) { // baseUrl 틀리거나, 서버 사망, 망에 문제발생

            }
        });

    }
}