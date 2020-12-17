package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView sportsTitle;
    private ImageView sportsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.configureProperties();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.sportsTitle = findViewById(R.id.titleDetail);
        this.sportsImage = findViewById(R.id.sportsImageDetail);

        this.sportsTitle.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0)).into(this.sportsImage);
    }
}
