package Screens;

import Game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TCG;

import java.io.IOException;

/**
 * Created by acurr on 6/2/2016.
 */
public class PlayScreen implements Screen {
    private Player player;
    private Texture card;
    private Vector2 position;
    private TCG game;

    public PlayScreen(TCG game, Player player) {
        this.game = game;
    }

    @Override
    public void show() {

        card = new Texture(Gdx.files.internal("createcard.jpg"));
        position = new Vector2(50, 50);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 3, 3, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y--;


        }
        game.batch.begin();
        game.batch.draw(card, position.x, position.y);
        game.batch.end();

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
        try {
            player.savePlayer(player);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
