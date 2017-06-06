package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Camera {

    private static OrthographicCamera camera;
    private static Viewport viewport;

    private int width;
    private int height;

    public Camera(int w, int h){
        width = w;
        height = h;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(width, height, camera);
        viewport.apply();
    }

    public void update() {
        camera.update();
    }

    public Matrix4 combined(){
        return camera.combined;
    }

    public void resize(int width, int height){
        viewport.update(width, height, true);
        camera.position.set(width/2, height/2, 0);
    }

    public Viewport getViewport(){
        return viewport;
    }

    public static Vector2 getUnprojectAt(int x, int y, int z){
        Vector3 tmp = camera.unproject(new Vector3(x,y,z));
        return new Vector2(tmp.x,tmp.y);
    }
}
