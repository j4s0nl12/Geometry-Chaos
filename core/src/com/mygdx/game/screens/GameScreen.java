package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.GameObjectManager;

public class GameScreen extends BaseScreen{

    private static GameObjectManager gom;

    public GameScreen(GeometryChaos gam) {
        super(gam);
        gom = new GameObjectManager();
        gom.addPlayer(GeometryChaos.getWidth()/2,GeometryChaos.getHeight()/2);
        for(Actor a : gom.getPlayer().getActorList()){
            this.stage.addActor(a);
        }
    }

    @Override
    public void render(float delta){
        super.render(delta);
        gom.update(delta);

        game.getBatch().begin();
        gom.draw(game.getBatch());
        this.displayTime(game.getBatch());
        game.getBatch().end();

        this.stage.draw();
    }
}
