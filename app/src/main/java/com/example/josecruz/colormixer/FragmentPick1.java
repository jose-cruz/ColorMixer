package com.example.josecruz.colormixer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FragmentPick1 extends Fragment {

    String TAG = "JoseCruzTest";

    int[] colorSelected = new int[3];
    int[] colorPassive = new int[3];

    EditText r;
    EditText g;
    EditText b;

    Button setColor;

    FrameLayout layoutToColor;
    SetColor coloredLayout;

    int id;
    int[] idArray;
    String pFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflated =  inflater.inflate(R.layout.fragment_pick1, container, false);

        idArray = this.getArguments().getIntArray("IdArray");
        id = this.getArguments().getInt("ID");
        pFrag = this.getArguments().getString("pFrag");

        Log.i(TAG, "FragmentPick1: onCreateView Method, id: " + id + " idArray: " + toString(idArray));
        if(id == idArray[0]){
            colorSelected = this.getArguments().getIntArray("color1");
            colorPassive = this.getArguments().getIntArray("color2");
            Log.i(TAG, "FragmentPick1: color picked is 1, Selected: " + toString(colorSelected) + " Passive: " + toString(colorPassive));
        }
        else{
            colorSelected = this.getArguments().getIntArray("color2");
            colorPassive = this.getArguments().getIntArray("color1");
            Log.i(TAG, "FragmentPick1: color picked is 2, Selected: " + toString(colorSelected) + " Passive: " + toString(colorPassive));

        }

        return inflated;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Reference to EditText in color picker XML
        r = (EditText) getActivity().findViewById(R.id.valueRed);
        g = (EditText) getActivity().findViewById(R.id.valueGreen);
        b = (EditText) getActivity().findViewById(R.id.valueBlue);
        setColor = (Button) getActivity().findViewById(R.id.BTNpickColor);
        layoutToColor = (FrameLayout) getActivity().findViewById(R.id.colorShower);


        r.setText(String.valueOf(colorSelected[0]));
        g.setText(String.valueOf(colorSelected[1]));
        b.setText(String.valueOf(colorSelected[2]));

        displayColor();

        r.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                checkNumbers();

                Log.i(TAG, "Fragment Pick: updating Color in SetColor object");
                coloredLayout.updateColor(colorSelected[0], colorSelected[1], colorSelected[2]);

                return true;
            }
        });

        g.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                checkNumbers();

                Log.i(TAG, "Fragment Pick: updating Color in SetColor object");
                coloredLayout.updateColor(colorSelected[0], colorSelected[1], colorSelected[2]);

                return true;
            }
        });

        b.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                checkNumbers();

                Log.i(TAG, "Fragment Pick: updating Color in SetColor object");
                coloredLayout.updateColor(colorSelected[0], colorSelected[1], colorSelected[2]);

                return true;
            }
        });

        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pFrag.equals("main")){
                    if (id == idArray[0]) {
                        Log.i(TAG, "FragmentPick1: colorSelected: " + colorSelected[0] + ", " + colorSelected[1] + ", " + colorSelected[2]);
                        Log.i(TAG, "FragmentPick1: colorPassive: " + colorPassive[0] + ", " + colorPassive[1] + ", " + colorPassive[2]);
                        ((MainActivity) getActivity()).color1 = colorSelected;
                        ((MainActivity) getActivity()).color2 = colorPassive;

                    } else {
                        Log.i(TAG, "FragmentPick1: colorSelected: " + colorSelected[0] + ", " + colorSelected[1] + ", " + colorSelected[2]);
                        Log.i(TAG, "FragmentPick1: colorPassive: " + colorPassive[0] + ", " + colorPassive[1] + ", " + colorPassive[2]);
                        ((MainActivity) getActivity()).color1 = colorPassive;
                        ((MainActivity) getActivity()).color2 = colorSelected;
                    }

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                manager.popBackStack();
                }
                else {

                    if (id == idArray[0]) {
                        Log.i(TAG, "FragmentPick1: colorSelected: " + colorSelected[0] + ", " + colorSelected[1] + ", " + colorSelected[2]);
                        Log.i(TAG, "FragmentPick1: colorPassive: " + colorPassive[0] + ", " + colorPassive[1] + ", " + colorPassive[2]);
                        ((MixerActivity) getActivity()).color1 = colorSelected;
                        ((MixerActivity) getActivity()).color2 = colorPassive;

                    } else {
                        Log.i(TAG, "FragmentPick1: colorSelected: " + colorSelected[0] + ", " + colorSelected[1] + ", " + colorSelected[2]);
                        Log.i(TAG, "FragmentPick1: colorPassive: " + colorPassive[0] + ", " + colorPassive[1] + ", " + colorPassive[2]);
                        ((MixerActivity) getActivity()).color1 = colorPassive;
                        ((MixerActivity) getActivity()).color2 = colorSelected;
                    }

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    manager.popBackStack();

                }

            }


        });

    }

    public void displayColor(){

        Log.i(TAG, "Fragment Pick: displaying the color, creating a new SetColor object");
        coloredLayout = new SetColor(colorSelected[0], colorSelected[1],
                colorSelected[2], layoutToColor);

    }

    public void checkNumbers(){

        String regexStr = "^[0-9]*$";

        if (r.getText().toString().equals("")){

            r.setText("0");

        }
        if (g.getText().toString().equals("")){

            g.setText("0");

        }
        if (b.getText().toString().equals("")){

            b.setText("0");

        }

        if(r.getText().toString().matches(regexStr)){
            colorSelected[0] = Integer.parseInt(r.getText().toString());
            colorSelected[0] = checkRage(colorSelected[0]);
            r.setText(String.valueOf(colorSelected[0]));
        }
        if(g.getText().toString().matches(regexStr)){
            colorSelected[1] = Integer.parseInt(g.getText().toString());
            colorSelected[1] = checkRage(colorSelected[1]);
            g.setText(String.valueOf(colorSelected[1]));
        }
        if(b.getText().toString().matches(regexStr)){
            colorSelected[2] = Integer.parseInt(b.getText().toString());
            colorSelected[2] = checkRage(colorSelected[2]);
            b.setText(String.valueOf(colorSelected[2]));
        }

        Log.i(TAG, "Color: " + toString(colorSelected));

    }
    public int checkRage(int x){

        while (x > 255)
            x -= 256;
        while (x < 0)
            x += 256;

        Log.i(TAG, "FragmentPick1, After color update: Selected: " + toString(colorSelected) + " Passive: " + toString(colorPassive));


        return x;
    }

    public String toString(int[] array){

        String output = "[";

        for (int i = 0; i < array.length; i++){
            if (i == array.length-1){
                output = output + array[i] + "]";
            }
            else{
                output = output + array[i] + ", ";
            }
        }

        return output;
    }
}
