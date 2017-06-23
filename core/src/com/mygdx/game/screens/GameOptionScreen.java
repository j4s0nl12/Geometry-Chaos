package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.ui.MyCheckBox;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.utility.GameConstants;

public class GameOptionScreen extends BaseScreen{

    private MyLabel title;
    private MyCheckBox hide;
    private MyCheckBox lock;
    private MyTextButton back;

    public GameOptionScreen(GeometryChaos gam) {
        super(gam);

        float width = GameConstants.getVirtualWidth();
        float middleX = width/2;
        float height = GameConstants.getVirtualHeight();

        this.title = new MyLabel("Gameplay Options", 160, middleX, height*8.5f/10);
        this.hide = new MyCheckBox(middleX, height*6.5f/10,"Hide CirclePads", PrefManager.hideCpads_str);
        this.lock = new MyCheckBox(middleX, height*4.75f/10,"Lock CirclePads", PrefManager.lockCpads_str);
        this.back = new MyTextButton("Back", 25, middleX, height*1.75f/10, 200, 125){
            @Override
            public void click(){
                game.goBack();
            }
        };

        this.stage.addActor(this.hide.getCheckBox());
        this.stage.addActor(this.lock.getCheckBox());
        this.stage.addActor(this.back.getActor());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        if(this.hide.isChecked()){
            this.lock.setChecked(false);
        }

        game.batch.begin();
        this.title.draw(game.batch);
        this.hide.draw(game.batch);
        this.lock.draw(game.batch);
        this.back.draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }
}
