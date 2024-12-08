package business.exceptions;

public class CheckoutException extends Exception {
    private static final long serialVersionUID = -5633915762703837868L;

    public CheckoutException() {
        super();
    }

    public CheckoutException(String msg) {
        super(msg);
    }

    public CheckoutException(Throwable t) {
        super(t);
    }
    
}
