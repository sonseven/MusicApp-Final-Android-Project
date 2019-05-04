package com.example.hotmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotmusic.Activity.PlayNhacActivity;
import com.example.hotmusic.Model.Baihat;
import com.example.hotmusic.R;
import com.example.hotmusic.Service.APIService;
import com.example.hotmusic.Service.DataService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> mangbaihathot;

    public BaihathotAdapter(Context context, ArrayList<Baihat> mangbaihathot) {
        this.context = context;
        this.mangbaihathot = mangbaihathot;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_hot_song, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Baihat baihat = mangbaihathot.get(position);
        viewHolder.txtcasi.setText(baihat.getCasi());
        viewHolder.txtten.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(viewHolder.imghinh);
    }

    @Override
    public int getItemCount() {
        return mangbaihathot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.tvtenbaihathot);
            txtcasi = itemView.findViewById(R.id.tvcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihathot.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, baihatArrayList.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",mangbaihathot.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Loi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
