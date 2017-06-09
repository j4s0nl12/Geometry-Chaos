package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.math.Vector2;

public class TestEnemy extends BaseEnemy{

    public TestEnemy(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite("Images/HumanTmp.png");
    }
}
