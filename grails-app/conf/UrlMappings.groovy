class UrlMappings {
    static mappings = {
        "/$urlHash"(controller: 'grurl', action:'redirectRequest') {
            constraints {
                urlHash matches: /[a-z0-9]*/
            }
        }
        "/"(controller: 'grurl', action: 'index')
        "/app/questions"(view: '/questions')
        "/app/developers"(view: '/developers')
        "/app/terms"(view: '/terms')
        "/app/contact"(view: '/contact')
        "/app/generateGrurl"(action: 'generate', controller: 'grurl')
        
        "500"(view:'/error')
        "/rest/generate" {
            controller = 'rest'
            action = [POST:'xmlGenerate', GET:'paramGenerate']
        }
        "/rest/grurlifyString" {
            controller = 'rest'
            action = [GET:'grurlifyString']
        }
    }
}
