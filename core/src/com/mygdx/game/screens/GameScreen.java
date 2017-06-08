package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class GameScreen extends BaseScreen{

    private static GameObjectManager gom;

    public GameScreen(GeometryChaos gam) {
        super(gam);
        gom = new GameObjectManager();
        gom.addPlayer(GameConstants.getVirtualWidth()/2,GameConstants.getVirtualHeight()/2);
        for(Actor a : gom.getPlayerController().getActors()){
            this.stage.addActor(a);
        }
    }

    @Override
    public void render(float delta){
        super.render(delta);
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.update(delta);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            PrefManager.setLockCpads(!PrefManager.getLockCpads());
        }

        game.batch.begin();
        gom.draw(game.batch);
        gom.getPlayerController().draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        //Change Cpad position
        Vector2 mPos = Utility.getUnprojectAt(screenX, screenY, 0);
        if(!PrefManager.getLockCpads()){
            if (mPos.x < GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Lpad.isTouched()) {
                    gom.getPlayerController().Lpad.setPosition(mPos);
                    if(PrefManager.getHideCpads()) {
                        gom.getPlayerController().Lpad.setVisible(true);
                    }
                    gom.getPlayerController().Lpad.fire(mPos, pointer);
                }
            } else if (mPos.x > GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Rpad.isTouched()) {
                    gom.getPlayerController().Rpad.setPosition(mPos);
                    if(PrefManager.getHideCpads()) {
                        gom.getPlayerController().Rpad.setVisible(true);
                    }
                    gom.getPlayerController().Rpad.fire(mPos, pointer);
                }
            }
        }
        return true;
    }
}
