package com.example.mouhannad.notepad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;


public class Main extends Activity {
    final JSONArray jArr = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveButton = (Button)findViewById(R.id.saveButton);
        final EditText noteText = (EditText)findViewById(R.id.noteText);
        Button allNotesButton = (Button)findViewById(R.id.allNotesButton);





        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String note = pref2.getString("note", null);
        noteText.setText(note);






        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref",  MODE_PRIVATE);
                SharedPreferences.Editor editor2 = pref2.edit();
                editor2.putString("note", noteText.getText().toString());
                editor2.commit();
                JSONObject jObj = new JSONObject();

                    try {

                        jObj.put("note", noteText.getText().toString());



                        jArr.put(jObj);

                    } catch (Exception e) {
                        System.out.println("Error:" + e);
                    }

                   SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",  MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("notes", jArr.toString());
                editor.commit();

                }

        });

        allNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this,allNotes.class);
                startActivityForResult(intent,0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
