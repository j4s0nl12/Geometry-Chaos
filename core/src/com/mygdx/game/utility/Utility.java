package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.main.GeometryChaos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {

    public static boolean debug = true;
    //public static boolean debug = false;

    public static void print(String tag, String msg){long t = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(t);
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        String curTime = sdf.format(cal.getTime());
        Gdx.app.log(tag, msg + "[" + curTime +"s]");
    }

    public static Vector2 getUnprojectAt(int x, int y, int z){
        Vector3 tmp = GeometryChaos.camera.unproject(new Vector3(x,y,z));
        return new Vector2(tmp.x, tmp.y);
    }
}
