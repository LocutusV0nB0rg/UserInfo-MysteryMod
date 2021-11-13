package de.kyleonaut.userinfo.provider;

import com.google.inject.Provider;
import de.kyleonaut.userinfo.repository.MojangRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
public class MojangRepositoryProvider implements Provider<MojangRepository> {
  @Override
  public MojangRepository get() {
    return new Retrofit.Builder()
      .baseUrl("https://api.mojang.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build().create(MojangRepository.class);
  }
}
