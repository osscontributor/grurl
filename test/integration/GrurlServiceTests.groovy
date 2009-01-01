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
}