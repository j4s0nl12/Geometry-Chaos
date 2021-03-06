package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.BaseGameObject;

public class GameConstants {

    private static int VIRTUAL_WIDTH = 1920;
    private static int VIRTUAL_HEIGHT = 1080;
    private static int GAME_WORLD_WIDTH = 1600;
    private static int GAME_WORLD_HEIGHT = 900;

    private static float VOLUME_OFFSET = .2f;
    private static float DEFAULT_MASTER_VOLUME = 1f;
    private static float DEFAULT_BG_VOLUME = 1f;
    private static float DEFAULT_SFX_VOLUME = 1f;

    private static Color DEFAULT_THEME_COLOR = new Color(0, 204f/255, 204f/255, 1);

    private static boolean DEFAULT_HIDECPADS = false;
    private static boolean DEFAULT_LOCKCPADS = true;

    private static float DEFAULT_LPADX = VIRTUAL_WIDTH * .11f;
    private static float DEFAULT_LPADY = VIRTUAL_HEIGHT * .2f;
    private static float DEFAULT_RPADX = VIRTUAL_WIDTH * .89f;
    private static float DEFAULT_RPADY = VIRTUAL_HEIGHT * .2f;

    private static int MAX_SPLITS = 3;
    private static int MAX_BOUNCES = 3;
    private static int MAX_SPREAD = 4;
    private static int MAX_SPD = 10;

    public static int getVirtualWidth(){
        return VIRTUAL_WIDTH;
    }
    public static int getVirtualHeight(){
        return VIRTUAL_HEIGHT;
    }
    public static int getGameWorldWidth(){
        return GAME_WORLD_WIDTH;
    }
    public static int getGameWorldHeight(){
        return GAME_WORLD_HEIGHT;
    }
    public static int getGameWorldX(){
        return (VIRTUAL_WIDTH - GAME_WORLD_WIDTH)/2;
    }
    public static int getGameWorldY(){
        return (VIRTUAL_HEIGHT - GAME_WORLD_HEIGHT)/2;
    }
    public static int getGameWorldX2(){
        return getGameWorldX() + GAME_WORLD_WIDTH;
    }
    public static int getGameWorldY2(){
        return getGameWorldY() + GAME_WORLD_HEIGHT;
    }

    public static float getVolumeOffset(){
        return VOLUME_OFFSET;
    }
    public static float getDefaultMasterVolume(){
        return DEFAULT_MASTER_VOLUME;
    }
    public static float getDefaultBgVolume(){
        return DEFAULT_BG_VOLUME;
    }
    public static float getDefaultSfxVolume(){
        return DEFAULT_SFX_VOLUME;
    }

    public static Color getDefaultThemeColor(){
        return DEFAULT_THEME_COLOR;
    }

    public static boolean getDefaultHideCpads(){
        return DEFAULT_HIDECPADS;
    }
    public static boolean getDefaultLockCpads(){
        return DEFAULT_LOCKCPADS;
    }

    public static float getDefaultLpadX(){
        return DEFAULT_LPADX;
    }
    public static float getDefaultLpadY(){
        return DEFAULT_LPADY;
    }
    public static float getDefaultRpadX(){
        return DEFAULT_RPADX;
    }
    public static float getDefaultRpadY(){
        return DEFAULT_RPADY;
    }

    public static int getMaxSplits(){
        return MAX_SPLITS;
    }
    public static int getMaxBounces(){
        return MAX_BOUNCES;
    }
    public static int getMaxSpread(){
        return MAX_SPREAD;
    }
    public static int getMaxSpd(){
        return MAX_SPD;
    }
}
