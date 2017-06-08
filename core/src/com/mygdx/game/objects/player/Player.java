package com.mygdx.game.objects.player;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.ui.CirclePad;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class Player extends BaseGameObject{

    private long lastShot;
    private long shotDelay;

    public Player(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite("Images/HumanTmp.png");

        this.setMaxAccel(8f);
        this.setAccelIncr(this.getMaxAccel()*4f);
    }

    @Override
    public void update(float delta){
        super.update(delta);

        this.simpleBorder();

    }

    public void shoot(){

    }
}
