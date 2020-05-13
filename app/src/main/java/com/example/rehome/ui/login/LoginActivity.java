package com.example.rehome.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rehome.ConstantUrls;
import com.example.rehome.MainActivity;
import com.example.rehome.R;
import com.example.rehome.connection.HttpClient;
import com.example.rehome.connection.RequestParamsFactory;
import com.example.rehome.connection.ResponseHandler;
import com.example.rehome.ui.msnackbar.ThematicSnackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    ImageView logo;
    EditText emailInput;
    EditText passwordInput;
    TextView createAccount;

    Animation logo_fetching;
    Animation logo_decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context = this;

        logo = findViewById(R.id.logo_loginActivity);
        loginBtn = findViewById(R.id.btn_login);
        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
        createAccount = findViewById(R.id.link_signup);

        logo_fetching = AnimationUtils.loadAnimation(this, R.anim.rotate);
        logo_decline = AnimationUtils.loadAnimation(this, R.anim.shake);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = RequestParamsFactory.loginParams(
                        emailInput.getText().toString().trim(),
                        passwordInput.getText().toString());

                logo.startAnimation(logo_fetching);

                HttpClient.post(ConstantUrls.login, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if(ResponseHandler.IsSuccessed(response)) {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            logo.startAnimation(logo_decline);
                            ThematicSnackbar.SnackbarTextShow(getString(R.string.wrongLoginData), emailInput);
                        }
                    }
                });


            }
        });
    }
}
