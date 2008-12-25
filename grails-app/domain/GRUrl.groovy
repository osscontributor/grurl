class GRUrl {
    String realUrl
    Date dateCreated
    Date lastUpdated
    
    static constraints = {
        realUrl url: true, unique: true
    }
    
}
