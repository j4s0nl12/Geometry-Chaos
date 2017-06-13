package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.enemies.BaseEnemy;
import com.mygdx.game.utility.GameConstants;

public class BaseProjectile extends BaseGameObject{

    private BaseGameObject owner;

    private Array<BaseGameObject> hitList;

    private int splitDepth;

    private int bounces;

    private boolean pierces;
    private boolean splits;
    private boolean cluster;

    public BaseProjectile(Vector2 pos, Vector2 vel, BaseGameObject owner) {
        super(pos, vel);
        this.init(owner, 0, 0, false, false, false);
    }

    public BaseProjectile(Vector2 pos, Vector2 vel, BaseGameObject owner, int splitDepth,
                          int bounces, boolean pierces, boolean splits, boolean cluster){
        super(pos,vel);
        this.init(owner, splitDepth, bounces, pierces, splits, cluster);
    }

    private void init(BaseGameObject owner, int splitDepth, int bounces, boolean pierces, boolean splits, boolean cluster){
        this.owner = owner;
        this.hitList = new Array();

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
        if(o.equals(this.owner) || o.getClass().getSuperclass() == BaseProjectile.class){

        }else if(o.getClass().getSuperclass() == BaseEnemy.class && !this.hitList.contains(o, true)){
            this.hitList.add(o);
            //super.collision(o);
            if(this.cluster && this.splitDepth == 0){
                this.cluster();
            }
            if(this.splits && this.splitDepth < GameConstants.getMaxSplits()){
                this.split(o);
            }
            if(!this.pierces){
                this.destroy();
            }
        }
    }

    public void addToHitList(BaseGameObject o){
        this.hitList.add(o);
    }

    public void split(BaseGameObject o){

    }

    public void cluster(){

    }

    public int getSplitDepth(){
        return this.splitDepth;
    }

    public int getBounces(){
        return this.bounces;
    }

    public boolean getPierces(){
        return this.pierces;
    }

    public boolean getSplits(){
        return this.splits;
    }

    public boolean getCluster(){
        return this.cluster;
    }

    public void bounceBorder(){
        if(this.getPosX() <= GameConstants.getGameWorldX() ||
           this.getPosX() >= GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth()){
            if(this.bounces > 0){
                this.setVelX(-this.getVelX());
                this.bounces--;
            }else{
                this.destroy();
            }
        } else if(this.getPosY() <= GameConstants.getGameWorldY() ||
                  this.getPosY() >= GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight()) {
            if(this.bounces > 0) {
                this.setVelY(-this.getVelY());
                this.bounces--;
            }else{
                this.destroy();
            }
        }
    }
}
