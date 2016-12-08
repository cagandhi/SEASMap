package com.projects.chintangandhi.dsa_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button startButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSharedPreferences("StartActivitySP",Context.MODE_PRIVATE).contains("appLaunchedFirstTime"))
            startActivity(new Intent(this, FirstNodeActivity.class));

        else
        {
            setContentView(R.layout.activity_start);

            startButton = (Button) findViewById(R.id.startButton);
            startButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.startButton)
        {
            sharedPreferences = getSharedPreferences("StartActivitySP", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("appLaunchedFirstTime",true);
            editor.commit();

            Intent intent = new Intent(this, FirstNodeActivity.class);
            startActivity(intent);
        }
    }
}
