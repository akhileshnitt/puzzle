package map.entities

import org.game.map.entities.Entity
import org.game.map.entities.EntityType
import org.game.map.entities.GameCharacter
import org.game.map.entities.SimpleEntity
import org.game.map.entities.character.Race
import org.game.map.entities.character.Sex
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.util.function.Predicate

import static org.game.map.entities.EntityType.STONE
import static org.game.map.entities.EntityFactory.character

class SimpleEntitySpec extends Specification{


    @Shared
    Predicate<Entity> foreverTrueCondition = {true}
    @Shared
    String entityName = "Stone"

    @Subject
    SimpleEntity simpleEntity = new SimpleEntity(entityName,EntityType.STONE,10);

    void "constructor element should be set into fields and entity has default nehaviour"(){
        expect:
        with(simpleEntity){
            name == entityName
            type == STONE
            !isUser()
            health ==100
            attackPower==10
            !containAnotherEntity()
            !innerEntity.isPresent()
            !defended
            !canContainAnotherEntity()
            isAlive()
            containTasks(foreverTrueCondition)

        }
    }

    void "entity should not be able to contain another entity"(){
        expect:
        !simpleEntity.canContainAnotherEntity()
    }

    void 'UnsupportedOperationException should be thrown when take method is called'() {
        when:
        simpleEntity.take(Stub(Entity))
        then:
        UnsupportedOperationException exception = thrown(UnsupportedOperationException)
        exception.message == 'This method is not supported.'
    }


    void 'UnsupportedOperationException should be thrown when clear method is called'() {
        when:
        simpleEntity.clear()
        then:
        UnsupportedOperationException exception = thrown(UnsupportedOperationException)
        exception.message == 'This operation is not supported'
    }


    void "entity should be beaten by another entity"(){
        given:
        Entity characterEntity = character('Some Character',Race.HUMAN,Sex.MALE,100)
        expect:
        simpleEntity.isBeatenBy(characterEntity) ==100
        when:
        int damage= simpleEntity.isBeatenBy(characterEntity)
        then:
        damage == 0
        and:
        old(simpleEntity.health) == simpleEntity.health

    }

    void "entity should be not in defended mode when it relaxes"(){
        given:
        simpleEntity.defense()
        when:
        simpleEntity.relax()
        then:
        old(simpleEntity.defended)
        and:
        !simpleEntity.defended

    }

    void 'entity should be not beaten when entity is in defended mode'() {
        given:
        Entity entity = character('Some character', Race.HUMAN, Sex.FEMALE, 1)
        and:
        simpleEntity.defense()
        when:
        int damage = simpleEntity.isBeatenBy(entity);
        then:
        damage == 0
        and:
        simpleEntity.health == 100

    }

}
