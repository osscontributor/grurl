class GrurlServiceTests extends GroovyTestCase {
    
    def grurlService
    
    void testRefineWithNoProtocol() {
        def result = grurlService.refine('grails.org')
        assertEquals 'http://grails.org', result
    }
    
    void testRefineWithProtocol() {
        def result = grurlService.refine('http://groovy.codehaus.org')
        assertEquals 'http://groovy.codehaus.org', result
    }
    
    void testFindUrls_OneFullUrl() {
        def s = 'this is a test to find one url http://www.google.com and that was it'
        def urls = grurlService.findUrls(s)
        assertLength 1, urls
        assertEquals 'http://www.google.com', urls[0]
        s = 'this contains another url http://www.example.com right in here'
        urls = grurlService.findUrls(s)
        assertLength 1, urls
        assertEquals 'http://www.example.com', urls[0]
    }
    
    void testFindUrls_ManyFullUrls() {
        def s = 'this is a http://one.com test to find many url http://www.two.com and that was it'
        def urls = grurlService.findUrls(s)
        assertEquals 2, urls.size()
        assertEquals 'http://one.com', urls[0]
        assertEquals 'http://www.two.com', urls[1]
    }
    
    void testFindUrls_ManyComplexUrls() {
        def w = [
            'http://en.wikipedia.org/wiki/Octavia_(She-Ra)',
            'http://en.wikipedia.org/wiki/Jay_Peak_(Vermont)',
            'http://en.wikipedia.org/wiki/%C5%9Awiercze,_Lublin_Voivodeship'
        ]
        def s = "blah ${w[0]} blah ${w[1]} blah ${w[2]} blah"
        def urls = grurlService.findUrls(s)
        assertEquals 3, urls.size()
        w.eachWithIndex { it, i ->
            assertEquals it, urls[i]
        }
    }
}