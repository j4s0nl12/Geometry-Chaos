package com.mygdx.game.utility;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.BaseGameObject;

public class QuadTree {

    private int MAX_OBJECTS = 5;
    private int MAX_DEPTH = 5;

    private int curDepth;
    private Array<BaseGameObject> olist;
    private Rectangle bounds;
    private QuadTree[] nodes;

    public QuadTree(int depth, float x, float y, float width, float height){
        this.curDepth = depth;
        this.olist = new Array();
        this.bounds = new Rectangle(x,y,width,height);
        this.nodes = new QuadTree[4];
    }

    public void update(Array<BaseGameObject> list){
        this.clear();
        for(int i = 0; i < list.size; i++){
            this.insert(list.get(i));
        }

        Array<BaseGameObject> returnObjects = new Array();
        for(int i = 0; i < list.size; i++){
            returnObjects.clear();
            this.retrieve(returnObjects, list.get(i));

            for(int x = 0; x < returnObjects.size; x++){
                if(list.get(i).checkCollision(returnObjects.get(x)) &&
                   !list.get(i).equals(returnObjects.get(x)))
                    list.get(i).collision(returnObjects.get(x));
            }
        }
    }

    private void clear(){
        this.olist.clear();
        for(int i = 0; i < this.nodes.length; i++){
            if(this.nodes[i] != null){
                this.nodes[i].clear();
                this.nodes[i] = null;
            }
        }
    }

    private void split(){
        int subWidth = (int)(this.bounds.getWidth()/2);
        int subHeight = (int)(this.bounds.getHeight()/2);
        int x = (int)this.bounds.getX();
        int y = (int)this.bounds.getY();

        this.nodes[0] = new QuadTree(this.curDepth + 1, x + subWidth, y, subWidth, subHeight);
        this.nodes[1] = new QuadTree(this.curDepth + 1, x, y, subWidth, subHeight);
        this.nodes[2] = new QuadTree(this.curDepth + 1, x, y + subHeight, subWidth, subHeight);
        this.nodes[3] = new QuadTree(this.curDepth + 1, x + subWidth, y + subHeight, subWidth, subHeight);
    }

    private int getIndex(BaseGameObject o){
        int index = -1;
        double verticalMidpoint = this.bounds.getX() + (this.bounds.getWidth()/2);
        double horizontalMidpoint = this.bounds.getY() + (this.bounds.getHeight()/2);

        boolean topQuadrant = (o.getPosY() < horizontalMidpoint && o.getPosY() + o.getHeight() < horizontalMidpoint);
        boolean bottomQuadrant = (o.getPosY() > horizontalMidpoint);

        if(o.getPosX() < verticalMidpoint && o.getPosX() + o.getWidth() < verticalMidpoint){
            if(topQuadrant){
                index = 1;
            }else if(bottomQuadrant){
                index = 2;
            }
        }else if(o.getPosX() > verticalMidpoint){
            if(topQuadrant){
                index = 0;
            }else if(bottomQuadrant){
                index = 3;
            }
        }

        return index;
    }

    private void insert(BaseGameObject o){
        if(this.nodes[0] != null){
            int index = this.getIndex(o);

            if(index != -1){
                this.nodes[index].insert(o);
                return;
            }
        }

        this.olist.add(o);

        if(this.olist.size > MAX_OBJECTS && this.curDepth < MAX_DEPTH){
            if(this.nodes[0] == null){
                split();
            }

            int i = 0;
            while(i < this.olist.size){
                int index = this.getIndex(this.olist.get(i));
                if(index != -1){
                    this.nodes[index].insert(this.olist.removeIndex(i));
                }else{
                    i++;
                }
            }
        }
    }

    private Array<BaseGameObject> retrieve(Array<BaseGameObject> returnObjects, BaseGameObject o){
        int index = this.getIndex(o);
        if(index != -1 && this.nodes[0] != null){
            this.nodes[index].retrieve(returnObjects, o);
        }

        returnObjects.addAll(this.olist);

        return returnObjects;
    }
}
