package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class BaseGameObject implements Comparable<BaseGameObject>{

    private String TAG = this.getClass().getSimpleName();

    private Vector2 pos;
    private Vector2 vel;
    private float maxAccel;
    private float accelIncr;

    private Sprite sprite;
    private float spriteScl;

    private float boundingCircleScl;

    private Circle sightCircle;

    private boolean toDestroy;

    private boolean canCollide;

    public ShapeRenderer sr;
    public boolean thisDebug;

    public BaseGameObject(Vector2 pos, Vector2 vel){
        this.pos = pos;
        this.vel = vel;

        //Default
        this.maxAccel = 0;
        this.accelIncr = 0;
        this.spriteScl = 1f;
        this.boundingCircleScl = 1f;

        this.canCollide = true;

        this.toDestroy = false;

        this.sr = new ShapeRenderer();
        this.thisDebug = false;
    }

    //FUNCTIONS=====================================================================================
    public void update(float delta){
        this.pos.add(this.vel);
        if(this.sprite != null)
            this.setSpritePos(this.pos);
        if(this.sightCircle != null){
            Vector2 center = this.getCenterPos();
            this.sightCircle.setPosition(center.x, center.y);
        }
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
        if(Utility.debug && this.thisDebug){
            batch.end();
            this.sr.setProjectionMatrix(GeometryChaos.camera.combined);
            this.sr.begin(ShapeRenderer.ShapeType.Line);
            Circle c = this.getBoundingCircle();
            this.sr.circle(c.x, c.y, c.radius);
            if(this.sightCircle != null){
                this.sr.circle(this.sightCircle.x, this.sightCircle.y, this.sightCircle.radius);
            }
            this.sr.end();
            batch.begin();
        }
    }

    public void collision(BaseGameObject o){
        if (Utility.debug && this.thisDebug)
            Utility.print(this.TAG + "(Debug)", "Collided with [" + o.TAG + "]");
    }

    public boolean checkCollision(BaseGameObject o){
        if(this.getBoundingCircle().overlaps(o.getBoundingCircle())){
            return true;
        }
        return false;
    }

    public void inSightAction(BaseGameObject o){
        if (Utility.debug && this.thisDebug)
            Utility.print(this.TAG + "(Debug)", "[" + o.TAG + "] in sight!");
    }

    public boolean checkInSight(BaseGameObject o){
        if(this.sightCircle != null) {
            if (this.sightCircle.overlaps(o.getBoundingCircle())) {
                return true;
            }
        }
        return false;
    }

    public void accel(Vector2 acc, float delta){
        this.vel.add(acc.cpy().scl(delta));
        if(this.vel.dot(this.vel) > Math.pow(this.maxAccel,2)){
            this.vel.nor().scl(this.maxAccel);
        }
    }

    public void applyFrict(float f, float delta){
        Vector2 old = this.vel.cpy();
        Vector2 frict = this.vel.cpy().nor().scl(-f*delta);
        this.vel.add(frict);
        if(this.vel.dot(old) > 0){
            this.vel = new Vector2();
        }
    }

    public void flipSprite(){
        this.sprite.flip(true, false);
    }

    public void destroy(){
        this.toDestroy = true;
    }

    //SETTERS=======================================================================================
    public void setRotation(float a){
        this.sprite.setRotation(a);
    }

    public void setRotation(float x, float y){
        //Assume's default image points upward
        float angle = 0;
        if(x != 0 && y != 0){
            if(x > 0 && y == 0)
                angle = 270;
            else if(x < 0 && y == 0)
                angle = 90;
            else if(y < 0 && x == 0)
                angle = 180;
            else if(y > 0 && x == 0){
                angle = 0;
            }
            else{
                angle = MathUtils.radiansToDegrees * MathUtils.atan2(y, x) - 90f;
                if (angle > 360)
                    angle -= 360;
            }
            this.setRotation(angle);
        }
    }

    public void setRotation(Vector2 dir){
        this.setRotation(dir.x,dir.y);
    }

    public void setSprite(Sprite s){
        if(this.sprite == null) {
            this.sprite = s;
            this.sprite.setScale(this.spriteScl);
            this.pos.sub(this.sprite.getWidth()/2, this.sprite.getHeight()/2);
            this.setSpritePos(this.pos);
        }else{
            if(Utility.debug)
                Utility.print(this.TAG+"(Debug)", "Error: Already called setSprite()!");
        }
    }

    public void setSprite(Sprite s, float scl){
        this.spriteScl = scl;
        this.setSprite(s);
    }

    public void setSpritePos(float x, float y){
        this.sprite.setPosition(x,y);
    }

    public void setSpritePos(Vector2 pos){
        this.setSpritePos(pos.x, pos.y);
    }

    public void setSpriteColor(Color c){
        this.sprite.setColor(c.r,c.g,c.b,c.a);
    }

    public void setSpriteColor(float r, float g, float b, float a){
        this.setSpriteColor(new Color(r,g,b,a));
    }

    public void setAlpha(float a){
        this.sprite.setAlpha(a);
    }

    public void setPos(Vector2 pos){
        this.pos = pos;
    }

    public void setPosX(float x){
        this.setPos(new Vector2(x, this.getPosY()));
    }

    public void setPosY(float y){
        this.setPos(new Vector2(this.getPosX(), y));
    }

    public void setVel(Vector2 vel){
        this.vel = vel;
    }

    public void setVelX(float x){
        this.setVel(new Vector2(x, this.getVelY()));
    }

    public void setVelY(float y){
        this.setVel(new Vector2(this.getVelX(), y));
    }

    public void setMaxAccel(float a){
        this.maxAccel = a;
    }

    public void setAccelIncr(float a){
        this.accelIncr = a;
    }

    public void setSpriteScl(float s){
        this.spriteScl = s;
    }

    public void setBoundingCircleScl(float s){
        this.boundingCircleScl = s;
    }

    public void setCanCollide(boolean collideable){
        this.canCollide = collideable;
    }

    public void setSightCircle(float rad){
        Vector2 center = this.getCenterPos();
        this.sightCircle = new Circle(center.x, center.y, rad);
    }

    //Getters=======================================================================================
    public Circle getBoundingCircle(){
        return this.getBoundingCircle(this.spriteScl*this.boundingCircleScl);
    }

    public Circle getBoundingCircle(float scl){
        Vector2 pos = this.getCenterPos();
        float rad = this.getHeight();
        if(this.getWidth() > this.getHeight()){
            rad = this.getWidth();
        }
        return new Circle(pos.x, pos.y, rad*scl);
    }

    public float getBoundingCircleRad(){
        return this.getBoundingCircle().radius;
    }

    public Vector2 getCenterPos(){
        return this.pos.cpy().add(this.getWidth()/2, this.getHeight()/2);
    }

    public Vector2 getPos(){
        return this.pos;
    }

    public float getPosX(){
        return this.pos.x;
    }

    public float getPosY(){
        return this.pos.y;
    }

    public Vector2 getVel(){
        return this.vel;
    }

    public float getVelX(){
        return this.vel.x;
    }

    public float getVelY(){
        return this.vel.y;
    }

    public float getWidth(){
        return this.sprite.getWidth();
    }

    public float getHeight(){
        return this.sprite.getHeight();
    }

    public float getMaxAccel(){
        return this.maxAccel;
    }

    public float getAccelIncr(){
        return this.accelIncr;
    }

    public boolean getToDestory(){
        return this.toDestroy;
    }

    public Vector2 getDir(float angle){
        float rad = (angle + 90) * MathUtils.degreesToRadians;
        return new Vector2(MathUtils.cos(rad),MathUtils.sin(rad));
    }

    public Vector2 getThisDir(){
        return getDir(this.getAngle());
    }

    public float getAngle(){
        return this.sprite.getRotation();
    }

    public float getSpriteScl(){
        return this.spriteScl;
    }

    public float getBoundingCircleScl(){
        return this.boundingCircleScl;
    }

    public float getSightRad(){
        return this.sightCircle.radius;
    }

    public boolean canCollide(){
        return this.canCollide;
    }

    //OTHER=========================================================================================
    @Override
    //We have compareTo for drawing in order of the object's Y
    //So the closer we are to the bottom of the screen, the more outward objects appear
    public int compareTo(BaseGameObject o) {
        return (int)o.getPosY() - (int)this.getPosY();
    }

    public void simpleBorder(){
        if(this.getPosX() < GameConstants.getGameWorldX()){
            this.setPosX(GameConstants.getGameWorldX());
            this.setVelX(0);
        }else if(this.getPosX() > GameConstants.getGameWorldX2() - this.getWidth()){
            this.setPosX(GameConstants.getGameWorldX2() - this.getWidth());
            this.setVelX(0);
        }
        if(this.getPosY() < GameConstants.getGameWorldY()){
            this.setPosY(GameConstants.getGameWorldY());
            this.setVelY(0);
        }else if(this.getPosY() > GameConstants.getGameWorldY2() - this.getHeight()){
            this.setPosY(GameConstants.getGameWorldY2() - this.getHeight());
            this.setVelY(0);
        }
        this.sprite.setPosition(this.pos.x, this.pos.y);
    }

    public void outOfBorder(){
        if((this.getPosX() < GameConstants.getGameWorldX()) ||
           (this.getPosX() > GameConstants.getGameWorldX2() - this.getWidth()) ||
           (this.getPosY() < GameConstants.getGameWorldY()) ||
           (this.getPosY() > GameConstants.getGameWorldY2() - this.getHeight())){
            this.toDestroy = true;
        }
    }
}
