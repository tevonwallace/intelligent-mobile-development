package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button toastButton;
    private TextView textView;
    private Button countButton;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.setTitle("Hello Toast");

        this.toastButton = this.findViewById(R.id.toastButton);
        this.textView = this.findViewById(R.id.textView);
        this.countButton = this.findViewById(R.id.countButton);

        this.counter = 0;
    }

    private void addActionListeners() {
        this.toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello Toast!", Toast.LENGTH_SHORT).show();
            }
        });

        this.countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView();
            }
        });
    }

    private void updateTextView() {
        this.counter += 1;

        this.textView.setText(""+this.counter);
    }
}
