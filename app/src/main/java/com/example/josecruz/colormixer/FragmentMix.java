package com.example.josecruz.colormixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.util.zip.Inflater;


public class FragmentMix extends Fragment {

    int[] color1 = new int[3];
    int[] color2 = new int[3];
    int[] colorMixed = new int[3];

    Button BtnC1Mix;
    Button BtnC2Mix;

    FrameLayout shower;
    SetColor coloredLayout;

    SeekBar mixer;

    int BtnC1ID;
    int BtnC2ID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflated = inflater.inflate(R.layout.fragment_mix, container, false);

        color1 = ((MixerActivity)getActivity()).color1;
        color2 = ((MixerActivity)getActivity()).color2;

        return inflated;
    }

    @Override
    public void onStart() {
        super.onStart();

        BtnC1Mix = (Button) getActivity().findViewById(R.id.BTNcolor1Mixer);
        BtnC2Mix = (Button) getActivity().findViewById(R.id.BTNcolor2Mixer);

        mixer = (SeekBar) getActivity().findViewById(R.id.mixer);

        shower = (FrameLayout) getActivity().findViewById(R.id.shower);

        BtnC1Mix.setBackgroundColor(Color.rgb(color1[0], color1[1], color1[2]));
        BtnC2Mix.setBackgroundColor(Color.rgb(color2[0], color2[1], color2[2]));

        BtnC1ID = BtnC1Mix.getId();
        BtnC2ID = BtnC2Mix.getId();

        mixer.setMax(100);
        mixer.setProgress(50);

        setColoring(mixer.getProgress());
        coloredLayout = new SetColor(colorMixed[0], colorMixed[1],
                colorMixed[2], shower);

        BtnC1Mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pickColorFromMix(v);

            }
        });

        BtnC2Mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pickColorFromMix(v);

            }
        });

        mixer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                setColoring(progress);
                setColorOnFrame();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void setColoring(int p){

        colorMixed[0] = color2[0]*p/100 + color1[0]*(100-p)/100;
        colorMixed[1] = color2[1]*p/100 + color1[1]*(100-p)/100;
        colorMixed[2] = color2[2]*p/100 + color1[2]*(100-p)/100;

    }

    public void setColorOnFrame(){

        coloredLayout.updateColor(colorMixed[0], colorMixed[1], colorMixed[2]);

    }

    public void pickColorFromMix(View v){

        int id = v.getId();

        int[] idArray = {BtnC1ID, BtnC2ID};

        FragmentPick1 colorPicker1 = new FragmentPick1();

        Bundle args = new Bundle();

        args.putInt("ID", id);
        args.putIntArray("IdArray", idArray);
        args.putString("pFrag", "mixer");
        args.putIntArray("color1", color1);
        args.putIntArray("color2", color2);

        colorPicker1.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.screen, colorPicker1);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
