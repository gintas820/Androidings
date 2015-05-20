package com.example.tuf40653.variety;

import android.app.ActionBar;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


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
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.topGroup);
        Button btn = (Button) findViewById(R.id.shrink1);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.secondGroup);
        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.thirdGroup);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.fourthGroup);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fifthGroup);

        //Have the top and height of each of the layout groups available
        int topFra = frameLayout.getTop();      int heightFra = frameLayout.getHeight();
        int topRel = relativeLayout.getTop();   int heightRel = relativeLayout.getHeight();
        int topRel1 = relativeLayout1.getTop(); int heightRel1 = relativeLayout1.getHeight();
        int topRel2 = relativeLayout2.getTop(); int heightRel2 = relativeLayout2.getHeight();
        int topLin = linearLayout.getTop();     int heightLin = linearLayout.getHeight();

        if(btn.getText().toString().contains("Shrink")){
            btn.setText(R.string.expand1);
            frameLayout.setVisibility(View.INVISIBLE);

            //Take original measurements of the views and use them as references to move them around
            relativeLayout.setY(topFra + 30);
            relativeLayout1.setY(topFra + 30 + heightRel + 30);
            relativeLayout2.setY(topFra + 30 + heightRel + 30 + heightRel1 + 30);
            linearLayout.setY(topFra + 30 + heightRel + 30 + heightRel1 + 30 + heightRel2 + 30);
        }else{
            btn.setText(R.string.shrink1);
            frameLayout.setVisibility(View.VISIBLE);
            relativeLayout.setY(topRel);
            relativeLayout1.setY(topRel1);
            relativeLayout2.setY(topRel2);
            linearLayout.setY(topLin);
        }
    }

    //Needs to have a method that will be called whenever something is changed in the form
    //Upon change it needs to update the monthly fee and the total
}
