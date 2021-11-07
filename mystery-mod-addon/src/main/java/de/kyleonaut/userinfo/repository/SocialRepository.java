package de.kyleonaut.userinfo.repository;

import com.google.inject.ProvidedBy;
import de.kyleonaut.userinfo.entity.SocialUser;
import de.kyleonaut.userinfo.provider.SocialRepositoryProvider;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
@ProvidedBy(SocialRepositoryProvider.class)
public interface SocialRepository {

  @GET("scammer/scammers")
  Call<List<SocialUser>> getAllScammer();

  @GET("mm/middlemans")
  Call<List<SocialUser>> getAllMiddlemans();
}
