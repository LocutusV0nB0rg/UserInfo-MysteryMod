package de.kyleonaut.userinfo;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.kyleonaut.userinfo.controller.InfoCommandController;
import de.kyleonaut.userinfo.controller.UserController;
import de.kyleonaut.userinfo.entity.SocialUser;
import de.kyleonaut.userinfo.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import net.mysterymod.api.listener.ListenerChannel;
import net.mysterymod.mod.MysteryMod;
import net.mysterymod.mod.addon.Addon;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserInfoAddon extends Addon {
  private final Logger logger;
  private final Injector injector;
  private final ListenerChannel listenerChannel;
  private final InfoCommandController infoCommandController;
  private final SocialRepository socialRepository;
  private final UserController userController;

  @Override
  public void onEnable() {
    CompletableFuture.runAsync(() -> {
      try {
        final List<SocialUser> middlemans = socialRepository.getAllMiddlemans().execute().body();
        final List<SocialUser> scammers = socialRepository.getAllScammer().execute().body();
        if (middlemans != null) {
          userController.getTrustedMMs().addAll(middlemans);
        }
        if (scammers != null) {
          userController.getScammers().addAll(scammers);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    listenerChannel.registerListener(infoCommandController);
  }
}
