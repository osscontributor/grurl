class GRUrl {
    
    def grurlService
    
    String realUrl
    Date dateCreated
    Date lastUpdated
    Integer accessCount = 0
    String grurlUrl
    
    static transients = ['grurlUrl']
    
    static constraints = {
        realUrl url: true, unique: true
    }
    
    def getGrurlUrl() {
        grurlService.conjureGrurlUrl(this)
    }
    
}
