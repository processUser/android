package com.koreait.myapplication.ch10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.koreait.myapplication.R;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeResultVO;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyBoxOfficeActivity extends AppCompatActivity {

    private BoxOfficeAdapter adapter;

    private DatePicker dpTargetDt;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_box_office);
        adapter = new BoxOfficeAdapter();

        dpTargetDt = findViewById(R.id.dpTargetDt);
        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);
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

                    BoxOfficeResultVO resultVo = vo.getBoxOfficeResult();
                    List<BoxOfficeVO> list = resultVo.getDailyBoxOfficeList();


                    List<BoxOfficeVO> list2 = vo.getBoxOfficeResult().getDailyBoxOfficeList();

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
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
/*
class DailyBoxofficeAdapter extends RecyclerView.Adapter<DailyBoxofficeAdapter.MyViewHolder>{

    private List<BoxOfficeVO> list;

    public void setList(List<BoxOfficeVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_daily_boxoffice,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoxOfficeVO vo = list.get(position);
        holder.setItem(vo);
        //holder.setItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAudienceCnt;

        public MyViewHolder(View v){
            super(v);

            tvTitle = v.findViewById(R.id.tvTitle);
            tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
        }
        public void setItem(BoxOfficeVO vo) {
            tvTitle.setText(vo.getMovieNm());
            tvAudienceCnt.setText(Utils.getNumberComma(vo.getAudiCnt())+" 명");
        }

    }
}

 */