package com.mygdx.game.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.screens.BaseScreen;
import com.mygdx.game.screens.GameOptionScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.LevelEditor;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.OptionsScreen;
import com.mygdx.game.screens.SoundScreen;
import com.mygdx.game.utility.Utility;

public class ScreenManager {

    final GeometryChaos game;

    public final static int MAINMENUSCREEN = 0;
    public final static int GAMESCREEN = 1;
    public final static int OPTIONSSCREEN = 2;
    public final static int GAMEOPTIONSCREEN = 3;
    public final static int SOUNDSCREEN = 4;
    public final static int EDITOR = 5;

    private Array<BaseScreen> screenList;

    private BaseScreen mainmenu;
    private BaseScreen gamescreen;
    private BaseScreen options;
    private BaseScreen gameOptions;
    private BaseScreen sound;
    private BaseScreen editor;

    public ScreenManager(final GeometryChaos gam){
        game = gam;

        mainmenu = new MainMenuScreen(game);
        gamescreen = new GameScreen(game);
        options = new OptionsScreen(game);
        gameOptions = new GameOptionScreen(game);
        sound = new SoundScreen(game);
        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            editor = new LevelEditor(game);
        }

        screenList = new Array();

        screenList.add(mainmenu);
    }

    public void goToScreen(int screenIdx){
        BaseScreen screen = getScreen(screenIdx);
        if(!screenList.contains(screen, true)){
            screenList.add(screen);
        }
        game.setScreen(screen);
    }

    public void goBack(){
        screenList.pop();
        game.setScreen(screenList.peek());
    }

    public BaseScreen getScreen(int screenIdx){
        BaseScreen tmp = null;
        switch(screenIdx){
            case MAINMENUSCREEN:
                tmp = mainmenu;
                break;
            case GAMESCREEN:
                tmp = gamescreen;
                break;
            case OPTIONSSCREEN:
                tmp = options;
                break;
            case GAMEOPTIONSCREEN:
                tmp = gameOptions;
                break;
            case SOUNDSCREEN:
                tmp = sound;
                break;
            case EDITOR:
                tmp = editor;
                break;
            default:
                Utility.print("ScreenManager","Error: Unknown screen index: '" + screenIdx + "'.");
        }
        return tmp;
    }

    public void newGame(){
        if(gameExists()) {
            screenList.removeValue(gamescreen, true);
        }
        gamescreen = new GameScreen(game);
        goToScreen(GAMESCREEN);
    }

    public boolean gameExists(){
        return screenList.contains(gamescreen, true);
    }
}
