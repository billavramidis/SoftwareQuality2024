package minicraft.screen;

import minicraft.graphic.Dimension;
import minicraft.graphic.Point;
import minicraft.graphic.Rectangle;
import minicraft.util.Utils;

// stands for "Relative Position"
public enum RelPos {
	TOP_LEFT, TOP, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;

	public int xIndex;
	public int yIndex;

	// I think this way, the enums will all be constructed before this gets called,
	// so there won't be any mishaps with number of values.
	static {
		for (RelPos rp : values()) {
			int ord = rp.ordinal();
			rp.xIndex = ord % 3;
			rp.yIndex = ord / 3;
		}
	}

	public static RelPos getPos(int xIndex, int yIndex) {
		return values()[Utils.clamp(xIndex, 0, 2) + Utils.clamp(yIndex, 0, 2) * 3];
	}

	public RelPos getOpposite() {
		int nx = -(xIndex - 1) + 1;
		int ny = -(yIndex - 1) + 1;
		return getPos(nx, ny);
	}

	/**
	 * positions the given rect around the given anchor. The double size is what
	 * aligns it to a point rather than a rect.
	 */
	public Point positionRect(Dimension rectSize, Point anchor) {
		Rectangle bounds = new Rectangle(anchor.x, anchor.y, rectSize.width * 2, rectSize.height * 2, Rectangle.CENTER_DIMS);
		return positionRect(rectSize, bounds);
	}

	// the point is returned as a rectangle with the given dimension and the found
	// location, within the provided dummy rectangle.
	public Rectangle positionRect(Dimension rectSize, Point anchor, Rectangle dummy) {
		Point pos = positionRect(rectSize, anchor);
		dummy.setSize(rectSize, RelPos.TOP_LEFT);
		dummy.setPosition(pos, RelPos.TOP_LEFT);
		return dummy;
	}

	/** positions the given rect to a relative position in the container. */
	public Point positionRect(Dimension rectSize, Rectangle container) {
		Point tlcorner = container.getCenter();

		// this moves the inner box correctly
		tlcorner.x += ((xIndex - 1) * container.getWidth() / 2) - (xIndex * rectSize.width / 2);
		tlcorner.y += ((yIndex - 1) * container.getHeight() / 2) - (yIndex * rectSize.height / 2);

		return tlcorner;
	}

	// the point is returned as a rectangle with the given dimension and the found
	// location, within the provided dummy rectangle.
	public Rectangle positionRect(Dimension rectSize, Rectangle container, Rectangle dummy) {
		Point pos = positionRect(rectSize, container);
		dummy.setSize(rectSize, RelPos.TOP_LEFT);
		dummy.setPosition(pos, RelPos.TOP_LEFT);
		return dummy;
	}
}
