package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.state.GamePlayState;

public final class Player extends MovableEntity
{
    public static final float SIZE = 25f;
    public static final float SPEED = 100f;

    private GamePlayState game;
    private Image playerImage;

    public Player(float x, float y, GamePlayState game)
    {
        super(new Rectangle(x, y, SIZE, SIZE), 100f);

        this.game = game;
        setPlayerImage();
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        Input input = gc.getInput();

        float distance = this.speed * dt;

        updateY(input, distance);
        updateX(input, distance);
    }

    private void updateX(Input input, float distance)
    {
        float dx = 0;

        if (input.isKeyDown(Input.KEY_A))
            dx -= distance;

        if (input.isKeyDown(Input.KEY_D))
            dx += distance;

        moveAndCheckCollisionsX(dx);
    }

    private void updateY(Input in, float distance)
    {
        float dy = 0;

        if (in.isKeyDown(Input.KEY_W))
            dy -= distance;

        if (in.isKeyDown(Input.KEY_S))
            dy += distance;

        moveAndCheckCollisionsY(dy);
    }

    private void moveAndCheckCollisionsY(float dy)
    {
        this.moveY(dy);

        if (this.collidedWithWall())
            this.moveY(-dy);
    }

    private void moveAndCheckCollisionsX(float dx)
    {
        this.moveX(dx);

        if (this.collidedWithWall())
            this.moveX(-dx);
    }

    @Override
    public void render(Graphics g)
    {
        this.playerImage.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    private boolean collidedWithWall()
    {
        return this.game.collidesWithWall(this.getBody());
    }

    private void setPlayerImage()
    {
        try
        {
            this.playerImage = new Image("res/player2.png");
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
