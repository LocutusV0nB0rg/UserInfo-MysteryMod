package de.kyleonaut.userinfo.version_specific;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class TestMixin {

  @Inject(method = "startGame", at = @At(value = "HEAD"))
  public void startGame(CallbackInfo callbackInfo) {

  }
}
