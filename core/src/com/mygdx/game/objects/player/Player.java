package com.mygdx.game.objects.player;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.ui.CirclePad;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class Player extends BaseGameObject{

    private Array<Actor> actorList;

    public CirclePad Lpad;
    public CirclePad Rpad;

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
        float size = 340f;
        float leftX = GameConstants.getVirtualWidth()*.11f;
        float rightX = GameConstants.getVirtualWidth() - leftX;
        float height = GameConstants.getVirtualHeight()*.2f;
        this.Lpad = new CirclePad(leftX, height, size);
        this.Rpad = new CirclePad(rightX, height, size);
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
                Vector2 dir = Utility.getUnprojectAt(Gdx.input.getX(), Gdx.input.getY(), 0).sub(this.getCenterPos());
                this.setRotation(dir);
            }
        }

        if(!this.Lpad.isTouched() && !this.isKeyPressed){
            this.applyFrict(this.getAccelIncr()*2, delta);
        }

        if(PrefManager.getHideCpads()) {
            if (!this.Lpad.isTouched()) {
                this.Lpad.setVisibile(false);
            }
            if (!this.Rpad.isTouched()) {
                this.Rpad.setVisibile(false);
            }
        }

        super.update(delta);

        this.simpleBorder();

    }

    public Array<Actor> getActorList(){
        return this.actorList;
    }
}
