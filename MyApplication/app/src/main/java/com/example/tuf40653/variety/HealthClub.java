package com.example.tuf40653.variety;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class HealthClub extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_club);
        EditText editText = (EditText) findViewById(R.id.editMonths);
        editText.clearFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_club, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Close current window
    public void hcClose(View view){
        System.exit(0);
    }

    //Restore all entries to defaults
    public void hcClear(View view){
        //Retrieve all the changeable views by id and set them back to default values

    }

    //Will shrink the area it's currently in, and if it's already shrunk, expand back to original state
    public void shrinkClick(View view){
        //Select the parent element, and graciously clear it of all the things in it



    }

    //Needs to have a method that will be called whenever something is changed in the form
    //Upon change it needs to update the monthly fee and the total
}
