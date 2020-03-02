package com.example.mynewsapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mynewsapi.R;
import com.example.mynewsapi.adapter.RecyclerNewsAdapter;
import com.example.mynewsapi.model.SuitcaseNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "ZZZ";
    int flag=0;
    RecyclerView recyclerView;
    ArrayList<SuitcaseNews> newsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView=findViewById(R.id.rv);
        newsArrayList=new ArrayList<>();
        final String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=710535bafc3444d3a26e184ff4ae6f6f";

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray articles=response.getJSONArray("articles");

                            for (int i = 0; i < articles.length(); i++) {

                                SuitcaseNews news=new SuitcaseNews();

                                JSONObject obnews=articles.getJSONObject(i);

                                String author=obnews.getString("author");
                                String title=obnews.getString("title");
                                String description=obnews.getString("description");
                                String detailurl=obnews.getString("url");
                                String imgUrl=obnews.getString("urlToImage");
                                String publishTime=obnews.getString("publishedAt");
                                String content=obnews.getString("content");



                                if (author!=null) {
                                    news.setAuthor(author);
                                }else {
                                    news.setAuthor("");
                                }
                                if (title!=null) {
                                    news.setTitle(title);
                                }else {
                                    news.setTitle("");
                                }
                                if (description!=null) {
                                    news.setDescription(description);
                                }else{
                                    news.setDescription("");
                                }
                                if (detailurl!=null) {
                                    news.setDetailUrl(detailurl);
                                }else {
                                    news.setDetailUrl("");
                                }
                                if (imgUrl!=null) {
                                    news.setUrlImg(imgUrl);
                                }else {
                                    news.setUrlImg("");
                                }
                                if (publishTime!=null) {
                                    news.setPublishTime(publishTime);
                                }else {
                                    news.setPublishTime("");
                                }

                                if (content!=null) {
                                    news.setContent(content);
                                }else {
                                    news.setContent("");
                                }

                                newsArrayList.add(news);




                            }

                            RecyclerNewsAdapter adapter=new RecyclerNewsAdapter(newsArrayList,getApplicationContext());

                            recyclerView.setLayoutManager(new LinearLayoutManager(LoginActivity.this,LinearLayoutManager.VERTICAL,false));
                            recyclerView.setAdapter(adapter);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });





    }


    @Override
    public void onBackPressed() {

        if (flag==0) {

            Toast.makeText(this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
            flag=1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                flag=0;
                }
            }, 2000);
        }
        else super.onBackPressed();


    }
}

//Glide.with(this)
//        .load("https://img.etimg.com/thumb/msid-73837056,width-1070,height-580,imgsize-403188,overlay-etwealth/photo.jpg")
//        .into(imageView);
