package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
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
        for(Actor a : gom.getPlayer().getActorList()){
            this.stage.addActor(a);
        }
    }

    @Override
    public void render(float delta){
        super.render(delta);
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.update(delta);
        }

        game.batch.begin();
        gom.draw(game.batch);
        for(Actor a : this.stage.getActors()){
            a.draw(game.batch,1f);
        }
        this.displayTime(game.batch);
        game.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Vector2 mPos = Utility.getUnprojectAt(screenX, screenY, 0);
            System.out.println(pointer);
            if (mPos.x < GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayer().Lpad.isTouched()) {
                    gom.getPlayer().Lpad.setPosition(mPos);
                    if(PrefManager.getHideCpads()) {
                        gom.getPlayer().Lpad.setVisibile(true);
                    }
                    System.out.println(mPos);
                    gom.getPlayer().Lpad.fire(mPos, pointer);
                }
            } else if (mPos.x > GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayer().Rpad.isTouched()) {
                    gom.getPlayer().Rpad.setPosition(mPos);
                    if(PrefManager.getHideCpads()) {
                        gom.getPlayer().Rpad.setVisibile(true);
                    }
                    System.out.println(mPos);
                    gom.getPlayer().Rpad.fire(mPos, pointer);
                }
            }
        }
        return true;
    }
}
