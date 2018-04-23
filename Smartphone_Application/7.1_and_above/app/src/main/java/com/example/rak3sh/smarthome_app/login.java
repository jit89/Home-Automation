package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends Activity {
    EditText getName,getEmail;
    Button signButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.login_screen);


        getName=(EditText)findViewById(R.id.getName);
        getEmail=(EditText)findViewById(R.id.getEmail);
        signButton=(Button)findViewById(R.id.signButton);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref=getSharedPreferences("homepref",0);
                SharedPreferences.Editor edit=pref.edit();

                String em=getEmail.getText().toString();
                String nm=getName.getText().toString();

                edit.putString("name",nm);
                edit.putString("email",em);
                edit.apply();
                Log.e("sharedpref",pref.getString("name",null));
                Intent i=new Intent(login.this,SplashActivity.class);
                startActivity(i);
                finish();
            }
        });



    }
}
