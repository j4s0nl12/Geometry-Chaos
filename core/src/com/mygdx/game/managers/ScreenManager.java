package com.mygdx.game.managers;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.screens.BaseScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager {

    final GeometryChaos game;

    Map<String, GameScreen> map;

    public final static int MAINMENUSCREEN = 0;
    public final static int GAMESCREEN = 1;

    private int lastScreen;
    private int currentScreen;

    private Array<BaseScreen> screenList;

    public ScreenManager(final GeometryChaos gam){
        game = gam;
        map = new HashMap();

        screenList = new Array();
        init();
    }

    private void init(){
        screenList.add(new MainMenuScreen(game));
        screenList.add(new GameScreen(game));

        //currentScreen = MAINMENUSCREEN;
        currentScreen = GAMESCREEN;
        goToScreen(currentScreen);
    }

    public BaseScreen getScreen(int screenIdx){
        return screenList.get(screenIdx);
    }

    public void goToScreen(int screenIdx){
        lastScreen = currentScreen;
        game.setScreen(getScreen(screenIdx));
        currentScreen = screenIdx;
    }

    public void startNewGame(){
        screenList.removeIndex(GAMESCREEN);
        screenList.insert(GAMESCREEN, new GameScreen(game));
        goToScreen(GAMESCREEN);
    }
}
