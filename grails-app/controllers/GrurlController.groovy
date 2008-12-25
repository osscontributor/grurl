class GrurlController {

    def urlValidator
    
    def index = {
        [urlInstance: flash.urlInstance]
    }

    def redirectRequest = {
        def u = GRUrl.get(params.urlHash.decodeGrurl())
        if (u) {
            u.lock()
            u.accessCount++
            u.save()
            redirect url: u.realUrl
        } else {
            flash.message = "No matching record was found"
            render view: 'index'
        }
    }

    def generate = {
        def realUrl = params.realUrl
        if(!urlValidator.isValid(realUrl)) {
            realUrl = "http://${realUrl}"
        }
        def urlInstance = GRUrl.findByRealUrl(realUrl)
        if (!urlInstance) {
            urlInstance = new GRUrl(realUrl: realUrl)
            if (!urlInstance.save()) {
                flash.message = "An error occurred processing URL: ${params.realUrl}"
            }
        }
        flash.urlInstance = urlInstance
        redirect action: index
    }

    def questions = {}
}