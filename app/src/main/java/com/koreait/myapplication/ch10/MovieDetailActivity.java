package com.koreait.myapplication.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreait.myapplication.R;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.ActorVO;
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

    private ActorAdapter adapter;

    private TextView tvMovieNm;
    private TextView tvMovieNmEn;
    private TextView tvShowTm;
    private RecyclerView rvActorList;

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
        rvActorList = findViewById(R.id.rvActorList);
        adapter = new ActorAdapter();
        rvActorList.setAdapter(adapter); // rvActorList에 adapter 연결
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

                    List<ActorVO> list = data.getActors();
                    adapter.setList(list);

                    //adapter.setList(data.getActors());
                    adapter.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) { // baseUrl 틀리거나, 서버 사망, 망에 문제발생

            }
        });

    }
}
class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder>{
    private List<ActorVO> list;

    public void setList(List<ActorVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_actor,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder - 데이터 세트의 주어진 위치에서 항목의 내용을 나타내도록 업데이트되어야 하는 ViewHolder입니다.
        //position - 어댑터 데이터 세트 내 항목의 위치입니다.
        ActorVO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView PeopleNm;
        private TextView PeopleNmEn;
        private TextView tvCast;
        private TextView tvCastEn;

        public MyViewHolder(View v){
            super(v);
            PeopleNm = v.findViewById(R.id.PeopleNm);
            PeopleNmEn = v.findViewById(R.id.PeopleNmEn);
            tvCast = v.findViewById(R.id.tvCast);
            tvCastEn = v.findViewById(R.id.tvCastEn);
        }
        public void setItem(ActorVO vo){
            PeopleNm.setText(vo.getPeopleNm());
            PeopleNmEn.setText(vo.getPeopleNmEn());
            tvCast.setText(vo.getCast());
            tvCastEn.setText(vo.getCastEn());
        }
    }
}

//참고 https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#onBindViewHolder(VH,%20int)