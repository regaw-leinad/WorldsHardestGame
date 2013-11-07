package com.regawmod.hardestgame.entity;

import com.regawmod.hardestgame.level.Level;

public class AwareEnemy extends Enemy
{
    private Level level;
    private double theta;
    private boolean shouldTrack;

    public AwareEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.level = level;

        setSpeed(30f);

        this.shouldTrack = true;
    }

    @Override
    protected void update(float dt)
    {
        if (this.shouldTrack)
        {
            calculateTheta(this.level.getPlayerX(), this.level.getPlayerY());
            moveEnemy(dt);
        }
    }

    private void moveEnemy(float dt)
    {
        this.moveX((float)(Math.cos(theta) * this.getSpeed() * dt));
        this.moveY((float)(Math.sin(theta) * this.getSpeed() * dt));
    }

    private void calculateTheta(float toX, float toY)
    {
        this.theta = Math.atan2(toY - this.getCenterY(), toX - this.getCenterX());
    }

    @Override
    public void onCoinCollected(float coinX, float coinY, int coinsRemaining)
    {
    }

    @Override
    public void onPlayerDeath()
    {
        this.shouldTrack = false;
    }

    @Override
    public void onPlayerRespawn()
    {
        this.shouldTrack = true;
    }
}
