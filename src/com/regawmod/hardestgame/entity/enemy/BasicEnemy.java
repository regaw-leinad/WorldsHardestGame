package com.regawmod.hardestgame.entity.enemy;

import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * The most basic implementation of an Enemy.
 * Doesn't move.
 * 
 * @author Dan Wager
 */
public class BasicEnemy extends Enemy
{
    /**
     * Creates a new {@link BasicEnemy}.
     * 
     * @param x The enemy's X position
     * @param y Then enemy's Y position
     * @param level The level
     */
    public BasicEnemy(float x, float y, Level level)
    {
        super(x, y, level, true);
    }
}
