package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.utility.GameConstants;

public class MainMenuScreen extends BaseScreen{

    private MyLabel title;
    private MyTextButton newGame;
    private MyTextButton continueGame;
    private MyTextButton options;
    private MyTextButton editor;

    public MainMenuScreen(GeometryChaos gam){
        super(gam);

        float middleX = GameConstants.getVirtualWidth()/2;
        float newGameHeight = GameConstants.getVirtualHeight()*4f/10;
        float continueGameHeight = GameConstants.getVirtualHeight()*5.75f/10;
        int textButtonSize = 53;
        int buttonWidth = 300;
        int buttonHeight = 175;

        this.title = new MyLabel("Geomtry Chaos", 200, middleX,
                                 GameConstants.getVirtualHeight()*8.25f/10);

        this.newGame = new MyTextButton("New Game", textButtonSize, middleX, newGameHeight,
                                        buttonWidth, buttonHeight){
            @Override
            public void click(){
                game.newGame();
            }
        };
        this.continueGame = new MyTextButton("Continue", textButtonSize, middleX,
                                             continueGameHeight, buttonWidth, buttonHeight){
            @Override
            public void click(){
                game.goToScreen(ScreenManager.GAMESCREEN);
            }
        };
        this.options = new MyTextButton("Options", textButtonSize, middleX,
                                        GameConstants.getVirtualHeight()*2.25f/10,
                                        buttonWidth, buttonHeight){
            @Override
            public void click(){
                game.goToScreen(ScreenManager.OPTIONSSCREEN);
            }
        };

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            this.editor = new MyTextButton("Editor", 35, GameConstants.getGameWorldX2()-100,
                                           GameConstants.getGameWorldY() + 125f/2, 200,125){
                @Override
                public void click(){
                    game.goToScreen(ScreenManager.EDITOR);
                }
            };
        }

        this.stage.addActor(this.continueGame.getActor());
        this.stage.addActor(this.newGame.getActor());
        this.stage.addActor(this.options.getActor());
        if(this.editor != null){
            this.stage.addActor(this.editor.getActor());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(game.gameExists()){
            this.continueGame.setDisabled(false);
        }else{
            this.continueGame.setDisabled(true);
        }

        game.batch.begin();
        this.title.draw(game.batch);
        this.newGame.draw(game.batch);
        if(!this.continueGame.getIsDisabled())
            this.continueGame.draw(game.batch);
        this.options.draw(game.batch);
        if(this.editor != null){
            this.editor.draw(game.batch);
        }
        this.displayTime(game.batch);
        game.batch.end();
    }
}
