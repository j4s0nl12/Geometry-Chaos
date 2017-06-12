package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.utility.GameConstants;

public class MainMenuScreen extends BaseScreen{

    private FreeText title;
    private MyButton newGame;
    private MyButton continueGame;
    private MyButton options;

    public MainMenuScreen(GeometryChaos gam){
        super(gam);

        float middleX = GameConstants.getVirtualWidth()/2;
        float newGameHeight = GameConstants.getVirtualHeight()*4f/10;
        float continueGameHeight = GameConstants.getVirtualHeight()*5.75f/10;

        this.title = new FreeText(middleX, GameConstants.getVirtualHeight()*8.25f/10,
                                  200, "Geometry Chaos");
        this.newGame = new MyButton(middleX, newGameHeight,
                                    300, 175, "New Game");
        this.continueGame = new MyButton(middleX, continueGameHeight,
                                         300, 175, "Continue");
        this.options = new MyButton(middleX, GameConstants.getVirtualHeight()*2.25f/10,
                                    300,175, "Options");

        this.stage.addActor(this.continueGame.getButton());
        this.stage.addActor(this.newGame.getButton());
        this.stage.addActor(this.options.getButton());
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(game.gameExists()){
            this.continueGame.setDisabled(false);
        }else{
            this.continueGame.setDisabled(true);
        }

        if (this.newGame.isChecked()) {
            this.newGame.clicked();
            game.newGame();
        }else if(this.continueGame.isChecked()){
            this.continueGame.clicked();
            game.goToScreen(ScreenManager.GAMESCREEN);
        }else if(this.options.isChecked()){
            this.options.clicked();
            game.goToScreen(ScreenManager.OPTIONSSCREEN);
        }

        game.batch.begin();
        this.title.draw(game.batch);
        this.newGame.draw(game.batch);
        if(!this.continueGame.isDisabled())
            this.continueGame.draw(game.batch);
        this.options.draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }

    @Override
    public void dispose(){
        this.title.dispose();
    }
}
