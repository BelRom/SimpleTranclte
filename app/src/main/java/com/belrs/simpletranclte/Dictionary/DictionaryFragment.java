package com.belrs.simpletranclte.Dictionary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belrs.simpletranclte.R;
import com.belrs.simpletranclte.WordLab;


public class DictionaryFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerWordAdapter mRecyclerWordAdapter;
    private WordLab mWordLab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWordLab = WordLab.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        mRecyclerWordAdapter = new RecyclerWordAdapter(mWordLab.getWord(),getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mRecyclerWordAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

}
