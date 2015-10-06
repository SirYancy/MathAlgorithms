package com.spin_onehalf.mathalgorithmexecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrimeFactorFinder extends Activity {

    @Bind(R.id.factor_input)EditText input;

    @Bind(R.id.output_box)TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_factor_finder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.run_factorize_button)
    public void doFactorAlgorithm(){
        String inputString = input.getText().toString();

        if(inputString.matches("")){
            Toast.makeText(this, "You did not enter any input", Toast.LENGTH_LONG).show();
        }
        else{
            int in = Integer.parseInt(inputString);
            if(in == 0){
                Toast.makeText(this, "0 has no factors, prime or otherwise", Toast.LENGTH_LONG).show();
            }
            else{
                String result = doFactorize(in);
                doOutput(result);
            }
        }
    }

    public void doOutput(String result){
        output.setText(Html.fromHtml("The Prime Factorization is:<br>" + result));
    }

    public String doFactorize(int number){
        int n = number;
        List<Integer> factors = new ArrayList<>();
        for(int i = 2; i <= n / i; i++) {
            while( n % i == 0){
                factors.add(i);
                n /= i;
            }
        }
        if(n > 1)
            factors.add(n);

        return makeFactorString(factors);
    }

    private String makeFactorString(List<Integer> factors) {
        StringBuilder outputString = new StringBuilder();

        int currentFactor = 0;
        int factorCount = 1;

        for(int i = 0; i < factors.size() + 1; i++){
            int nextFactor;
            //This is the cheatiest bunch of cheating ever.
            try {
                nextFactor = factors.get(i);
            }catch(Exception e){
                nextFactor = currentFactor + 1;
            }
            //if this is the same as the previous number
            if(nextFactor == currentFactor){
                factorCount ++;
            }
            //otherwise
            else{
                if(currentFactor != 0) {
                    outputString.append(currentFactor);
                    outputString.append("<sup><small>");
                    outputString.append(factorCount);
                    outputString.append("</small></sup>");
                    if (i < factors.size()) {
                        outputString.append(" + ");
                    }
                    currentFactor = nextFactor;
                    factorCount = 1;
                }
                else{
                    currentFactor = nextFactor;
                }
            }
        }
        return outputString.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prime_factor_finder, menu);
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

    public static Intent getIntent(Activity from){
        return new Intent(from, PrimeFactorFinder.class);
    }
}
