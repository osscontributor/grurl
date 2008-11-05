class UrlMappings {
    static mappings = {
        "/grurl/generate" (controller: 'grurl', action: 'generate')
        "/$urlHash?"(controller: 'grurl', action:'index')
        "500"(view:'/error')
    }
}
