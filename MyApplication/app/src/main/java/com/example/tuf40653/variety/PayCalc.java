package com.example.tuf40653.variety;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class PayCalc extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_calc);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pay_calc, menu);
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

    //Should close the application
    public void onClose(View view) {
        System.exit(0);
    }

    //Calls the calculatePay method and sets the text accordingly
    public void calcClick(View view){
        EditText hours = (EditText) findViewById(R.id.Hours);
        EditText rate = (EditText) findViewById(R.id.Rate);
        TextView resultText = (TextView) findViewById(R.id.TextShowResult);
        if(Double.parseDouble(hours.getText().toString()) == 0 && Double.parseDouble(rate.getText().toString()) == 0){
            resultText.setText(R.string.zero_input);
        }else {
            double amount = Calc.calculatePay(Double.parseDouble(hours.getText().toString()), Double.parseDouble(rate.getText().toString()));
            double net = amount - (Calc.calculateSIT(amount) + Calc.calculateFIT(amount) + Calc.calculateSS(amount) + Calc.calculateMed(amount));
            resultText.setText("Gross Pay:" + String.format("%.2f", Double.parseDouble(String.valueOf(amount))) +
                    "\nState Income Tax Deduction:" + String.format("%.2f", Double.parseDouble(String.valueOf(Calc.calculateSIT(amount)))) +
                    "\nFederal Income Tax Deduction:" + String.format("%.2f", Double.parseDouble(String.valueOf(Calc.calculateFIT(amount)))) +
                    "\nSocial Security:" + String.format("%.2f", Double.parseDouble(String.valueOf(Calc.calculateSS(amount)))) +
                    "\nMedicare:" + String.format("%.2f", Double.parseDouble(String.valueOf(Calc.calculateMed(amount)))) +
                    "\nNet Pay:" + String.format("%.2f", Double.parseDouble(String.valueOf(net))));
        }
    }

    //Will clear the current entries in the EditText fields
    public void clearClick(View view){
        EditText hrs = (EditText) findViewById(R.id.Hours);
        EditText rate = (EditText) findViewById(R.id.Rate);
        TextView res = (TextView) findViewById(R.id.TextShowResult);
        hrs.setText(R.string.zeros);
        rate.setText(R.string.zeros);
        res.setText(R.string.result);
    }
}