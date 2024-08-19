package dev.microcontrollers.numericalenchantments.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.microcontrollers.numericalenchantments.config.NumericalEnchantmentsConfig;
import net.minecraft.enchantment.Enchantment;
//#if MC >= 1.21
import net.minecraft.registry.entry.RegistryEntry;
//#endif
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @WrapOperation(method = "getName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;append(Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;", ordinal = 1))
    private
    //#if MC >= 1.21
    static
    //#endif
    MutableText appendArabicNumeral(
            MutableText instance,
            Text text,
            Operation<MutableText> original,
            //#if MC >= 1.21
            RegistryEntry<Enchantment> enchantment,
            //#endif
            int level
    ) {
        return NumericalEnchantmentsConfig.CONFIG.instance().enabled ? instance.append(String.valueOf(level)) : original.call(instance, text);
    }
}
