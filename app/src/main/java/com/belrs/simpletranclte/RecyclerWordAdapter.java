package com.belrs.simpletranclte;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.belrs.simpletranclte.Data.Word;

import java.util.List;


/**
 * Created by Роман on 09.01.2017.
 */

public class RecyclerWordAdapter extends RecyclerView.Adapter<RecyclerWordAdapter.ViewHolder> {

    private List<Word> mWords;
    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public RecyclerWordAdapter(List<Word> words, Context context) {
        mWords = words;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.item_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Word word = mWords.get(position);

        TextView firstItemColumn = holder.mFirstItemColumn;
        firstItemColumn.setText(word.getFerstWord());
//        TextView idItemColumn = holder.mIdItemColumn;
//        idItemColumn.setText(word.getId().toString());
        TextView secondItemColumn = holder.mSecondItemColumn;
        secondItemColumn.setText(word.getSecondWord());
        TextView countItemColumn = holder.mCountItemColumn;
        countItemColumn.setText((Integer.toString(word.getSolved())));

    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView mFirstItemColumn;
        private TextView mIdItemColumn;
        private TextView mSecondItemColumn;
        private TextView mCountItemColumn;

        public ViewHolder(View itemView) {
            super(itemView);
            mFirstItemColumn = (TextView) itemView.findViewById(R.id.firstItemColumn);
            mIdItemColumn = (TextView) itemView.findViewById(R.id.idItemColumn);
            mSecondItemColumn = (TextView) itemView.findViewById(R.id.secondItemColumn);
            mCountItemColumn = (TextView) itemView.findViewById(R.id.countItemColumn);
        }
    }


    public void swap(List<Word> word){
        mWords.clear();
        mWords.addAll(word);
        notifyDataSetChanged();
    }

}
