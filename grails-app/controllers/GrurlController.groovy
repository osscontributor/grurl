class GrurlController {

    def index = {
        if(params.urlHash) {
            def u = GRUrl.get(Long.parseLong(params.urlHash, Character.MAX_RADIX))
            if(u) {
                redirect url: u.realUrl
            } else {
                flash.message = "No matching record was found"
            }
        } else {
            return [urlInstance: flash.urlInstance]
        }
    }

    def generate = {
        def urlInstance = GRUrl.findByRealUrl(params.realUrl)
        if(!urlInstance) {
            urlInstance = new GRUrl(params)
            if(!urlInstance.save()) {
                flash.message = "An error occurred processing URL: ${params.realUrl}"
            }
        }
        if(!urlInstance.hasErrors()) {
            flash.urlInstance = urlInstance
        }
        redirect action: index
    }
}