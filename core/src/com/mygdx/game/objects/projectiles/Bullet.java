package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.particles.RainbowParticle;

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
        this.setSprite(AssetManager.getBulletSprite(), .4f);
        this.setBoundingCircleScl(.5f);
        this.setVel(this.getVel().scl(this.spd));
        //this.thisDebug = true;
    }

    @Override
    public void split(BaseGameObject o){
        Bullet tmp1 = new Bullet(this.getCenterPos().cpy(), this.getVel().cpy().rotate(-15).nor(),
                this.getOwner(), this.getSplitDepth()+1, this.getBounces(),
                this.getPierces(), this.getSplits(), this.getCluster());
        tmp1.addToHitList(o);
        Bullet tmp2 = new Bullet(this.getCenterPos().cpy(), this.getVel().cpy().rotate(15).nor(),
                this.getOwner(), this.getSplitDepth()+1, this.getBounces(),
                this.getPierces(), this.getSplits(), this.getCluster());
        tmp2.addToHitList(o);
        GameObjectManager.add(tmp1);
        GameObjectManager.add(tmp2);
    }

    /*
    @Override
    public void collideParticleEffects(Vector2 collidePos){
        int numParticles = MathUtils.random(4,6);
        for(int i = 0; i < numParticles; i++){
            Vector2 dir = new Vector2(MathUtils.random(-1f,1f),MathUtils.random(-1f,1f)).nor();
            GameObjectManager.add(new RainbowParticle(collidePos.cpy(), dir.cpy(), 2000L));
        }
    }*/
}
