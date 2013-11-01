package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.hardestgame.entity.BackAndForthEnemy;
import com.regawmod.hardestgame.entity.CirclingEnemy;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.entity.Player;

public class GamePlayState extends AbstractGameState
{
    Polygon level;
    String collide = "";
    Image playerImg;

    Player player;
    Enemy en;

    Enemy[] enemyCircle;

    public boolean collidesWithWall(Shape p)
    {
        return this.level.intersects(p);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        this.playerImg = new Image("res/player.png");
        this.player = new Player(65, 65, this);

        en = new BackAndForthEnemy(200, 400, this);

        enemyCircle = new Enemy[17];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                enemyCircle[i * 4 + j] = new CirclingEnemy(160, 160, j * Math.PI / 2, 25 + 25 * i, Math.PI / 2, this);

        enemyCircle[16] = new Enemy(160, 160, this);

        float[] p = { 10, 10, 70, 10, 70, 110, 45, 110, 45, 70, 30, 70, 30, 100, 10, 100 };

        for (int i = 0; i < p.length; i++)
            p[i] *= 4f;

        level = new Polygon(p);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        player.update(gc, dt);
        en.update(gc, dt);

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                enemyCircle[i * 4 + j].update(gc, dt);

        enemyCircle[16].update(gc, dt);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setBackground(Color.lightGray);
        g.setColor(Color.black);
        g.draw(level);

        player.render(g);
        en.render(g);

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                enemyCircle[i * 4 + j].render(g);

        enemyCircle[16].render(g);
    }

    @Override
    public int getID()
    {
        return GameState.TESTING;
    }
}
