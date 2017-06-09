package com.mygdx.game.objects.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.BaseGameObject;

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
