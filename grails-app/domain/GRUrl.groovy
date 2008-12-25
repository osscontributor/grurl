class GRUrl {
    String realUrl
    Date dateCreated
    Date lastUpdated
    Integer accessCount = 0
    
    static constraints = {
        realUrl url: true, unique: true
    }
    
}
