package org.game.map.entities;

import org.game.map.entities.character.Race;
import org.game.map.entities.character.Sex;

import static org.game.map.entities.EntityType.CHARACTER;

public class GameCharacter extends SimpleEntity {

    private final boolean isUserCharacter;

    private final Race race;

    private final Sex sex;

    public GameCharacter(String name, boolean isUserCharacter, Race race, Sex sex, int attackPower) {
        super(name, CHARACTER, attackPower);
        this.isUserCharacter = isUserCharacter;
        this.race = race;
        this.sex = sex;
    }


    @Override
    public boolean isUser() {
        return isUserCharacter;
    }

    public boolean isUserCharacter() {
        return isUserCharacter;
    }

    public Race getRace() {
        return race;
    }

    public Sex getSex() {
        return sex;
    }


    @Override
    public String toString() {
        return "GameCharacter{" +
                "isUserCharacter=" + isUserCharacter +
                ", race=" + race +
                ", sex=" + sex +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameCharacter that = (GameCharacter) o;

        if (isUserCharacter != that.isUserCharacter) return false;
        if (race != that.race) return false;
        return sex == that.sex;
    }

    @Override
    public int hashCode() {
        int result = (isUserCharacter ? 1 : 0);
        result = 31 * result + race.hashCode();
        result = 31 * result + sex.hashCode();
        return result;
    }
}
