package minicraft.entity.particle;

import minicraft.graphic.Sprite;

public class FerrositeParticle extends Particle {
	/// This is used for the player when steppen on in Ferrosite tile

	/**
	 * Creates a new particle at the given position. It has a lifetime of 30 ticks
	 * and a Ferrosite clouds looking sprite.
	 * 
	 * @param x X map position
	 * @param y Y map position
	 */
	public FerrositeParticle(int x, int y) {
		super(x, y, 8, new Sprite(6, 14, 3));
	}
}