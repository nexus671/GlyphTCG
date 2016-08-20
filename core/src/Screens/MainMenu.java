package Screens;


import Game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.TCG;

import java.io.IOException;


/**
 * Created by acurr on 6/2/2016.
 */
public class MainMenu implements Screen {
    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle style;
    private TCG game;
    private TextureAtlas startAtlas;
    private TextButton startButton;
    private TextButton collectionButton;
    private Skin skin;
    private Stage stage;
    private Label label;
    private Player player;
    private float time;

    public MainMenu(TCG game) {
        this.game = game;
    }

    @Override
    public void show() {
        accessPlayer();

        stage = new Stage(new ScreenViewport());
        style = new Label.LabelStyle(game.font, Color.WHITE);
        label = new Label("", style);
        label.setPosition(50, 50);
        stage.addActor(label);

        skin = new Skin();
        startAtlas = new TextureAtlas("Button/Button.pack");
        skin.addRegions(startAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button");
        textButtonStyle.over = skin.getDrawable("button_pressed");
        textButtonStyle.down = skin.getDrawable("button_pressed");
        textButtonStyle.font = game.font;
        startButton = new TextButton("Start", textButtonStyle);
        startButton.setWidth(Gdx.graphics.getWidth() / 2);
        startButton.setHeight(Gdx.graphics.getHeight() / 4);
        startButton.setPosition(Gdx.graphics.getWidth() / 2 - (startButton.getWidth() / 2), Gdx.graphics.getHeight() / 2);
        collectionButton = new TextButton("Collection", textButtonStyle);
        collectionButton.setWidth(Gdx.graphics.getWidth() / 2);
        collectionButton.setHeight(Gdx.graphics.getHeight() / 4);
        collectionButton.setPosition(Gdx.graphics.getWidth() / 2 - (startButton.getWidth() / 2), Gdx.graphics.getHeight() / 5);
        collectionButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CollectionScreen(game, player));
                stage.clear();
                return true;
            }
        });
        stage.addActor(startButton);
        stage.addActor(collectionButton);
        Gdx.input.setInputProcessor(stage);
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game, player));
                stage.clear();
                return true;
            }
        });
    }

    private void accessPlayer() {
        if (Gdx.files.local("player.dat").exists()) {
            try {
                player = Player.readPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Player exists, Reading File");
        } else {
            player = new Player("");
            try {
                player.savePlayer(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Player does not exist, Creating player and File");
        }
    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(30, 25, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
