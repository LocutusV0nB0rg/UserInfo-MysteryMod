package de.kyleonaut.userinfo.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 07.11.2021
 */
@Data
public class SocialUser {
  private UUID uuid;
  private long addedBy;
  private Date date;
}
