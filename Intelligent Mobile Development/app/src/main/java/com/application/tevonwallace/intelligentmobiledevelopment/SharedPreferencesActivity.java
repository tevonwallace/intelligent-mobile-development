package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {
    // Current count
    private int mCount = 0;
    // Current background color
    private int mColor;
    // Text view to display both count and color
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        this.configureProperties();
    }

    private void configureProperties() {
        // Initialize views, color
        this.mShowCountTextView = this.findViewById(R.id.count_textview);
        this.mColor = ContextCompat.getColor(this, R.color.default_background);

        this.mPreferences = (SharedPreferences) getSharedPreferences(this.sharedPrefFile, MODE_PRIVATE);

        // Restore the saved instance state.
        this.mCount = this.mPreferences.getInt(COUNT_KEY, 0);

        this.mShowCountTextView.setText(String.format("%s", mCount));

        this.mColor = this.mPreferences.getInt(COLOR_KEY, this.mColor);

        this.mShowCountTextView.setBackgroundColor(this.mColor);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();


    }

    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        this.mShowCountTextView.setBackgroundColor(color);
        this.mColor = color;
    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        this.mCount++;
        this.mShowCountTextView.setText(String.format("%s", this.mCount));
    }

    /**
     * Saves the instance state if the activity is restarted (for example,
     * on device rotation.) Here you save the values for the count and the
     * background color.
     *
     * @param outState The state data.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT_KEY, this.mCount);
        outState.putInt(COLOR_KEY, this.mColor);
    }

    /**
     * Handles the onClick for the Reset button. Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        // Reset count
        this.mCount = 0;
        this.mShowCountTextView.setText(String.format("%s", this.mCount));

        // Reset color
        this.mColor = ContextCompat.getColor(this, R.color.default_background);
        this.mShowCountTextView.setBackgroundColor(this.mColor);

        SharedPreferences.Editor preferencesEditor = this.mPreferences.edit();

        // Clear preferences
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}
