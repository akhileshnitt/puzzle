package org.game.map.behaviour.user;

import org.game.map.utils.Position;

public interface UserMovementInput {
    Position getNextPosition(Position currentPosition);
}
