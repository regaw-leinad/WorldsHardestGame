package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

public final class Player extends MovableEntity
{
    public static final float SIZE = 26f;
    public static final float SPEED = 100f;
    public static final float FADE_ALPHA_PER_SEC = 1.5f;

    private Level level;
    private boolean collidedWithEnemy;

    private Color outsideColor;
    private Color insideColor;

    public Player(Level level)
    {
        super(new Rectangle(level.getPlayerStartX() - SIZE / 2, level.getPlayerStartY() - SIZE / 2, SIZE, SIZE), SPEED);

        this.level = level;
        this.collidedWithEnemy = false;
        this.outsideColor = new Color(Color.black);
        this.insideColor = new Color(Color.red);
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        if (this.collidedWithEnemy)
        {
            this.insideColor.a -= FADE_ALPHA_PER_SEC * dt;
            this.outsideColor.a -= FADE_ALPHA_PER_SEC * dt;

            if (this.insideColor.a <= 0)
                resetLevel();
        }
        else
        {
            Input input = gc.getInput();
            float distance = this.speed * dt;

            updateY(input, distance);
            updateX(input, distance);

            checkEnemyCollision();
            checkGoldCoinCollision();
        }
    }

    private void checkGoldCoinCollision()
    {
        this.level.collidesWithGoldCoin(this);
    }

    private void resetLevel()
    {
        this.level.resetLevelAfterEnemyCollision();

        resetColor();
        resetPosition();
        this.collidedWithEnemy = false;
    }

    private void resetColor()
    {
        this.insideColor.a = 1.0f;
        this.outsideColor.a = 1.0f;
    }

    private void resetPosition()
    {
        this.setCenterX(this.level.getPlayerStartX());
        this.setCenterY(this.level.getPlayerStartY());
    }

    private void checkEnemyCollision()
    {
        this.collidedWithEnemy = this.level.collidesWithEnemy(this);
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

        while (this.collidedWithWall())
        {
            if (dy <= 0)
                this.moveY(.1f);
            else
                this.moveY(-.1f);
        }
    }

    private void moveAndCheckCollisionsX(float dx)
    {
        this.moveX(dx);

        while (this.collidedWithWall())
        {
            if (dx <= 0)
                this.moveX(.1f);
            else
                this.moveX(-.1f);
        }
    }

    @Override
    public void render(Graphics g)
    {
        drawPlayer(g);
    }

    private void drawPlayer(Graphics g)
    {

        g.setColor(this.outsideColor);
        g.fillRect(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
        g.setColor(this.insideColor);
        g.fillRect(this.getX() + 5, this.getY() + 5, this.getWidth() - 9, this.getHeight() - 9);

        //        g.setColor(Color.cyan);
        //        g.draw(this.getBody());
    }

    private boolean collidedWithWall()
    {
        return this.level.collidesWithWall(this);
    }
}
