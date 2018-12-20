package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
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


    public String ur = "http://phpexampl.000webhostapp.com/test.php?opt=y&pass=9733028233&dat="; //<<<<<<---url here

    //// TODO: 4/16/2018 onstart status changes



    boolean imButtonStat11 = false;         //ButtonState for on off checking
    boolean imButtonStat21 = false;
    boolean imButtonStat31 = false;
    boolean imButtonStat41 = false;
    boolean imButtonStat12 = false;         //ButtonState for on off checking
    boolean imButtonStat22 = false;
    boolean imButtonStat32 = false;
    boolean imButtonStat42 = false;
    boolean imButtonStat13 = false;         //ButtonState for on off checking
    boolean imButtonStat23 = false;
    boolean imButtonStat33 = false;
    boolean imButtonStat43 = false;



    boolean app11, app21, app31, app41,app12,app22,app32,app42,app13,app23,app33,app43;         //bools for json
    int seeka1= 0;
    int seeka2=0;
    int seeka3=0;
    int seekb1=0;
    int seekb2=0;
    int seekb3=0;
    int room=1;


    ImageButton imageButton1,imageButton2,imageButton3,imageButton4,infoButton,roomim1,roomim2,roomim3,menu;

    TextView connectionCheckText;
    TextView appText1,appText2,appText3,appText4;
    SeekBar seekbar1,seekbar2;
      DrawerLayout mdrawerLayout;
      String UserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i=getIntent();

        imButtonStat11 = i.getBooleanExtra("app11",true);         //ButtonState for on off checking
        imButtonStat21 = i.getBooleanExtra("app21",false);
        imButtonStat31 = i.getBooleanExtra("app31",false);;
        imButtonStat41 = i.getBooleanExtra("app41",false);;
        imButtonStat12 = i.getBooleanExtra("app12",false);;         //ButtonState for on off checking
        imButtonStat22 = i.getBooleanExtra("app22",false);;
        imButtonStat32 = i.getBooleanExtra("app32",false);;
        imButtonStat42 = i.getBooleanExtra("app42",false);;
        imButtonStat13 = i.getBooleanExtra("app13",false);;         //ButtonState for on off checking
        imButtonStat23 = i.getBooleanExtra("app23",false);;
        imButtonStat33 = i.getBooleanExtra("app33",false);;
        imButtonStat43 = i.getBooleanExtra("app43",false);;

        //=====================>>>>>>>>>nav header setup<<<<<<<<<<<<<<<<<<<<=======================


        SharedPreferences pref=getSharedPreferences("homepref",0);
        UserName=pref.getString("name","Smart Home");

       NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
       View hview=navigationView.getHeaderView(0);
       TextView userName=(TextView) hview.findViewById(R.id.navHeaderText);


        userName.setText(UserName);


//================vars=============


        imageButton1 = (ImageButton) findViewById(R.id.imButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imButton3);
        imageButton4 = (ImageButton) findViewById(R.id.imButton4);
        menu=(ImageButton)findViewById(R.id.imMenuButt);
        infoButton = (ImageButton) findViewById(R.id.infoButton);
        roomim1 = (ImageButton) findViewById(R.id.imroomButton1);
        roomim2 = (ImageButton) findViewById(R.id.imroomButton2);
        roomim3 = (ImageButton) findViewById(R.id.imroomButton3);

        mdrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);



        appText1 = (TextView) findViewById(R.id.appText1);
        appText2 = (TextView) findViewById(R.id.appText2);
        appText3 = (TextView) findViewById(R.id.appText3);
        appText4 = (TextView) findViewById(R.id.appText4);


        //connectionCheckText = (TextView) findViewById(R.id.connectionCheckText);

        seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekbar2 = (SeekBar) findViewById(R.id.seekBar2);

        roomim1.performClick();
        //-------------------------------Splash screen oncreate-------------------

                        //created with splashActivity



        //============================>>>>>>>>>>>info<<<<<<<<<<<<<<<==============================
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(MainActivity.this, NewActivity.class);
                startActivity(info);
            }
        });


        //========================================>>>>>>>>>>>>>>>>>>>menu<<<<<<<<<<<<<<<<<<<<<<<===================

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdrawerLayout.openDrawer(Gravity.START);

            }
        });




        NavigationView nav_view= (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //NavigationView nav_view= (NavigationView) findViewById(R.id.nav_view);

               item.setChecked(false);
               switch(item.getItemId()){
                   case R.id.one:
                       Toast.makeText(getApplicationContext(),"one",Toast.LENGTH_LONG).show();
                       Intent j=new Intent(MainActivity.this,login.class);
                       startActivity(j);
                       finish();
                       break;

                   case R.id.two:
                       Toast.makeText(getApplicationContext(),"Two",Toast.LENGTH_LONG).show();
                       break;

                   case R.id.three:
                        Intent x=new Intent(MainActivity.this,NewActivity.class);
                        startActivity(x);
                       break;
                   case R.id.four:
                       finish();
                       Intent intent = new Intent(Intent.ACTION_MAIN);
                       intent.addCategory(Intent.CATEGORY_HOME);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);


               }



                mdrawerLayout.closeDrawers();
                return false;

            }
        });


//==============================>>>>>>>>>>>app1<<<<<<<<<<<<<<<<<<===============================
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(room==1) {

                    if (imButtonStat11 == false) {
                        imButtonStat11 = true;
                        // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                        seekbar1.setProgress(50);
                        seeka1 = 50;
                        appText1.setText("FAN ON (50%)");
                        app11 = true;
                    } else {
                        imButtonStat11 = false;
                        //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                        appText1.setText("FAN OFF");
                        seekbar1.setProgress(0);
                        seeka1 = 0;
                        app11 = false;
                    }
                    checkConnection();                //checking connection
                    new postReq().execute();
                }
                else if(room==2){

                    if (imButtonStat12 == false) {
                        imButtonStat12 = true;
                        // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                        seekbar1.setProgress(50);
                        seeka2 = 50;
                        appText1.setText("FAN ON (50%)");
                        app12 = true;
                    } else {
                        imButtonStat12 = false;
                        //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                        appText1.setText("FAN OFF");
                        seekbar1.setProgress(0);
                        seeka2 = 0;
                        app12 = false;
                    }
                    checkConnection();                //checking connection
                    new postReq().execute();
                }
                else if(room==3){
                    if (imButtonStat13 == false) {
                        imButtonStat13 = true;
                        // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                        seekbar1.setProgress(50);
                        seeka3 = 50;
                        appText1.setText("FAN ON (50%)");
                        app13 = true;
                    } else {
                        imButtonStat13 = false;
                        //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                        appText1.setText("FAN OFF");
                        seekbar1.setProgress(0);
                        seeka3 = 0;
                        app13 = false;
                    }
                    checkConnection();                //checking connection
                    new postReq().execute();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Unexpected Error Occured",Toast.LENGTH_LONG).show();

                }//// TODO: 4/15/2018  add thread
            }
        });


        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(room==1){seeka1 = progress;}
                else if(room==2){seeka2=progress;}
                else if (room==3){seeka3=progress;}
                else{Toast.makeText(getApplicationContext(),"Unexpected Error Occured",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                                               //// TODO: 4/15/2018  add thread
                // Toast.makeText(getApplicationContext(),"speed is:"+seek+"%",Toast.LENGTH_SHORT).show();
                if(room==1){ if (seeka1 != 0) {
                    app11 = true;
                    imButtonStat11 = true;
                }
                    appText1.setText("FAN ON" + "(" + seeka1 + "%)");
                }
                else if(room==2){
                    if (seeka2 != 0) {
                        app12 = true;
                        imButtonStat12 = true;
                    }
                    appText1.setText("FAN ON" + "(" + seeka2 + "%)");
                }
                else if(room==3){
                    if (seeka3 != 0) {
                        app13 = true;
                        imButtonStat13 = true;
                    }
                    appText1.setText("FAN ON" + "(" + seeka3 + "%)");
                }

                checkConnection();
                new postReq().execute();



            }
        });
        //=======================>>>>>>>>>>>>>>>>app2<<<<<<<<<<<<<<<====================================


                imageButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkConnection();
                        if(room==1){
                            if (imButtonStat21 == false) {
                                imButtonStat21 = true;
                                appText2.setText("Light ON");
                                app21 = true;
                            } else {
                                imButtonStat21 = false;
                                appText2.setText("Light OFF");
                                app21 = false;

                            }
                            new postReq().execute();
                        }
                        else if(room==2){ if (imButtonStat22 == false) {
                            imButtonStat22 = true;
                            appText2.setText("Light ON");
                            app22 = true;
                        } else {
                            imButtonStat22 = false;
                            appText2.setText("Light OFF");
                            app22 = false;

                        }
                            new postReq().execute();}
                        else if(room==3){ if (imButtonStat23 == false) {
                            imButtonStat23 = true;
                            appText2.setText("Light ON");
                            app23 = true;
                        } else {
                            imButtonStat23 = false;
                            appText2.setText("Light OFF");
                            app23 = false;

                        }
                            new postReq().execute();}


                    }
                });

//========================>>>>>>>>>>>>>>>>>>>>>app3<<<<<<<<<<<<<<<<<===============================
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                if(room==1){
                    if (imButtonStat31 == false) {
                        imButtonStat31 = true;
                        appText3.setText("Light On");
                        app31 = true;
                    } else {
                        imButtonStat31 = false;
                        app31 = false;
                        appText3.setText("Light OFF");
                    }
                    new postReq().execute();
                }
                else if(room==2){

                    if (imButtonStat32 == false) {
                        imButtonStat32 = true;
                        appText3.setText("Light On");
                        app32 = true;
                    } else {
                        imButtonStat32 = false;
                        app32 = false;
                        appText3.setText("Light OFF");
                    }
                    new postReq().execute();
                }
                else if(room==3){ if (imButtonStat33 == false) {
                    imButtonStat33 = true;
                    appText3.setText("Light On");
                    app33 = true;
                } else {
                    imButtonStat33 = false;
                    app33 = false;
                    appText3.setText("Light OFF");
                }
                    new postReq().execute();}




                //todo light2 logic
            }
        });

//===================>>>>>>>>>>>>>>>>>app4<<<<<<<<<<<<<<<<<<<<<<<<==================================
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                if(room==1){if (imButtonStat41 == false) {
                    imButtonStat41 = true;
                    // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                    seekbar2.setProgress(50);
                    seekb1 = 50;
                    appText4.setText("FAN ON (50%)");
                    app41 = true;
                } else {
                    imButtonStat41 = false;
                    //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                    appText4.setText("FAN OFF");
                    seekbar2.setProgress(0);
                    seekb1 = 0;
                    app41 = false;
                }
                new postReq().execute();}
                else if(room==2){if (imButtonStat42 == false) {
                    imButtonStat42 = true;
                    // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                    seekbar2.setProgress(50);
                    seekb2 = 50;
                    appText4.setText("FAN ON (50%)");
                    app42 = true;
                } else {
                    imButtonStat42 = false;
                    //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                    appText4.setText("FAN OFF");
                    seekbar2.setProgress(0);
                    seekb2 = 0;
                    app42 = false;
                }
                    new postReq().execute();}
                else if(room==3){
                    if (imButtonStat43 == false) {
                        imButtonStat43 = true;
                        // Toast.makeText(getApplicationContext(),"FAN ON",Toast.LENGTH_LONG).show();
                        seekbar2.setProgress(50);
                        seekb3 = 50;
                        appText4.setText("FAN ON (50%)");
                        app43 = true;
                    } else {
                        imButtonStat43 = false;
                        //Toast.makeText(getApplicationContext(),"FAN OFF",Toast.LENGTH_LONG).show();
                        appText4.setText("FAN OFF");
                        seekbar2.setProgress(0);
                        seekb3 = 0;
                        app43 = false;
                    }
                    new postReq().execute();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Unexpected Error Occured",Toast.LENGTH_LONG);

                }

                           //// TODO: 4/15/2018  add thread

            }
        });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(room==1){seekb1 = progress;}
                else if(room==2){seekb2 = progress;}
                else if(room==3){seekb3 = progress;}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                checkConnection();
                if(room==1){if (seekb1 != 0) {
                    checkConnection();
                    app41 = true;
                    imButtonStat41 = true;
                }
                    appText4.setText("FAN ON" + "(" + seekb1 + "%)");}
                else if(room==2){if (seekb2 != 0) {
                    checkConnection();
                    app42 = true;
                    imButtonStat42 = true;
                }
                    appText4.setText("FAN ON" + "(" + seekb2 + "%)");}
                else if(room==3){if (seekb3 != 0) {
                    checkConnection();
                    app43 = true;
                    imButtonStat43 = true;
                }
                    appText4.setText("FAN ON" + "(" + seekb3 + "%)");}

                new postReq().execute();
            }
        });


        //================================>>>>>>>>>>>>getting intent values<<<<<<<<<<<<<<<===========




        app11 = i.getBooleanExtra("app11",true);         //ButtonState for on off checking
        app21 = i.getBooleanExtra("app21",false);
        app31 = i.getBooleanExtra("app31",false);;
        app41 = i.getBooleanExtra("app41",false);;
        app12 = i.getBooleanExtra("app12",false);;         //ButtonState for on off checking
        app22 = i.getBooleanExtra("app22",false);;
        app32 = i.getBooleanExtra("app32",false);;
        app42 = i.getBooleanExtra("app42",false);;
        app13 = i.getBooleanExtra("app13",false);;         //ButtonState for on off checking
        app23 = i.getBooleanExtra("app23",false);;
        app33 = i.getBooleanExtra("app33",false);;
        app43 = i.getBooleanExtra("app43",false);;




        seeka1 = i.getIntExtra("seeka1",0);
        seeka2 = i.getIntExtra("seeka2",0);
        seeka3 = i.getIntExtra("seeka3",0);
        seekb1 = i.getIntExtra("seekb1",0);
        seekb2 = i.getIntExtra("seeka2",0);
        seekb3 = i.getIntExtra("seekb3",0);

        //=======================>>>>>>>>>>>>>room1<<<<<<<<<<<<<<<<<<<<<<==================================
        roomim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room=1;
                roomim1.setBackgroundColor(0xFF578276);
                roomim2.setBackgroundColor(0xFF3eb291);
                roomim3.setBackgroundColor(0xFF3eb291);

                Toast.makeText(getApplicationContext(),String.valueOf(app11),Toast.LENGTH_LONG).show();

                if(app11==true){appText1.setText("FAN ON" + "(" + seeka1 + "%)");
                seekbar1.setProgress(seeka1);}
                else{appText1.setText("FAN OFF");
                seekbar1.setProgress(0);}

                if(app21==true){appText2.setText("LIGHT ON");}
                else{appText2.setText("LIGHT OFF");}

                if(app31==true){appText3.setText("LIGHT ON");}
                else{appText3.setText("LIGHT OFF");}

                if(app41==true){appText4.setText("FAN ON" + "(" + seekb1 + "%)");
                seekbar2.setProgress(seekb1);}
                else{appText4.setText("FAN OFF");
                seekbar2.setProgress(0);}


                app11=imButtonStat11; app12=imButtonStat12; app13=imButtonStat13;
                app21=imButtonStat21; app22=imButtonStat22; app23=imButtonStat23;
                app31=imButtonStat31; app32=imButtonStat32; app33=imButtonStat33;
                app41=imButtonStat41; app42=imButtonStat42; app43=imButtonStat43;



            }
        });

        roomim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room=2;
                roomim2.setBackgroundColor(0xFF578276);
                roomim1.setBackgroundColor(0xFF3eb291);
                roomim3.setBackgroundColor(0xFF3eb291);

                if(app12){appText1.setText("FAN ON" + "(" + seeka2 + "%)");
                seekbar1.setProgress(seeka2);}
                else{appText1.setText("FAN OFF");
                seekbar1.setProgress(0);}

                if(app22){appText2.setText("LIGHT ON");}
                else{appText2.setText("LIGHT OFF");}

                if(app32){appText3.setText("LIGHT ON");}
                else{appText3.setText("LIGHT OFF");}

                if(app42){appText4.setText("FAN ON" + "(" + seekb2 + "%)");
                seekbar2.setProgress(seekb2);}
                else{appText4.setText("FAN OFF");
                seekbar2.setProgress(0);}


            }
        });
        roomim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room=3;
                roomim3.setBackgroundColor(0xFF578276);
                roomim1.setBackgroundColor(0xFF3eb291);
                roomim2.setBackgroundColor(0xFF3eb291);

                if(app13){appText1.setText("FAN ON" + "(" + seeka3 + "%)");
                seekbar1.setProgress(seeka3);}
                else{appText1.setText("FAN OFF");
                seekbar1.setProgress(0);}

                if(app23){appText2.setText("LIGHT ON");}
                else{appText2.setText("LIGHT OFF");}

                if(app33){appText3.setText("LIGHT ON");}
                else{appText3.setText("LIGHT OFF");}

                if(app43){appText4.setText("FAN ON" + "(" + seekb3 + "%)");
                seekbar2.setProgress(seekb3);}
                else{appText4.setText("FAN OFF");
                seekbar1.setProgress(0);}
            }
        });

        roomim1.performClick();

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



                JSONObject postDataParams = new JSONObject();

                postDataParams.put("app11",app11);
                postDataParams.put("app21",app21);
                postDataParams.put("app31",app31);
                postDataParams.put("app41",app41);
                postDataParams.put("app12",app12);
                postDataParams.put("app22",app22);
                postDataParams.put("app32",app32);
                postDataParams.put("app42",app42);
                postDataParams.put("app13",app13);
                postDataParams.put("app23",app23);
                postDataParams.put("app33",app33);
                postDataParams.put("app43",app43);
                postDataParams.put("seeka1",seeka1);
                postDataParams.put("seekb1",seekb1);
                postDataParams.put("seeka2",seeka2);
                postDataParams.put("seekb2",seekb2);
                postDataParams.put("seeka3",seeka3);
                postDataParams.put("seekb3",seekb3);





                Log.e("params", postDataParams.toString());
                URL url = new URL(ur+postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();

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
