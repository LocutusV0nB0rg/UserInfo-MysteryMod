package de.kyleonaut.userinfo.provider;


import com.google.inject.Provider;
import de.kyleonaut.userinfo.repository.SocialRepository;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
public class SocialRepositoryProvider implements Provider<SocialRepository> {
  @Override
  public SocialRepository get() {
    return new Retrofit.Builder()
      .baseUrl("http://newh1ve.de:8080/")
      .addConverterFactory(JacksonConverterFactory.create())
      .build().create(SocialRepository.class);
  }
}
