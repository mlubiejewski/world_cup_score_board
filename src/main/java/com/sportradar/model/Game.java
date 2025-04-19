package com.sportradar.model;

import java.time.Instant;
import java.time.LocalDateTime;

public class Game {
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;
    private final Instant startTime;

    public Game(String homeTeam, String awayTeam, int homeScore, int awayScore, Instant startTime) {
        validate(homeTeam, awayTeam, homeScore, awayScore);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.startTime = startTime != null ? startTime : Instant.now();
    }

    private static void validate(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeTeam == null || homeTeam.isBlank() || awayTeam == null || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Team names cannot be null or empty");
        }
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public Game updateScore(int homeScore, int awayScore) {
        return new Game(homeTeam, awayTeam, homeScore, awayScore, startTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return homeTeam.equals(game.homeTeam) && awayTeam.equals(game.awayTeam);
    }

    @Override
    public int hashCode() {
        return homeTeam.hashCode() + awayTeam.hashCode();
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam;
    }
}