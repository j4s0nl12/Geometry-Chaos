package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.projectiles.BaseProjectile;

public class Dummy extends BaseEnemy{

    public Dummy(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite(AssetManager.getDummySprite());
    }

    @Override
    public void collision(BaseGameObject o){
        if(o.getClass().getSuperclass() == BaseProjectile.class){

        }
    }
}
