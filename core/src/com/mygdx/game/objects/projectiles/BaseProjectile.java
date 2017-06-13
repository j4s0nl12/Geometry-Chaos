package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.utility.GameConstants;

public class BaseProjectile extends BaseGameObject{

    private BaseGameObject owner;

    private static int MAX_SPLITS = 3;
    private int splitDepth;

    private int bounces;

    private boolean pierces;
    private boolean splits;
    private boolean cluster;

    public BaseProjectile(Vector2 pos, Vector2 vel, BaseGameObject owner) {
        super(pos, vel);
        this.init(owner, 0, 3, false, false, false);
    }

    public BaseProjectile(Vector2 pos, Vector2 vel, BaseGameObject owner, int splitDepth,
                          int bounces, boolean pierces, boolean splits, boolean cluster){
        super(pos,vel);
        this.init(owner, splitDepth, bounces, pierces, splits, cluster);
    }

    private void init(BaseGameObject owner, int splitDepth, int bounces, boolean pierces, boolean splits, boolean cluster){
        this.owner = owner;

        this.splitDepth = splitDepth;
        this.bounces = bounces;
        this.pierces = pierces;
        this.splits = splits;
        this.cluster = cluster;
    }

    @Override
    public void update(float delta){
        super.update(delta);
        this.bounceBorder();
    }

    public BaseGameObject getOwner(){
        return this.owner;
    }

    @Override
    public void collision(BaseGameObject o){
        if(!o.equals(this.owner)){
            super.collision(o);
        }
    }

    public void bounceBorder(){
        if(this.bounces > 0){
            if(this.getPosX() <= GameConstants.getGameWorldX() ||
               this.getPosX() >= GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth()){
                this.setVelX(-this.getVelX());
                this.bounces--;
            }
            if(this.getPosY() <= GameConstants.getGameWorldY() ||
               this.getPosY() >= GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight()){
                this.setVelY(-this.getVelY());
                this.bounces--;
            }
        }else{
            this.destroy();
        }
    }
}
