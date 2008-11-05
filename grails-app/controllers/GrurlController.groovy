class GrurlController {

    def index = {
    }

    def redirectRequest = {
        def u = GRUrl.get(Long.parseLong(params.urlHash, Character.MAX_RADIX))
        if (u) {
            redirect url: u.realUrl
        } else {
            flash.message = "No matching record was found"
            render view: 'index'
        }
    }

    def generate = {
        def urlInstance = GRUrl.findByRealUrl(params.realUrl)
        if (!urlInstance) {
            urlInstance = new GRUrl(params)
            if (!urlInstance.save()) {
                flash.message = "An error occurred processing URL: ${params.realUrl}"
            }
        }
        render view: 'index', model: [urlInstance: urlInstance.hasErrors() ? null : urlInstance]
    }

    def questions = {}
}