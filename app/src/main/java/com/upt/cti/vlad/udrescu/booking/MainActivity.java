package com.upt.cti.vlad.udrescu.booking;

import androidx.appcompat.app.AppCompatActivity;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.upt.cti.vlad.udrescu.booking.common.Common;
import com.upt.cti.vlad.udrescu.booking.model.RecentHotels;
import com.upt.cti.vlad.udrescu.booking.services.HomeActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int APP_REQUEST_CODE = 6969;

    TextView login_as_guest;
    LoginButton loginButton;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login_as_guest = (TextView) findViewById(R.id.login_as_guest);
        login_as_guest.setOnClickListener(this);
//        ButterKnife.bind(this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList(EMAIL));

        CallbackManager callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                        Log.d("STATUS:", accessToken.getToken());

                        Log.d("Success", "Login");
                        loginResult.toString();
                        Intent intent_login_with_fb = new Intent(getApplicationContext(), HomeActivity.class);
                        //intent_login_with_fb.putExtra(Common.IS_LOGIN, false);
                        startActivity(intent_login_with_fb);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                        Log.d("CANCEL","CANCEL");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("ERROR","ERROR");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(login_as_guest)){
            Log.d("Success", "Login");
            Intent intent_login_as_guest = new Intent(getApplicationContext(), HomeActivity.class);
            intent_login_as_guest.putExtra(Common.IS_LOGIN, false);
            startActivity(intent_login_as_guest);
            finish();
        }
    }
}