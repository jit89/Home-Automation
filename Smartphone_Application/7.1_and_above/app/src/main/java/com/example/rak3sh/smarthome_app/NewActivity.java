package com.example.rak3sh.smarthome_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by RAK3SH on 4/17/2018.
 * About information will go there.
 */

public class NewActivity extends Activity {
    ImageButton gitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoactivity);

      gitButton=(ImageButton)findViewById(R.id.gitButton);

      gitButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/jit89/Home-Automation")));
          }
      });



    }
}
