package minicraft.screen;

import minicraft.core.Game;
import minicraft.core.io.InputHandler;
import minicraft.core.io.Localization;
import minicraft.entity.mob.Player;
import minicraft.graphic.Color;
import minicraft.graphic.Font;
import minicraft.graphic.FontStyle;
import minicraft.graphic.Screen;
import minicraft.item.RemoveInventoryStrategy;

public class PlayerInvDisplay extends Display {

	private final Player player;

	public PlayerInvDisplay(Player player) {
		super(new InventoryMenu(player, player.getInventory(), "Inventory"));
		this.player = player;
	}

	@Override
	public void tick(InputHandler input) {
		super.tick(input);

		if (!menus[0].searcherBarActive && input.getKey("menu").clicked) {
			Game.exitDisplay();
			return;
		}

		if (input.getKey("attack").clicked && menus[0].getNumOptions() > 0) {
			int index = menus[0].getSelection();  // Get the selected index
			player.activeItem = player.getInventory().get(index);  // Get the item BEFORE removal
			player.getInventory().executeStrategy(RemoveInventoryStrategy.class, index);  // Remove it

			Game.exitDisplay();
		}
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);
		
		if (!menus[0].searcherBarActive) {
			String text = "(" + Game.input.getMapping("SEARCHER-BAR") + ") " + Localization.getLocalized("to search");
			FontStyle style = new FontStyle(Color.WHITE).setShadowType(Color.BLACK, true).setXPos(menus[0].getBounds().getLeft() + 2).setYPos(menus[0].getBounds().getHeight() + 10);
			Font.drawParagraph(text, screen, style, 1);
		}
	}
}
