package com.example.jaydmodi.dsa_project;

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

//import com.projects.anshul.dsa_project.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FirstNodeActivity extends AppCompatActivity implements View.OnClickListener {

    Button buildButton;

    SharedPreferences sharedPreferences;

    String Vname,Gname;

    ArrayList<Graph> graphArrayList;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        index = getIntent().getExtras().getInt("indexOfGraph");
        Log.e("Index",String.valueOf(index));

//        if(getSharedPreferences("graphSP",Context.MODE_PRIVATE).contains("graphAlreadyBuilt")) {
//            startActivity(new Intent(this, MainActivity.class));
//        }

//        else
//        {
            setContentView(R.layout.activity_first_node);

            buildButton = (Button) findViewById(R.id.buildGraphButton);
            buildButton.setOnClickListener(this);
            buildButton.setEnabled(true);
            buildButton.setClickable(true);
//        }
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(v.getContext(),"toast",Toast.LENGTH_SHORT).show();
        Vname = ((EditText) findViewById(R.id.firstNodeNameEditText)).getText().toString();
        Gname = ((EditText) findViewById(R.id.graphNameEditText)).getText().toString();

        Graph g = new Graph(Gname,Vname);

        try {
            //read object from file
            FileInputStream fIn = getApplicationContext().openFileInput("graph.ser");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            graphArrayList = (ArrayList<Graph>) objIn.readObject();

            objIn.close();
            fIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int id = v.getId();
        if(id == R.id.buildGraphButton)
        {
            graphArrayList.add(g);
            try
            {
                //arraylist of graphs to be stored
                FileOutputStream fOut = openFileOutput("graph.ser", Context.MODE_PRIVATE);
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.writeObject(graphArrayList);
                objOut.flush();
                objOut.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("Index",String.valueOf(index));
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("indexOfGraph", index);
            startActivity(intent);
        }
    }
}
