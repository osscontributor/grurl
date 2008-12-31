import grails.converters.XML

class RestController {
    
    def grurlService
    
    def generate = {
        def urlRequest
        if (params) {
            urlRequest = params.rawUrl
        } else {
            urlRequest = XML.parse(request)
        }
        
        def realUrl = grurlService.refine(urlRequest)      
        def result 
        try {
            result = grurlService.resolve(realUrl)
        } catch (GrurlException ex) {
            return render(contentType: "application/xml", encoding: "UTF-8") {
                error("${ex.message}")
            }
        }
        
        if (params) {
            return render(result)
        } else {
            return render(contentType: "application/xml", encoding: "UTF-8") {
                grurl("$result")
            }
        }
        
    }
}
