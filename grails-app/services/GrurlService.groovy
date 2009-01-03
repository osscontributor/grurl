import java.util.regex.Pattern
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class GrurlService implements ApplicationContextAware {
    def taglib
    def urlValidator
    def urlRegex = /https?:\/\/([-\w\.]+)+(:\d+)?(\/([\(\-\)\%\,\w\/_\.]*(\?\S+)?)?)?/
    def urlPattern = Pattern.compile(urlRegex)
    
    def refine(url) {
        def realUrl = url
        if(!urlValidator.isValid(realUrl)) {
            realUrl = "http://${realUrl}"
        }
        realUrl
    }
    
    def resolve(url) throws GrurlException {
        def urlInstance = GRUrl.findByRealUrl(url)
        if (!urlInstance) {
            urlInstance = new GRUrl(realUrl: url)
            if (!urlInstance.save()) {
                throw new GrurlException("An error occurred processing URL: ${url}")
            }
        }
        urlInstance
    }
    
    def findUrls(haystack) {
        def matcher = haystack =~ urlPattern
        matcher.collect { it[0] }
    }
    
    def replaceUrls(haystack) {
        haystack.replaceAll(urlRegex, { Object[] it ->
            resolve(refine(it[0])).grurlUrl
        })
    }
    
    def conjureGrurlUrl(grurl) {
        taglib.createLink(
            controller: 'grurl',
            action: 'redirectRequest', 
            params: [urlHash: grurl.id.encodeAsGrurl()], 
            absolute: true)
    }
    
    // only getting the app context so we can grab the ApplicationTagLib, which will be used to createLink 
    // for the GRUrl
    public void setApplicationContext(ApplicationContext applicationContext) {
        taglib = applicationContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')
    }
    
}