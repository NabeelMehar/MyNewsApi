package com.example.mynewsapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mynewsapi.R;
import com.example.mynewsapi.model.SuitcaseNews;

public class ShowActivity extends AppCompatActivity {

    private static final String TAGGED = "MMMM";

    ImageView imgnews;
    TextView titletv,authortv,descriptiontv,datetv,contenttv,detailUrltv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        imgnews=findViewById(R.id.showimg);
        titletv=findViewById(R.id.showtitletv);
        authortv=findViewById(R.id.showauthortv);
        descriptiontv=findViewById(R.id.showdesctv);
        datetv=findViewById(R.id.showdatetv);
        contenttv=findViewById(R.id.showcontenttv);
        detailUrltv=findViewById(R.id.showdetailurl);

        SuitcaseNews news=new SuitcaseNews();

        Bundle bundle=getIntent().getExtras();

        news.setTitle(bundle.getString("TITLE"));
        news.setAuthor(bundle.getString("AUTHOR"));
        news.setDescription(bundle.getString("DESCRIPTION"));
        news.setUrlImg(bundle.getString("URLIMG"));
        news.setDetailUrl(bundle.getString("DETAILURL"));
        news.setPublishTime(bundle.getString("PUBLISHTIME"));
        news.setContent(bundle.getString("CONTENT"));

        Log.d(TAGGED, "onCreate: "+news.getTitle());

        Glide.with(this).load(news.getUrlImg()).into(imgnews);
        titletv.setText(news.getTitle());
        authortv.setText(news.getAuthor());
        descriptiontv.setText(news.getDescription());
        datetv.setText(news.getPublishTime());
        contenttv.setText(news.getContent());

        Spanned text= Html.fromHtml("<a href = \""+news.getDetailUrl()+"\">"+" Click here For More Info"+"</a>");
        detailUrltv.setMovementMethod(LinkMovementMethod.getInstance());
        detailUrltv.setText(text);




    }
}
