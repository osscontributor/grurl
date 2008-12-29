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
        "500"(view:'/error')
    }
}
