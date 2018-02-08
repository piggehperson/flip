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
public class CoinFragment extends ButtonFragment {

    private TextView resultDisplay;

    private OnFragmentInteractionListener mListener;

    public CoinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coin, container, false);
        resultDisplay = (TextView) view.findViewById(R.id.coin_result_display);
        if (savedInstanceState != null){
            resultDisplay.setText(savedInstanceState.getString("display"));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", resultDisplay.getText().toString());
    }

    public void onButtonPressed() {
        int diceNumber = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        switch (diceNumber){
            default: resultDisplay.setText("Error");
                break;
            case 1: resultDisplay.setText(R.string.coin_result_heads);
                break;
            case 2:resultDisplay.setText(R.string.coin_result_tails);
            break;
        }
        //try to show ripple effect around number
        resultDisplay.dispatchDrawableHotspotChanged(
                resultDisplay.getWidth() / 2,
                resultDisplay.getHeight() / 2);
        resultDisplay.setPressed(true);
        resultDisplay.setPressed(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           /* throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
