package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import static android.content.ContentValues.TAG;

public class SplashActivity extends Activity {
    public String jsonTest;
    public boolean app11, app21, app31, app41,app12,app22,app32,app42,app13,app23,app33,app43;
    public int seeka1,seekb1,seeka2,seekb2,seeka3,seekb3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new fetchdata().execute();
        Log.e("fetchdata","ok");

    }



    public  class fetchdata extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http call
             }


        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("do back","ok");

            HttpHandler sh = new HttpHandler();
            Log.e("Handler","ok");

            String u="https://api.androidhive.info/game/game_stats.json";    //URL goes here for fetching

            String jsonStr = sh.makeServiceCall(u);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject json = new JSONObject(jsonStr);//.getJSONObject("game_stat");


                   // jsonTest=json.getString("now_playing");
                    //Log.d("json after filter",jsonTest.toString());   //json parsing and filtering
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

                }
                catch (final JSONException e) {

                    Log.e(TAG, "Json parsing error: " + e.getMessage());


                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");


            }




            return null;
        }

        @Override
        protected void  onPostExecute(Void result){
            super.onPostExecute(result);
            Intent i=new Intent(SplashActivity.this,MainActivity.class);
                    i.putExtra("app11",app11);
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
    }
}