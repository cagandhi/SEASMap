package com.example.jaydmodi.dsa_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

//import com.projects.anshul.dsa_project.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button displayButton;
    Spinner srcSpinner;
    Spinner destSpinner;

    Graph g;
    List<String> vertexList;
    ArrayList<Vertex> arrayList;
    ArrayList<Graph> graphArrayList;

    ListView displayListView;

    String src,dest;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortest_path);

        index = getIntent().getExtras().getInt("indexOfGraph");

        displayButton = (Button) findViewById(R.id.displayPathButton);
        displayButton.setOnClickListener(this);

        srcSpinner = (Spinner) findViewById(R.id.pickSourceSpinner);
        srcSpinner.setOnItemSelectedListener(this);

        destSpinner = (Spinner) findViewById((R.id.pickDestinationSpinner));
        destSpinner.setOnItemSelectedListener(this);

        displayListView = (ListView) findViewById(R.id.displayListView);

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

        //store vertices in arrayList and vertoices names in vertexList
        arrayList = g.getVertices();

        vertexList = new ArrayList<String>();

        int i=0;
        for(Vertex v: arrayList)
        {
            if(v.getName().equals("empty"))
                continue;
            vertexList.add(v.getName());
            i++;
        }

        // Initializing ArrayAdapters for pick source and destination spinners
        ArrayAdapter<String> spinnerArrayAdapterSrc = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vertexList);
        ArrayAdapter<String> spinnerArrayAdapterDest = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vertexList);

        spinnerArrayAdapterSrc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapterDest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        srcSpinner.setAdapter(spinnerArrayAdapterSrc);
        destSpinner.setAdapter(spinnerArrayAdapterDest);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.displayPathButton)
        {
            //storing path in linkedList
            LinkedList<Vertex> path;

            ArrayList<String> pathArray = new ArrayList<String>();
            Vertex srcV = arrayList.get(vertexList.indexOf(src)+1);
            Vertex destV = arrayList.get(vertexList.indexOf(dest)+1);

            //calculate shortest path between src and dest using Dijkstra's algorithm
            path = g.calculateShortestPath(srcV,destV);

            //add destination vertex while displaying path
            path.add(destV);

            //add all vertex coming in path in pathArray so it is easier to display in ListView
            for(Vertex v1: path) {
                pathArray.add(v1.getName());
            }

            ArrayAdapter<String> displayAdapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,pathArray);
            displayListView.setAdapter(displayAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        //if pick Source spinner is selected
        if(spinner.getId() == R.id.pickSourceSpinner) {
            src = spinner.getItemAtPosition(position).toString();
            Log.e("SRC", "Selected " + src);
        }

        //if pick desination spinner is selected
        if(spinner.getId() == R.id.pickDestinationSpinner) {
            dest = spinner.getItemAtPosition(position).toString();
            Log.e("DEST", "Selected " + dest);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
