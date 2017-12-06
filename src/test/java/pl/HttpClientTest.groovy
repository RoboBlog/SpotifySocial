package pl

import pl.util.HttpClient
import spock.lang.Specification

class HttpClientTest extends Specification{

    def "get request with invalid url should return MaformedURLException"() {
        given:
            def client = new HttpClient()
        when:
            client.get("dsff", 500, 500)
        then:
            thrown(MalformedURLException)
    }

    def "get request with unknown host should return UnknownHostException"() {
        given:
            def client = new HttpClient()
        when:
            client.get("http://www.baselddsaung.com/groovy-spock", 500, 500)
        then:
            thrown(UnknownHostException)
    }

    def "get request with correct web page should return string response"() {
        given:
            def client = new HttpClient()
        when:
            def response = client.get("https://www.google.pl/", 5000, 5000)
        then:
            !response.empty
    }


    def "get request with minimal timeout should SocketTimeoutException"() {
        given:
            def client = new HttpClient()
        when:
            def response = client.get("https://www.google.pl/", 1, 1)
        then:
            thrown(SocketTimeoutException)
    }


    def "setAuthHeader should return auth header hash map"() {
        given:
            def client = new HttpClient()
            Map<String, String> headers = new LinkedHashMap<>()
            headers.put("Authorization", "Bearer test")
        when:
            def header = client.setAuthHeader("test")
        then:
            header == headers
    }


}
