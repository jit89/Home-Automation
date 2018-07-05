package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import static android.content.ContentValues.TAG;

public class SplashActivity extends Activity {
    public String jsonTest;
    public boolean app11, app21, app31, app41,app12,app22,app32,app42,app13,app23,app33,app43;
    public int seeka1,seekb1,seeka2,seekb2,seeka3,seekb3;
    public boolean flag;
    public JSONObject json;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences pref=getSharedPreferences("homepref",0);
        String Name= pref.getString("name",null);
        if(Name!=null){
        Toast.makeText(getApplicationContext(),("Hello,"+Name),Toast.LENGTH_SHORT).show();}
        if((Name!="")&&(Name!=null)){


            //fetching initial data

            String u="http://phmexmpl.000webhostapp.com/test.php?opt=x";
            final RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);

            StringRequest stringreq = new StringRequest(Request.Method.GET, u, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.e(response,"initial parsed json");              //Toast.makeText(this,response,Toast.LENGTH_LONG).show();


                    //String jstring=response.toString();
                    try {
                       json = new JSONObject(response);

                        app11=json.getBoolean("app11");
                        app21=json.getBoolean("app21");
                        app31=json.getBoolean("app31");         //appliences data fetching from json
                        app41=json.getBoolean("app41");

                        app12=json.getBoolean("app12");
                        app22=json.getBoolean("app22");
                        app32=json.getBoolean("app32");
                        app42=json.getBoolean("app42");

                        app13=json.getBoolean("app13");
                        app23=json.getBoolean("app23");
                        app33=json.getBoolean("app33");
                        app43=json.getBoolean("app43");

                        seeka1=json.getInt("seeka1");
                        seekb1=json.getInt("seekb1");
                        seeka2=json.getInt("seeka2");
                        seekb2=json.getInt("seekb2");           //seek data fetching from json
                        seeka3=json.getInt("seeka3");
                        seekb3=json.getInt("seekb3");

//new intent for main activity

                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        i.putExtra("app11",app11);    Log.e(String.valueOf(app11),"test");
                        i.putExtra("app21",app21);
                        i.putExtra("app31",app31);
                        i.putExtra("app41",app41);
                        i.putExtra("app12",app12);
                        i.putExtra("app22",app22);
                        i.putExtra("app32",app32);
                        i.putExtra("app42",app42);
                        i.putExtra("app13",app13);
                        i.putExtra("app23",app23);
                        i.putExtra("app33",app33);
                        i.putExtra("app43",app43);
                        i.putExtra("seeka1",seeka1);
                        i.putExtra("seekb1",seekb1);
                        i.putExtra("seeka2",seekb2);
                        i.putExtra("seekb2",seekb2);
                        i.putExtra("seeka3",seeka3);
                        i.putExtra("seekb3",seekb3);
                        startActivity(i);
                        finish();
                    }

                    catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Unable to load data..exiting",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        finish();
                    }
                    requestQueue.stop();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    flag=true;
                    requestQueue.stop();
                    Toast.makeText(getApplicationContext(),"Unable to load data..exiting",Toast.LENGTH_LONG).show();
                    finish();

                }
            });
            requestQueue.add(stringreq);
            Log.e("fetchdata","ok");



        }
        else {
            Intent k=new Intent(SplashActivity.this,login.class);
            startActivity(k);
            finish();

        }




    }


}