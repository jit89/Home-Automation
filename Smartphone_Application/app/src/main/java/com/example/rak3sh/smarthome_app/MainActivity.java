package com.example.rak3sh.smarthome_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    public String ur = "http://ptsv2.com/t/vbvn/post"; //<<<<<<---url here

    //// TODO: 4/16/2018 onstart status changes


    boolean imButtonStat1 = false;         //ButtonState for on off checking
    boolean imButtonStat2=false;
    boolean imButtonStat3= false;
    boolean imButtonStat4=false;
    

    boolean app1, app2, app3, app4;         //bools for json
    int seek = 0;
    int seek2=0;

    ImageButton imageButton1,imageButton2,imageButton3,imageButton4,infoButton;

    TextView connectionCheckText;
    TextView appText1,appText2,appText3,appText4;
    SeekBar seekbar1,seekbar2;
     private Spinner spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton1 = (ImageButton) findViewById(R.id.imButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imButton3);
        imageButton4 = (ImageButton) findViewById(R.id.imButton4);
        infoButton=(ImageButton)findViewById(R.id.infoButton);

        appText1 = (TextView) findViewById(R.id.appText1);
        appText2 = (TextView) findViewById(R.id.appText2);
        appText3 = (TextView) findViewById(R.id.appText3);
        appText4 = (TextView) findViewById(R.id.appText4);

        //connectionCheckText = (TextView) findViewById(R.id.connectionCheckText);

        seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
        //spinner2=(Spinner)findViewById(R.id.spinner2);


 //============================>>>>>>>>>>>info<<<<<<<<<<<<<<<==============================
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info=new Intent(MainActivity.this,NewActivity.class);
                startActivity(info);
            }
        });


//==============================>>>>>>>>>>>app1<<<<<<<<<<<<<<<<<<===============================
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imButtonStat1 == false) {
                    imButtonStat1 = true;
                    // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                    seekbar1.setProgress(50);
                    seek = 50;
                    appText1.setText("FAN ON (50%)");
                    app1 = true;
                } else {
                    imButtonStat1 = false;
                    //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                    appText1.setText("FAN OFF");
                    seekbar1.setProgress(0);
                    seek = 0;
                    app1 = false;
                }
                checkConnection();                //checking connection
                new postReq().execute();           //// TODO: 4/15/2018  add thread
            }
        });


        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new postReq().execute();                                 //// TODO: 4/15/2018  add thread
                // Toast.makeText(getApplicationContext(),"speed is:"+seek+"%",Toast.LENGTH_SHORT).show();
                if (seek != 0) {
                    app1 = true;
                    imButtonStat1 = true;
                }
                checkConnection();


                appText1.setText("FAN ON" + "(" + seek + "%)");
            }
        });
        //=======================>>>>>>>>>>>>>>>>app2<<<<<<<<<<<<<<<====================================

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkConnection();


                        if (imButtonStat2 == false) {
                            imButtonStat2 = true;
                            appText2.setText("Light ON");
                            app2 = true;
                        } else {
                            imButtonStat2 = false;
                            appText2.setText("Light OFF");
                            app2 = false;

                        }
                        new postReq().execute();

                    }
                });
            }
        });

//========================>>>>>>>>>>>>>>>>>>>>>app3<<<<<<<<<<<<<<<<<===============================
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();


                if (imButtonStat3 == false) {
                    imButtonStat3 = true;
                    appText3.setText("Light On");
                    app3 = true;
                } else {
                    imButtonStat3 = false;
                    app3 = false;
                    appText3.setText("Light OFF");
                }
                new postReq().execute();
                //todo light2 logic
            }
        });

//===================>>>>>>>>>>>>>>>>>app4<<<<<<<<<<<<<<<<<<<<<<<<==================================
    imageButton4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkConnection();
            if (imButtonStat4 == false) {
                imButtonStat4 = true;
                // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                seekbar2.setProgress(50);
                seek2 = 50;
                appText4.setText("FAN ON (50%)");
                app4 = true;
            } else {
                imButtonStat4 = false;
                //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                appText4.setText("FAN OFF");
                seekbar2.setProgress(0);
                seek2 = 0;
                app4 = false;
            }
            new postReq().execute();           //// TODO: 4/15/2018  add thread

        }
    });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek2=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seek != 0) {
                    checkConnection();
                    app4 = true;
                    imButtonStat4 = true;
                }
                appText4.setText("FAN ON" + "(" + seek2 + "%)");
                new postReq().execute();
            }
        });

    }



    //---------------------------------connectivity checking------------------

    /*public Boolean connectionCheck() {                                       //// TODO: 4/15/2018 connectivity in thread
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
*/
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    public void checkConnection() {
        connectionCheckText = (TextView) findViewById(R.id.connectionCheckText);

        if (isOnline()) {
            Log.d("Connection Status:", "ok");
            connectionCheckText.setText("  ");

        } else {
            connectionCheckText.setText("NO INTERNET CONNECTION!!");
            Log.d("connection Status:", "no connection");
            Toast.makeText(MainActivity.this, "Please Check the connection,or\n \t\t\t" +
                    "Try agatn in a while ", Toast.LENGTH_LONG).show();

        }
    }


    //-------------------------------------HTTP posting-----------------------

    public class postReq extends AsyncTask<String, Void, String> {

        protected void onPreExecute(String result) {


        }

        @Override
        protected String doInBackground(String... params) {


            try {

                URL url = new URL(ur);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("room", "1");
                postDataParams.put("app1", app1);
                postDataParams.put("app1a", seek * (10.24));
                postDataParams.put("app2", app2);
                postDataParams.put("app3", app3);
                postDataParams.put("app4", app4);
                postDataParams.put("app4a",seek2*10.24);

                Log.e("params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }


            //return null;
        }


        protected void onPostExecute(String result) {
            // Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Log.d("Post Status:", result);
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
