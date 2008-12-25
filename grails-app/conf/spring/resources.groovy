// Place your Spring DSL code here
beans = {
    urlValidator(org.codehaus.groovy.grails.validation.routines.UrlValidator, ['http', 'https'] as Set)
}