package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.objects.BaseGameObject;

public class Bullet extends BaseProjectile {

    private float spd = 10f;

    public Bullet(Vector2 pos, Vector2 vel, BaseGameObject owner) {
        super(pos, vel, owner);
        this.setSprite(AssetManager.getBulletSprite());
        this.setVel(this.getVel().scl(this.spd));
    }
}
