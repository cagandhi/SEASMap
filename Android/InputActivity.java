package com.example.jaydmodi.dsa_project;

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
import android.widget.Toast;

//import com.projects.anshul.dsa_project.R;

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

    ArrayList<Graph> graphArrayList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        index = getIntent().getExtras().getInt("indexOfGraph");

        try {
            //read object from file
            FileInputStream fIn = getApplicationContext().openFileInput("graph.ser");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            graphArrayList = (ArrayList<Graph>) objIn.readObject();

            g = graphArrayList.get(index);
            objIn.close();
            fIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        //arrayList contains vertices of the graph
        arrayList = g.getVertices();
        int i=0;

        //storing the names of vertices in vertexList from arrayList
        vertexList = new ArrayList<String>();

        for(Vertex v: arrayList)
        {
            if(v.getName().equals("empty"))
                continue;
            vertexList.add(v.getName());
            i++;
        }

        //spinner object
        neighbourSpinner = (Spinner) findViewById(R.id.neighbourSpinner);
        neighbourSpinner.setOnItemSelectedListener(this);

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vertexList);

        //wraps the array adapter on spinner
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addButton)
        {
            weight = Double.parseDouble(weightEditText.getText().toString());
            name = nodeNameEditText.getText().toString();

            if(!vertexList.contains(name))
            {
                Vertex vNew = new Vertex(name);
                arrayList.add(vNew);
                vertexList.add(name);
            }
            int srcIndex = vertexList.indexOf(name)+1;
            Log.e("Input check ", String.valueOf(srcIndex));
            int destIndex = vertexList.indexOf(neighbour)+1;
//            Log.e("Input", String.valueOf(destIndex));

            g.addEdge(srcIndex,destIndex,weight);
            weightEditText.setText("");

            Toast.makeText(v.getContext(),"Location successfully stored", Toast.LENGTH_SHORT).show();

            //delete file
            boolean deleted = deleteFile("graph.ser");

            if(deleted)
                Log.e( "onClick: ", "deleted");
            try
            {
                //write the object back into the same file
                FileOutputStream fOut = openFileOutput("graph.ser", Context.MODE_PRIVATE);
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.writeObject(graphArrayList);
                objOut.flush();
                objOut.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(id == R.id.doneButton) {
            //delete file
            boolean deleted = deleteFile("graph.ser");
            if(deleted)
                Log.e( "onClick: done ", "deleted");
            try
            {
                //write the object back into the same file
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("indexOfGraph", index);
            startActivity(intent);
        }
    }
}