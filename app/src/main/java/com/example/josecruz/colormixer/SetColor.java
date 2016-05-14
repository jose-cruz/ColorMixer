package com.example.josecruz.colormixer;

import android.graphics.Color;
import android.widget.FrameLayout;

/**
 * Created by Jose Cruz on 3/12/2016.
 */
public class SetColor {

    int red;
    int green;
    int blue;
    FrameLayout frame;

    public SetColor(int r, int g, int b, FrameLayout f){

        setRed(r);
        setGreen(g);
        setBlue(b);
        setFrameLayout(f);

        updateColor(red, green, blue);

    }

    public void updateColor(int x, int y, int z){

        frame.setBackgroundColor(Color.rgb(x, y, z));

    }

    public void setRed(int r){

        red = r;

    }

    public void setGreen(int g){

        green = g;

    }

    public void setBlue(int b){

        blue = b;

    }

    public void setFrameLayout(FrameLayout f){

        frame = f;

    }

    public int getRed(){

        return red;

    }

    public int getGreen(){

        return green;

    }

    public int getBlue(){

        return blue;

    }

    public FrameLayout getFrameLayout(){

        return frame;

    }

}
