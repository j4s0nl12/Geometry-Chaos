package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class BaseGameObject implements Comparable<BaseGameObject>{

    private String TAG = this.getClass().getSimpleName();

    private Vector2 pos;
    private Vector2 vel;
    private float maxAccel;
    private float accelIncr;

    private Sprite sprite;

    private boolean toDestroy;

    public BaseGameObject(Vector2 pos, Vector2 vel){
        this.pos = pos;
        this.vel = vel;

        //Default
        this.maxAccel = 0;
        this.accelIncr = 0;

        this.toDestroy = false;
    }

    public void update(float delta){
        this.pos.add(this.vel);
        this.setSpritePos(this.pos);
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void collision(BaseGameObject o){
        if(Utility.debug)
            Utility.print(this.TAG + "(Debug)", "Collided with [" + o.TAG + "]");
    }

    public boolean checkCollision(BaseGameObject o){
        if(this.getBoundingCircle().overlaps(o.getBoundingCircle())){
            return true;
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

    public void setRotation(float a){
        this.sprite.setRotation(a);
    }

    //Assume's default image points upward
    public void setRotation(float x, float y){
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

    public void setSprite(String imgFile){
        if(this.sprite == null) {
            this.sprite = new Sprite(new Texture(imgFile));
            this.pos.sub(this.sprite.getWidth()/2, this.sprite.getHeight()/2);
            this.setSpritePos(this.pos);
        }else{
            if(Utility.debug)
                Utility.print(this.TAG+"(Debug)", "Error: Already called setSprite()!");
        }
    }

    public void setSpritePos(float x, float y){
        this.sprite.setPosition(x,y);
    }

    public void setSpritePos(Vector2 pos){
        this.setSpritePos(pos.x, pos.y);
    }

    public void destroy(){
        this.toDestroy = true;
    }

    public Circle getBoundingCircle(){
        Vector2 pos = this.getCenterPos();
        float rad = this.getHeight();
        if(this.getWidth() > this.getHeight()){
            rad = this.getWidth();
        }
        return new Circle(pos.x, pos.y, rad);
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
        }else if(this.getPosX() > GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth()){
            this.setPosX(GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth());
            this.setVelX(0);
        }
        if(this.getPosY() < GameConstants.getGameWorldY()){
            this.setPosY(GameConstants.getGameWorldY());
            this.setVelY(0);
        }else if(this.getPosY() > GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight()){
            this.setPosY(GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight());
            this.setVelY(0);
        }
        this.sprite.setPosition(this.pos.x, this.pos.y);
    }

    public void outOfBorder(){
        if((this.getPosX() < GameConstants.getGameWorldX()) ||
           (this.getPosX() > GameConstants.getGameWorldX() + GameConstants.getGameWorldWidth() - this.getWidth()) ||
           (this.getPosY() < GameConstants.getGameWorldY()) ||
           (this.getPosY() > GameConstants.getGameWorldY() + GameConstants.getGameWorldHeight() - this.getHeight())){
            this.toDestroy = true;
        }
    }
}
