package com.mygdx.game.objects.player;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.utility.Camera;

public class Player extends BaseGameObject{

    private Array<Actor> actorList;

    private com.mygdx.game.ui.CirclePad Lpad;
    private com.mygdx.game.ui.CirclePad Rpad;

    private long lastShot;
    private long shotDelay;

    private boolean isKeyPressed;

    public Player(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite("Images/HumanTmp.png");
        this.actorList = new Array();

        this.setMaxAccel(6f);
        this.setAccelIncr(this.getMaxAccel()*4f);

        //Circlepads
        float size = 275f;
        float leftX = GeometryChaos.getWidth()*4/30;
        float rightX = GeometryChaos.getWidth() - leftX;
        float height = GeometryChaos.getHeight()*7/30;
        this.Lpad = new com.mygdx.game.ui.CirclePad(leftX, height, size);
        this.Rpad = new com.mygdx.game.ui.CirclePad(rightX, height, size);
        this.actorList.add(this.Lpad.getTouchpad());
        this.actorList.add(this.Rpad.getTouchpad());

        this.isKeyPressed = false;
    }

    @Override
    public void update(float delta){
        if(this.Lpad.isTouched()){
            Vector2 dir = this.Lpad.getPercents();
            dir.nor().scl(this.getAccelIncr());
            this.accel(dir, delta);
        }

        if(!Gdx.app.getType().equals(Application.ApplicationType.Android)){
            this.isKeyPressed = false;
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                this.accel(new Vector2(0, this.getAccelIncr()), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                this.accel(new Vector2(0, -this.getAccelIncr()), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                this.accel(new Vector2(-this.getAccelIncr(), 0), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                this.accel(new Vector2(this.getAccelIncr(), 0), delta);
                this.isKeyPressed = true;
            }
        }

        if(this.Rpad.isTouched()){
            this.setRotation(this.Rpad.getPercents());
            //shoot
        }else if(!Gdx.app.getType().equals(Application.ApplicationType.Android)){
            if(Gdx.input.isTouched() && !this.Lpad.isTouched()) {
                Vector2 dir = Camera.getUnprojectAt(Gdx.input.getX(), Gdx.input.getY(), 0).sub(this.getCenterPos());
                this.setRotation(dir);
            }
        }

        if(!this.Lpad.isTouched() && !this.isKeyPressed){
            this.applyFrict(this.getAccelIncr()*2, delta);
        }

        super.update(delta);

        this.simpleBorder();

    }

    public Array<Actor> getActorList(){
        return this.actorList;
    }
}
