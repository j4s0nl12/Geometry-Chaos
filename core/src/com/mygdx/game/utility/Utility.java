package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.main.GeometryChaos;

public class Utility {

    public static boolean debug = true;
    //public static boolean debug = false;

    public static void print(String tag, String msg){
        Gdx.app.log(tag, msg);
    }

    public static Vector2 getUnprojectAt(int x, int y, int z){
        Vector3 tmp = GeometryChaos.camera.unproject(new Vector3(x,y,z));
        return new Vector2(tmp.x, tmp.y);
    }
}
