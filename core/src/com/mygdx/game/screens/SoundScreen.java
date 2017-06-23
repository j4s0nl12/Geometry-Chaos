package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MySlider;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.utility.GameConstants;

public class SoundScreen extends BaseScreen{

    private MyLabel title;

    private MySlider master;
    private MySlider bg;
    private MySlider sfx;

    private MyTextButton back;

    public SoundScreen(GeometryChaos gam) {
        super(gam);

        float width = GameConstants.getVirtualWidth();
        float middleX = width/2;
        float height = GameConstants.getVirtualHeight();

        this.title = new MyLabel("Sound", 160, middleX, height*8.5f/10);
        this.master = new MySlider(middleX, height*6.5f/10, "Master Volume:", PrefManager.master_volume_str);
        this.bg = new MySlider(middleX, height*4.75f/10, "BG Volume:", PrefManager.bg_volume_str);
        this.sfx = new MySlider(middleX, height*3f/10,"SFX Volume:", PrefManager.sfx_volume_str);
        this.back = new MyTextButton("Back", 25, middleX, height*1.75f/10, 200, 125){
            @Override
            public void click(){
                game.goBack();
            }
        };

        this.stage.addActor(this.master.getSlider());
        this.stage.addActor(this.bg.getSlider());
        this.stage.addActor(this.sfx.getSlider());
        this.stage.addActor(this.back.getActor());
    }

    @Override
    public void render(float delta){
        super.render(delta);

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
