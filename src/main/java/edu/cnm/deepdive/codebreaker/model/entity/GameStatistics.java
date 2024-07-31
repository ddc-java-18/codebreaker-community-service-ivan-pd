package edu.cnm.deepdive.codebreaker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.codebreaker.model.pojo.GameStatisticsKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@IdClass(GameStatisticsKey.class)
@Immutable
@Subselect("SELECT * FROM game_statistics")
@JsonPropertyOrder(value = {"player", "poolSize", "codeLength", "gamesPlayed", "avgGuessCount", "avgDuration"})
public class GameStatistics {

  @Id
  @Column(name = "player_id", nullable = false, updatable = false)
  @JsonIgnore
  private long playerId;

  @Id
  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int poolSize;

  @Id
  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int codeLength;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  @MapsId
  @JsonProperty(access = Access.READ_ONLY)
  private User player;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int gamesPlayed;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private double avgGuessCount;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private double avgDuration;

  public long getPlayerId() {
    return playerId;
  }

  public User getPlayer() {
    return player;
  }

  public int getPoolSize() {
    return poolSize;
  }

  public int getCodeLength() {
    return codeLength;
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }

  public double getAvgGuessCount() {
    return avgGuessCount;
  }

  public double getAvgDuration() {
    return avgDuration;
  }
}
