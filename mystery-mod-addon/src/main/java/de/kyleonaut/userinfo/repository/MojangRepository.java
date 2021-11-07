package de.kyleonaut.userinfo.repository;

import com.google.inject.ProvidedBy;
import de.kyleonaut.userinfo.entity.MojangUser;
import de.kyleonaut.userinfo.provider.MojangRepositoryProvider;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
@ProvidedBy(MojangRepositoryProvider.class)
public interface MojangRepository {

  @GET("users/profiles/minecraft/{name}")
  Call<MojangUser> getMojangUserByName(@Path("name") String name);
}
