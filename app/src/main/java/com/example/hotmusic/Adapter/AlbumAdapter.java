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

import com.example.hotmusic.Activity.DanhsachbaihatActivity;
import com.example.hotmusic.Model.Album;
import com.example.hotmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> arrayalbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayalbum) {
        this.context = context;
        this.arrayalbum = arrayalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Album album = arrayalbum.get(position);
        viewHolder.txtcasialbum.setText(album.getTenCaSiAlbum());
        viewHolder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(viewHolder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return arrayalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imghinhalbum;
        TextView txttenalbum, txtcasialbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.tvtenalbum);
            txtcasialbum = itemView.findViewById(R.id.tvtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",arrayalbum.get(getPosition()));
                    context.startActivity((intent));
                }
            });
        }
    }
}
