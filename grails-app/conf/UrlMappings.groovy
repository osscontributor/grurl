class UrlMappings {
    static mappings = {
        "/$controller/$action"{}
        "/$urlHash"(controller: 'grurl', action:'redirectRequest')
        "/"(controller: 'grurl', action: 'index')
        "500"(view:'/error')
    }
}
