package org.game.map.utils

import spock.lang.Specification
import spock.lang.Subject

class IntRangeTest extends Specification{

    @Subject
    IntRange intRange = IntRange.of(1,5)

    void "IllegalStateException is thrown when min value is grater than max value"(){
        when:
        IntRange.of(2,1)
        then:
        IllegalStateException exception = thrown(IllegalStateException)
        exception.message == "Min value is greater than max. Min: 2, Max: 1"
    }

    void 'range should return #expectedResult when value is #value'()
    {
        expect:
            intRange.contains(value) == expectedResult
        where:
           value || expectedResult
            1    ||  true
            0    ||  false
            6    ||  false
            5    ||  true
    }
}
