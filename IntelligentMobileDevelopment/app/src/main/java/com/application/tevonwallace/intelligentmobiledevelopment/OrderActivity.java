package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    private TextView textView;
    private RadioButton sameDayMessengerButton, nextDayGroundDeliveryButton, pickupButton;

    public static String orderItem = "com.application.tevonwallace.intelligentmobiledevelopment.orderItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.textView = this.findViewById(R.id.textView);

        this.textView.setText(getIntent().getStringExtra(OrderActivity.orderItem));

        this.sameDayMessengerButton = this.findViewById(R.id.sameDayMessenger);
        this.nextDayGroundDeliveryButton = this.findViewById(R.id.nextDayGroundDelivery);
        this.pickupButton = this.findViewById(R.id.pickup);
    }

    private void addActionListeners() {
        this.sameDayMessengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSameDayMessengerDescription();
            }
        });

        this.nextDayGroundDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextDayGroundDescription();
            }
        });

        this.pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickupDescription();
            }
        });
    }

    private void showSameDayMessengerDescription() {
        Toast.makeText(this, "Same day messenger service", Toast.LENGTH_SHORT).show();
    }

    private void showNextDayGroundDescription() {
        Toast.makeText(this, "Next day ground delivery", Toast.LENGTH_SHORT).show();
    }

    private void showPickupDescription() {
        Toast.makeText(this, "Pickup", Toast.LENGTH_SHORT).show();
    }
}
