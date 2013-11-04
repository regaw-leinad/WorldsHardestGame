package com.regawmod.hardestgame.entity;

import com.regawmod.hardestgame.level.Level;

public class UpAndDownEnemy extends Enemy
{
    public UpAndDownEnemy(float x, float y, float speed, Level level)
    {
        super(x, y, level, true);

        this.setSpeed(speed);
    }

    @Override
    public void update(float dt)
    {
        this.moveY(this.speed * dt);

        if (this.collidesWithWall() || this.collidesWithZone())
        {
            while (this.collidesWithWall() || this.collidesWithZone())
                this.moveY(this.getBactrackComponent());

            this.turnAround();
        }
    }
}
