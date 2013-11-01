package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import com.regawmod.hardestgame.state.GamePlayState;

public class CirclingEnemy extends Enemy
{
    private float radius;
    private double angle;
    private float revolveAroundX;
    private float revolveAroundY;

    public CirclingEnemy(float cX, float cY, double initAngle, float radius, double radiansPerSecond, GamePlayState game)
    {
        super(cX + (float)Math.cos(initAngle) * radius, cY + (float)Math.sin(initAngle) * radius, game);

        this.revolveAroundX = cX;
        this.revolveAroundY = cY;
        this.radius = radius;
        this.angle = initAngle;
        this.speed = (float)radiansPerSecond;
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        this.angle += this.speed * dt;

        if (this.angle >= 360)
            this.angle -= 360;

        this.setCenterX((float)(this.revolveAroundX + this.radius * Math.cos(angle)));
        this.setCenterY((float)(this.revolveAroundY + this.radius * Math.sin(angle)));
    }
}
