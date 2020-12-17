package com.application.tevonwallace.intelligentmobiledevelopment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView wordItemView;
    final WordListAdapter wordListAdapter;

    public WordViewHolder(View itemView, WordListAdapter wordListAdapter) {
        super(itemView);
        this.wordItemView = itemView.findViewById(R.id.word);
        this.wordItemView.setOnClickListener(this);
        this.wordListAdapter = wordListAdapter;
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();

        String element = this.wordListAdapter.getLinkedList().get(mPosition);

        this.wordListAdapter.getLinkedList().set(mPosition, "Clicked! " + element);

        this.wordListAdapter.notifyDataSetChanged();
    }
}

public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final LinkedList<String> linkedList;
    private LayoutInflater layoutInflater;

    public WordListAdapter(Context context,  LinkedList<String> wordList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.linkedList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.wordlist_item, parent, false);

        return new WordViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.wordItemView.setText(this.linkedList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.linkedList.size();
    }

    public LinkedList<String> getLinkedList() {
        return this.linkedList;
    }
}