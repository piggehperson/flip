package com.piggeh.flipnew.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.piggeh.flipnew.R;
import com.piggeh.flipnew.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends ButtonFragment {


    public ListFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Dummy text " + i);
        }// define an adapter
        listAdapter = new ListAdapter(input);
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onButtonPressed() {
        int diceNumber = ThreadLocalRandom.current().nextInt(0, listAdapter.getItemCount());
        Toast.makeText(getContext(), String.valueOf(diceNumber), Toast.LENGTH_SHORT).show();
        recyclerView.smoothScrollToPosition(diceNumber);
    }

}
