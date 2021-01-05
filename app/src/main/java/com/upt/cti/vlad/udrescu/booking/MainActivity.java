package com.upt.cti.vlad.udrescu.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upt.cti.vlad.udrescu.booking.common.Common;
import com.upt.cti.vlad.udrescu.booking.model.RecentHotels;
import com.upt.cti.vlad.udrescu.booking.model.User;
import com.upt.cti.vlad.udrescu.booking.services.HomeActivity;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int APP_REQUEST_CODE = 6969;

    TextView login_as_guest;
    EditText login_phone_number;
    LoginButton loginButton;
    private static final String EMAIL = "email";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getInstance().getReference();

    private void writeNewUser(String phone_number) {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String userId = new String(array, Charset.forName("UTF-8"));

        User user = new User(phone_number);
        myRef.child("/logged_users/users").child("24234").setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("SUCCESS"," WRITE IN DB");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FAILURE"," WRITE IN DB");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login_as_guest = (TextView) findViewById(R.id.login_as_guest);
        login_as_guest.setOnClickListener(this);

        login_phone_number = (EditText) findViewById(R.id.phone_number);
        login_phone_number.setOnClickListener(this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions("email");

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
            Intent intent_login_as_guest = new Intent(getApplicationContext(), HomeActivity.class);
            intent_login_as_guest.putExtra(Common.IS_LOGIN, false);
            writeNewUser("123123123123");
            startActivity(intent_login_as_guest);
            finish();
        }
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        if(v.equals(login_as_guest)){
//            Intent intent_login_as_guest = new Intent(getApplicationContext(), HomeActivity.class);
//            intent_login_as_guest.putExtra(Common.IS_LOGIN, false);
//            startActivity(intent_login_as_guest);
//            finish();
//        }
//    }

//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if(v.equals(login_phone_number)) {
//            if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                Intent intent_login_as_guest = new Intent(getApplicationContext(), HomeActivity.class);
//                intent_login_as_guest.putExtra(Common.IS_LOGIN, false);
//                writeNewUser(login_phone_number.getText().toString());
//            }
//        }
//        return false;
//    }
}