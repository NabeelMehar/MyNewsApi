package com.example.mynewsapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mynewsapi.R;
import com.example.mynewsapi.activities.ShowActivity;
import com.example.mynewsapi.model.SuitcaseNews;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.ViewHolder> {

    private static final String TAGG = "YYY";
    ArrayList<SuitcaseNews> arrayList;
    Context context;

    public RecyclerNewsAdapter(ArrayList<SuitcaseNews> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.custom_news_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SuitcaseNews news=arrayList.get(position);
        holder.titletv.setText(news.getTitle());

        if (news.getAuthor().equals("null")){
            holder.authortv.setText("");
        }else {
            holder.authortv.setText(news.getAuthor());
        }
        Glide.with(context).load(news.getUrlImg()).into(holder.imgv);

        DateTimeFormatter inputFormatter=DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter=DateTimeFormatter
                .ofPattern("dd-MM-yy",Locale.ENGLISH);
        LocalDate date=LocalDate.parse(news.getPublishTime(),inputFormatter);
        String formatteddate=outputFormatter.format(date);

        holder.datetv.setText(formatteddate);
    }

    @Override
    public int getItemCount() {
        if (arrayList!=null)
        return arrayList.size();

        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgv;
        TextView titletv,datetv,authortv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgv=itemView.findViewById(R.id.imgv);
            titletv=itemView.findViewById(R.id.titletv);
            datetv=itemView.findViewById(R.id.datetv);
            authortv=itemView.findViewById(R.id.authortv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            SuitcaseNews news=new SuitcaseNews();

            news=arrayList.get(position);


            Intent intent=new Intent(context, ShowActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("AUTHOR",news.getAuthor());
            intent.putExtra("TITLE",news.getTitle());
            intent.putExtra("DESCRIPTION",news.getDescription());
            intent.putExtra("URLIMG",news.getUrlImg());
            intent.putExtra("DETAILURL",news.getDetailUrl());
            intent.putExtra("PUBLISHTIME",news.getPublishTime());
            intent.putExtra("CONTENT",news.getContent());

            context.startActivity(intent);

        }
    }
}
