package com.mygdx.game.objects.projectiles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.enemies.BaseEnemy;
import com.mygdx.game.objects.particles.DamageNumber;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.utility.GameConstants;

public class BaseProjectile extends BaseGameObject{

    private BaseGameObject owner;

    private Array<BaseGameObject> hitList;

    private int splitDepth;

    private int bounces;

    private boolean pierces;
    private boolean splits;
    private boolean cluster;

    private float dmgMultiplier;

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

        this.dmgMultiplier = 0f;
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

        }else {
            if (o.getClass().getSuperclass() == BaseEnemy.class && !this.hitList.contains(o, true) && this.getOwner().getClass() == Player.class) {
                this.hitList.add(o);
                int dmg = (int)(((Player)this.getOwner()).getDamage() * this.dmgMultiplier);
                if(dmg != 0){
                    Vector2 pos = o.getCenterPos().cpy().add(new Vector2(MathUtils.random(-.5f, .5f), MathUtils.random(.2f)+1).scl(o.getHeight()*2/3));
                    GameObjectManager.add(new DamageNumber(pos, 1000L, dmg));
                }
                Vector2 dir = this.getCenterPos().cpy().sub(o.getCenterPos()).nor();
                float dst = this.getBoundingCircleRad();
                this.collideParticleEffects(this.getCenterPos().cpy().sub(dir.scl(dst)));
                if (this.cluster && this.splitDepth == 0) {
                    this.cluster();
                }
                if (this.splits && this.splitDepth < GameConstants.getMaxSplits()) {
                    this.split(o);
                }
                if (!this.pierces) {
                    this.destroy();
                }
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
        if(this.getPosX() <= GameConstants.getGameWorldX()){
            this.collideParticleEffects(new Vector2(GameConstants.getGameWorldX(),this.getCenterPos().y));
            if(this.bounces > 0){
                this.setVelX(-this.getVelX());
                this.bounces--;
            }else{
                this.destroy();
            }
        }else if(this.getPosX() >= GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth()){
            this.collideParticleEffects(new Vector2(GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth(), this.getCenterPos().y));
            if(this.bounces > 0){
                this.setVelX(-this.getVelX());
                this.bounces--;
            }else{
                this.destroy();
            }
        }
        if(this.getPosY() <= GameConstants.getGameWorldY()){
            this.collideParticleEffects(new Vector2(this.getCenterPos().x, GameConstants.getGameWorldY()));
            if(this.bounces > 0) {
                this.setVelY(-this.getVelY());
                this.bounces--;
            }else{
                this.destroy();
            }
        }else if(this.getPosY() >= GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight()) {
            this.collideParticleEffects(new Vector2(this.getCenterPos().x,GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight()));
            if(this.bounces > 0) {
                this.setVelY(-this.getVelY());
                this.bounces--;
            }else{
                this.destroy();
            }
        }
    }

    public void setDmgMultiplier(float dmg){
        this.dmgMultiplier = dmg;
    }

    public float getDmgMultiplier(){
        return this.dmgMultiplier;
    }

    public void collideParticleEffects(Vector2 collidePos){

    }
}
