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
import com.koreait.myapplication.ch10.searchmoviemodel.MovieListResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {
    private Retrofit rf;
    private final String KEY = "c368df0859f6194cec7d22fe3a4756dc";

    private MovieListAdapter adapter;
    private RecyclerView rvList;

    private final String ITEM_PER_PAGE = "20";
    private int curpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rvList = findViewById(R.id.rvList);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE){
                    Log.i("myLog", "스크롤 끝!");
                    getList();
                }
            }
        });

        adapter = new MovieListAdapter();
        rvList.setAdapter(adapter);

        getList();
    }

    private void getList() {
        KobisService service = rf.create(KobisService.class);

        Call<MovieListResultBodyVO> call = service.searchMovieList(KEY, ITEM_PER_PAGE, curpage++);
        call.enqueue(new Callback<MovieListResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieListResultBodyVO> call, Response<MovieListResultBodyVO> response) {
                if(response.isSuccessful()){
                    MovieListResultBodyVO result = response.body();

                    List<MovieVO> list = result.getMovieListResult().getMovieList();
                    for(MovieVO vo : list) {
                        adapter.addItem(vo);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("myLog","통신 오류");

                }
            }

            @Override
            public void onFailure(Call<MovieListResultBodyVO> call, Throwable t) {
                //통신 실패
                Log.e("myLog","통신 실패");
            }
        });
    }
}

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder>{
    static private List<MovieVO> list = new ArrayList<>();

    public void addItem(MovieVO vo){
        list.add(vo);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_movie, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieVO obj = list.get(position);
        holder.setItem(obj);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieCd=obj.getMovieCd();
                Log.i("myLog",movieCd);

                Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
                intent.putExtra("movieCd", movieCd);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMovieNm;
        private TextView tvRepNationNm;
        private TextView tvMovieNmEn;
        private TextView RepGenreNm;

        public MyViewHolder(View v){
            super(v);

            tvMovieNm = v.findViewById(R.id.tvMovieNm);
            tvRepNationNm = v.findViewById(R.id.tvRepNationNm);
            tvMovieNmEn = v.findViewById(R.id.tvMovieNmEn);
            RepGenreNm = v.findViewById(R.id.RepGenreNm);
        }
        public void setItem(MovieVO vo){
            tvMovieNm.setText(vo.getMovieNm());
            tvRepNationNm.setText(vo.getRepNationNm());
            tvMovieNmEn.setText(vo.getMovieNmEn());
            RepGenreNm.setText(vo.getRepGenreNm());
        }
    }
}