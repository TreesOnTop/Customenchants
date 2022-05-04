package net.fabricmc.example.enchants;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.tick.SimpleTickScheduler;

public class Lifesteal extends Enchantment {
    public Lifesteal() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    boolean hit = false;
    @Override
    public int getMinPower(int level) {
        return 10*level;
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        Thread t = new Thread(() -> Lifestealf(user, target, level));
        t.start();
    }
    private void Lifestealf(LivingEntity user, Entity target, int level) {
        if(!(target instanceof PlayerEntity) && target instanceof LivingEntity && !(user.getWorld().isClient())) {
            if(!hit) {
                hit = true;
                if (Math.random() < (double) level / (100.0 / 15)) {
                    user.setHealth(user.getHealth() + 1);
                    if (user.getHealth() > user.getMaxHealth()) {
                        user.setHealth(user.getMaxHealth());
                    }
                }
            }
        } try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hit = false;
    }
}