package de.kyleonaut.userinfo.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 08.11.2021
 */
@Data
public class ListUser {
  private String name;
  @Expose private long changedToAt;
}
