package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.state.GamePlayState;

public class Enemy extends MovableEntity
{
    public static final float ENEMY_RADIUS = 8;

    protected GamePlayState game;
    private Image enemyImage;

    public Enemy(float x, float y, GamePlayState game)
    {
        super(new Circle(x, y, ENEMY_RADIUS), 300f);

        this.game = game;

        setEnemyImage();
    }

    @Override
    public final void render(Graphics g)
    {
        this.enemyImage.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void update(GameContainer gc, float dt)
    {

    }

    private void setEnemyImage()
    {
        try
        {
            this.enemyImage = new Image("res/enemy.png");
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
