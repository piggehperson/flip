package com.piggeh.flipnew.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.piggeh.flipnew.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends ButtonFragment {


    public ListFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler);
        return view;
    }

}
