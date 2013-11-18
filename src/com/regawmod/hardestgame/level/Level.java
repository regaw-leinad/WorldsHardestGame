package com.regawmod.hardestgame.level;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.ResourceManager;
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
    /** The player in the level */
    private Player player;
    /** If the player has died */
    private boolean playerDied;

    /** The level's background image */
    private Image levelImage;
    /** Our level's bounding polygon */
    private Polygon boundingPoly;
    /** Extra bounding polygons inside of the level */
    private ArrayList<Polygon> insideLevelPolygons;
    /** The bounding poly for the start zone */
    private Polygon startZone;
    /** The bounding poly for the end zone */
    private Polygon endZone;

    /** If the level has been completed */
    private boolean levelCompleted;

    /** The collection of enemies in the level */
    private ArrayList<Enemy> enemies;
    /** The collection of gold coins in the level */
    private ArrayList<GoldCoin> goldCoins;

    /** The number of gold coins collected by the player */
    private int coinsCollected;
    /** The total number of coins in the level */
    private int totalCoins;

    /**
     * Creates a new {@link Level}.
     */
    protected Level()
    {
        this.playerDied = false;
        this.levelCompleted = false;
        this.coinsCollected = 0;

        loadLevelImage();

        initBoundingPolygon();
        initInsidePolygons();
        initStartZonePolygon();
        initEndZonePolygon();
        initPlayer();

        checkZoneStates();

        initEnemies();
        initGoldCoins();
    }

    /**
     * Gets a float array containing the x, y coordinates of the bounding polygon of the level
     * 
     * @return An array of points in the order [x1, y1, x2, y2...]
     */
    protected abstract float[] getBoundingPolygon();

    /**
     * Gets a float array containing the x, y coordinates of the start zone's bounding polygon
     * 
     * @return An array of points in the order [x1, y1, x2, y2...]
     */
    protected abstract float[] getStartZoneBoundingPolygon();

    /**
     * Gets a float array containing the x, y coordinates of the end zone's bounding polygon
     * 
     * @return An array of points in the order [x1, y1, x2, y2...]
     */
    protected abstract float[] getEndZoneBoundingPolygon();

    /**
     * Gets a list of float arrays containing the x, y coordinates for each of the inside bounding polygons
     * 
     * @return An ArrayList of float arrays each containing points in the order [x1, y1, x2, y2...]
     */
    protected abstract ArrayList<float[]> getInsideLevelBoundingPolygons();

    /**
     * Gets a value indicating the starting X coordinate of the player.
     * 
     * @return The starting X coordinate of the player
     */
    public abstract float getPlayerStartX();

    /**
     * Gets a value indicating the starting Y coordinate of the player.
     * 
     * @return The starting Y coordinate of the player
     */
    public abstract float getPlayerStartY();

    /**
     * Gets an ArrayList containing all of the enemies in the level.
     * 
     * @return An ArrayList containing all of the enemies in the level
     */
    protected abstract ArrayList<Enemy> getEnemies();

    /**
     * Gets an ArrayList containing all of the gold coins in the level.
     * 
     * @return An ArrayList containing all of the gold coins in the level
     */
    protected abstract ArrayList<GoldCoin> getGoldCoins();

    /**
     * Initialize and add the enemies for the level here.
     */
    private void initEnemies()
    {
        this.enemies = getEnemies();

        if (this.enemies == null)
            this.enemies = new ArrayList<Enemy>();
    }

    /**
     * Initialize and add the gold coins for the level here.
     */
    private void initGoldCoins()
    {
        this.goldCoins = getGoldCoins();

        if (this.goldCoins == null)
            this.goldCoins = new ArrayList<GoldCoin>();

        this.totalCoins = this.goldCoins.size();
    }

    /**
     * Initialize the bounding polygon for the level here.
     */
    private void initBoundingPolygon()
    {
        float[] points = this.getBoundingPolygon();

        if (points == null)
            points = new float[0];

        if (points.length < 8)
            throw new IllegalArgumentException("Bounding polygon is not set up correctly!");

        this.boundingPoly = new Polygon(points);
    }

    /**
     * Initialize the bounding polygon for the start zone here.
     */
    private void initStartZonePolygon()
    {
        this.startZone = new Polygon();

        float[] points = this.getStartZoneBoundingPolygon();

        if (points == null)
            points = new float[0];

        if (points.length < 8)
            throw new IllegalArgumentException("Start zone's bounding polygon is not set up correctly!");

        this.startZone = new Polygon(points);
    }

    /**
     * Initialize the bounding polygon for the start zone here.
     */
    private void initEndZonePolygon()
    {
        this.endZone = new Polygon();

        float[] points = this.getEndZoneBoundingPolygon();

        if (points == null)
            points = new float[0];

        if (points.length < 8)
            throw new IllegalArgumentException("End zone's bounding polygon is not set up correctly!");

        this.endZone = new Polygon(points);
    }

    /**
     * Initialize all of the bounding polygons inside of the level's bounds.
     */
    private void initInsidePolygons()
    {
        this.insideLevelPolygons = new ArrayList<Polygon>();

        ArrayList<float[]> points = getInsideLevelBoundingPolygons();

        if (points != null)
        {
            for (int i = 0; i < points.size(); i++)
            {
                float[] newPoints = points.get(i);

                if (newPoints.length < 8)
                    throw new IllegalArgumentException("Inside bounding polygon #" + (i + 1) + " is not set up correctly!");

                this.insideLevelPolygons.add(new Polygon(newPoints));
            }
        }
    }

    /**
     * Initialize the player's start position here.
     */
    private void initPlayer()
    {
        this.player = new Player(this);
    }

    /**
     * Gets a value indicating if all of the gold coins have been collected.
     * 
     * @return If all of the gold coins have been collected
     */
    private boolean allCoinsCollected()
    {
        return this.coinsCollected == this.totalCoins;
    }

    /**
     * Checks the ending conditions of the level.
     * Has the level been completed?
     */
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

    /**
     * Checks that the bounding polygons have been set up correctly.
     * Also makes sure that the player starts in the start zone.
     */
    private void checkZoneStates()
    {
        if (this.boundingPoly.getPointCount() < 4)
            throw new IllegalStateException("Bounding polygon is not set up correctly!");

        if (this.startZone.getPointCount() < 4)
            throw new IllegalStateException("Start zone is not set up correctly!");

        if (this.endZone.getPointCount() < 4)
            throw new IllegalStateException("End zone is not set up correctly!");

        if (!this.startZone.contains(this.player.getBody()))
            throw new IllegalStateException("Player must start in the Start zone!");
    }

    /**
     * Gets a value indicating if the entity collided with an enemy.
     * 
     * @param entity The entity to check for collisions
     * @return If the entity collided with an enemy
     */
    public final boolean collidesWithEnemy(Entity entity)
    {
        for (Entity e : this.enemies)
            if (entity.getBody().intersects(e.getBody()) && !e.equals(entity))
                return true;

        return false;
    }

    /**
     * Gets a value indicating if the entity collided with a gold coin.
     * 
     * @param entity The entity to check for collisions
     * @return If the entity collided with a gold coin
     */
    public final boolean collidesWithGoldCoin(Entity entity)
    {
        boolean collided = false;

        for (GoldCoin coin : this.goldCoins)
        {
            if (!coin.hasBeenCollected() && entity.getBody().intersects(coin.getBody()))
            {
                onGoldCoinCollected(coin);

                coin.flagAsCollected();
                collided = true;
            }
        }

        return collided;
    }

    /**
     * Gets a value indicating if the entity collided with the level walls.
     * 
     * @param entity The entity to check for collisions
     * @return If the entity collided with the level walls
     */
    public final boolean collidesWithWall(Entity entity)
    {
        for (Polygon p : this.insideLevelPolygons)
            if (entity.getBody().intersects(p))
                return true;

        // We need this !contains instead of intersects because large deltas 
        // might bring the entity outside of the bounding poly
        return !this.boundingPoly.contains(entity.getBody());
    }

    /**
     * Gets a value indicating if the entity collided with the start or end zone.
     * 
     * @param entity The entity to check for collisions
     * @return If the entity collided with the start or end zone
     */
    public final boolean collidesWithZones(Entity entity)
    {
        return entity.getBody().intersects(this.startZone) || this.startZone.contains(entity.getBody()) ||
                entity.getBody().intersects(this.endZone) || this.endZone.contains(entity.getBody());
    }

    /**
     * Gets a value indicating the player's current X position.
     * 
     * @return The player's current X position
     */
    public final float getPlayerX()
    {
        return this.player.getCenterX();
    }

    /**
     * Gets a value indicating the player's current Y position.
     * 
     * @return The player's current Y position
     */
    public final float getPlayerY()
    {
        return this.player.getCenterY();
    }

    /**
     * Gets a value indicating if the player has died.
     * 
     * @return If the player has died
     */
    public final boolean hasPlayerDied()
    {
        return this.playerDied;
    }

    /**
     * Gets a value indicating if the level has been completed.
     * 
     * @return If the level has been completed
     */
    public final boolean isLevelComplete()
    {
        return this.levelCompleted;
    }

    /**
     * Loads the level image so we can render it.
     */
    private void loadLevelImage()
    {
        this.levelImage = ResourceManager.getLevelImage(this.getClass().getSimpleName());
    }

    /**
     * Notifies all enemies that the player has collected a gold coin.
     * 
     * @param coin The coin that was collected
     */
    private void onGoldCoinCollected(GoldCoin coin)
    {
        for (Enemy e : this.enemies)
            e.onCoinCollected(coin.getCenterX(), coin.getCenterY(), this.totalCoins - this.coinsCollected);
    }

    /**
     * Notifies all enemies that the player has died.
     */
    private void onPlayerDeath()
    {
        for (Enemy e : this.enemies)
            e.onPlayerDeath();
    }

    /**
     * Notifies all enemies that the player has just respawned.
     */
    private void onPlayerRespawn()
    {
        for (Enemy e : this.enemies)
            e.onPlayerRespawn();
    }

    /**
     * Gets a value indicating if the player has beat the level.
     * 
     * @return If the player has beat the level
     */
    private boolean playerHasWon()
    {
        return allCoinsCollected() && playerInEndZone();
    }

    /**
     * Gets a value indicating if the player is in the end zone.
     * 
     * @return If the player is in the end zone
     */
    private boolean playerInEndZone()
    {
        return this.endZone.contains(this.player.getCenterX(), this.player.getCenterY());
    }

    /**
     * Removes all enemies flagged for removal.
     */
    private void removeFlaggedEnemies()
    {
        for (int i = this.enemies.size() - 1; i >= 0; i--)
            if (this.enemies.get(i).shouldRemove())
                this.enemies.remove(i);
    }

    /**
     * Removes all gold coins flagged for removal.
     */
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

    @Override
    public final void render(Graphics g)
    {
        this.levelImage.draw(0, 0);

        renderEnemies(g);
        renderPlayer(g);
        renderGoldCoins(g);
    }

    /**
     * Renders all of the enemies in the level.
     * 
     * @param g The graphics object
     */
    private void renderEnemies(Graphics g)
    {
        g.setColor(Color.white);
        for (Entity e : this.enemies)
            e.render(g);

    }

    /**
     * Renders all of the gold coins in the level.
     * 
     * @param g The graphics object
     */
    private void renderGoldCoins(Graphics g)
    {
        g.setColor(Color.white);
        for (Entity e : this.goldCoins)
            e.render(g);
    }

    /**
     * Renders the player.
     * 
     * @param g The graphics object
     */
    private void renderPlayer(Graphics g)
    {
        g.setColor(Color.white);
        this.player.render(g);
    }

    /**
     * Resets the level after a player has died.
     * "Revives" the player.
     * Resets all gold coins to original positions.
     */
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

        onPlayerRespawn();
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        updateEnemies(gc, dt);
        updateGoldCoins(gc, dt);
        updatePlayer(gc, dt);

        checkLevelState();
    }

    /**
     * Updates the enemies in the game.
     * 
     * @param gc The game container
     * @param dt The delta time
     */
    private void updateEnemies(GameContainer gc, float dt)
    {
        for (Entity e : this.enemies)
            e.update(gc, dt);

        removeFlaggedEnemies();
    }

    /**
     * Updates the gold coins in the game.
     * 
     * @param gc The game container
     * @param dt The delta time
     */
    private void updateGoldCoins(GameContainer gc, float dt)
    {
        for (Entity g : this.goldCoins)
            g.update(gc, dt);

        removeFlaggedGoldCoins();
    }

    /**
     * Updates the player in the game.
     * 
     * @param gc The game container
     * @param dt The delta time
     */
    private void updatePlayer(GameContainer gc, float dt)
    {
        this.playerDied = false;
        this.player.update(gc, dt);
    }
}
