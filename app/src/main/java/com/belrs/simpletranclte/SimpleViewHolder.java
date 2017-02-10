package com.belrs.simpletranclte;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Роман on 09.01.2017.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textView;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        textView = (TextView) itemView.findViewById(R.id.TW);
    }



    public void bindData(String data) {
        textView.setText(data);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "cliked", Toast.LENGTH_SHORT).show();
    }
}
