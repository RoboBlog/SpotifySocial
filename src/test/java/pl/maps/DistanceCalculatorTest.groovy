package pl.maps

import spock.lang.Specification

class DistanceCalculatorTest extends Specification {

    def "distance1To2Check with good distance should return true"() {
        given:
        def distance = new DistanceCalculator()
        when:
        def check = distance.distance1To2Check(50, 50.0, 50.1, 50.1, 50)
        then:
        check

    }


}
