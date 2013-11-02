package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import com.regawmod.hardestgame.level.Level;

public class UpAndDownEnemy extends Enemy
{
    public UpAndDownEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.setSpeed(300);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        this.moveY(this.speed * dt);

        if (this.collidedWithWall())
        {
            while (this.collidedWithWall())
            {
                if (this.speed <= 0)
                    this.moveY(.1f);
                else
                    this.moveY(-.1f);
            }

            this.speed *= -1;
        }
    }

    private boolean collidedWithWall()
    {
        return this.level.collidesWithWall(this);
    }
}
