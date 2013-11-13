package com.regawmod.hardestgame.entity.enemy;

import java.util.ArrayList;
import java.util.List;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

public class WaveEnemy extends Enemy
{
    private float amplitude;
    private float centerY;
    private float delay;
    private boolean shouldDelay;
    float runningCount = 0;

    public WaveEnemy(float x, float startY, float centerY, float amplitude, float speed, float delay, Level level)
    {
        super(x, startY, level);

        setSpeed(speed);

        this.centerY = centerY + Level.LEVEL_OFFSET;
        this.amplitude = amplitude;
        this.delay = delay;
        this.shouldDelay = true;
    }

    @Override
    protected void update(float dt)
    {
        if (shouldDelay)
        {
            runningCount += dt;

            if (runningCount >= this.delay)
                shouldDelay = false;
        }
        else
        {
            if (isOutsideOfAmplitude())
            {

                while (isOutsideOfAmplitude())
                    this.moveY(this.getBactrackComponent(dt));

                this.turnAround();
            }

            this.moveY(getSpeed() * dt);
        }
    }

    private boolean isOutsideOfAmplitude()
    {
        return this.getCenterY() - Enemy.ENEMY_RADIUS <= this.centerY - amplitude && getSpeed() <= 0 ||
                this.getCenterY() + Enemy.ENEMY_RADIUS >= this.centerY + amplitude && getSpeed() >= 0;
    }

    public static List<Enemy> getWaveEnemyGroup(float startX, float endX, float centerY, float amplitude, Level level)
    {
        int numOfEnemies = (int)((endX - startX) / (2 * Enemy.ENEMY_RADIUS));
        //      double angle = 0;
        //        double increment = Math.PI * 2 / numOfEnemies;

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        //        float speed = 1f;
        //        double sectionCounter = 1;

        for (int i = 0; i < numOfEnemies; i++)
        {
            //            if (angle >= sectionCounter * Math.PI / 2)
            //            {
            //                speed *= -1;
            //                sectionCounter++;
            //            }
            enemies.add(new WaveEnemy((startX + Enemy.ENEMY_RADIUS) + (2 * Enemy.ENEMY_RADIUS) * i, centerY, centerY, amplitude, 80f, 0.09f * i, level));

            //            enemies.add(new WaveEnemy((startX + Enemy.ENEMY_RADIUS) + (2 * Enemy.ENEMY_RADIUS) * i,
            //                    (float)(centerY + Math.sin(angle) * amplitude), centerY, amplitude, 100f * speed, level));

            //      angle += increment;
        }

        return enemies;
    }
}
