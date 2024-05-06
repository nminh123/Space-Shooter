package com.spaceshooter.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;
    //graphics
    private SpriteBatch batch;
    //private Texture background;
    private Texture[] background;
    //timing
    //private int backgroundOffset;
    private float[]backgroundOffset = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;
    private final int WORLD_HEIGHT = 128;
    private final int WORLD_WIDTH = 72;

    GameScreen()
    {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        background = new Texture[4];
//        background = new Texture("darkPurpleStarscape.png");
//        backgroundOffset = 0;

        background[0] = new Texture("Starscape00.png");
        background[1] = new Texture("Starscape01.png");
        background[2] = new Texture("Starscape02.png");
        background[3] = new Texture("Starscape03.png");

        backgroundMaxScrollingSpeed = (float)(WORLD_HEIGHT)/4;

        batch = new SpriteBatch();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        batch.begin();
        //scrolling background
        renderBackground(deltaTime);
        batch.end();
    }

    public void renderBackground(float deltaTime)
    {
        backgroundOffset[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
        backgroundOffset[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
        backgroundOffset[2] += deltaTime * backgroundMaxScrollingSpeed / 2;
        backgroundOffset[3] += deltaTime * backgroundMaxScrollingSpeed;

        for(int layer = 0; layer < backgroundOffset.length; layer++)
        {
            batch.draw(background[layer],0,-backgroundOffset[layer],WORLD_WIDTH,WORLD_HEIGHT);
            batch.draw(background[layer],0,-backgroundOffset[layer]+WORLD_HEIGHT
                    ,WORLD_WIDTH,WORLD_HEIGHT);
            if(backgroundOffset[layer] > WORLD_HEIGHT)
            {
                backgroundOffset[layer] = 0;
            }
        }

    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
