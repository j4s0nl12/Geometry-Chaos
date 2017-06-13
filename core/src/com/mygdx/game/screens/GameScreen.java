package com.mygdx.game.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class GameScreen extends BaseScreen{

    private static GameObjectManager gom;

    private FreeText pause_title;
    private MyButton pause;
    private MyButton unpause;
    private MyButton mainmenu;
    private MyButton options;

    public GameScreen(GeometryChaos gam) {
        super(gam);
        gom = new GameObjectManager();
        gom.addPlayer(GameConstants.getVirtualWidth()/2,GameConstants.getVirtualHeight()/2);
        for(Actor a : gom.getPlayerController().getActors()){
            this.stage.addActor(a);
        }

        int button_width = 275;
        int button_height = 175;

        pause_title = new FreeText(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*8/10,
                                   160, "Paused");
        pause = new MyButton(GameConstants.getVirtualWidth()*24/25, GameConstants.getVirtualHeight()*93/100,
                           70, 75, 40, "||");
        unpause = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*6.25f/10,
                             button_width, button_height, "Continue");
        options = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*4.5f/10,
                             button_width, button_height, "Options");
        mainmenu = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*2.75f/10,
                              button_width, button_height, "MainMenu");

        this.stage.addActor(pause.getButton());
        this.stage.addActor(unpause.getButton());
        this.stage.addActor(options.getButton());
        this.stage.addActor(mainmenu.getButton());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        //Handle Buttons
        if(this.compareState(BaseScreen.STATE_RUN)) {
            if(this.pause.isChecked()){
                this.pause.clicked();
                this.pause();
            }
        }else if(this.compareState(BaseScreen.STATE_PAUSED)){
            if(this.unpause.isChecked()){
                this.unpause.clicked();
                this.resume();
            }else if(this.options.isChecked()){
                this.options.clicked();
                game.goToScreen(ScreenManager.OPTIONSSCREEN);
            }else if(this.mainmenu.isChecked()){
                this.mainmenu.clicked();
                game.goToScreen(ScreenManager.MAINMENUSCREEN);
            }
        }

        //Updates
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.update(delta);
        }

        game.batch.begin();
        gom.draw(game.batch);
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.getPlayerController().draw(game.batch);
            this.pause.draw(game.batch);
        }else if(this.compareState(BaseScreen.STATE_PAUSED)){
            this.pause_title.draw(game.batch);
            this.unpause.draw(game.batch);
            this.mainmenu.draw(game.batch);
            this.options.draw(game.batch);
        }
        this.displayTime(game.batch);
        game.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        //Change Cpad position
        Vector2 mPos = Utility.getUnprojectAt(screenX, screenY, 0);
        if(!PrefManager.getBoolean(PrefManager.lockCpads_str)){
            if (mPos.x < GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Lpad.isTouched()) {
                    gom.getPlayerController().Lpad.setPosition(mPos);
                    gom.getPlayerController().Lpad.fire(mPos, pointer);
                }
            } else if (mPos.x > GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Rpad.isTouched()) {
                    gom.getPlayerController().Rpad.setPosition(mPos);
                    gom.getPlayerController().Rpad.fire(mPos, pointer);
                }
            }
        }
        return true;
    }
}
