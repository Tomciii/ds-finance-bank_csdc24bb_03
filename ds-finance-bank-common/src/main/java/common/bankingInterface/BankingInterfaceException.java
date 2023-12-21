package common.bankingInterface;

// TODO - Have subclasses of BankingInterfaceException for each special case and throw appropriately
public class BankingInterfaceException extends Exception {
    /**
     * Creates a new instance of <code>common.bankingInterface.BankingInterfaceException</code> without detail message.
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
