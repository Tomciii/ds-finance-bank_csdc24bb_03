package common;

public class BankingInterfaceException extends java.lang.Exception {
    /**
     * Creates a new instance of <code>common.BankingInterfaceException</code> without detail message.
     */
    public BankingInterfaceException() {
    }

    public BankingInterfaceException(String msg) {
        super(msg);
    }

    public BankingInterfaceException(String msg, Throwable cause) {
        super(msg,cause);
    }

    public BankingInterfaceException(Throwable cause) {
        super(cause);
    }

}
