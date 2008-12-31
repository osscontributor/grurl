class RestControllerTests extends ControllerUnitTestCase {

    void testSomething() {
        
        setXmlRequestContent {
            rawUrl('http://www.google.com')
        }
        
        def controller = new RestController()
        controller.generate()
        
    }
}
