package de.kyleonaut.userinfo;

import com.google.inject.Binder;
import com.google.inject.Module;
import de.kyleonaut.userinfo.provider.MojangRepositoryProvider;
import de.kyleonaut.userinfo.provider.SocialRepositoryProvider;
import de.kyleonaut.userinfo.repository.MojangRepository;
import de.kyleonaut.userinfo.repository.SocialRepository;

public class GuiceInjectModule implements Module {
  public static GuiceInjectModule create() {
    return new GuiceInjectModule();
  }

  @Override
  public void configure(Binder binder) {
    binder.bind(SocialRepository.class).toProvider(SocialRepositoryProvider.class);
    binder.bind(MojangRepository.class).toProvider(MojangRepositoryProvider.class);
  }

  private <T> Class<? extends T> findVersionClass(String classPath) {
    try {
      Class<?> versionClass = Class.forName("de.kyleonaut.userinfo.version_specific." + classPath);
      return (Class<? extends T>) versionClass;
    } catch (ClassNotFoundException classNotFoundFailure) {
      throw new RuntimeException(classNotFoundFailure);
    }
  }
}