package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;

public class Utility {

    public static boolean debug = true;
    //public static boolean debug = false;

    public static void print(String tag, String msg){
        Gdx.app.log(tag, msg);
    }
}
