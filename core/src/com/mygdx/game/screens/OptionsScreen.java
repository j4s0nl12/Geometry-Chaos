package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.utility.GameConstants;

public class OptionsScreen extends BaseScreen{

    private FreeText title;

    private MyButton gameplay;
    private MyButton sound;
    private MyButton reset;
    private MyButton back;

    public OptionsScreen(final GeometryChaos gam){
        super(gam);
        this.title = new FreeText(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*8/10,
                                  160, "Options");
        this.gameplay = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*6/10,
                                   280, 175, "Gameplay");
        this.sound = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*4/10,
                                280, 175, "Sound");
        this.reset = new MyButton(GameConstants.getVirtualWidth()*4/5, GameConstants.getVirtualHeight()*2/10,
                                  200, 125, "Default");
        this.back = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*2/10,
                               200, 125, "Back");

        this.stage.addActor(this.gameplay.getButton());
        this.stage.addActor(this.sound.getButton());
        this.stage.addActor(this.reset.getButton());
        this.stage.addActor(this.back.getButton());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        if(this.gameplay.isChecked()){
            this.gameplay.clicked();
            game.goToScreen(ScreenManager.GAMEOPTIONSCREEN);
        }else if(this.sound.isChecked()){
            this.sound.clicked();
            game.goToScreen(ScreenManager.SOUNDSCREEN);
        }else if(this.reset.isChecked()){
            this.reset.clicked();
            PrefManager.defaults();
        }else if(this.back.isChecked()){
            this.back.clicked();
            game.goBack();
        }

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
