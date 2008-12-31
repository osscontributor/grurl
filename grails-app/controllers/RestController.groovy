import grails.converters.XML

class RestController {
    
    def grurlService
    
    def generate = {
        def xmlResult = XML.parse(request)
        
        def realUrl = grurlService.refine(xmlResult)      
        def result 
        try {
            result = grurlService.resolve(realUrl)
        } catch (GrurlException ex) {
            return render(contentType: "application/xml", encoding: "UTF-8") {
                error("${ex.message}")
            }
        }
        
        render(contentType: "application/xml", encoding: "UTF-8") {
            grurl("$result")
        }
    }
}
