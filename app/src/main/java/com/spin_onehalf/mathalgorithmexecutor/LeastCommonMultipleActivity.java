package com.spin_onehalf.mathalgorithmexecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeastCommonMultipleActivity extends Activity {

    @Bind(R.id.lcm_a_input)EditText mAInput;
    @Bind(R.id.lcm_b_input)EditText mBInput;

    @Bind(R.id.output_box)TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_least_common_multiple);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.run_lcm_button)
    public void doLCM(){
        String aString = mAInput.getText().toString();
        String bString = mBInput.getText().toString();

        if(aString.matches("") || bString.matches("")){
            Toast.makeText(this, "You did not enter an input", Toast.LENGTH_LONG).show();
        }
        else{
            int a = Integer.parseInt(aString);
            int b = Integer.parseInt(bString);
            int result = MathAlgorithms.leastCommonMultiple(a, b);
            doOutput(result);
        }
    }

    public void doOutput(int result){
        mOutputText.setText("The LCM is: " + result);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_least_common_multiple, menu);
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
        return new Intent(from, LeastCommonMultipleActivity.class);
    }
}
