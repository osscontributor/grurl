class UrlMappings {
    static mappings = {
        "/$controller/$action"{}
        "/$urlHash"(controller: 'grurl', action:'redirectRequest') {
            constraints {
                urlHash matches: /[a-z0-9]*/
            }
        }
        "/"(controller: 'grurl', action: 'index')
        "/questions"(controller: 'grurl', action: 'questions')
        "/developers"(controller: 'grurl', action: 'developers')
        "/terms"(controller: 'grurl', action: 'terms')
        "/contact"(controller: 'grurl', action: 'contact')
        "500"(view:'/error')
        "/generate" {
            controller = 'rest'
            action = [POST:'xmlGenerate', GET:'paramGenerate']
        }
    }
}
