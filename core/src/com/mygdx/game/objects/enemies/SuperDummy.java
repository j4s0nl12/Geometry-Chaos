package com.mygdx.game.objects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.player.Player;

public class SuperDummy extends BaseEnemy{

    public SuperDummy(Vector2 pos, Vector2 vel) {
        super(pos, vel);
        this.setSprite(AssetManager.getSuperDummySprite());
        this.setBoundingCircleScl(.66f);
        this.setSightCircle(300f);
        this.setWanderDelay(3000L);
        this.setMaxAccel(5f);
        this.setAccelIncr(this.getMaxAccel()*4f);
        this.thisDebug = true;
    }

    @Override
    public void update(float delta){
        super.update(delta);
        if(!this.targetInSight && this.canWander() && this.getAggroTimer() <= 0){
            this.wander();
            this.setRandomWanderDelay(4000,7000);
        }
        if(this.getSeekV() != null)
            this.seek(this.getSeekV(), delta);
        this.simpleBorder();
    }

    @Override
    public void inSightAction(BaseGameObject o){
        super.inSightAction(o);
        if(o.getClass() == Player.class) {
            this.setSeekV(o.getCenterPos().cpy());
        }
    }

    @Override
    public boolean checkInSight(BaseGameObject o){
        if(o.getClass() == Player.class) {
            boolean old = this.targetInSight;
            this.targetInSight = super.checkInSight(o);
            if (old && !this.targetInSight) {
                this.setRandomWanderDelay(6000,8000);
            }
            return this.targetInSight;
        }
        return false;
    }
}
