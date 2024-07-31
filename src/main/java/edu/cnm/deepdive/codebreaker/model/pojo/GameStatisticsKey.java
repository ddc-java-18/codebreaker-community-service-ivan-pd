package edu.cnm.deepdive.codebreaker.model.pojo;

import java.io.Serializable;
import java.util.Objects;

public class GameStatisticsKey implements Serializable {

  private long playerId;
  private int poolSize;
  private int codeLength;

  public GameStatisticsKey() {
  }

  public GameStatisticsKey(Long playerId, int poolSize, int codeLength) {
    this.playerId = playerId;
    this.poolSize = poolSize;
    this.codeLength = codeLength;
  }

  @Override
  public boolean equals(Object obj) {
    boolean result;
    if (this == obj) {
      result = true;
    } else if (obj instanceof GameStatisticsKey other) {
      result = this.playerId == other.playerId
          && this.poolSize == other.poolSize
          && this.codeLength == other.codeLength;
    } else {
      result = false;
    }
    return result;
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, poolSize, codeLength);
  }
}
