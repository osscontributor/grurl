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
        "500"(view:'/error')
        "/generate/$rawUrl" {
            controller = 'rest'
            action = [POST:'xmlGenerate', GET:'paramGenerate']
        }
    }
}
