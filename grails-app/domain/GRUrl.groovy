class GRUrl {
    String realUrl
    Date dateCreated
    
    static constraints = {
        realUrl url: true, unique: true
    }
    
}
