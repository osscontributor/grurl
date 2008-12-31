class GrurlController {

    def grurlService 
    
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
        def realUrl = grurlService.refine(params.realUrl)        
        try {
            flash.urlInstance = grurlService.resolve(realUrl)
        } catch (Throwable ex) {
            println ex.message
            flash.message = ex.message
        }
        redirect action: index
    }

    def questions = {}
}