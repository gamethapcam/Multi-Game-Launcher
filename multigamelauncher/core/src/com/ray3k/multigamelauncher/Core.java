package com.ray3k.multigamelauncher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.io.File;
import java.io.IOException;

public class Core extends ApplicationAdapter {
    private Skin skin;
    private Stage stage;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("ui/metal-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        root.defaults().space(20.0f);
        TextButton textButton = new TextButton("Test Game 1", skin);
        root.add(textButton);
        
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                
            }
        });
        
        textButton = new TextButton("Test Game 2", skin);
        root.add(textButton);
        
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                
            }
        });
    }
    
    public void runLocalGame(String localPath) {
        try {
            File file = new File(System.getProperty("user.dir") + "/" + localPath);
            Runtime.getRuntime().exec("java -jar \"" + file.getName() + "\"", null, file.getParentFile());
        } catch (IOException e) {
            Gdx.app.error("Multi Game Launcher", "Error while executing external game...", e);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(239.0f / 255.0f, 239.0f / 255.0f, 239.0f / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
