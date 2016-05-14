package com.example.josecruz.colormixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentMain extends Fragment {

    String TAG = "JoseCruzTest";
    final static String COLOR_1 = "color1";
    final static String COLOR_2 = "color2";

    int[] color1 = new int[3];
    int[] color2 = new int[3];

    int c1ID, c2ID;

    Button BtnC1;
    Button BtnC2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflated = inflater.inflate(R.layout.fragment_main, container, false);

        color1 = ((MainActivity)getActivity()).color1;
        color2 = ((MainActivity)getActivity()).color2;


//        Log.i(TAG, "OnCreateView Method: savedInstanceState checkup");
//
//        if(savedInstanceState != null){
//
//            Log.i(TAG, "Going into if statement, SIS not null");
//
//            color1 = savedInstanceState.getIntArray(COLOR_1);
//            color2 = savedInstanceState.getIntArray(COLOR_2);
//            Log.i(TAG, "OnCreateView Method: Color1: " + toString(color1));
//
//        }
//        else{
//            Log.i(TAG, "SIS is null for some fucking reason -_-");
//        }

        return inflated;
    }


    @Override
    public void onStart() {
        super.onStart();

        updateColors(color1, color2);

    }

    public void updateColors(int[] c1, int[] c2){

        BtnC1 = (Button) getActivity().findViewById(R.id.BTNcolor1);
        BtnC2 = (Button) getActivity().findViewById(R.id.BTNcolor2);

        c1ID = BtnC1.getId();
        c2ID = BtnC2.getId();

        //Setting color of the buttons
        Log.i(TAG, "Color1: " + toString(color1));
        Log.i(TAG, "Color2: " + toString(color2));
        BtnC1.setBackgroundColor(Color.rgb(color1[0], color1[1], color1[2]));
        BtnC2.setBackgroundColor(Color.rgb(color2[0], color2[1], color2[2]));

        BtnC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor(v);
            }
        });

        BtnC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor(v);
            }
        });
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

    public void pickColor(View v){

        //get ID of button that was pressed
        int id = v.getId();
        Log.i(TAG, "Button ID: " + id);
        int[] idArray = {c1ID, c2ID};

        //start new fragment where you get to pick the color
        FragmentPick1 colorPicker1 = new FragmentPick1();

        Bundle args = new Bundle();

        args.putInt("ID", id);
        args.putIntArray("IdArray", idArray);
        args.putString("pFrag", "main");
        args.putIntArray("color1", color1);
        args.putIntArray("color2", color2);

        colorPicker1.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.screen, colorPicker1);
        transaction.addToBackStack(null);
        transaction.commit();
        //**********************************************

    }


}
