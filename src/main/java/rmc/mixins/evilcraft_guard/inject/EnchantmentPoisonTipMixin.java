package rmc.mixins.evilcraft_guard.inject;

import org.cyclops.evilcraft.enchantment.EnchantmentPoisonTip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import rmc.libs.event_factory.EventFactory;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = EnchantmentPoisonTip.class)
public abstract class EnchantmentPoisonTipMixin {

    @Inject(method = "Lorg/cyclops/evilcraft/enchantment/EnchantmentPoisonTip;poisonTipEvent(Lnet/minecraftforge/event/entity/living/LivingAttackEvent;)V",
            remap = false,
            cancellable = true,
            at = @At(value = "INVOKE",
                     target = "Lorg/cyclops/evilcraft/enchantment/EnchantmentPoisonTip;poison(Lnet/minecraft/entity/LivingEntity;I)V"))
    private void guardPoisonAttack(LivingAttackEvent event, CallbackInfo mixin) {
        if (!EventFactory.testEntityInteract(EventFactory.convert(event.getSource().getEntity()), event.getEntity().level, event.getEntity())) {
            mixin.cancel();
        }
    }

}