package com.example.preventoffhand.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyboardInputMixin {

    @Inject(method = "onKeyPressed", at = @At("HEAD"), cancellable = true)
    private static void onKeyPress(int keyCode, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null && client.options != null) {
            if (client.options.swapHandsKey.matchesKey(keyCode, 0)) {
                if (client.currentScreen != null && !client.player.getOffHandStack().isEmpty()) {
                    ci.cancel();
                }
            }
        }
    }
}
