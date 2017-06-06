package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.utility.QuadTree;

import java.util.Iterator;

public class GameObjectManager {

    private Array<BaseGameObject> olist;
    private QuadTree quad;
    int playerIdx;

    public GameObjectManager(){
        olist = new Array();
        quad = new QuadTree(0, 0, 0, GeometryChaos.getWidth(), GeometryChaos.getHeight());
    }

    public void update(float delta){
        Iterator<BaseGameObject> it = olist.iterator();
        while(it.hasNext()){
            BaseGameObject tmp = it.next();
            if(tmp.getToDestory()){
                it.remove();
            }
        }

        for(BaseGameObject o : this.olist){
            o.update(delta);
        }

        quad.update(this.olist);
    }

    public void draw(SpriteBatch batch){
        olist.sort();
        for(int i = 0; i < olist.size; i++){
            if(olist.get(i).getClass().getSimpleName().equals("Player")){
                playerIdx = i;
            }
            olist.get(i).draw(batch);
        }
    }

    public void add(BaseGameObject o){
        olist.add(o);
    }

    public void addPlayer(float x, float y){
        playerIdx = this.olist.size;
        add(new Player(new Vector2(x,y), new Vector2()));
    }

    public Player getPlayer(){
        return (Player) olist.get(playerIdx);
    }
}
