package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.main.GeometryChaos;

public class PrefManager {

    final GeometryChaos game;

    private static Preferences pref;
    private final static String PREF_NAME = "mygame_pref";

    private static float volume_offset = 0.2f;

    private static float master_volume;
    private static String master_volume_str = "master_volume";
    private static float bg_volume;
    private static String bg_volume_str = "bg_volume";
    private static float sfx_volume;
    private static String sfx_volume_str = "sfx_volume";

    private static boolean gameInProgress;

    public PrefManager(final GeometryChaos gam){
        game = gam;
        pref = Gdx.app.getPreferences(PREF_NAME);
        init();
    }

    private void init(){

        //Master Volume
        if(!pref.contains(master_volume_str)){

        }else{

        }

        //BG Volume
        if(!pref.contains(bg_volume_str)){

        }else{

        }

        //SFX Volume
        if(!pref.contains(sfx_volume_str)){

        }else{

        }
    }

    public boolean getIsGameInProgress(){
        return gameInProgress;
    }

    public String getPrefName(){
        return PREF_NAME;
    }
}
