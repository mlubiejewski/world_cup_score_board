# Football World Cup Score Board

A simple Java library implementing a Football World Cup Score Board, designed to manage live football matches and their scores.

## Features
- Start a game with home and away teams (initial score 0-0).
- Finish a game, removing it from the scoreboard.
- Update the score of an existing game.
- Get a summary of games, ordered by total score (descending) and then by most recently added.

## Assumptions
- Team names are unique for a game (a team cannot play multiple games simultaneously).
- Scores are non-negative integers.
- Games are identified by the combination of home and away teams.
- The summary prioritizes total score (sum of home and away scores) and uses start time as a tiebreaker.