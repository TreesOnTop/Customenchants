package net.fabricmc.example.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Lifesteal extends Enchantment {
    public Lifesteal() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
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
        if(target instanceof LivingEntity && !target.isInvulnerable() && !(target instanceof PlayerEntity)) {
            if(Math.random() < level/4) {
                user.setHealth(user.getHealth() + 1);
                if(user.getHealth() > user.getMaxHealth()) {
                    user.setHealth(user.getMaxHealth());
                }
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}