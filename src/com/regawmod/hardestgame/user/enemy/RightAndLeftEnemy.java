package com.regawmod.hardestgame.user.enemy;

import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * An enemy, bounded by the walls of the level, that moves right to left.
 * 
 * @author Dan Wager
 */
public class RightAndLeftEnemy extends Enemy
{
    /**
     * Creates a new {@link RightAndLeftEnemy}.
     * 
     * @param x The X position
     * @param y The Y position
     * @param speed The speed of the enemy
     * @param level The level
     */
    public RightAndLeftEnemy(float x, float y, float speed, Level level)
    {
        super(x, y, level, true);

        this.setSpeed(speed);
    }

    @Override
    public void update(float dt)
    {
        this.moveX(getSpeed() * dt);

        if (this.collidesWithWall() || this.collidesWithZone())
        {
            while (this.collidesWithWall() || this.collidesWithZone())
                this.moveX(this.getBactrackComponent(dt));

            this.turnAround();
        }
    }
}
