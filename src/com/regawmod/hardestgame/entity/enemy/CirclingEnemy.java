package com.regawmod.hardestgame.entity.enemy;

import java.util.ArrayList;
import java.util.List;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * An enemy that revolves around a center point.
 * 
 * @author Dan Wager
 */
public class CirclingEnemy extends Enemy
{
    /** The distance from the center point */
    private float radius;
    /** The current angle to the center point */
    private double angle;
    /** The X coordinate to revolve around */
    private float revolveAroundX;
    /** The Y coordinate to revolve around */
    private float revolveAroundY;

    /**
     * Creates a new {@link CirclingEnemy}.
     * 
     * @param cX The center X coordinate to revolve around
     * @param cY The center Y coordinate to revolve around
     * @param initAngle The initial angle offset
     * @param radius The distance from the center point
     * @param speed The speed in radians per second
     * @param level The level
     */
    public CirclingEnemy(float cX, float cY, double initAngle, float radius, double speed, Level level)
    {
        super(cX + (float)Math.cos(initAngle) * radius, cY + (float)Math.sin(initAngle) * radius, level);

        this.revolveAroundX = cX;
        this.revolveAroundY = cY + Level.LEVEL_OFFSET;
        this.radius = radius;
        this.angle = initAngle;

        setSpeed((float)speed);
    }

    @Override
    public void update(float dt)
    {
        this.angle += getSpeed() * dt;

        if (this.angle >= 2 * Math.PI)
            this.angle -= 2 * Math.PI;

        this.setCenterX((float)(this.revolveAroundX + this.radius * Math.cos(angle)));
        this.setCenterY((float)(this.revolveAroundY + this.radius * Math.sin(angle)));
    }

    /**
     * Gets a new collection of enemies that act together circling around a common point.
     * 
     * @param level The level
     * @param centerX The center X coordinate to revolve around
     * @param centerY The center Y coordinate to revolve around
     * @param numOfLayers The number of enemy layers
     * @param enemPerLayer The number of enemies per layer
     * @param initRadius The distance from the center point to the first layer
     * @param indivRadius The radial distance from between each enemy
     * @param speed The speed in radians per second
     * @param bladeAngle The angle to fan out the entities
     * @return A collection of {@link CirclingEnemy}s that act together in a group
     */
    public static List<Enemy> getCircleGroupEnemy(Level level, float centerX, float centerY, int numOfLayers, int enemPerLayer, float initRadius, float indivRadius, double speed, double bladeAngle)
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();

        for (int i = 0; i < numOfLayers; i++)
            for (int j = 0; j < enemPerLayer; j++)
                result.add(new CirclingEnemy(centerX, centerY, (j * Math.PI / (enemPerLayer / 2)) - bladeAngle * i, initRadius + indivRadius * i, speed, level));

        return result;
    }

    /**
     * Gets a new collection of enemies that act together circling around a common point.
     * 
     * @param level The level
     * @param centerX The center X coordinate to revolve around
     * @param centerY The center Y coordinate to revolve around
     * @param numOfLayers The number of enemy layers
     * @param enemPerLayer The number of enemies per layer
     * @param initRadius The distance from the center point to the first layer
     * @param indivRadius The radial distance from between each enemy
     * @param speed The speed in radians per second
     * @return A collection of {@link CirclingEnemy}s that act together in a group
     */
    public static List<Enemy> getCircleGroupEnemy(Level level, float centerX, float centerY, int numOfLayers, int enemPerLayer, float initRadius, float indivRadius, double speed)
    {
        return getCircleGroupEnemy(level, centerX, centerY, numOfLayers, enemPerLayer, initRadius, indivRadius, speed, 0);
    }
}
