package com.projects.chintangandhi.dsa_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InputActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    EditText nodeNameEditText, weightEditText;
    Spinner neighbourSpinner;

    String name, neighbour;
    Double weight;

    Button addButton, doneButton;

    Graph g;
    List<String> vertexList;
    ArrayList<Vertex> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Log.e("Input", "Before try");
        try {
            Log.e("Input", "Before fIn");
            FileInputStream fIn = getApplicationContext().openFileInput("graph.ser");
            Log.e("Input", "Before objIn");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            Log.e("Input", "After objIn");
            g = (Graph) objIn.readObject();
            Log.e("Input", "After readObject");
            objIn.close();
            Log.e("Input", "After objInclose");
            fIn.close();
            Log.e("Input", "After fInclose");
        } catch (IOException e) {
            Log.e("Input", "catch1");
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            Log.e("Input", "catch2");
            e.printStackTrace();
            return;
        }

        arrayList = g.getVertices();
        int size = arrayList.size();
        int i=0;

        vertexList = new ArrayList<String>();

        for(Vertex v: arrayList)
        {
            if(v.getName().equals("empty"))
                continue;
            vertexList.add(v.getName());
            i++;
        }
        i=0;
        for(String s :vertexList)
        {
            Log.e("Input", vertexList.get(i));
            i++;
        }

        neighbourSpinner = (Spinner) findViewById(R.id.neighbourSpinner);
        neighbourSpinner.setOnItemSelectedListener(this);

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vertexList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        neighbourSpinner.setAdapter(spinnerArrayAdapter);

        nodeNameEditText = (EditText) findViewById(R.id.nodeNameEditText);

        weightEditText = (EditText) findViewById(R.id.weightEditText);

        addButton = (Button) findViewById(R.id.addButton);
        doneButton = (Button) findViewById(R.id.doneButton);

        addButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        neighbour = parent.getItemAtPosition(position).toString();

        Log.e("Input", "Selected" + neighbour);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addButton)
        {
            Log.e("Input", "in add button");
            weight = Double.parseDouble(weightEditText.getText().toString());
            name = nodeNameEditText.getText().toString();

            if(!vertexList.contains(name))
            {
                Log.e("Input", "in if vertex");
                g.addVertex(name);
                Vertex vNew = new Vertex(name);
                arrayList.add(vNew);
                vertexList.add(name);
            }
            int srcIndex = vertexList.indexOf(name)+1;
            Log.e("Input", String.valueOf(srcIndex));
            int destIndex = vertexList.indexOf(neighbour)+1;
            Log.e("Input", String.valueOf(destIndex));

            g.addEdge(srcIndex,destIndex,weight);
            Log.e("Input", "Exit from add");

            weightEditText.setText("");
        }
        if(id == R.id.doneButton)
        {
            startActivity(new Intent(this, MainActivity.class));

            try
            {
                FileOutputStream fOut = openFileOutput("graph.ser", Context.MODE_PRIVATE);
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.writeObject(g);
                objOut.flush();
                objOut.close();
            }
            catch (IOException e) {

            e.printStackTrace();
        }
        }
    }
}