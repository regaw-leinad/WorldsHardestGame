package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.level.Level;

/**
 * A gold coin that the player must collect
 * 
 * @author Dan Wager
 */
public final class GoldCoin extends Entity
{
    /** How fast the coin fades out on collection */
    public static final float FADE_ALPHA_PER_SEC = 2f;

    /** The fill color of the coin */
    private Color insideColor;
    /** The border color of the coin */
    private Color outsideColor;
    /** If the coin has been collected */
    private boolean collected;

    /**
     * Creates a new {@link GoldCoin}.
     * 
     * @param x The coin's X position
     * @param y The coin's Y postition
     * @param level The level
     */
    public GoldCoin(float x, float y, Level level)
    {
        super(new Circle(x, y, 6));

        this.insideColor = new Color(255, 216, 0);
        this.outsideColor = new Color(Color.black);
        this.collected = false;
    }

    /**
     * Sets the collected flag.
     */
    public void flagAsCollected()
    {
        this.collected = true;
    }

    /**
     * Gets a value indicating if the coin has been collected.
     * 
     * @return If the coin has been collected
     */
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
