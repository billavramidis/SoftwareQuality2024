package minicraft.screen;

import minicraft.core.Game;
import minicraft.util.Utils;

public class TempDisplay extends Display {

    private int milliDelay;

    public TempDisplay(int milliDelay) {
        this.milliDelay = milliDelay;
    }

    public TempDisplay(int milliDelay, Menu... menus) {
        super(menus);
        this.milliDelay = milliDelay;
    }

    public TempDisplay(int milliDelay, boolean clearScreen) {
        super(clearScreen);
        this.milliDelay = milliDelay;
    }

    public TempDisplay(int milliDelay, boolean clearScreen, Menu... menus) {
        super(clearScreen, menus);
        this.milliDelay = milliDelay;
    }

    public TempDisplay(int milliDelay, boolean clearScreen, boolean canExit) {
        super(clearScreen, canExit);
        this.milliDelay = milliDelay;
    }

    public TempDisplay(int milliDelay, boolean clearScreen, boolean canExit, Menu... menus) {
        super(clearScreen, canExit, menus);
        this.milliDelay = milliDelay;
    }

    @Override
    public void init(Display parent) {
        super.init(parent);

        new Thread(() -> {
            Utils.sleep(milliDelay);
            if (Game.getDisplay() == this) {
                Game.exitDisplay();
            }
        }).start();
    }
}
