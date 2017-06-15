package com.mygdx.game.objects.particles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.BaseGameObject;

public class BaseParticle extends BaseGameObject{

    private long ttl;
    private long spawnedTime;

    public BaseParticle(Vector2 pos, Vector2 vel, long ttl) {
        super(pos, vel);
        this.setCanCollide(false);
        this.spawnedTime = System.currentTimeMillis();
        this.ttl = ttl;
    }

    @Override
    public void update(float delta){
        if(System.currentTimeMillis() >= this.spawnedTime + this.ttl){
            this.destroy();
        }
        super.update(delta);
    }

    @Override
    public void collision(BaseGameObject o){

    }
}
