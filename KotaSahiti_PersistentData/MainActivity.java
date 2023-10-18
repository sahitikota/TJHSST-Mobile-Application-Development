package com.kotasahiti.persistentdata;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = "com.kotasahiti.persistentdata.sharedprefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button bRight, bLeft;
    TextView tLeft, tRight;
    SeekBar seekBar;
    TextView[] views;
    ConstraintLayout layout;
    int ids[];
    int count = 0;
    LifecycleData currentRun, lifeTime;
    TextView currentRunTV, lifeTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ids = new int[] {R.layout.layout1, R.layout.layout2, R.layout.layout3, R.layout.layout4, R.layout.layout5, R.layout.layout6, R.layout.layout7};
        bRight=findViewById(R.id.botright_button);
        bLeft=findViewById(R.id.botleft_button);
        tLeft=findViewById(R.id.topleft_txtvw);
        tRight=findViewById(R.id.topright_txtvw);
        seekBar=findViewById(R.id.seekbar);
        views= new TextView[]{bLeft, bRight, tLeft, tRight};
        layout = findViewById(R.id.activity_main_layout);
        tLeft.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        bRight.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //instantiate classes
        currentRun = new LifecycleData();
        currentRun.duration="Current Run";
        //get LifecycleData from sharedPrefrences as String
        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        //Instantiate new LifecycleData if empty string
        //else convert JSON to LifecycleData object
        if (lifecycleDataAsString.equals("")){
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime Data";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        //instantiate TextViews
        currentRunTV = findViewById(R.id.current);
        lifeTimeTV = findViewById(R.id.lifetime);
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (TextView x: views) {x.setTextSize(progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress=seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar snackbar = Snackbar.make(layout,"Font size changed to " +  seekBar.getProgress()+"SP",Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO",
                        // UNDO event listener
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                seekBar.setProgress(lastProgress);
                                for (TextView x: views) {x.setTextSize(lastProgress);}
                                Snackbar.make(layout,"Font size reverted back to "+ lastProgress + "SP",Snackbar.LENGTH_LONG).show();
                            }
                        });
                snackbar.setActionTextColor(Color.BLUE);
                View snackBarView = snackbar.getView();
                snackbar.show();
            }
        });
        setInitialValues();
    }

    //convert lifetime to String and store in SharedPreferences
    public void storeData(){
        editor.putString("lifetime",lifeTime.toJSON()).apply();
    }
    //display data on TextViews
    public void displayData(){
        currentRunTV.setText(currentRun.toString());
        lifeTimeTV.setText(lifeTime.toString());
    }

    public void updateCount(String currentEnclosingMethod){
        //pass name to LifecycleData to update count
        lifeTime.updateEvent(currentEnclosingMethod);
        currentRun.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }


    @Override
    protected void onStart() {
        super.onStart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
        setInitialValues();
    }
    @Override
    protected void onPause(){
        super.onPause();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onStop(){
        super.onStop();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            count = (++count)%ids.length;
            setContentView(ids[count]);
        }
        return true;
    }

    private void setInitialValues() {
        for (TextView x: views) {
            x.setText(sharedPreferences.getString(x.getTag().toString(),"0") );
        }
        seekBar.setProgress(30);
    }

    @Override
    public void onClick(View v) {
        TextView x = (TextView) v;
        x.setText("" + (Integer.parseInt(x.getText().toString()) + 1));
        editor.putString(x.getTag().toString(), x.getText().toString());
        editor.apply();
    }
}