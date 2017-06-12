package com.mygdx.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.utility.GameConstants;

public class PrefManager {

    private static Preferences pref;
    private final static String PREF_NAME = "mygame_pref";

    public static String master_volume_str = "master_volume";
    public static String bg_volume_str = "bg_volume";
    public static String sfx_volume_str = "sfx_volume";

    public static String hideCpads_str = "hide_cpads";

    public static String lockCpads_str = "lock_cpads";

    public static String LpadX_str = "Lpad_x";
    public static String LpadY_str = "Lpad_y";
    public static String RpadX_str = "Rpad_x";
    public static String RpadY_str = " Rpad_y";

    public PrefManager(){
        pref = Gdx.app.getPreferences(PREF_NAME);
        init();
    }

    private static void init(){
        boolean needToFlush = false;

        //Master Volume
        if(!pref.contains(master_volume_str)){
            pref.putFloat(master_volume_str, GameConstants.getDefaultMasterVolume());
            needToFlush = true;
        }
        //BG Volume
        if(!pref.contains(bg_volume_str)){
            pref.putFloat(bg_volume_str, GameConstants.getDefaultBgVolume());
            needToFlush = true;
        }

        //SFX Volume
        if(!pref.contains(sfx_volume_str)){
            pref.putFloat(sfx_volume_str, GameConstants.getDefaultSfxVolume());
            needToFlush = true;
        }

        //Hide Cpads
        if(!pref.contains(hideCpads_str)){
            pref.putBoolean(hideCpads_str, GameConstants.getDefaultHideCpads());
            needToFlush = true;
        }

        //Lock Cpads
        if(!pref.contains(lockCpads_str)){
            pref.putBoolean(lockCpads_str, GameConstants.getDefaultLockCpads());
            needToFlush = true;
        }

        //LpadX
        if(!pref.contains(LpadX_str)){
            pref.putFloat(LpadX_str, GameConstants.getDefaultLpadX());
            needToFlush = true;
        }

        //LpadY
        if(!pref.contains(LpadY_str)){
            pref.putFloat(LpadY_str, GameConstants.getDefaultLpadY());
            needToFlush = true;
        }

        //RpadX
        if(!pref.contains(RpadX_str)){
            pref.putFloat(RpadX_str, GameConstants.getDefaultRpadX());
            needToFlush = true;
        }

        //RpadY
        if(!pref.contains(RpadY_str)){
            pref.putFloat(RpadY_str, GameConstants.getDefaultRpadY());
            needToFlush = true;
        }

        if(needToFlush){
            pref.flush();
        }
    }

    public static void defaults(){
        pref.clear();
        init();
    }

    public static float getFloat(String key){
        return pref.getFloat(key);
    }

    public static boolean getBoolean(String key){
        return pref.getBoolean(key);
    }

    public static void setFloat(String key, float val){
        pref.putFloat(key, val);
        pref.flush();
    }

    public static void setBoolean(String key, boolean val){
        pref.putBoolean(key, val);
        pref.flush();
    }
}
