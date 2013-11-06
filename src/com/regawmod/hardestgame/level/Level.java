package com.regawmod.hardestgame.level;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.Resources;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.entity.Player;
import com.regawmod.slick.interfaces.Renderable;
import com.regawmod.slick.interfaces.Updatable;

/**
 * An abstract level for the game.
 * 
 * @author Dan Wager
 */
public abstract class Level implements Updatable, Renderable
{
    /** The offset of the level in the window, public for now :( */
    public static final float LEVEL_OFFSET = 60f;

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

    private float playerStartX;
    private float playerStartY;

    private boolean playerDied;

    public Level()
    {
        this.enemies = new ArrayList<Enemy>();
        this.goldCoins = new ArrayList<GoldCoin>();

        this.boundingPoly = new Polygon();
        this.startZone = new Polygon();
        this.endZone = new Polygon();

        this.zoneColor = new Color(181, 254, 180);

        this.playerStartX = 0;
        this.playerStartY = 0;

        loadLevelImage();

        initBoundingPolygon();
        initStartZonePolygon();
        initEndZonePolygon();
        initPlayerStartPosition();

        checkZoneStates();

        initEnemies();
        initGoldCoins();

        this.totalCoins = this.goldCoins.size();
        this.coinsCollected = 0;

        this.levelCompleted = false;

        this.playerDied = false;
    }

    protected final void setPlayerStartPosition(float x, float y)
    {
        this.playerStartX = x;
        this.playerStartY = y + LEVEL_OFFSET;

        this.player = new Player(this);
    }

    private void checkZoneStates()
    {
        if (this.boundingPoly.getPointCount() < 4)
            throw new IllegalStateException("Bounding polygon is not set up correctly!");

        if (this.startZone.getPointCount() < 4)
            throw new IllegalStateException("Start zone is not set up correctly!");

        if (this.endZone.getPointCount() < 4)
            throw new IllegalStateException("End zone is not set up correctly!");

        if (!this.startZone.contains(this.player.getBody()))
            throw new IllegalStateException("Player must start in the Start zone.");
    }

    private void loadLevelImage()
    {
        this.levelImage = Resources.getLevelImage(this.getClass().getSimpleName());
        System.out.println();
        //new Image(LevelLoader.LEVEL_RES_DIRECTORY + File.separator + this.getClass().getSimpleName() + ".png");
    }

    protected abstract void initBoundingPolygon();

    protected abstract void initStartZonePolygon();

    protected abstract void initEndZonePolygon();

    protected abstract void initPlayerStartPosition();

    protected abstract void initEnemies();

    protected abstract void initGoldCoins();

    public abstract String getName();

    protected final void addEnemy(Enemy enemy)
    {
        enemy.setCenterY(enemy.getCenterY() + LEVEL_OFFSET);

        if (enemy.isBoundedByLevel() && !this.boundingPoly.contains(enemy.getBody()))
            throw new IllegalStateException("Bounded Enemy at x:" + enemy.getCenterX() + " y:" +
                    (enemy.getCenterY() - LEVEL_OFFSET) + " is placed out of bounds of the level!");

        this.enemies.add(enemy);
    }

    protected final void addEnemies(List<Enemy> enemies)
    {
        for (Enemy e : enemies)
        {
            e.setCenterY(e.getCenterY() + LEVEL_OFFSET);

            if (e.isBoundedByLevel() && !this.boundingPoly.contains(e.getBody()))
                throw new IllegalStateException("Bounded Enemy at x:" + e.getCenterX() + " y:" +
                        (e.getCenterY() - LEVEL_OFFSET) + " is placed out of bounds of the level!");

            this.enemies.add(e);
        }
    }

    protected final void addGoldCoin(GoldCoin goldCoin)
    {
        goldCoin.setCenterY(goldCoin.getCenterY() + LEVEL_OFFSET);

        if (!this.boundingPoly.contains(goldCoin.getBody()))
            throw new IllegalStateException("GoldCoin at x:" + goldCoin.getCenterX() + " y:" +
                    (goldCoin.getCenterY() - LEVEL_OFFSET) + " is placed out of bounds of the level!");

        this.goldCoins.add(goldCoin);
    }

    protected final void addBoundingPolygonPoint(float x, float y)
    {
        this.boundingPoly.addPoint(x, y + LEVEL_OFFSET);
    }

    protected final void addStartZonePolygonPoint(float x, float y)
    {
        this.startZone.addPoint(x, y + LEVEL_OFFSET);
    }

    protected final void addEndZonePolygonPoint(float x, float y)
    {
        this.endZone.addPoint(x, y + LEVEL_OFFSET);
    }

    public final boolean collidesWithZones(Entity entity)
    {
        return entity.getBody().intersects(this.startZone) || this.startZone.contains(entity.getBody()) ||
                entity.getBody().intersects(this.endZone) || this.endZone.contains(entity.getBody());
    }

    public final boolean collidesWithWall(Entity entity)
    {
        // We need this !contains because large deltas 
        // might bring the entity outside of the bounding poly
        return !this.boundingPoly.contains(entity.getBody());
        //        return boundingPoly.intersects(entity.getBody());
    }

    public final boolean collidesWithEnemy(Entity entity)
    {
        for (Entity e : this.enemies)
            if (entity.getBody().intersects(e.getBody()) && !e.equals(entity))
                return true;

        return false;
    }

    public final boolean collidesWithGoldCoin(Entity entity)
    {
        boolean collided = false;

        for (GoldCoin coin : this.goldCoins)
        {
            if (entity.getBody().intersects(coin.getBody()))
            {
                onGoldCoinCollected(coin);

                coin.flagAsCollected();
                collided = true;
            }
        }

        return collided;
    }

    private final void resetLevelAfterEnemyCollision()
    {
        this.player.revive();
        this.playerDied = true;

        if (this.coinsCollected > 0)
        {
            this.coinsCollected = 0;
            this.goldCoins.clear();
            initGoldCoins();
        }
    }

    private void onPlayerDeath()
    {
        for (Enemy e : this.enemies)
            e.onPlayerDeath();
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        updateEnemies(gc, dt);
        updateGoldCoins(gc, dt);
        updatePlayer(gc, dt);

        checkLevelState();
    }

    private void checkLevelState()
    {
        if (this.player.hasDied())
        {
            onPlayerDeath();
        }
        else if (this.player.shouldRevive())
        {
            resetLevelAfterEnemyCollision();
        }
        else if (playerHasWon())
        {
            this.levelCompleted = true;
        }
    }

    private boolean playerHasWon()
    {
        return allCoinsCollected() && playerInEndZone();
    }

    private boolean playerInEndZone()
    {
        return this.endZone.contains(this.player.getCenterX(), this.player.getCenterY());
    }

    private boolean allCoinsCollected()
    {
        return this.coinsCollected == this.totalCoins;
    }

    @Override
    public final void render(Graphics g)
    {
        this.levelImage.draw(0, LEVEL_OFFSET);

        renderZones(g);

        //drawBoundingPoly(g);

        renderEnemies(g);
        renderPlayer(g);
        renderGoldCoins(g);
    }

    private void drawBoundingPoly(Graphics g)
    {
        g.setColor(Color.cyan);
        g.draw(this.boundingPoly);
    }

    private void renderZones(Graphics g)
    {
        g.setColor(this.zoneColor);
        g.fill(this.startZone);
        g.fill(this.endZone);
    }

    private void updatePlayer(GameContainer gc, float dt)
    {
        this.playerDied = false;
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
        g.setColor(Color.white);
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

    private void onGoldCoinCollected(GoldCoin coin)
    {
        for (Enemy e : this.enemies)
            e.onCoinCollected(coin.getCenterX(), coin.getCenterY());
    }

    public final boolean isLevelComplete()
    {
        return this.levelCompleted;
    }

    public final float getPlayerStartX()
    {
        return this.playerStartX;
    }

    public final float getPlayerStartY()
    {
        return this.playerStartY;
    }

    public final boolean hasPlayerDied()
    {
        return this.playerDied;
    }
}
