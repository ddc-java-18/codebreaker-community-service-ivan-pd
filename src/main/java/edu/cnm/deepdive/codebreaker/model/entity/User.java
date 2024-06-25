package edu.cnm.deepdive.codebreaker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_profile_id", updatable = false)
  private long id;

  @Column(nullable = false, updatable = false, unique = true)
  private UUID externalKey;

  @Column(nullable = false, updatable = true, unique = true)
  private String display_name;

  @Column(nullable = false, updatable = false, unique = true)
  private String oauth_key;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Instant created;

  @Column(nullable = false, updatable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Instant accessed;

  public long getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public String getOauth_key() {
    return oauth_key;
  }

  public void setOauth_key(String oauth_key) {
    this.oauth_key = oauth_key;
  }

  public Instant getCreated() {
    return created;
  }

  public Instant getAccessed() {
    return accessed;
  }

  public void setAccessed(Instant accessed) {
    this.accessed = accessed;
  }

  @PrePersist
  void setExternalKey() {
    externalKey = UUID.randomUUID();
  }
}
