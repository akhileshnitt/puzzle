package org.game.map.task;

import org.game.map.entities.Entity;

public interface TaskCompletionStrategy {
    void complete(Entity user, Entity task);
}
