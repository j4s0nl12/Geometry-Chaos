package com.mygdx.game.objects.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.projectiles.Bullet;

public class Player extends BaseGameObject{

    private long lastShot;
    private long shotDelay;

    public Player(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite(AssetManager.getPlayerSprite());

        this.setMaxAccel(8f);
        this.setAccelIncr(this.getMaxAccel()*4f);

        this.shotDelay = 1000L;
        this.lastShot = System.currentTimeMillis() - this.shotDelay;
    }

    @Override
    public void update(float delta){
        super.update(delta);

        this.simpleBorder();
    }

    public void shoot(){
        if(System.currentTimeMillis() > this.lastShot + this.shotDelay){
            Vector2 dir = getThisDir();
            Vector2 pos = this.getCenterPos().cpy();
            pos.add(dir.cpy().scl(this.getHeight()*2/3));
            GameObjectManager.add(new Bullet(pos, dir, this));
            this.lastShot = System.currentTimeMillis();
        }
    }
}
