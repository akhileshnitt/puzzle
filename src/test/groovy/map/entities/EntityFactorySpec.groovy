package map.entities

import org.game.map.entities.Entity
import org.game.map.entities.EntityFactory
import org.game.map.entities.EntityType
import org.game.map.entities.GameCharacter
import org.game.map.entities.SimpleEntity
import org.game.map.entities.character.Race
import org.game.map.entities.character.Sex
import spock.lang.Specification

class EntityFactorySpec  extends Specification{

    void "road should be created without inner entity"(){
        when:
        Entity road = EntityFactory.road()
        then:
        with(road as SimpleEntity){
            name == "Road"
            type == EntityType.ROAD
            health == 100
            !containAnotherEntity()
            attackPower == 0

        }
    }


    void "road should be created with inner entity"(){
        given:
        Entity entity = Stub(Entity)
        when:
        Entity road = EntityFactory.road(entity)
        then:
        with(road as SimpleEntity){
            name == "Road"
            type == EntityType.ROAD
            health == 100
            containAnotherEntity()
            attackPower == 0

        }
    }


    void 'wolf should be created'() {
        when:
        Entity wolf = EntityFactory.wolf()
        then:
        with(wolf as SimpleEntity) {
            name == 'Wolf'
            type == EntityType.WOLF
            health == 100
            attackPower == 10
            !defended
            !containAnotherEntity()
        }
    }

    void 'bear should be created'() {
        when:
        Entity bear = EntityFactory.bear()
        then:
        with(bear as SimpleEntity) {
            name == 'Bear'
            type == EntityType.BEAR
            health == 100
            attackPower == 20
            !defended
            !containAnotherEntity()
        }
    }

    void 'tree should be created'() {
        when:
        Entity tree = EntityFactory.tree()
        then:
        with(tree as SimpleEntity) {
            name == 'Tree'
            type == EntityType.TREE
            health == 100
            attackPower == 0
            !defended
            !containAnotherEntity()
        }
    }

    void 'stone should be created'() {
        when:
        Entity stone = EntityFactory.stone()
        then:
        with(stone as SimpleEntity) {
            name == 'Stone'
            type == EntityType.STONE
            health == 100
            attackPower == 0
            !defended
            !containAnotherEntity()
        }
    }

    void 'user character should be created'() {
        when:
        Entity userCharacter = EntityFactory.userCharacter("akhi",Race.HUMAN,Sex.MALE)
        then:
        with(userCharacter as GameCharacter){
            name == "akhi"
            type == EntityType.CHARACTER
            userCharacter.isUser()
            userCharacter.isAlive()
            race == Race.HUMAN
            sex == Sex.MALE
            !defended
            !containAnotherEntity()
            health == 100
            attackPower == 50
        }
    }

    void ' character should be created'() {

        when:
        Entity character = EntityFactory.character("ninza",Race.GNOME,Sex.FEMALE,100)
        then:
        with(character as GameCharacter){
            name == "ninza"
            type == EntityType.CHARACTER
            !isUser()
            isAlive()
            race == Race.GNOME
            sex == Sex.FEMALE
            !defended
            !containAnotherEntity()
            health == 100
            attackPower == 100
        }
    }



}
