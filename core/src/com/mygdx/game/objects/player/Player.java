package com.mygdx.game.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.projectiles.BaseProjectile;
import com.mygdx.game.objects.projectiles.Bullet;

public class Player extends BaseGameObject{

    private long lastShot;
    private long shotDelay;

    //Power ups
    private int missles;
    private int spread;
    private int spd;
    //Projectile related
    private int bounces;
    private boolean pierces;
    private boolean splits;
    private boolean cluster;

    private int dmg = 10;

    public Player(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite(AssetManager.getPlayerSprite());
        this.setBoundingCircleScl(.5f);

        this.setMaxAccel(8f);
        this.setAccelIncr(this.getMaxAccel()*4f);

        this.missles = 0;
        this.spread = 1;
        this.spd = 3;//GameConstants.getMaxSpd();
        this.bounces = 2;//GameConstants.getMaxBounces();
        this.pierces = false;
        this.splits = true;
        this.cluster = false;

        this.shotDelay = 1000L;
        this.lastShot = System.currentTimeMillis() - this.shotDelay;

        //this.thisDebug = true;
    }

    @Override
    public void update(float delta){
        super.update(delta);

        this.shotDelay = 1000L/spd;

        this.simpleBorder();
    }

    public void shoot(){
        if(System.currentTimeMillis() > this.lastShot + this.shotDelay){
            Vector2 dir = getThisDir();
            Vector2 pos = this.getCenterPos().cpy();
            pos.add(dir.cpy().scl(this.getHeight()*2/3));
            this.spreadShot(2 + this.spread*2, 60);
            this.lastShot = System.currentTimeMillis();
        }
    }

    public void spreadShot(int shots, float angle){
        int half = (shots-1)/2;
        float a = angle/(shots-1);
        Vector2 dir = getThisDir();
        Vector2 pos = this.getCenterPos().cpy();
        pos.add(dir.cpy().scl(this.getHeight()*2/3));
        for(int i = -half; i <= half; i++){
            GameObjectManager.add(augmentedBullet(pos.cpy(), dir.cpy().rotate(a*i).nor()));
        }
    }

    public int getDamage(){
        return this.dmg;
    }

    public Bullet simpleBullet(Vector2 pos, Vector2 dir){
        return new Bullet(pos, dir, this);
    }

    public Bullet augmentedBullet(Vector2 pos, Vector2 dir){
        return new Bullet(pos, dir, this, 0, this.bounces, this.pierces, this.splits, this.cluster);
    }

    @Override
    public void collision(BaseGameObject o){
        if(o.getClass().getSuperclass() == BaseProjectile.class){
            BaseProjectile tmp = (BaseProjectile) o;
            if(tmp.getOwner() != this){
                super.collision(o);
            }
        }else{
            super.collision(o);
        }
    }
}
