package com.example.jaydmodi.dsa_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import com.projects.anshul.dsa_project.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button addNodeButton, calculatePathButton;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = getIntent().getExtras().getInt("indexOfGraph");

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
            Log.e("Index",String.valueOf(index));
            Intent intent = new Intent(this, InputActivity.class);
            intent.putExtra("indexOfGraph", index);
            startActivity(intent);
        }
        if(id == R.id.calculateButton)
        {
            Log.e("Index",String.valueOf(index));
            Intent intent = new Intent(this, ShortestPathActivity.class);
            intent.putExtra("indexOfGraph", index);
            startActivity(intent);
        }
    }

}
