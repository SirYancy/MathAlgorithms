package com.spin_onehalf.mathalgorithmexecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EuclidAlgorithm extends Activity {

    @Bind(R.id.euclid_a_input) EditText mAInput;
    @Bind(R.id.euclid_b_input) EditText mBInput;

    @Bind(R.id.output_box) TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euclid_algorithm);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.run_euclid_button)
    public void doEuclid(){
        String aString = mAInput.getText().toString();
        String bString = mBInput.getText().toString();

        if(aString.matches("") || bString.matches("")){
            Toast.makeText(this, "You did not enter any inputs", Toast.LENGTH_LONG).show();
        }
        else{
            int a = Integer.parseInt(aString);
            int b = Integer.parseInt(bString);
            if(a == 0 || b == 0){
                Toast.makeText(this, "Zero has no GCD with anyone.", Toast.LENGTH_LONG).show();
            }
            else {
                int result = euclidAlgorithm(a, b);
                doOutput(result);
            }
        }
    }

    public void doOutput(int result){
        outputText.setText("The GCD is: " + result);
    }

    /**
     * This method uses Euclid's Algorithm to calculate the GCD of any two integers.  Negative numbers
     * automatically converted to their absolute value.
     * @param a first integer
     * @param b second integer
     * @return the GCD of two integers.
     */
    private int euclidAlgorithm(int a, int b) {
        if(a < 0 || b< 0){
            a = Math.abs(a);
            b = Math.abs(b);
        }

        //swap the two variables if b > a
        if (b > a) {
            int swap = a;
            a = b;
            b = swap;
        }

        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        } else {
            return euclidAlgorithm(b, a % b);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_euclid_algorithm, menu);
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
        return new Intent(from, EuclidAlgorithm.class);
    }
}
