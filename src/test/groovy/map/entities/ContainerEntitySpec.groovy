package map.entities

import org.game.map.entities.ContainerEntity
import org.game.map.entities.Entity
import org.game.map.entities.EntityFactory
import org.game.map.entities.EntityType
import org.game.map.entities.character.Race
import org.game.map.entities.character.Sex
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

class ContainerEntitySpec  extends Specification{

    @Shared
    Predicate<Entity> foreverTrueCondition = { true }

    @Shared
    Predicate<Entity> foreverFalseCondition = { false }

    @Shared
    String containerName = "road"

    @Shared
    Entity userCharacter = EntityFactory.userCharacter("akhi", Race.GNOME, Sex.MALE)

    @Shared
    Entity character = EntityFactory.character("Andrii",Race.GNOME,Sex.MALE)

    @Subject
    ContainerEntity container = new ContainerEntity(containerName, EntityType.ROAD,0)

    void 'constructor argument should be set into fields and container has default behaviour'() {
        expect:
        with(container) {
            name == containerName
            type == EntityType.ROAD
            health == 100
            attackPower == 0
            !defended
            !innerEntity.isPresent()
            canContainAnotherEntity()
            !containAnotherEntity()
            !containUserCharacter()
            containTasks(foreverTrueCondition)
        }
    }

    void "container should take another element"(){
        when:
        container.take(Stub(Entity))
        then:
        container.containAnotherEntity()
        when:
        container.clear()
        then:
        !container.containAnotherEntity()
    }


    void "container should contain user character when user character is present"(){
        given:
        container.take entity
        expect:
        container.containUserCharacter()
        where:
        entity << [userCharacter,
                   EntityFactory.road(userCharacter),
                   EntityFactory.road(EntityFactory.road(userCharacter))]
    }

    void "container should not contain user character when user character is not present"(){
        given :
        container.take entity
        expect:
        !container.containUserCharacter()
        where:
        entity <<[
                character,
                EntityFactory.stone(),
                EntityFactory.road(EntityFactory.stone())
        ]
    }

    void 'container should contain tasks when condition is true'() {
        expect:
        container.containTasks(foreverTrueCondition)
    }

    void 'inner entity should be found when it matches condition'() {
        given:
        container.take character
        expect:
        container.findEntity(foreverTrueCondition) == character
    }

    void 'IllegalStateException should be thrown when no entities match condition'() {
        given:
        container.take EntityFactory.road(character)
        when:
        container.findEntity(foreverFalseCondition)
        then:
        IllegalStateException exception = thrown(IllegalStateException)
        exception.message == 'There is no entities with such condition'
    }

    void 'inner entity should relax together with root entity'() {
        given:
        container.take userCharacter
        and:
        container.defense()
        userCharacter.defense()
        when:
        container.relax()
        then:
        old(container.defended)
        old(userCharacter.defended)
        and:
        !container.defended
        !userCharacter.defended
    }
}
