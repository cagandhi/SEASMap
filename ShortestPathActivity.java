package com.projects.chintangandhi.dsa_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button displayButton;
    Spinner srcSpinner;
    Spinner destSpinner;

    Graph g;
    List<String> vertexList;
    ArrayList<Vertex> arrayList;

    String neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortest_path);

        displayButton = (Button) findViewById(R.id.displayPathButton);
        displayButton.setOnClickListener(this);

        srcSpinner = (Spinner) findViewById(R.id.pickSourceSpinner);
        srcSpinner.setOnItemSelectedListener(this);

        destSpinner = (Spinner) findViewById((R.id.pickDestinationSpinner));
        destSpinner.setOnItemSelectedListener(this);

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


        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vertexList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        srcSpinner.setAdapter(spinnerArrayAdapter);
        destSpinner.setAdapter(spinnerArrayAdapter);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.displayPathButton)
        {

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        neighbour = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
