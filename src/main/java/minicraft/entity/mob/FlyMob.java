package minicraft.entity.mob;

import minicraft.core.Game;
import minicraft.core.Updater;
import minicraft.core.io.Settings;
import minicraft.graphic.MobSprite;
import minicraft.graphic.Screen;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import minicraft.level.tile.Tiles;

public class FlyMob extends MobAi {
	/**
	 * Constructor for a non-hostile (passive) mob.
	 * healthFactor = 3.
	 * @param sprites The mob's sprites.
	 */
	public FlyMob(MobSprite[][] sprites) {
		this(sprites, 3);
	}
	
	/**
	 * Constructor for a non-hostile (passive) mob.
	 * @param sprites The mob's sprites.
	 * @param healthFactor Determines the mobs health. Will be multiplied by the difficulty
	 * and then added with 5.
	 */
	public FlyMob(MobSprite[][] sprites, int healthFactor) {
		super(sprites, 5 + healthFactor * Settings.getIndex("diff"), 5 * 60 * Updater.normalSpeed, 45, 40);
	}
	
	@Override
	public void render(Screen screen) {
		super.render(screen);
	}
	
    @Override
    public boolean canSwim() {
        return true;
    }
	
	public void die() {
		super.die(15);
	}
	
	/**
	 * Checks a given position in a given level to see if the mob can spawn there.
	 * Passive mobs can only spawn on grass or flower tiles.
	 * @param level The level which the mob wants to spawn in.
	 * @param x X map spawn coordinate.
	 * @param y Y map spawn coordinate.
	 * @return true if the mob can spawn here, false if not.
	 */
	public static boolean checkStartPos(Level level, int x, int y) {
		
		// Get no-mob radius by
		int r = (Game.isMode("score") ? 22 : 15) + (Updater.getTime() == Updater.Time.Night ? 0 : 5); 
		
		if (!MobAi.checkStartPos(level, x, y, 120, r)) {
			return false;
		}
		
		Tile tile = level.getTile(x >> 4, y >> 4);
		return tile == Tiles.get("Oak tree") || tile == Tiles.get("Birch tree") || tile == Tiles.get("Rock") || tile == Tiles.get("Up Rock") || tile == Tiles.get("Lawn") || tile == Tiles.get("Grass");
		
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
}