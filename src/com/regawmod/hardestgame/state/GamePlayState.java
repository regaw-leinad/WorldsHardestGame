package com.regawmod.hardestgame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.entity.Player;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.level.TestLevel;

public class GamePlayState extends AbstractGameState
{
    Player player;
    Level level;

    public boolean collidesWithWall(Entity entity)
    {
        return this.level.collidesWithWall(entity);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        level = new TestLevel();
        this.player = new Player(this.level);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        level.update(gc, dt);
        player.update(gc, dt);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        //g.setBackground(Color.white);
        level.render(g);
        player.render(g);
    }

    @Override
    public int getID()
    {
        return GameState.TESTING;
    }
}
