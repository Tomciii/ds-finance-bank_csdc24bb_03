package net.froihofer.util.jboss;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;

import common.BankingInterface;
import common.BankingInterfaceException;

@Stateless(name="BankingInterfaceService")
@PermitAll
public class BankingInterfaceImpl implements BankingInterface {

    @Inject PersonDAO personDAO;
    @Inject PersonTranslator personTranslator;

    Bank bank = new Bank();

    public boolean login(String username, String password) throws BankingInterfaceException {
        System.out.println("Test");
        System.out.println(username);
        if(username=="test" && password=="test"){
            return true;
        }else{
            return true;
        }
    }

    @Override
    public boolean isEmployee(String username, String password) throws BankingInterfaceException {
        return false;
    }

    @Override
    public boolean isCustomer(String username, String password) throws BankingInterfaceException {
        return false;
    }

    @Override
    public String searchStockByISIN(String isin) throws BankingInterfaceException {
        return null;
    }

    @Override
    public String searchStockByName(String name) throws BankingInterfaceException {
        return null;
    }

    @Override
    public void buySockByISIN(String ISIN, double amount, String customerNr) throws BankingInterfaceException {

    }

    @Override
    public void sellStockByISIN(String ISIN, double amount, String customerNr) throws BankingInterfaceException {

    }

    @Override
    public String[] getDepot(String customerNr) throws BankingInterfaceException {
        return new String[0];
    }

    @Override
    public void createCustomer(String name, String givenname, String address, String customerNr, int svnr, String username, String password) {

    }

    @Override
    public String searchCustomer(String name, String givenname, String customerNr) throws BankingInterfaceException {
        return null;
    }

    @Override
    public String getInvestableVolume() throws BankingInterfaceException {
        return "Test";
    }

    @Override
    public long getVariable(String name) throws BankingInterfaceException {
        return 0;
    }
}
