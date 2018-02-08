package com.piggeh.flipnew.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.piggeh.flipnew.R;

import org.w3c.dom.Text;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFragment extends android.support.v4.app.Fragment {
    public ButtonFragment() {
        // Required empty public constructor
    }

    public void onButtonPressed() {
        //Override this method
    }
}
