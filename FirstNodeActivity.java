package com.projects.chintangandhi.dsa_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FirstNodeActivity extends AppCompatActivity implements View.OnClickListener {

    Button buildButton;

    SharedPreferences sharedPreferences, objectSharedPref;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("FNA", "oncreate");


        if(getSharedPreferences("graphSP",Context.MODE_PRIVATE).contains("graphAlreadyBuilt")) {
            Log.e("FNA", "In if oncreate");
            startActivity(new Intent(this, MainActivity.class));
        }

        else
        {
            Log.e("FNA", "In else oncreate");
            setContentView(R.layout.activity_first_node);

            //name = ((EditText) findViewById(R.id.firstNodeNameEditText)).getText().toString();

            buildButton = (Button) findViewById(R.id.buildGraphButton);
            buildButton.setOnClickListener(this);
            buildButton.setEnabled(true);
            buildButton.setClickable(true);
            Log.e("FNA", "out else oncreate");
        }
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(v.getContext(),"toast",Toast.LENGTH_SHORT).show();
        Log.e("FNA", "Before v.getID");
        name = ((EditText) findViewById(R.id.firstNodeNameEditText)).getText().toString();
        Graph g = new Graph(name);
        Log.e("FNA", "Before creating graph");

        int id = v.getId();
        Log.e("FNA", "After v.getID");
        if(id == R.id.buildGraphButton)
        {
            Log.e("FNA", "In Listener");
            sharedPreferences = getSharedPreferences("graphSP", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("graphAlreadyBuilt",true);
            editor.commit();

            Log.e("FNA", "Before try");
            try
            {
                Log.e("FNA", "Before fOut");
                FileOutputStream fOut = openFileOutput("graph.ser", Context.MODE_PRIVATE);
                Log.e("FNA", "After fOut");
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                Log.e("FNA", "Before writeobject");
                objOut.writeObject(g);
                Log.e("FNA", "After writeobject");
                objOut.flush();
                Log.e("FNA", "After flush");
                objOut.close();
                Log.e("FNA", "After close");
            }
            catch (IOException e) {
                Log.e("FNA", "Catch");
                e.printStackTrace();
            }

            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
