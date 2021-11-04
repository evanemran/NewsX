package com.example.newsx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsx.Models.NewsHeadline;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadline headline;
    TextView text_title, text_published, text_source, text_data;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        headline = (NewsHeadline) getIntent().getSerializableExtra("data");

        text_data = findViewById(R.id.text_desc_full);
        text_published = findViewById(R.id.text_desc_published);
        text_source = findViewById(R.id.text_desc_source);
        text_title = findViewById(R.id.text_desc_title);
        img_news = findViewById(R.id.img_news);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(headline.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);

        text_title.setText(headline.getTitle());
        text_source.setText(headline.getAuthor());
        text_published.setText(dateStr);
        text_data.setText(headline.getContent());


        Picasso.get().load(headline.getUrlToImage()).into(img_news);



    }
}