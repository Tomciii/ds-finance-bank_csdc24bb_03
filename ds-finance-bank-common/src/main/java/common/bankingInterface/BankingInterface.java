package common.bankingInterface;

import common.dto.DepotDTO;
import common.dto.ListStockDTO;
import common.dto.PersonDTO;
import common.dto.TradeDTO;

import javax.ejb.Remote;

@Remote
public interface BankingInterface {

    // TODO - Refactor to stateful bean so that we can remove login, isEmployee, isCustomer
    /** Returns a boolean value, if a Member for given username and password is a member. */
    boolean login(String username, String password) throws BankingInterfaceException;

    boolean isEmployee() throws BankingInterfaceException;

    boolean isCustomer() throws BankingInterfaceException;

    ListStockDTO searchStockByISIN(String isin) throws BankingInterfaceException;

    ListStockDTO searchStockByName(String name) throws BankingInterfaceException;

    void buySockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException;

    void sellStockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException;

    DepotDTO getDepot(int customerNr) throws BankingInterfaceException;

    void createPerson(String name, String givenname, String address, int svnr, String username, String password);

    String createCustomer(String name, String givenname, String address, int svnr, String username, String password);

    void createEmployee(int snvt);

    // TODO: Return Person
    PersonDTO searchCustomer(Integer customerNr)  throws BankingInterfaceException;

    String getInvestableVolume() throws BankingInterfaceException;


    /** Persistently stores the value of the given variable. */
    //public void storeVariable(VariableDTO variable) throws common.bankingInterface.BankingInterfaceException;

    /** Returns the value of a persisted variable. */
    public long getVariable(String name) throws BankingInterfaceException;
}
