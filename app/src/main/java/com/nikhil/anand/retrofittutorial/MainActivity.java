package com.nikhil.anand.retrofittutorial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView postTextView;
    JsonPlaceHolder jsonPlaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTextView = findViewById(R.id.post_text);

        //create an instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        //getPost();
        getNews();
    }

    public void getNews(){
        Call<News> news = jsonPlaceHolder.getNews("tesla", "0a5d17752d9f4ee29370cf0337956531");

        news.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News todaysNews = response.body();

                String content ="";
                List<Articles> articles = todaysNews.getArticles();
                for (Articles a : articles){
                   content += "Title : "+a.getTitle()+"\n"
                           +"Description : "+a.getDescription()+
                           "\nURL : "+a.getUrl()
                           +"\nImage Url : "+a.getUrlToImage()
                           +"\nPublished At : "+a.getPublishedAt()+"\n\n";
                }
                postTextView.setText(content);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                postTextView.setText(t.getMessage());
            }
        });
    }

    public void getPost(){

        Call<List<Post>> call = jsonPlaceHolder.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    postTextView.setText("Code : "+response.code());
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "Updated : "+post.getUpdated()+"\n"
                            +"Cases : "+post.getCases()+"\n"+"\n\n";

                    postTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postTextView.setText(t.getMessage());
            }
        });
    }
}