package common;

import javax.ejb.Remote;

@Remote
public interface BankingInterface {

    /** Returns a boolean value, if a Member for given username and password is a member. */
    public boolean login(String username, String password) throws BankingInterfaceException;

    public boolean isEmployee(String username, String password) throws BankingInterfaceException;

    public boolean isCustomer(String username, String password) throws BankingInterfaceException;

    public String searchStockByISIN(String isin) throws BankingInterfaceException;

    public String searchStockByName(String name) throws BankingInterfaceException;

    public void buySockByISIN(String ISIN, double amount, String customerNr) throws BankingInterfaceException;

    public void sellStockByISIN(String ISIN, double amount, String customerNr) throws BankingInterfaceException;

    public String[] getDepot(String customerNr) throws BankingInterfaceException;

    public void createCustomer(String name, String givenname, String address, String customerNr, int svnr, String username, String password);

    public String searchCustomer(String name, String givenname, String customerNr)  throws BankingInterfaceException;

    public String getInvestableVolume() throws BankingInterfaceException;


    /** Persistently stores the value of the given variable. */
    //public void storeVariable(VariableDTO variable) throws common.BankingInterfaceException;

    /** Returns the value of a persisted variable. */
    public long getVariable(String name) throws BankingInterfaceException;
}
