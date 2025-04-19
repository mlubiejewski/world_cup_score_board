package com.sportradar.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldThrowExceptionOnInvalidTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> new Game("", "France", 0, 0, Instant.now()));
        assertThrows(IllegalArgumentException.class, () -> new Game(null, "France", 0, 0, Instant.now()));
    }

    @Test
    void shouldThrowExceptionOnNegativeScoreValue() {
        assertThrows(IllegalArgumentException.class, () -> new Game("Mexico", "Canada", -1, 5, Instant.now()));
    }

}