package com.application.tevonwallace.intelligentmobiledevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {
    private TextView receivedMessageLabel;
    private TextView receivedMessage;
    private EditText messageTextField;
    private Button sendButton;

    public static final int TEXT_REQUEST = 1;
    public static String sentMessageString = "com.application.tevonwallace.intelligentmobiledevelopment.sentMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.receivedMessageLabel = this.findViewById(R.id.messageReceivedLabel);
        this.receivedMessage = this.findViewById(R.id.receivedMessage);

        this.messageTextField = this.findViewById(R.id.messageTextField);
        this.sendButton = this.findViewById(R.id.sendButton);
    }

    private void addActionListeners() {
        this.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondIntent();
            }
        });
    }

    private void showSecondIntent() {
        Intent intent = new Intent(this, ActivityThree.class);
        intent.putExtra(ActivityTwo.sentMessageString, this.messageTextField.getText().toString());

        this.messageTextField.setText("");

        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == TEXT_REQUEST) && (resultCode == RESULT_OK) ){
            String reply = data.getStringExtra(ActivityThree.replyMessageString);

            receivedMessageLabel.setVisibility(View.VISIBLE);
            receivedMessage.setText(reply);
            receivedMessage.setVisibility(View.VISIBLE);
        }
    }
}
