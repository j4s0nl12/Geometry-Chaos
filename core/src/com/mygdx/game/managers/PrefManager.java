package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.utility.GameConstants;

public class PrefManager {

    private static Preferences pref;
    private final static String PREF_NAME = "mygame_pref";

    private static float master_volume;
    private static String master_volume_str = "master_volume";
    private static float bg_volume;
    private static String bg_volume_str = "bg_volume";
    private static float sfx_volume;
    private static String sfx_volume_str = "sfx_volume";

    private static boolean hideCpads;
    private static String hideCpads_str = "hide_cpads";

    private static boolean lockCpads;
    private static String lockCpads_str = "lock_cpads";

    private static float LpadX;
    private static String LpadX_str = "Lpad_x";
    private static float LpadY;
    private static String LpadY_str = "Lpad_y";
    private static float RpadX;
    private static String RpadX_str = "Rpad_x";
    private static float RpadY;
    private static String RpadY_str = " Rpad_y";

    public PrefManager(){
        pref = Gdx.app.getPreferences(PREF_NAME);
        init();
    }

    private static void init(){
        boolean needToFlush = false;

        //Master Volume
        if(!pref.contains(master_volume_str)){
            master_volume = GameConstants.getDefaultMasterVolume();
            pref.putFloat(master_volume_str, master_volume);
            needToFlush = true;
        }else{
            master_volume = pref.getFloat(master_volume_str);
        }

        //BG Volume
        if(!pref.contains(bg_volume_str)){
            bg_volume = GameConstants.getDefaultBgVolume();
            pref.putFloat(bg_volume_str, bg_volume);
            needToFlush = true;
        }else{
            bg_volume = pref.getFloat(bg_volume_str);
        }

        //SFX Volume
        if(!pref.contains(sfx_volume_str)){
            sfx_volume = GameConstants.getDefaultSfxVolume();
            pref.putFloat(sfx_volume_str, sfx_volume);
            needToFlush = true;
        }else{
            sfx_volume = pref.getFloat(sfx_volume_str);
        }

        //Hide Cpads
        if(!pref.contains(hideCpads_str)){
            hideCpads = GameConstants.getDefaultHideCpads();
            pref.putBoolean(hideCpads_str, hideCpads);
            needToFlush = true;
        }else{
            hideCpads = pref.getBoolean(hideCpads_str);
        }

        //Lock Cpads
        if(!pref.contains(lockCpads_str)){
            lockCpads = GameConstants.getDefaultLockCpads();
            pref.putBoolean(lockCpads_str, lockCpads);
            needToFlush = true;
        }else{
            lockCpads = pref.getBoolean(lockCpads_str);
        }

        //LpadX
        if(!pref.contains(LpadX_str)){
            LpadX = GameConstants.getDefaultLpadX();
            pref.putFloat(LpadX_str, LpadX);
            needToFlush = true;
        }else{
            LpadX = pref.getFloat(LpadX_str);
        }

        //LpadY
        if(!pref.contains(LpadY_str)){
            LpadY = GameConstants.getDefaultLpadY();
            pref.putFloat(LpadY_str, LpadY);
            needToFlush = true;
        }else{
            LpadY = pref.getFloat(LpadY_str);
        }

        //RpadX
        if(!pref.contains(RpadX_str)){
            RpadX = GameConstants.getDefaultRpadX();
            pref.putFloat(RpadX_str, RpadX);
            needToFlush = true;
        }else{
            RpadX = pref.getFloat(RpadX_str);
        }

        //RpadY
        if(!pref.contains(RpadY_str)){
            RpadY = GameConstants.getDefaultRpadY();
            pref.putFloat(RpadY_str, RpadY);
            needToFlush = true;
        }else{
            RpadY = pref.getFloat(RpadY_str);
        }

        if(needToFlush){
            pref.flush();
        }
    }

    public static void defaults(){
        pref.clear();
        init();
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
