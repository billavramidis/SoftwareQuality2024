package minicraft.entity.mob;

import minicraft.core.io.Settings;
import minicraft.entity.Entity;
import minicraft.graphic.MobSprite;
import minicraft.item.Items;

public class Snake extends EnemyMob {
    private static MobSprite[][][] sprites;
    static {
        sprites = new MobSprite[4][4][2];
        for (int i = 0; i < 4; i++) {
            MobSprite[][] list = MobSprite.compileMobSpriteAnimations(10, 10 + (i * 2));
            sprites[i] = list;
        }
    }

    public Snake(int lvl) {
        super(lvl, sprites, lvl > 1 ? 8 : 7, 100);
    }

    @Override
    protected void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            int damage = lvl + Settings.getIndex("diff");
            ((Player) entity).hurt(this, damage);
        }
    }

    @Override
    public void die() {
        int num = Settings.get("diff").equals("Hard") ? 1 : 0;
        dropItem(num, num + 1, Items.get("scale"));

        if (random.nextInt(24 / lvl / (Settings.getIndex("diff") + 1)) == 0) {
            dropItem(1, 1, Items.get("key"));
        }

        super.die();
    }
}
