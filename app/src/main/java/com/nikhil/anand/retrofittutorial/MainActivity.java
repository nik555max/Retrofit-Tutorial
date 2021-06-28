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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTextView = findViewById(R.id.post_text);

        //create an instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

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
                    content += "User Id : "+post.getUserID()+"\n"
                            +"Id : "+post.getId()+"\n"+
                            "Title : "+post.getTitle()+"\n"+
                            "Body : "+post.getBody()+"\n\n";

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