class GrurlService {
    
    def urlValidator
    
    def refine(url) {
        def realUrl = url
        if(!urlValidator.isValid(realUrl)) {
            realUrl = "http://${realUrl}"
        }
        realUrl
    }
    
    def resolve(url) throws GrurlException {
        def urlInstance = GRUrl.findByRealUrl(url)
        if (!urlInstance) {
            urlInstance = new GRUrl(realUrl: url)
            if (!urlInstance.save()) {
                throw new GrurlException("An error occurred processing URL: ${url}")
            }
        }
        urlInstance
    }
    
}