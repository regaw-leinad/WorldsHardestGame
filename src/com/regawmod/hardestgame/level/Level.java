package com.regawmod.hardestgame.level;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.slick.interfaces.Renderable;
import com.regawmod.slick.interfaces.Updatable;

public abstract class Level implements Updatable, Renderable
{
    private Polygon boundingPoly;
    private List<Enemy> enemies;
    private Image levelImage;

    public Level()
    {
        this.enemies = new ArrayList<Enemy>();
        this.boundingPoly = new Polygon();

        loadLevelImage();
        initBoundingPolygon();
        initEnemies();
        initGoldCoins();
    }

    private void loadLevelImage()
    {
        try
        {
            this.levelImage = new Image("res/testGame1.png");
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected abstract void initBoundingPolygon();

    protected abstract void initEnemies();

    protected abstract void initGoldCoins();

    public abstract float getPlayerStartX();

    public abstract float getPlayerStartY();

    protected void addEnemy(Enemy e)
    {
        if (!this.boundingPoly.contains(e.getBody()))
            throw new IllegalStateException();
        this.enemies.add(e);
    }

    public List<Enemy> getEnemies()
    {
        return this.enemies;
    }

    protected void addPolygonPoint(float x, float y)
    {
        this.boundingPoly.addPoint(x, y);
    }

    protected void setPolygonPoints(float[] points)
    {
        this.boundingPoly = new Polygon(points);
    }

    public final boolean collidesWithWall(Entity entity)
    {
        return !this.boundingPoly.contains(entity.getBody());
        //return boundingPoly.intersects(entity.getBody());
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        updateEnemies(gc, dt);
    }

    private void updateEnemies(GameContainer gc, float dt)
    {
        for (Enemy e : this.enemies)
            e.update(gc, dt);

        removeFlaggedEnemies();
    }

    private void removeFlaggedEnemies()
    {
        for (int i = this.enemies.size() - 1; i >= 0; i--)
            if (this.enemies.get(i).shouldRemove())
                this.enemies.remove(i);
    }

    @Override
    public final void render(Graphics g)
    {
        this.levelImage.draw();

        g.setColor(Color.cyan);
        g.draw(this.boundingPoly);

        g.setColor(Color.white);
        for (Enemy e : this.enemies)
            e.render(g);
    }
}
