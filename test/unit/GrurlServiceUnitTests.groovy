class GrurlServiceUnitTests extends GroovyTestCase {
        
    void testReplaceUrls_ManyFullUrls() {
        def grurlService = new GrurlService()
        def input = ['http://one.com', 'http://two.com']
        def expected = ['http://grurl.net/1', 'http://grurl.net/2']
        def resolveCnt = 0
        grurlService.metaClass.refine = {url -> url}
        grurlService.metaClass.resolve = { url ->
            assertEquals input[resolveCnt], url
            [grurlUrl:expected[resolveCnt++]]
        }
        def s = "this is a ${input[0]} test to find many url ${input[1]} and that was it"
        def newString = grurlService.replaceUrls(s)
        assertEquals 'this is a http://grurl.net/1 test to find many url http://grurl.net/2 and that was it', newString
    }
}