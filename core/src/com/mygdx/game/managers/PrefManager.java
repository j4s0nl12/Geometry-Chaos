package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PrefManager {

    private static Preferences pref;
    private final static String PREF_NAME = "mygame_pref";

    private static float volume_offset = 0.2f;

    private static float master_volume;
    private static float default_master_volume = 1f;
    private static String master_volume_str = "master_volume";
    private static float bg_volume;
    private static float default_bg_volume = 1f;
    private static String bg_volume_str = "bg_volume";
    private static float sfx_volume;
    private static float default_sfx_volume = 1f;
    private static String sfx_volume_str = "sfx_volume";

    private static boolean hideCpads = true;
    private static boolean default_hideCpads = false;
    private static String hideCpads_str = "hide_cpads";

    private static boolean lockCpads = false;
    private static boolean default_lockCpads = false;
    private static String lockCpads_str = "lock_cpads";

    public PrefManager(){
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

    public static boolean getHideCpads(){
        return hideCpads;
    }

    public static void setHideCpads(boolean isHidden){
        hideCpads = isHidden;
    }

    public static boolean getLockCpads(){
        return lockCpads;
    }

    public static void setLockCpads(boolean isLocked){
        lockCpads = isLocked;
    }
}
