package de.kyleonaut.userinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 08.11.2021
 */
@JsonIgnoreProperties
@Data
public class ListUser {
  private String name;
  private long changedToAt;
}
