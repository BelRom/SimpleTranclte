package com.belrs.simpletranclte;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belrs.simpletranclte.Data.Word;

import java.util.List;


/**
 * Created by Роман on 09.01.2017.
 */

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<Word> mWords;

    String[] data = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

   // private final PublishSubject<String> onClickSubject = PublishSubject.create();

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bindData(data[position]);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }


}
