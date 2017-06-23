package com.mygdx.game.objects.other;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.BaseGameObject;

public class Hole extends BaseGameObject {

    public Hole(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        //this.setSprite();
    }

    @Override
    public void collision(BaseGameObject o){
        super.collision(o);
    }
}
