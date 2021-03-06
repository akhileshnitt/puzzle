package play

import org.game.map.GameMap
import org.game.play.GameImpl
import org.game.play.GameView
import spock.lang.Specification
import spock.lang.Subject


class GameImplSpec extends Specification{

    GameMap map = Mock()

    GameView view = Mock()

    @Subject
    GameImpl game = new GameImpl(map,view)

    void "game should end when no user character on map "(){
        when:
        game.start()
        then:
        1 * view.draw(map)
        and:
        2*map.containsUserCharacter() >>> [true,false,false]
        1*map.containsTasks()
        0*map.goToNextIteration()

    }


    void 'game should end when no task to complete on map'() {
        when:
        game.start()
        then:
        2 * view.draw(map)
        and:
        3 * map.containsUserCharacter() >> true
        2 * map.containsTasks() >>> [true, false]
        1 * map.goToNextIteration()

    }
}
