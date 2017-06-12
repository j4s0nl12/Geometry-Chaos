package com.mygdx.game.screens;

import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.MyCheckBox;
import com.mygdx.game.utility.GameConstants;

public class GameOptionScreen extends BaseScreen{

    private FreeText title;
    private MyCheckBox hide;
    private MyCheckBox lock;
    private MyButton back;

    public GameOptionScreen(GeometryChaos gam) {
        super(gam);

        this.title = new FreeText(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*8.5f/10,
                                  160, "Gameplay Options");
        this.hide = new MyCheckBox(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*6.5f/10,
                                   "Hide CirclePads", PrefManager.hideCpads_str);
        this.lock = new MyCheckBox(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*4.75f/10,
                                   "Lock CirclePads", PrefManager.lockCpads_str);
        this.back = new MyButton(GameConstants.getVirtualWidth()/2, GameConstants.getVirtualHeight()*1.75f/10,
                200, 125, "Back");

        this.stage.addActor(this.hide.getCheckBox());
        this.stage.addActor(this.lock.getCheckBox());
        this.stage.addActor(this.back.getButton());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        if(this.hide.isChecked()){
            this.lock.setChecked(false);
        }

        if(this.back.isChecked()){
            this.back.clicked();
            game.goBack();
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
