package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.objects.projectiles.BaseProjectile;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class BaseEnemy extends BaseGameObject{

    private long wanderDelay;
    private long lastWander;

    private long aggroTimer;

    private Vector2 seekV;
    public boolean targetInSight;

    public BaseEnemy(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.targetInSight = false;
        this.aggroTimer = 0L;
        this.lastWander = System.currentTimeMillis();
    }
    @Override
    public void update(float delta){
        super.update(delta);
        if(this.aggroTimer > 0){
            this.aggroTimer -= 1000L * delta;
        }
    }

    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);
        if(Utility.debug && this.thisDebug) {
            batch.end();
            this.sr.setProjectionMatrix(GeometryChaos.camera.combined);
            this.sr.begin(ShapeRenderer.ShapeType.Line);
            this.sr.circle(this.seekV.x, this.seekV.y, 2f);
            this.sr.end();
            batch.begin();
        }
    }

    @Override
    public void collision(BaseGameObject o){
        super.collision(o);
        if(o.getClass().getSuperclass() == BaseProjectile.class){
            BaseProjectile tmp = (BaseProjectile) o;
            if(tmp.getOwner().getClass() == Player.class){
                if(!this.targetInSight) {
                    Vector2 dir = tmp.getVel().cpy().nor().scl(-this.getSightRad()*1.5f);
                    Vector2 pos = this.getCenterPos().cpy().add(dir);
                    if(pos.x <= GameConstants.getGameWorldX()){
                        pos.set(GameConstants.getGameWorldX(), pos.y);
                    }else if(pos.x >= GameConstants.getGameWorldX2()){
                        pos.set(GameConstants.getGameWorldX2(), pos.y);
                    }
                    if(pos.y <= GameConstants.getGameWorldY()){
                        pos.set(pos.x, GameConstants.getGameWorldY());
                    }else if(pos.y >= GameConstants.getGameWorldY2()){
                        pos.set(pos.x, GameConstants.getGameWorldY2());
                    }
                    this.seekV = pos;
                    this.aggroTimer = 5000L;
                    this.setWanderDelay(5000L);
                }
            }
        }
    }

    public void seek(Vector2 pos, float delta){
        this.seek(pos, delta, false);
    }

    public void seek(Vector2 pos, float delta, boolean rotate){
        Vector2 dir = pos.cpy().sub(this.getCenterPos());
        dir.nor();
        if(rotate)
            this.setRotation(dir.x, dir.y);
        dir.scl(this.getAccelIncr());
        this.accel(dir, delta);
        if(this.getBoundingCircle().contains(pos)){
            this.setVel(new Vector2());
        }
    }

    public void wander(){
        Vector2 dir = new Vector2(MathUtils.random(-1f, 1f), MathUtils.random(-1f, 1f));
        dir.nor().scl(MathUtils.random(this.getSightRad()/2, this.getSightRad()));
        float checkX = this.getCenterPos().x + dir.x;
        float checkY = this.getCenterPos().y + dir.y;
        if(checkX <= GameConstants.getGameWorldX() || checkX >= GameConstants.getGameWorldX2()){
            dir.set(-dir.x, dir.y);
        }
        if(checkY <= GameConstants.getGameWorldY() || checkY >= GameConstants.getGameWorldY2()){
            dir.set(dir.x, -dir.y);
        }
        this.seekV = this.getCenterPos().cpy().add(dir);
        this.lastWander = System.currentTimeMillis();
    }

    public Vector2 getSeekV(){
        return this.seekV;
    }

    public void setSeekV(Vector2 pos){
        this.seekV = pos;
    }

    public void setWanderDelay(long t){
        this.wanderDelay = t;
    }

    public void setRandomWanderDelay(long min, long max){
        this.wanderDelay = MathUtils.random(min, max);
    }

    public boolean canWander(){
        if(System.currentTimeMillis() >= this.lastWander + this.wanderDelay){
            return true;
        }
        return false;
    }

    public long getAggroTimer(){
        return this.aggroTimer;
    }
}
