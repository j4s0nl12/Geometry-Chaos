package com.mygdx.game.utility;

public class GameConstants {

    private static int VIRTUAL_WIDTH = 1920;
    private static int VIRTUAL_HEIGHT = 1080;
    private static int GAME_WORLD_WIDTH = 1600;
    private static int GAME_WORLD_HEIGHT = 900;

    public static int getVirtualWidth(){
        return VIRTUAL_WIDTH;
    }

    public static int getVirtualHeight(){
        return VIRTUAL_HEIGHT;
    }

    public static int getGameWorldX(){
        return (VIRTUAL_WIDTH - GAME_WORLD_WIDTH)/2;
    }

    public static int getGameWorldY(){
        return (VIRTUAL_HEIGHT - GAME_WORLD_HEIGHT)/2;
    }

    public static int getGameWorldWidth(){
        return GAME_WORLD_WIDTH;
    }

    public static int getGameWorldHeight(){
        return GAME_WORLD_HEIGHT;
    }
}
