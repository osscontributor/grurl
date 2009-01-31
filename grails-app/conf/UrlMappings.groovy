class UrlMappings {
    static mappings = {
        "/$controller/$action"{}
        "/$urlHash"(controller: 'grurl', action:'redirectRequest') {
            constraints {
                urlHash matches: /[a-z0-9]*/
            }
        }
        "/"(controller: 'grurl', action: 'index')
        "/questions"(view: '/questions')
        "/developers"(view: '/developers')
        "/terms"(view: '/terms')
        "/contact"(view: '/contact')
        "/generateGrurl"(action: 'generate', controller: 'grurl')
        
        "500"(view:'/error')
        "/generate" {
            controller = 'rest'
            action = [POST:'xmlGenerate', GET:'paramGenerate']
        }
        "/grurlifyString" {
            controller = 'rest'
            action = [GET:'grurlifyString']
        }
    }
}
