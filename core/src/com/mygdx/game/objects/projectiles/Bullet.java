package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.objects.BaseGameObject;

public class Bullet extends BaseProjectile {

    private float spd = 10f;

    public Bullet(Vector2 pos, Vector2 vel, BaseGameObject owner) {
        super(pos, vel, owner);
        this.init();
    }

    public Bullet(Vector2 pos, Vector2 vel, BaseGameObject owner, int splitDepth,
                  int bounces, boolean pierces, boolean splits, boolean cluster){
        super(pos, vel, owner, splitDepth, bounces, pierces, splits, cluster);
        this.init();
    }

    public void init(){
        this.setSprite(AssetManager.getBulletSprite());
        this.setSpriteScale(.5f);
        this.setVel(this.getVel().scl(this.spd));
    }
}
