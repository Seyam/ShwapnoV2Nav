package com.example.shwapnov2nav.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.shwapnov2nav.API.APIService;
import com.example.shwapnov2nav.API.RetrofitInstance;
import com.example.shwapnov2nav.Database.DataLogger;
import com.example.shwapnov2nav.Model.User;
import com.example.shwapnov2nav.Model.UserResponse;
import com.example.shwapnov2nav.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private APIService apiService;
    private DataLogger dataLogger = new DataLogger(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        final EditText name = findViewById(R.id.etUserID);
        final EditText password = findViewById(R.id.etPassword);

        //get the button
        Button signInButton = findViewById(R.id.btnLogin);
        //set the button click handler
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Other way to pass the form data
//                deviceId = name.getText().toString();
//                temp =  password.getText().toString()


                User user = new User(
                        name.getText().toString(),
                        password.getText().toString()
                );

                sendNetworkRequest(user);

            }
        });


    }

    public void openMainActivity(){
        Intent mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
    }


    public void sendNetworkRequest(User user){
        String base = user.getName()+":"+user.getPassword();
        final String authHeader = "Basic "+ Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        ////create a retrofit instance
        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        Observable<UserResponse> myObservable = apiService.sendPostAsBasicAuthentication(authHeader)
                                                          .subscribeOn(Schedulers.io())
                                                          .observeOn(AndroidSchedulers.mainThread());

        myObservable.subscribe(new Observer<UserResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserResponse userResponse) {
                if (userResponse.getResponse().equals(authHeader)){
                    Log.e("Login", " Matched!");
                    Toast.makeText(LoginActivity.this, "response: "+userResponse.getResponse(), Toast.LENGTH_SHORT).show();
                    openMainActivity();
                    dataLogger.saveData("authHeader",authHeader);

                }
                else{
                    Log.e("Login", " Mismatched!");
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("Login", "Network request Error");
                Toast.makeText(LoginActivity.this, "Invalid inputs!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {

            }
        });


    }



}
