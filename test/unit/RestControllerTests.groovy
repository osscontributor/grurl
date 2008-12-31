import grails.test.ControllerUnitTestCase

class RestControllerTests extends ControllerUnitTestCase {

    void testOneRawUrlSuccess() {
        def newGrurl = 'http://grurl.com/WORD'
        
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
        controller.generate()
        
        assertEquals "<grurl>${newGrurl}</grurl>", mockResponse.contentAsString
    }
    
    void testOneRawUrlFailure_ReturnsErrorMessage() {
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
        controller.generate()
        
        assertEquals "<error>error message</error>", mockResponse.contentAsString
    }
}
