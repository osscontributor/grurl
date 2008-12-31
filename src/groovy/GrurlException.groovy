class GrurlException  extends Exception {
    
    public GrurlException() {
        super()
    }

    public GrurlException(String message) {
        super(message)
    }

    public GrurlException(Throwable throwable) {
        super(throwable)
    }

    public GrurlException(String message, Throwable throwable) {
        super(message, throwable)
    }
}