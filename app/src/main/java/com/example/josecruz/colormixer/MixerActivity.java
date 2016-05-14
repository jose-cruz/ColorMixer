package com.example.josecruz.colormixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MixerActivity extends Activity {

    View layout;
    String TAG = "JoseCruzTest";

    int [] color1 = {255, 255, 255};
    int [] color2 = {255, 255, 255};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixer);

        layout = findViewById(R.id.mixerScreen);

        Bundle colors = getIntent().getExtras();

        color1 = colors.getIntArray("Color1");
        color2 = colors.getIntArray("Color2");

        Bundle args = new Bundle();
        FragmentMix mixFragment = new FragmentMix();

        if (layout != null) {

            if (savedInstanceState != null) {
                return;
            }

            //Load Main fragment
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.mixerScreen, mixFragment);
            transaction.commit();
            //*******************************************
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mixer, menu);
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
}
