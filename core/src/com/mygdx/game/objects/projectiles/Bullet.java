package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.GameObjectManager;
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
        this.setSpriteScale(.4f);
        this.setVel(this.getVel().scl(this.spd));
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
}
