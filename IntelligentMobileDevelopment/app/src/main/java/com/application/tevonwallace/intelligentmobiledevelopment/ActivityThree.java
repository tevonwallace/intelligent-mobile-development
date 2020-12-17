package com.application.tevonwallace.intelligentmobiledevelopment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityThree extends AppCompatActivity {
    private TextView receivedMessageLabel;
    private TextView receivedMessage;
    private EditText messageTextField;
    private Button sendButton;

    public static String replyMessageString = "com.application.tevonwallace.intelligentmobiledevelopment.replyMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.receivedMessageLabel = this.findViewById(R.id.messageReceivedLabel);
        this.receivedMessage = this.findViewById(R.id.receivedMessage);

        this.messageTextField = this.findViewById(R.id.messageTextField);
        this.sendButton = this.findViewById(R.id.sendButton);

        this.getDataFromIntent();
    }

    private void getDataFromIntent() {
        String data = getIntent().getStringExtra(ActivityTwo.sentMessageString);

        this.receivedMessageLabel.setVisibility(View.VISIBLE);
        this.receivedMessage.setText(data);
        this.receivedMessage.setVisibility(View.VISIBLE);
    }

    private void addActionListeners() {
        this.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissView();
            }
        });
    }

    private void dismissView() {
        Intent intent = new Intent();
        intent.putExtra(ActivityThree.replyMessageString, this.messageTextField.getText().toString());

        setResult(RESULT_OK, intent);
        this.finish();
    }
}
