package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    FragmentA fragmentA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.main_textview);
        fragmentA = (FragmentA)getSupportFragmentManager().findFragmentById(R.id.a_fragment);
    }

    public void update_main(View view) {
        textView.setText("Hello TJHSST");
        ((Button)view).setText("This button has been clicked!");
        fragmentA.update("Text has been updated");
    }
}