package com.mygdx.game.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.ui.CirclePad;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class PlayerController {

    private Player controllee;

    private Array<Actor> alist;

    public CirclePad Lpad;
    public CirclePad Rpad;

    private boolean isKeyPressed;

    public PlayerController(Player p){
        this.controllee = p;

        //Circlepads
        float size = 340f;
        float leftX = GameConstants.getVirtualWidth()*.11f;
        float rightX = GameConstants.getVirtualWidth() - leftX;
        float height = GameConstants.getVirtualHeight() * .2f;
        this.Lpad = new CirclePad(leftX, height, size);
        this.Rpad = new CirclePad(rightX, height, size);
        this.alist = new Array();
        this.alist.add(this.Lpad.getTouchpad());
        this.alist.add(this.Rpad.getTouchpad());

        this.isKeyPressed = false;
    }

    public void update(float delta){
        //Movement (Lpad)
        if(this.Lpad.isTouched()){
            Vector2 dir = this.Lpad.getPercents();
            dir.nor().scl(this.controllee.getAccelIncr());
            this.controllee.accel(dir, delta);
        }
        //Movement (Keyboard)
        if(!Gdx.app.getType().equals(Application.ApplicationType.Android)){
            this.isKeyPressed = false;
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                this.controllee.accel(new Vector2(0, this.controllee.getAccelIncr()), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                this.controllee.accel(new Vector2(0, -this.controllee.getAccelIncr()), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                this.controllee.accel(new Vector2(-this.controllee.getAccelIncr(), 0), delta);
                this.isKeyPressed = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                this.controllee.accel(new Vector2(this.controllee.getAccelIncr(), 0), delta);
                this.isKeyPressed = true;
            }
        }
        //Rotation and Shooting (Rpad)
        if(this.Rpad.isTouched()){
            this.controllee.setRotation(this.Rpad.getPercents());
            this.controllee.shoot();
        }
        //Rotation and Shooting (Mouse onClick)
        else if(!Gdx.app.getType().equals(Application.ApplicationType.Android)){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !this.Lpad.isTouched()) {
                Vector2 dir = Utility.getUnprojectAt(Gdx.input.getX(), Gdx.input.getY(), 0).sub(this.controllee.getCenterPos());
                this.controllee.setRotation(dir);
                this.controllee.shoot();
            }
        }
        //Friction if no movement input
        if(!this.Lpad.isTouched() && !this.isKeyPressed){
            this.controllee.applyFrict(this.controllee.getAccelIncr()*2, delta);
        }
        //Hide Cpads
        if(PrefManager.getBoolean(PrefManager.hideCpads_str) && !PrefManager.getBoolean(PrefManager.lockCpads_str)) {
            if (!this.Lpad.isTouched()) {
                //this.Lpad.setVisible(false);
                this.Lpad.setPosition(100000,100000);
            }
            if (!this.Rpad.isTouched()) {
                //this.Rpad.setVisible(false);
                this.Rpad.setPosition(100000,100000);
            }
        }
    }

    public void draw(SpriteBatch batch){
        this.Lpad.draw(batch);
        this.Rpad.draw(batch);
    }

    public Player getControllee(){
        return this.controllee;
    }

    public Array<Actor> getActors(){
        return this.alist;
    }
}
