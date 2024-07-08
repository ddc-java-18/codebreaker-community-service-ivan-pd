package edu.cnm.deepdive.codebreaker.model.dao;

import edu.cnm.deepdive.codebreaker.model.entity.GameStatistics;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface GameStatisticsRepository extends ReadOnlyRepository<GameStatistics, Long> {

  String NO_THRESHOLD_QUERY = """
      SELECT
        gs
      FROM
        GameStatistics AS gs
      WHERE
        gs.poolSize = :poolSize
        AND gs.codeLength = :codeLength
      ORDER BY
        gs.avgGuessCount ASC,
        gs.avgDuration ASC
      """;

  String THRESHOLD_QUERY = """
      SELECT
        gs
      FROM
        GameStatistics AS gs
      WHERE
        gs.poolSize = :poolSize
        AND gs.codeLength = :codeLength
        AND gs.gamesPlayed >= :gamesPlayedThreshold
      ORDER BY
        gs.avgGuessCount ASC,
        gs.avgDuration ASC
      """;

  List<GameStatistics> findAllByPlayerId(long playerId);

  @Query(NO_THRESHOLD_QUERY)
  List<GameStatistics> selectWithoutThreshold(int poolSize, int codeLength);

  @Query(THRESHOLD_QUERY)
  List<GameStatistics> selectWithThreshold(int poolSize, int codeLength, int gamesPlayedThreshold);

  // JPAQL Option
//  List<GameStatistics> findAllByPoolSizeAndCodeLengthOrderByAvgGuessCountAscAvgDurationAsc(
//      int poolSize, int codeLength);
//
//  List<GameStatistics>
//  findAllByPoolSizeAndCodeLengthAndGamesPlayedGreaterThanEqualOrderByAvgGuessCountAscAvgDurationAsc(
//      int poolSize, int codeLength, int gamesPlayed);

  // Default Method wrappers around JPAQL
//  default List<GameStatistics> selectWithoutThreshold(int poolSize, int codeLength) {
//    return findAllByPoolSizeAndCodeLengthOrderByAvgGuessCountAscAvgDurationAsc(poolSize, codeLength);
//  }

//  default List<GameStatistics> selectWithThreshold(int poolSize, int codeLength, int gamesPlayedThreshold) {
//    return findAllByPoolSizeAndCodeLengthAndGamesPlayedGreaterThanEqualOrderByAvgGuessCountAscAvgDurationAsc(
//        poolSize, codeLength, gamesPlayedThreshold);
//  }
}
