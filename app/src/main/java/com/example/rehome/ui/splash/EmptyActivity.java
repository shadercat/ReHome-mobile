package com.example.rehome.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.rehome.ConstantUrls;
import com.example.rehome.MainActivity;
import com.example.rehome.R;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.ResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class EmptyActivity extends AppCompatActivity {

    ImageView logo;
    Animation logoRotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        logo = findViewById(R.id.logo_splash);
        logoRotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        logo.startAnimation(logoRotation);

        PersistentCookieStore store = new PersistentCookieStore(this);
        HttpClient.setCookieStorage(store);

        final Intent main = new Intent(this, MainActivity.class);

        HttpClient.get(ConstantUrls.isAuthorized, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(ResponseHandler.IsSuccessed(response)){
                    startActivity(main);
                } else {
                    startActivity(main);
                }
                finish();
            }
        });
    }
}
