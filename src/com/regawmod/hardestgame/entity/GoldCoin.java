package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.level.Level;

public class GoldCoin extends Entity
{
    public static final float FADE_ALPHA_PER_SEC = 2f;

    private Color insideColor;
    private Color outsideColor;
    private boolean collected;

    public GoldCoin(float x, float y, Level level)
    {
        super(new Circle(x, y, 6));

        this.insideColor = new Color(255, 216, 0);
        this.outsideColor = new Color(Color.black);
        this.collected = false;
    }

    public void flagAsCollected()
    {
        this.collected = true;
    }

    public boolean hasBeenCollected()
    {
        return this.collected;
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        if (hasBeenCollected())
        {
            this.insideColor.a -= FADE_ALPHA_PER_SEC * dt;
            this.outsideColor.a -= FADE_ALPHA_PER_SEC * dt;

            if (this.insideColor.a <= 0)
                this.flagForRemoval();
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(this.outsideColor);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(this.insideColor);
        g.fillOval(this.getX() + 2, this.getY() + 2, this.getWidth() - 4, this.getHeight() - 4);
    }
}
