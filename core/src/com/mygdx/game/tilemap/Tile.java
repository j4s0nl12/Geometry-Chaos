package com.mygdx.game.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.ui.ObjectScrollPane;
import com.mygdx.game.utility.Utility;

public class Tile {

    private Vector2 pos;
    private int width;
    private int height;
    private Rectangle rect;

    private BaseGameObject o;

    private boolean isOver;

    public Tile(Vector2 pos, int size){
        this.init(pos, size, size);
    }

    public Tile(Vector2 pos, int width, int height){
        this.init(pos, width, height);
    }

    private void init(Vector2 pos, int width, int height){
        this.isOver = false;
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(this.pos.x, this.pos.y, this.width, this.height);
    }

    public void draw(SpriteBatch batch){
        if(this.o != null){
            this.o.draw(batch);
        }
    }

    public void drawTiles(ShapeRenderer sr){
        if(this.isOver()){
            this.isOver = true;
            sr.set(ShapeRenderer.ShapeType.Filled);
            sr.setColor(0,204f/255,204f/255,.6f);
            if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                this.setObject(null);
            }
        }else{
            this.isOver = false;
            sr.setColor(1,1,1,1);
        }
        sr.rect(this.pos.x, this.pos.y, this.width, this.height);
        sr.set(ShapeRenderer.ShapeType.Line);
    }

    private boolean isInTile(Vector2 pos){
        if(this.rect.contains(pos)){
            return true;
        }
        return false;
    }

    public BaseGameObject getObject(){
        return this.o;
    }

    public void setObject(BaseGameObject o){
        this.o = o;
    }

    public Vector2 getCenterPos(){
        return this.pos.cpy().add(new Vector2(this.width/2, this.height/2));
    }

    public boolean isOver(){
        return this.isInTile(Utility.getUnprojectAt(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    public boolean getIsOver(){
        return this.isOver;
    }
}
