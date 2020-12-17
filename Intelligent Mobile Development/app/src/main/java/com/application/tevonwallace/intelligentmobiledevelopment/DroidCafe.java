package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DroidCafe extends AppCompatActivity {
    private ImageView donutImage, iceCreamImage, froyoImage;
    private FloatingActionButton floatingShoppingButton;

    private String orderItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid_cafe);

        this.configureProperties();
        this.addActionListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    // MARK: - Actions
    private void configureProperties() {
        this.donutImage = this.findViewById(R.id.donutImage);
        this.iceCreamImage = this.findViewById(R.id.iceCreamImage);
        this.froyoImage = this.findViewById(R.id.froyoImage);

        this.floatingShoppingButton = this.findViewById(R.id.floatingShoppingButton);
    }

    private void addActionListeners() {
        this.donutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDonutDescription();
            }
        });

        this.iceCreamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIceCreamDescription();
            }
        });

        this.froyoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFroyoDescription();
            }
        });

        this.floatingShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShoppingIntent();
            }
        });
    }

    private void showDonutDescription() {
        this.orderItem = getString(R.string.donutOrderMessage);
        Toast.makeText(this, this.orderItem, Toast.LENGTH_SHORT).show();
    }

    private void showIceCreamDescription() {
        this.orderItem = getString(R.string.iceCreamOrderMessage);
        Toast.makeText(this, this.orderItem, Toast.LENGTH_SHORT).show();
    }

    private void showFroyoDescription() {
        this.orderItem = getString(R.string.froyoOrderMessage);
        Toast.makeText(this, this.orderItem, Toast.LENGTH_SHORT).show();
    }

    private void showShoppingIntent() {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(OrderActivity.orderItem, this.orderItem);

        startActivity(intent);
    }
}
