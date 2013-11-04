package com.regawmod.hardestgame.entity;

import java.util.ArrayList;
import java.util.List;
import com.regawmod.hardestgame.level.Level;

public class CirclingEnemy extends Enemy
{
    private float radius;
    private double angle;
    private float revolveAroundX;
    private float revolveAroundY;

    public CirclingEnemy(float cX, float cY, double initAngle, float radius, double radiansPerSecond, Level level)
    {
        super(cX + (float)Math.cos(initAngle) * radius, cY + (float)Math.sin(initAngle) * radius, level);

        this.revolveAroundX = cX;
        this.revolveAroundY = cY;
        this.radius = radius;
        this.angle = initAngle;
        this.speed = (float)radiansPerSecond;
    }

    @Override
    public void update(float dt)
    {
        this.angle += this.speed * dt;

        if (this.angle >= 2 * Math.PI)
            this.angle -= 2 * Math.PI;

        this.setCenterX((float)(this.revolveAroundX + this.radius * Math.cos(angle)));
        this.setCenterY((float)(this.revolveAroundY + this.radius * Math.sin(angle)));
    }

    public static List<Enemy> getCircleGroupEnemy(Level level, float centerX, float centerY, int numOfLayers, int enemPerLayer, float initRadius, float indivRadius, double speed, float bladeAngle)
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();

        for (int i = 0; i < numOfLayers; i++)
            for (int j = 0; j < enemPerLayer; j++)
                result.add(new CirclingEnemy(centerX, centerY + Level.LEVEL_OFFSET, (j * Math.PI / (enemPerLayer / 2)) - bladeAngle * i, initRadius + indivRadius * i, speed, level));

        return result;
    }

    public static List<Enemy> getCircleGroupEnemy(Level level, float centerX, float centerY, int numOfLayers, int enemPerLayer, float initRadius, float indivRadius, double speed)
    {
        return getCircleGroupEnemy(level, centerX, centerY, numOfLayers, enemPerLayer, initRadius, indivRadius, speed, 0);
    }
}
