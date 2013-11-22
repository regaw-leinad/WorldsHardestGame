package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

/**
 * A player that moves around in the level and collects coins while avoiding enemies.
 * 
 * @author Dan Wager
 */
public final class Player extends MovableEntity
{
    /** The size of the player */
    public static final float SIZE = 26f;
    /** The speed of the player */
    public static final float SPEED = 100f;
    /** The speed to fade when colliding with an enemy */
    public static final float FADE_ALPHA_PER_SEC = 1.5f;

    /** The level */
    private Level level;

    /** If the player has collided with an enemy this frame */
    private boolean collidedWithEnemy;

    /** The fill color of the player */
    private Color outsideColor;
    /** The border color of the layer */
    private Color insideColor;

    /** If the player is in the process of dying */
    private boolean isDying;
    /** If the player should be revived */
    private boolean shouldRevive;

    /**
     * Creates a new {@link Player}.
     * 
     * @param level the level
     */
    public Player(Level level)
    {
        super(new Rectangle(level.getPlayerStartX() - SIZE / 2, level.getPlayerStartY() - SIZE / 2, SIZE, SIZE), SPEED);

        this.level = level;
        this.collidedWithEnemy = false;
        this.isDying = false;
        this.shouldRevive = false;
        this.outsideColor = new Color(Color.black);
        this.insideColor = new Color(Color.red);
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        if (this.isDying)
        {
            this.collidedWithEnemy = false;

            this.insideColor.a -= FADE_ALPHA_PER_SEC * dt;
            this.outsideColor.a -= FADE_ALPHA_PER_SEC * dt;

            if (this.insideColor.a <= 0)
                resetPlayer();
        }
        else
        {
            Input input = gc.getInput();
            float distance = getSpeed() * dt;

            updateY(input, distance, dt);
            updateX(input, distance, dt);

            checkEnemyCollision();
            checkGoldCoinCollision();
        }
    }

    /**
     * Checks if the player collides with a gold coin.
     */
    private void checkGoldCoinCollision()
    {
        this.level.collidesWithGoldCoin(this);
    }

    /**
     * Resets the player.
     */
    private void resetPlayer()
    {
        this.shouldRevive = true;
        this.collidedWithEnemy = false;

        resetColor();
        resetPosition();
    }

    /**
     * Resets the player's color.
     */
    private void resetColor()
    {
        this.insideColor.a = 1.0f;
        this.outsideColor.a = 1.0f;
    }

    /**
     * Resets the player's position.
     */
    private void resetPosition()
    {
        this.setCenterX(this.level.getPlayerStartX());
        this.setCenterY(this.level.getPlayerStartY());
    }

    /**
     * Checks if the player collides with an enemy.
     */
    private void checkEnemyCollision()
    {
        this.collidedWithEnemy = this.isDying = this.level.collidesWithEnemy(this);
    }

    /**
     * Updates the players movement in the X direction.
     * 
     * @param input The input
     * @param distance How far to move this frame, if possible
     */
    private void updateX(Input input, float distance, float dt)
    {
        float dx = 0;

        if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
            dx -= distance;

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
            dx += distance;

        moveAndCheckCollisionsX(dx, dt);
    }

    /**
     * Updates the players movement in the Y direction.
     * 
     * @param input The input
     * @param distance How far to move this frame, if possible
     */
    private void updateY(Input input, float distance, float dt)
    {
        float dy = 0;

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
            dy -= distance;

        if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
            dy += distance;

        moveAndCheckCollisionsY(dy, dt);
    }

    /**
     * Actually move the player in the Y direction and check for level collisions.
     * 
     * @param dy How far to move
     */
    private void moveAndCheckCollisionsY(float dy, float dt)
    {
        this.moveY(dy);

        while (this.collidedWithWall())
            this.moveY(this.getBactrackComponent(dy, dt));
    }

    /**
     * Actually move the player in the X direction and check for level collisions.
     * 
     * @param dx How far to move
     */
    private void moveAndCheckCollisionsX(float dx, float dt)
    {
        this.moveX(dx);

        while (this.collidedWithWall())
            this.moveX(this.getBactrackComponent(dx, dt));
    }

    @Override
    public void render(Graphics g)
    {
        drawPlayer(g);
    }

    /**
     * Draws the player.
     * 
     * @param g The graphics object
     */
    private void drawPlayer(Graphics g)
    {
        g.setColor(this.outsideColor);
        g.fillRect(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
        g.setColor(this.insideColor);
        g.fillRect(this.getX() + 5, this.getY() + 5, this.getWidth() - 9, this.getHeight() - 9);
    }

    /**
     * Gets a value indicating if the player collided with the wall this frame.
     * 
     * @return If the player collided with the wall
     */
    private boolean collidedWithWall()
    {
        return this.level.collidesWithWall(this);
    }

    /**
     * Gets a value indicating if the player has died this frame.
     * 
     * @return If the player has died
     */
    public boolean hasDied()
    {
        return this.collidedWithEnemy;
    }

    /**
     * Gets a value indicating if the player is waiting to be revived.
     * 
     * @return If the player has died
     */
    public boolean shouldRevive()
    {
        return this.shouldRevive;
    }

    /**
     * "Revives" the player.
     */
    public void revive()
    {
        this.shouldRevive = false;
        this.isDying = false;
    }
}
