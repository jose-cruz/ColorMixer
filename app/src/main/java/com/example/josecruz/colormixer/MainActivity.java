package com.example.josecruz.colormixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    View layout;
    String TAG = "JoseCruzTest";

    int [] color1 = {255, 255, 255};
    int [] color2 = {255, 255, 255};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.screen);

        Bundle args = new Bundle();
        FragmentMain mainFragment = new FragmentMain();

        if (layout != null){

            if (savedInstanceState != null){
                return;
            }

            //Load Main fragment
            args.putIntArray(FragmentMain.COLOR_1, color1);
            args.putIntArray(FragmentMain.COLOR_2, color2);

            mainFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.screen, mainFragment);
            transaction.commit();
            //*******************************************
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void mixColors(View v){

        Intent mixerIntent = new Intent(MainActivity.this, MixerActivity.class);

        mixerIntent.putExtra("Color1", color1);
        mixerIntent.putExtra("Color2", color2);

        startActivity(mixerIntent);

    }
//    public void mixColors(View v){
//
//        //load new Color Mixer Fragment
//        FragmentMix colorMixer1 = new FragmentMix();
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.screen, colorMixer1);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

}
