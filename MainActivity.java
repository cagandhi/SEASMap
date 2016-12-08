package com.projects.chintangandhi.dsa_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button addNodeButton, calculatePathButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNodeButton = (Button) findViewById(R.id.addNodeButton);
        calculatePathButton = (Button) findViewById(R.id.calculateButton);

        addNodeButton.setOnClickListener(this);
        calculatePathButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addNodeButton)
        {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
        }
        if(id == R.id.calculateButton)
        {
            startActivity(new Intent(this, ShortestPathActivity.class));
        }
    }

}
