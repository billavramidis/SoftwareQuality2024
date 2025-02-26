package minicraft.entity.mob;

import minicraft.core.Game;
import minicraft.core.Updater;
import minicraft.core.io.Settings;
import minicraft.graphic.MobSprite;
import minicraft.graphic.Screen;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import minicraft.level.tile.Tiles;

public class VillagerMob extends MobAi {

	/**
	 * Constructor for a non-hostile (Villager) mob. healthFactor = 3.
	 * 
	 * @param sprites The mob's sprites.
	 */
	public VillagerMob(MobSprite[][] sprites) {
		this(sprites, 3);
	}

	/**
	 * Constructor for a non-hostile (Villager) mob.
	 * 
	 * @param sprites      The mob's sprites.
	 * @param healthFactor Determines the mobs health. Will be multiplied by the
	 *                     difficulty and then added with 5.
	 */
	public VillagerMob(MobSprite[][] sprites, int healthFactor) {
		super(sprites, 5 + healthFactor * Settings.getIndex("diff"), -1, 45, 40);
	}

	public void tick() {
		super.tick();

	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
	}

	@Override
	public void randomizeWalkDir(boolean byChance) {
		if (xa == 0 && ya == 0 && random.nextInt(5) == 0 || byChance || random.nextInt(randomWalkChance) == 0) {
			randomWalkTime = randomWalkDuration;
			// multiple at end ups the chance of not moving by 50%.
			xa = (random.nextInt(3) - 1) * random.nextInt(2);
			ya = (random.nextInt(3) - 1) * random.nextInt(2);
		}
	}

	public void die() {
		super.die(15);
	}

	/**
	 * Checks a given position in a given level to see if the mob can spawn there.
	 * Passive mobs can only spawn on grass or flower tiles.
	 * 
	 * @param level The level which the mob wants to spawn in.
	 * @param x     X map spawn coordinate.
	 * @param y     Y map spawn coordinate.
	 * @return true if the mob can spawn here, false if not.
	 */
	public static boolean checkStartPos(Level level, int x, int y) {
		int r = (Game.isMode("score") ? 22 : 15) + (Updater.getTime() == Updater.Time.Night ? 0 : 5); // get no-mob radius by

		if (!MobAi.checkStartPos(level, x, y, 80, r)) {
			return false;
		}

		Tile tile = level.getTile(x >> 4, y >> 4);
		return tile == Tiles.get("Oak Planks") || tile == Tiles.get("path");
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getLightRadius() {
		return 3;
	}
}
