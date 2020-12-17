package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Week5Activity extends AppCompatActivity {
    private EditText websiteText, locationText, shareText;
    private Button openWebsiteButton, openLocationButton, shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week5);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        this.websiteText = this.findViewById(R.id.websiteText);
        this.locationText = this.findViewById(R.id.locationText);
        this.shareText = this.findViewById(R.id.shareText);

        this.openWebsiteButton = this.findViewById(R.id.openWebsiteButton);
        this.openLocationButton = this.findViewById(R.id.openLocationButton);
        this.shareButton = this.findViewById(R.id.shareButton);
    }

    private void addActionListeners() {
        this.openWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite();
            }
        });

        this.openLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation();
            }
        });

        this.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShareText();
            }
        });
    }

    private void openWebsite() {
        String url = websiteText.getText().toString();

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    private void openLocation() {
        String loc = locationText.getText().toString();

        Uri uri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    private void openShareText() {
        String txt = shareText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(R.string.shareText).setText(txt).startChooser();
    }
}
