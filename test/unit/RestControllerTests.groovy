import grails.test.ControllerUnitTestCase

class RestControllerTests extends ControllerUnitTestCase {

    void testXML_OneRawUrlSuccess() {
        def newGrurl = [realUrl:'http://grurl.com/WORD']
        
        def grurlServiceControl = mockFor(GrurlService)
        grurlServiceControl.demand.refine { url ->
            url
        }
        grurlServiceControl.demand.resolve { url ->
            newGrurl
        }
        
        setXmlRequestContent {
            rawUrl('http://www.google.com')
        }
        
        def controller = new RestController()
        controller.grurlService = grurlServiceControl.createMock()
        controller.xmlGenerate()
        
        assertEquals "<grurl>http://grurl.com/WORD</grurl>", mockResponse.contentAsString
    }
    
    void testXML_OneRawUrlFailure_ReturnsErrorMessage() {
        def grurlServiceControl = mockFor(GrurlService)
        grurlServiceControl.demand.refine { url ->
            url
        }
        grurlServiceControl.demand.resolve { url ->
            throw new GrurlException('error message')
        }
        
        setXmlRequestContent {
            rawUrl('!bad url input!')
        }
        
        def controller = new RestController()
        controller.grurlService = grurlServiceControl.createMock()
        controller.xmlGenerate()
        
        assertEquals "<error>error message</error>", mockResponse.contentAsString
    }
    
    void testGETParams_OneEncodedUrl() {
        def newGrurl = [realUrl:'http://grurl.com/WORD']
        
        def grurlServiceControl = mockFor(GrurlService)
        grurlServiceControl.demand.refine { url ->
            url
        }
        grurlServiceControl.demand.resolve { url ->
            newGrurl
        }
        
        def params = [rawUrl:'http://www.google.com']
        
        def controller = new RestController()
        controller.grurlService = grurlServiceControl.createMock()
        controller.metaClass.getParams = { -> params }
        controller.paramGenerate()
        
        assertEquals "http://grurl.com/WORD", mockResponse.contentAsString
    }
    
    void testGETParams_GrurlifyString() {
        def longUrls = [
            'http://www.example1.com',
            'http://www.example2.com',
            'http://www.example3.com'
        ]
        def grurls = [
            'http://grurl.net/1',
            'http://grurl.net/2',
            'http://grurl.net/3',
        ]
        
        def grurlService = new GrurlService()
        def resolveCnt = 0
        grurlService.metaClass.refine = {url -> url}
        grurlService.metaClass.resolve = { url ->
            assertEquals longUrls[resolveCnt], url
            [grurlUrl:grurls[resolveCnt++]]
        }
        
        /*def refineCnt = 0
                grurlServiceControl.demand.refine(3..3) { url ->
                    assertEquals 'url sent to refine was wrong', longUrls[refineCnt++], url
                    url
                }
                def resolveCnt = 0
                grurlServiceControl.demand.resolve(3..3) { url ->
                    assertEquals 'url sent to resolve was wrong', longUrls[resolveCnt], url
                    grurl[resolveCnt++]
                }*/
        
        def inputString = """
        Lorem ipsum dolor sit amet, ${longUrls[0]} adipiscing elit. Donec purus nibh, tincidunt eu, 
        consequat eget, tempor non, dolor. Pellentesque ultricies erat in tortor. Sed eu leo id quam 
        hendrerit molestie. ${longUrls[1]} tincidunt metus vel tellus. Praesent a tortor. Phasellus leo. 
        Mauris consectetur, enim at consequat pretium, justo nisi ullamcorper dui, a sodales nisi 
        urna et sem. Sed scelerisque auctor lacus. Aliquam erat volutpat. Quisque vitae velit at 
        neque luctus molestie. ${longUrls[2]} pellentesque nisi non orci. Fusce vehicula, arcu vitae 
        lobortis tempor, ipsum felis suscipit pede, at euismod odio tellus sit amet enim. 
        """
        def params = [inputString:inputString]
        
        def controller = new RestController()
        controller.grurlService = grurlService
        controller.metaClass.getParams = { -> params }
        controller.grurlifyString()
        
        def expected = """
        Lorem ipsum dolor sit amet, ${grurls[0]} adipiscing elit. Donec purus nibh, tincidunt eu, 
        consequat eget, tempor non, dolor. Pellentesque ultricies erat in tortor. Sed eu leo id quam 
        hendrerit molestie. ${grurls[1]} tincidunt metus vel tellus. Praesent a tortor. Phasellus leo. 
        Mauris consectetur, enim at consequat pretium, justo nisi ullamcorper dui, a sodales nisi 
        urna et sem. Sed scelerisque auctor lacus. Aliquam erat volutpat. Quisque vitae velit at 
        neque luctus molestie. ${grurls[2]} pellentesque nisi non orci. Fusce vehicula, arcu vitae 
        lobortis tempor, ipsum felis suscipit pede, at euismod odio tellus sit amet enim. 
        """
        
        assertEquals expected, mockResponse.contentAsString
    }
}
