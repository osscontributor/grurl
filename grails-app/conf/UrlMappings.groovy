class UrlMappings {
    static mappings = {
        "/$controller/$action"{}
        "/$urlHash"(controller: 'grurl', action:'redirectRequest') {
            constraints {
                urlHash matches: /[a-z0-9]*/
            }
        }
        "/"(controller: 'grurl', action: 'index')
        "/questions"(view: '/grurl/questions')
        "/developers"(view: '/grurl/developers')
        "/terms"(view: '/grurl/terms')
        "/contact"(view: '/grurl/contact')
        
        "500"(view:'/error')
        "/generate" {
            controller = 'rest'
            action = [POST:'xmlGenerate', GET:'paramGenerate']
        }
    }
}
