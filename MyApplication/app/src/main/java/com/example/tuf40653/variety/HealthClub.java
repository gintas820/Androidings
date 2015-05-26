package com.example.tuf40653.variety;

import android.app.ActionBar;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HealthClub extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_club);
        EditText editText = (EditText) findViewById(R.id.editMonths);
        editText.clearFocus();

        TextView textMonthly = (TextView) findViewById(R.id.monFee);
        TextView textTotal = (TextView) findViewById(R.id.total);
        textMonthly.setText((getString(R.string.monthFee)) + ": 40");
        textTotal.setText(getString(R.string.total) + ": 0");

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
        //ScrollView scrollView = (ScrollView) findViewById(R.id.encompassScroll);

        //Have the top and height of each of the layout groups available
        int topFra = frameLayout.getTop();      int heightFra = frameLayout.getHeight();
        int topRel = relativeLayout.getTop();   int heightRel = relativeLayout.getHeight();
        int topRel1 = relativeLayout1.getTop(); int heightRel1 = relativeLayout1.getHeight();
        int topRel2 = relativeLayout2.getTop(); int heightRel2 = relativeLayout2.getHeight();
        int topLin = linearLayout.getTop();     int heightLin = linearLayout.getHeight();
                                                //int heightScr = scrollView.getHeight();

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
    public void clickThings(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radGroup);
        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        TextView textMonthly = (TextView) findViewById(R.id.monFee);
        final TextView textTotal = (TextView) findViewById(R.id.total);
        EditText editText = (EditText) findViewById(R.id.editMonths);

        double amount = getMonthlyFee(radioButton.getText().toString());                //amount will keep monthly
        amount += getOptionsFee();
        double total = amount * Double.parseDouble(editText.getText().toString());      //total will keep amount * months

        textMonthly.setText((getString(R.string.monthFee)) + ": " + amount);
        textTotal.setText(getString(R.string.total) + ": " + total);

        final double total2 = total;


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textTotal.setText(getString(R.string.total) + ": " + total2);
            }

            @Override
            public void afterTextChanged(Editable s) {            }
        });

    }

    //Gets the monthly fee. Should be used by passing the text of the selected radio button
    private double getMonthlyFee(String s){
        double amount;
        switch (s){
            case "Standard Adult":
                amount = 40;
                break;
            case "Substandard Adult":
                amount = 240;
                break;
            case "Child":
                amount = 20;
                break;
            case "Student":
                amount = 25;
                break;
            case "Senior Citizen":
                amount = 30;
                break;
            case "Senior non-citizen":
                amount = 724;
                break;
            default:
                amount = 0;
        }
        return amount;
    }

    //Gets the options fee
    private  double getOptionsFee(){
        double fee = 0;

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.chkYoga);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.chkKarate);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.chkPersonal);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.chkCandle);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.chkBeach);

        if(checkBox1.isChecked()){
            fee += 10;
        }
        if(checkBox2.isChecked()){
            fee += 20;
        }
        if(checkBox3.isChecked()){
            fee += 50;
        }
        if(checkBox4.isChecked()){
            fee += 95;
        }
        if(checkBox5.isChecked()){
            fee += 125;
        }

        return fee;
    }
}
