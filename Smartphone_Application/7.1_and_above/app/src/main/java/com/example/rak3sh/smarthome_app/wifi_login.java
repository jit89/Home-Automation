package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.content.ContentValues.TAG;

public class wifi_login extends Activity {

    EditText SSID,Wifi_pass;
    TextView mTextview;
    Button wifi_cred;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_wifi);

        SSID=(EditText)findViewById(R.id.ssid);
        Wifi_pass=(EditText)findViewById(R.id.wifi_pass);
        wifi_cred=(Button)findViewById(R.id.wifi_cred);
        mTextview=(TextView)findViewById(R.id.mTextView);




        wifi_cred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String Ssid=SSID.getText().toString();
                final String pass=Wifi_pass.getText().toString();

                String url = "http://192.168.4.1:80/" + Ssid + "?" + pass;

                RequestQueue Queue;
                Queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"not worked",Toast.LENGTH_LONG).show();
                        Log.e(TAG, " error=" + error);
                    }
                    } );



                Queue.add(stringRequest);







                SharedPreferences pref=getSharedPreferences("homepref",0);
                SharedPreferences.Editor edit=pref.edit();
                edit.putString("flagg","ok");
                Intent k = new Intent(wifi_login.this, SplashActivity.class);
                startActivity(k);
                finish();

            }



        });




        }

}