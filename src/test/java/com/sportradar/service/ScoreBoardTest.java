package com.sportradar.service;

import com.sportradar.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void AGameShouldStartWith0To0Score() {

        scoreBoard.startGame("Mexico", "Canada");
        List<Game> summary = scoreBoard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 0 - 0 Canada", summary.get(0).toString());
    }

    @Test
    void ThereShouldNotBeMoreThanOneGameWithSameTeams() {
        scoreBoard.startGame("Mexico", "Canada");
        assertThrows(IllegalStateException.class, () -> scoreBoard.startGame("Mexico", "Canada"));
    }

    @Test
    void ScoreBoardShouldNotShowFinishedGames() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");
        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void FinishingANotExistingGameShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> scoreBoard.finishGame("Mexico", "Canada"));
    }

    @Test
    void ScoreBoardShouldShowUpdatedScores() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        List<Game> summary = scoreBoard.getSummary();
        assertEquals("Mexico 0 - 5 Canada", summary.get(0).toString());
    }

    @Test
    void ScoreUpdateOfANotExistingGameShouldThrowException() {
        assertThrows(IllegalStateException.class, () -> scoreBoard.updateScore("Mexico", "Canada", 0, 5));
    }

    @Test
    void ScoreBoardSummaryShouldShowGamesOrderedByTotalScoreThenByMostRecentStartTime() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);

        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);

        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);

        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);

        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<Game> summary = scoreBoard.getSummary();
        assertEquals("Uruguay 6 - 6 Italy", summary.get(0).toString());
        assertEquals("Spain 10 - 2 Brazil", summary.get(1).toString());
        assertEquals("Mexico 0 - 5 Canada", summary.get(2).toString());
        assertEquals("Argentina 3 - 1 Australia", summary.get(3).toString());
        assertEquals("Germany 2 - 2 France", summary.get(4).toString());
    }

}