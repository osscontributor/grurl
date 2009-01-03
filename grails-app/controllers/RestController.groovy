import grails.converters.XML

class RestController {
    
    def grurlService
    
    def xmlGenerate = {
        def urlRequest = XML.parse(request)
        
        def realUrl = grurlService.refine(urlRequest)      
        def result 
        try {
            result = grurlService.resolve(realUrl)
        } catch (GrurlException ex) {
            return render(contentType: "application/xml", encoding: "UTF-8") {
                error("${ex.message}")
            }
        }
        
        render(contentType: "application/xml", encoding: "UTF-8") {
            grurl("${result.realUrl}")
        }
    }
    
    def paramGenerate = {
        def urlRequest = params.rawUrl
        def realUrl = grurlService.refine(urlRequest)      
        def result 
        try {
            result = grurlService.resolve(realUrl)
        } catch (GrurlException ex) {
            return render("${ex.message}")
        }
        return render(result.realUrl)
    }
    
    def grurlifyString = {
        render grurlService.replaceUrls(params.inputString)
    }
}
