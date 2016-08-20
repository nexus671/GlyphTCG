package Screens;

import Game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.TCG;


/**
 * Created by acurr on 6/2/2016.
 */
public class CollectionScreen implements Screen {
    private TCG game;
    private Stage stage;
    private Table table;
    private TextureAtlas listAtlas;
    private Skin skin;
    private List.ListStyle style;
    private ShapeRenderer shapeRenderer;
    private TextButton addButton;
    private ScrollPane scrollPane;
    private Player player;
    private List list;


    public CollectionScreen(TCG game, Player player) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        this.player = player;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        listAtlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table(skin);
        style = new List.ListStyle();
        table.add("Cards");
        style.font = game.font;
        scrollPane = new ScrollPane(table);
        table.setBounds(Gdx.graphics.getWidth() / 48, 0, Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight());
        table.debug();
        list = new List(skin);
        list.setItems(player.getTrunk().getCollection().toArray());
        addButton = new TextButton("Add Card", MainMenu.textButtonStyle);
        addButton.setSize(10, 10);
        table.row();
        table.add(new Label("title", MainMenu.style)).expandX();

        table.add(list);

        table.add(new Label("dirt", MainMenu.style)).expandX();
        table.add(addButton).size(150, 100).expandX();
        table.add();


        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.end();
        shapeRenderer.begin();
        stage.draw();
        table.drawDebug(shapeRenderer);

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
