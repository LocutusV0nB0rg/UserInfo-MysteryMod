package de.kyleonaut.userinfo.controller;

import de.kyleonaut.userinfo.entity.MojangUser;
import de.kyleonaut.userinfo.entity.SocialUser;
import lombok.Getter;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
@Singleton
public class UserController {
  @Getter
  private final List<SocialUser> scammers;
  @Getter
  private final List<SocialUser> trustedMMs;

  public UserController() {
    this.scammers = new ArrayList<>();
    this.trustedMMs = new ArrayList<>();
  }

  public boolean isScammer(UUID uuid) {
    final Optional<SocialUser> user = this.scammers.stream()
      .filter(socialUser -> socialUser.getUuid().equals(uuid))
      .findFirst();
    return user.isPresent();
  }

  public boolean isTrustedMM(UUID uuid) {
    final Optional<SocialUser> user = this.trustedMMs.stream()
      .filter(socialUser -> socialUser.getUuid().equals(uuid))
      .findFirst();
    return user.isPresent();
  }

  public MojangUser formatMojangUser(MojangUser mojangUser) {
    final MojangUser user = new MojangUser();
    user.setName(mojangUser.getName());
    user.setId(mojangUser.getId().replaceAll(
      "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
      "$1-$2-$3-$4-$5"));
    return user;
  }
}
