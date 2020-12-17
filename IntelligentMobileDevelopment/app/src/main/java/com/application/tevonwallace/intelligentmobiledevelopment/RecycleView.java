package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class RecycleView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WordListAdapter wordListAdapter;
    private FloatingActionButton addRecycleItemViewButton;

    private final LinkedList<String> linkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        this.configureProperties();
        this.addActionListeners();
    }

    // MARK: - Actions
    private void configureProperties() {
        for (int index = 0; index < 15; index++) {
            this.linkedList.addLast("Word " + (index + 1));
        }

        this.recyclerView = findViewById(R.id.recyclerView);

        this.wordListAdapter = new WordListAdapter(this, this.linkedList);

        this.recyclerView.setAdapter(this.wordListAdapter);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.addRecycleItemViewButton = this.findViewById(R.id.addRecycleItemButton);
    }

    private void addActionListeners() {
        this.addRecycleItemViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecycleItemToView();
            }
        });
    }

    private void addRecycleItemToView() {
        int wordListSize = linkedList.size();

        this.linkedList.addLast("+ Word " + wordListSize);

        this.recyclerView.getAdapter().notifyItemInserted(wordListSize);

        this.recyclerView.smoothScrollToPosition(wordListSize);
    }
}
