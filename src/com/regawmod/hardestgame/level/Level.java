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
import com.regawmod.hardestgame.GameStats;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.entity.Player;
import com.regawmod.slick.interfaces.Renderable;
import com.regawmod.slick.interfaces.Updatable;

public abstract class Level implements Updatable, Renderable
{
    private GameStats stats;

    private Player player;

    private Polygon boundingPoly;
    private Polygon startZone;
    private Polygon endZone;
    private Color zoneColor;

    private List<Enemy> enemies;
    private List<GoldCoin> goldCoins;

    private Image levelImage;

    private int coinsCollected;
    private int totalCoins;

    private boolean levelCompleted;

    public Level(String levelImageName)
    {
        this.player = new Player(this);

        this.enemies = new ArrayList<Enemy>();
        this.goldCoins = new ArrayList<GoldCoin>();

        this.boundingPoly = new Polygon();
        this.startZone = new Polygon();
        this.endZone = new Polygon();
        this.zoneColor = new Color(181, 254, 180);

        loadLevelImage(levelImageName);
        initBoundingPolygon();
        initStartZonePolygon();
        initEndZonePolygon();
        initEnemies();
        initGoldCoins();

        this.totalCoins = this.goldCoins.size();
        this.coinsCollected = 0;

        this.levelCompleted = false;
    }

    public void setGameStats(GameStats stats)
    {
        this.stats = stats;
    }

    private void loadLevelImage(String levelImageName)
    {
        try
        {
            this.levelImage = new Image("res/" + levelImageName + (levelImageName.endsWith(".png") ? "" : ".png"));
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

    protected abstract void initStartZonePolygon();

    protected abstract void initEndZonePolygon();

    public abstract float getPlayerStartX();

    public abstract float getPlayerStartY();

    protected final void addEnemy(Enemy enemy)
    {
        if (enemy.isBoundedByLevel() && !this.boundingPoly.contains(enemy.getBody()))
            throw new IllegalStateException("Bounded Enemy at x:" + enemy.getCenterX() + " y:" +
                    enemy.getCenterY() + " is placed out of bounds of the level!");

        this.enemies.add(enemy);
    }

    protected final void addGoldCoin(GoldCoin goldCoin)
    {
        if (!this.boundingPoly.contains(goldCoin.getBody()))
            throw new IllegalStateException("GoldCoin at x:" + goldCoin.getCenterX() + " y:" +
                    goldCoin.getCenterY() + " is placed out of bounds of the level!");

        this.goldCoins.add(goldCoin);
    }

    protected final void addBoundingPolygonPoint(float x, float y)
    {
        this.boundingPoly.addPoint(x, y);
    }

    protected final void setBoundingPolygonPoints(float[] points)
    {
        this.boundingPoly = new Polygon(points);
    }

    protected final void addStartZonePolygonPoint(float x, float y)
    {
        this.startZone.addPoint(x, y);
    }

    protected final void setStartZonePolygonPoints(float[] points)
    {
        this.startZone = new Polygon(points);
    }

    protected final void addEndZonePolygonPoint(float x, float y)
    {
        this.endZone.addPoint(x, y);
    }

    protected final void setEndZonePolygonPoints(float[] points)
    {
        this.endZone = new Polygon(points);
    }

    public final boolean collidesWithWall(Entity entity)
    {
        return !this.boundingPoly.contains(entity.getBody());
        //return boundingPoly.intersects(entity.getBody());
    }

    public final boolean collidesWithEnemy(Entity entity)
    {
        for (Entity e : this.enemies)
            if (entity.getBody().intersects(e.getBody()))
                return true;

        return false;
    }

    public final boolean collidesWithGoldCoin(Entity entity)
    {
        for (GoldCoin coin : this.goldCoins)
        {
            if (entity.getBody().intersects(coin.getBody()))
            {
                coin.flagAsCollected();
                return true;
            }
        }

        return false;
    }

    public final void resetLevelAfterEnemyCollision()
    {
        if (this.coinsCollected > 0)
        {
            this.coinsCollected = 0;
            this.goldCoins.clear();
            initGoldCoins();
        }

        this.stats.incrementDeaths();
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        updateEnemies(gc, dt);
        updateGoldCoins(gc, dt);
        updatePlayer(gc, dt);

        updateLevelState();
    }

    private void updateLevelState()
    {
        if (playerHasWon())
            this.levelCompleted = true;
    }

    private boolean playerHasWon()
    {
        return this.endZone.contains(this.player.getCenterX(), this.player.getCenterY()) && this.coinsCollected == this.totalCoins;
    }

    @Override
    public final void render(Graphics g)
    {
        this.levelImage.draw();

        drawZones(g);

        renderEnemies(g);
        renderPlayer(g);
        renderGoldCoins(g);
    }

    private void drawZones(Graphics g)
    {
        g.setColor(this.zoneColor);
        g.fill(this.startZone);
        g.fill(this.endZone);
    }

    private void updatePlayer(GameContainer gc, float dt)
    {
        this.player.update(gc, dt);
    }

    private void updateEnemies(GameContainer gc, float dt)
    {
        for (Entity e : this.enemies)
            e.update(gc, dt);

        removeFlaggedEnemies();
    }

    private void removeFlaggedEnemies()
    {
        for (int i = this.enemies.size() - 1; i >= 0; i--)
            if (this.enemies.get(i).shouldRemove())
                this.enemies.remove(i);
    }

    private void updateGoldCoins(GameContainer gc, float dt)
    {
        for (Entity g : this.goldCoins)
            g.update(gc, dt);

        removeFlaggedGoldCoins();
    }

    private void renderPlayer(Graphics g)
    {
        this.player.render(g);
    }

    private void renderEnemies(Graphics g)
    {
        g.setColor(Color.white);
        for (Entity e : this.enemies)
            e.render(g);
    }

    private void renderGoldCoins(Graphics g)
    {
        g.setColor(Color.white);
        for (Entity e : this.goldCoins)
            e.render(g);
    }

    private void removeFlaggedGoldCoins()
    {
        for (int i = this.goldCoins.size() - 1; i >= 0; i--)
        {
            if (this.goldCoins.get(i).shouldRemove())
            {
                this.goldCoins.remove(i);
                this.coinsCollected++;
            }
        }
    }

    public final boolean isLevelComplete()
    {
        return this.levelCompleted;
    }
}
