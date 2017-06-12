package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.MySlider;
import com.mygdx.game.utility.GameConstants;

public class SoundScreen extends BaseScreen{

    private FreeText title;

    private MySlider master;
    private MySlider bg;
    private MySlider sfx;

    private MyButton back;

    public SoundScreen(GeometryChaos gam) {
        super(gam);
        this.title = new FreeText(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*8.5f/10,
                                  160, "Sound");
        this.master = new MySlider(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*6.5f/10,
                                   "Master Volume:", PrefManager.master_volume_str);
        this.bg = new MySlider(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*4.75f/10,
                               "BG Volume:", PrefManager.bg_volume_str);
        this.sfx = new MySlider(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*3f/10,
                                "SFX Volume:", PrefManager.sfx_volume_str);
        this.back = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*1.75f/10,
                                 200, 125, "Back");

        this.stage.addActor(this.master.getSlider());
        this.stage.addActor(this.bg.getSlider());
        this.stage.addActor(this.sfx.getSlider());
        this.stage.addActor(this.back.getButton());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        if(this.back.isChecked()){
            this.back.clicked();
            game.goBack();
        }

        game.batch.begin();
        this.title.draw(game.batch);
        this.master.draw(game.batch);
        this.bg.draw(game.batch);
        this.sfx.draw(game.batch);
        this.back.draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }
}
