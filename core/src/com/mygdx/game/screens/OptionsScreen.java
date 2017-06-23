package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.utility.GameConstants;

public class OptionsScreen extends BaseScreen{

    private MyLabel title;

    private MyTextButton gameplay;
    private MyTextButton sound;
    private MyTextButton reset;
    private MyTextButton back;

    public OptionsScreen(final GeometryChaos gam){
        super(gam);

        float width = GameConstants.getVirtualWidth();
        float middleX = width/2;
        float height = GameConstants.getVirtualHeight();

        this.title = new MyLabel("Options", 160, middleX, height*8/10);

        this.gameplay = new MyTextButton("Gameplay", 49, middleX, height*6/10,280, 175){
            @Override
            public void click(){
                game.goToScreen(ScreenManager.GAMEOPTIONSCREEN);
            }
        };
        this.sound = new MyTextButton("Sound", 49, middleX, height*4/10, 280, 175){
            @Override
            public void click(){
                game.goToScreen(ScreenManager.SOUNDSCREEN);
            }
        };
        this.reset = new MyTextButton("Default", 35, width*4/5, height*2/10, 200, 125){
            @Override
            public void click(){
                PrefManager.defaults();
            }
        };
        this.back = new MyTextButton("Back", 35, width/2, height*2/10, 200, 125){
            @Override
            public void click(){
                game.goBack();
            }
        };

        this.stage.addActor(this.gameplay.getActor());
        this.stage.addActor(this.sound.getActor());
        this.stage.addActor(this.reset.getActor());
        this.stage.addActor(this.back.getActor());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        game.batch.begin();
        this.title.draw(game.batch);
        this.gameplay.draw(game.batch);
        this.sound.draw(game.batch);
        this.reset.draw(game.batch);
        this.back.draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }
}
