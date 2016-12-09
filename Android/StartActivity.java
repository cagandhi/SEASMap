package com.example.jaydmodi.dsa_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

//import com.projects.anshul.dsa_project.R;


public class StartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button startButton;

    ArrayList<String> nameOfGraph;

    Spinner spinner;

    ArrayList<Graph> graphArrayList = new ArrayList<Graph>();
    ArrayList<String> nameArrayList;

    String graphName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = new File("graph.ser");

        //create SEAS map
        if(!file.exists())
        {
            Graph g = new Graph("SEAS", "Entrance");

            g.addVertex("Main Stairs");
            g.addVertex("CR006");
            g.addVertex("CR007");
            g.addVertex("MD LAB Stairs");
            g.addVertex("MD008");
            g.addVertex("MD009");
            g.addVertex("Lift1");
            g.addVertex("Canteen");
            g.addVertex("Canteen Stairs");
            g.addVertex("Toilet1");
            g.addVertex("IL013");
            g.addVertex("IL Stairs");
            g.addVertex("Toilet2");
            g.addVertex("016");
            g.addVertex("017");
            g.addVertex("018");
            g.addVertex("019");
            g.addVertex("Entrance2");
            g.addVertex("Lift2");
            g.addVertex("AMSOM admin");
            g.addVertex("Library Stairs");
            g.addVertex("Library");
            g.addVertex("CR107");
            g.addVertex("Stairs 6");
            g.addVertex("CR110");
            g.addVertex("Cr111");
            g.addVertex("MD Lab Stairs 1st floor");
            g.addVertex("CR112");
            g.addVertex("Mtech Study 1st floor");
            g.addVertex("Study Room 114");
            g.addVertex("Canteen Stairs 1st floor");
            g.addVertex("Toilet 3");
            g.addVertex("E lab 116");
            g.addVertex("E lab 117");
            g.addVertex("IL stairs 1st floor");
            g.addVertex("Toilet 4");
            g.addVertex("139");
            g.addVertex("138");
            g.addVertex("137");
            g.addVertex("136");
            g.addVertex("135");
            g.addVertex("134");
            g.addVertex("Visiting Faculty Room");
            g.addVertex("133");
            g.addVertex("132");
            g.addVertex("131");
            g.addVertex("130");
            g.addVertex("129");
            g.addVertex("128");
            g.addVertex("Kinjal Ma'am's Room");
            g.addVertex("Lift2");
            g.addVertex("Dean / Associate Dean");
            g.addVertex("Cr105");
            g.addVertex("Library Stairs 1sr flr");
            g.addVertex("Cr106");
            g.addVertex("Main Stairs 1st flr");
            g.addVertex("Lift1 1st flr");

            //graph for ground floor
//		g.addEdge(0, 1, 0);
//         g.addEdge(1, 2, 5);
            g.addEdge(2, 1, 5); g.addEdge(2, 3, 5); g.addEdge(2, 23, 25); g.addEdge(2, 57, 20);
            g.addEdge(3, 2, 5); g.addEdge(3, 4, 10);
            g.addEdge(4, 3, 10); g.addEdge(4, 5, 5);
            g.addEdge(5, 4, 5); g.addEdge(5, 6, 5); g.addEdge(5, 28, 15);
            g.addEdge(6, 5, 5); g.addEdge(6, 7, 10);
            g.addEdge(7, 6, 10); g.addEdge(7, 8, 0); g.addEdge(7, 9, 20); g.addEdge(7, 11, 10); g.addEdge(7, 12, 35);
            g.addEdge(8, 7, 0); //don't consider lift connections (edges)
            g.addEdge(9, 7, 20); g.addEdge(9, 10, 10); g.addEdge(9, 11, 20); g.addEdge(9, 12, 45);
            g.addEdge(10, 9, 10); g.addEdge(10, 32, 20);
            g.addEdge(11, 9, 20); g.addEdge(11, 7, 10); g.addEdge(11, 12, 35);
            g.addEdge(12, 7, 35); g.addEdge(12, 9, 45); g.addEdge(12, 11, 35); g.addEdge(12, 14, 15);
            g.addEdge(13, 12, 10); g.addEdge(13, 14, 5); g.addEdge(13, 15, 10); g.addEdge(13, 36, 20);
            g.addEdge(14, 13, 5);
            g.addEdge(15, 13, 10); g.addEdge(15, 16, 2);
            g.addEdge(16, 15, 2); g.addEdge(16, 17, 10);
            g.addEdge(17, 16, 10); g.addEdge(17, 18, 2);
            g.addEdge(18, 17, 2); g.addEdge(18, 19, 21); g.addEdge(18, 20, 21); g.addEdge(18, 22, 21);
            g.addEdge(19, 18, 21); g.addEdge(19, 20, 10); g.addEdge(19, 22, 10);
            g.addEdge(20, 18, 21); g.addEdge(20, 19, 10); g.addEdge(20, 21, 5); g.addEdge(20, 22, 10);
            g.addEdge(21, 20, 5);
            g.addEdge(22, 18, 21); g.addEdge(22, 19, 10); g.addEdge(22, 20, 10); g.addEdge(22, 23, 10); g.addEdge(22, 55, 20);
            g.addEdge(23, 2, 25); g.addEdge(23, 22, 10);

            //graph for 1st floor
            g.addEdge(24, 31, 66.6); g.addEdge(24, 57, 2.4);
            g.addEdge(25, 26, 13.5); g.addEdge(25, 57, 12);
            g.addEdge(26, 25, 13.5); g.addEdge(26, 27, 13.5);
            g.addEdge(27, 26, 13.5); g.addEdge(27, 28, 4.5);
            g.addEdge(28, 27, 4.5); g.addEdge(28, 29, 9); g.addEdge(28, 5, 20); g.addEdge(28, 58, 0);
            g.addEdge(29, 28, 9); g.addEdge(29, 30, 16.5); g.addEdge(29, 33, 16.5); g.addEdge(29, 34, 14.1);
            g.addEdge(30, 29, 16.5); g.addEdge(30, 31, 12); g.addEdge(30, 33, 24); g.addEdge(30, 34, 21.6);
            g.addEdge(31, 24, 66.6); g.addEdge(31, 30, 12); g.addEdge(31, 32, 2.4);
            g.addEdge(32, 31, 2.4); g.addEdge(32, 10, 20); g.addEdge(36, 13, 20);
            g.addEdge(33, 29, 16.5); g.addEdge(33, 30, 24); g.addEdge(33, 34, 7.2);
            g.addEdge(34, 29, 14.1); g.addEdge(34, 30, 21.6); g.addEdge(34, 33, 7.2); g.addEdge(34, 35, 7.2);
            g.addEdge(35, 34, 7.2); g.addEdge(35, 36, 12);
            g.addEdge(36, 35, 12); g.addEdge(36, 37, 12); g.addEdge(36, 38, 1.5);
            g.addEdge(37, 36,12); g.addEdge(37, 38, 4.5);
            g.addEdge(38, 36, 1.5); g.addEdge(38, 37, 4.5); g.addEdge(38, 39, 4.5);
            g.addEdge(39, 38,4.5); g.addEdge(39, 40, 4.5);
            g.addEdge(40, 39,4.5); g.addEdge(40, 41, 4.5);
            g.addEdge(41, 40,4.5); g.addEdge(41, 42, 4.5);
            g.addEdge(42, 41,4.5); g.addEdge(42, 43, 4.5);
            g.addEdge(43, 42, 4.5); g.addEdge(43, 44, 0); g.addEdge(43, 45, 4.5);
            g.addEdge(44, 43, 0);
            g.addEdge(45, 43,4.5); g.addEdge(45, 46, 4.5);
            g.addEdge(46, 45,4.5); g.addEdge(46, 47, 4.5);
            g.addEdge(47, 46,4.5); g.addEdge(47, 48, 4.5);
            g.addEdge(48, 47,4.5); g.addEdge(48, 49, 4.5);
            g.addEdge(49, 48,4.5); g.addEdge(49, 50, 4.5);
            g.addEdge(50, 49,4.5); g.addEdge(50, 51, 6);
            g.addEdge(51, 50, 6); g.addEdge(51, 52, 5); g.addEdge(51, 54, 4.8);
            g.addEdge(52, 51,5); g.addEdge(52, 53, 5);
            g.addEdge(53, 52,5);
            g.addEdge(54, 51,4.8); g.addEdge(54, 55, 7.2);
            g.addEdge(55, 54,7.2);  g.addEdge(55, 56,14.4); g.addEdge(55, 22, 20);
            g.addEdge(56, 55,14.4); g.addEdge(56, 57, 4.8);
            g.addEdge(57, 24,2.4); g.addEdge(57, 25, 12); g.addEdge(57, 56,4.8);
            g.addEdge(58, 28, 0);


            graphArrayList.add(g);

            //write the object back into the same file
            FileOutputStream fOut = null;
            try
            {
                fOut = openFileOutput("graph.ser", Context.MODE_PRIVATE);
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.writeObject(graphArrayList);
                objOut.flush();
                objOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //read the arrayList from the already existing file
        FileInputStream fIn = null;
        try {
            fIn = getApplicationContext().openFileInput("graph.ser");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            graphArrayList = (ArrayList) objIn.readObject();
            objIn.close();
            fIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


//        if(getSharedPreferences("StartActivitySP",Context.MODE_PRIVATE).contains("appLaunchedFirstTime"))
//            startActivity(new Intent(this, FirstNodeActivity.class));

        //else
//        {
            setContentView(R.layout.activity_start);

            startButton = (Button) findViewById(R.id.startButton);
            startButton.setOnClickListener(this);

            spinner = (Spinner) findViewById(R.id.startSpinner);
            spinner.setOnItemSelectedListener(this);

            nameArrayList = new ArrayList<String>();
            for(Graph g: graphArrayList) {
                nameArrayList.add(g.getGraphName());
            }

            nameArrayList.add("Add New");

            // Initializing an ArrayAdapter
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nameArrayList);

            //wraps the array adapter on spinner
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
//        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.startButton)
        {
            if(graphName.equals("Add New")) {

                Log.e("Index s ",String.valueOf(nameArrayList.indexOf(graphName)));
                Intent intent = new Intent(this, FirstNodeActivity.class);
                intent.putExtra("indexOfGraph", (Integer) nameArrayList.indexOf(graphName)); //
                startActivity(intent);
            }
            else
            {
                Log.e("Index s ",String.valueOf(nameArrayList.indexOf(graphName)));
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("indexOfGraph", (Integer) nameArrayList.indexOf(graphName));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        graphName = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
