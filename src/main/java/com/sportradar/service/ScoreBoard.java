package com.sportradar.service;

import com.sportradar.model.Game;

import java.time.Instant;
import java.util.*;

public class ScoreBoard {
    private final List<Game> games;

    public ScoreBoard() {
        this.games = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        Game newGame = new Game(homeTeam, awayTeam, 0, 0, Instant.now());
        if (games.contains(newGame)) {
            throw new IllegalStateException("Game between " + homeTeam + " and " + awayTeam + " already exists");
        }
        games.add(newGame);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        Game gameToRemove = new Game(homeTeam, awayTeam, 0, 0, null);
        if (!games.remove(gameToRemove)) {
            throw new IllegalStateException("Game between " + homeTeam + " and " + awayTeam + " not found");
        }
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Game existingGame = findGame(homeTeam, awayTeam)
                .orElseThrow(() -> new IllegalStateException("Game between " + homeTeam + " and " + awayTeam + " not found"));
        Game updatedGame = existingGame.updateScore(homeScore, awayScore);
        games.remove(existingGame);
        games.add(updatedGame);
    }

    public List<Game> getSummary() {
        return games.stream()
                .sorted(Comparator.comparingInt(Game::getTotalScore).reversed()
                        .thenComparing(Game::getStartTime, Comparator.reverseOrder())
                        .thenComparing(games::indexOf, Comparator.reverseOrder()))
                .toList();
    }

    private Optional<Game> findGame(String homeTeam, String awayTeam) {
        Game searchGame = new Game(homeTeam, awayTeam, 0, 0, null);
        return games.stream().filter(game -> game.equals(searchGame)).findFirst();
    }
}
