package de.kyleonaut.userinfo.controller;

import de.kyleonaut.userinfo.entity.ListUser;
import de.kyleonaut.userinfo.entity.MojangUser;
import de.kyleonaut.userinfo.repository.MojangRepository;
import lombok.RequiredArgsConstructor;
import net.mysterymod.api.event.EventHandler;
import net.mysterymod.api.event.message.MessageSendEvent;
import net.mysterymod.mod.MysteryMod;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class InfoCommandController {
  private final MojangRepository mojangRepository;
  private final UserController userController;

  @EventHandler
  public void onMessage(MessageSendEvent event) {
    if (!event.getMessage().startsWith(".info")) {
      return;
    }
    event.setCancelled(true);
    final String[] args = event.getMessage().split(" ");
    if (args.length != 2) {
      MysteryMod.getInstance().getMinecraft().addChatMessage("§8[§6UserInfo§8] §7Bitte benutze .info <Spieler>.");
      return;
    }
    CompletableFuture.runAsync(() -> {
      try {
        final MojangUser body = mojangRepository.getMojangUserByName(args[1]).execute().body();
        if (body == null) {
          MysteryMod.getInstance().getMinecraft().addChatMessage("§8[§6UserInfo§8] §7Der Spieler §e" + args[1] + "§7 konnte nicht gefunden werden.");
          return;
        }
        final MojangUser mojangUser = userController.formatMojangUser(body);
        MysteryMod.getInstance().getMinecraft().addChatMessage("§8§m                                §r§8[§6UserInfo§8]§m                                ");
        MysteryMod.getInstance().getMinecraft().addChatMessage("§7Spieler:§e " + mojangUser.getName());
        MysteryMod.getInstance().getMinecraft().addChatMessage("§7UUID:§e " + mojangUser.getId());
        MysteryMod.getInstance().getMinecraft().addChatMessage("§7Scammer:§e " +
          (userController.isScammer(UUID.fromString(mojangUser.getId())) ? "§aJa" : "§cNein"));
        MysteryMod.getInstance().getMinecraft().addChatMessage("§7Trusted-Middleman:§e " +
          (userController.isTrustedMM(UUID.fromString(mojangUser.getId())) ? "§aJa" : "§cNein"));

        MysteryMod.getInstance().getMinecraft().addChatMessage("§7Namensänderungen: ");
        final List<ListUser> listUsers = mojangRepository.getNames(UUID.fromString(mojangUser.getId())).execute().body();
        for (ListUser listUser : listUsers) {
          String date = "Original";
          if (listUser.getChangedToAt() != 0L){
            date = DateTimeFormatter.ofPattern("dd.MM.YYYY").format(new Timestamp(listUser.getChangedToAt()).toLocalDateTime());
          }
          MysteryMod.getInstance().getMinecraft().addChatMessage("§7-§e " + listUser.getName() + "§7 " + date);
        }
        MysteryMod.getInstance().getMinecraft().addChatMessage("§8§m                                §r§8[§6UserInfo§8]§m                                ");
      } catch (IOException e) {
        MysteryMod.getInstance().getMinecraft().addChatMessage("§8[§6UserInfo§8] §7Der Mojang Server ist zur Zeit nicht erreichbar.");
      }
    });
  }
}
