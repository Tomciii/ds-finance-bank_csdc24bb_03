package net.froihofer.dsfinance.bank.client.CommonClient;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.CustomerDTO;
import common.dto.TradeDTO;
import net.froihofer.util.CommonInputHandler;
import net.froihofer.util.RmiProxyBuilder;

import java.util.Scanner;

class BankClientInputHandler {
    private Scanner scanner = new Scanner(System.in);
    private RmiProxyBuilder rmiProxyBuilder = new RmiProxyBuilder();
    BankingInterface bankingInterface = null;

    // TODO Implement all the person persistance logic
    void searchStockByName() {
        CommonInputHandler.searchStockByName(bankingInterface);
    }

    String getRole() {
        return CommonInputHandler.getRole(bankingInterface);
    }

    public void login(String username, String password){
        bankingInterface = rmiProxyBuilder.getRmiProxy(username, password);
    }

    public void addCustomer() {
        String firstName = CommonInputHandler.getFirstName();
        String lastName = CommonInputHandler.getLastName();
        String address = CommonInputHandler.getAddress();
        Integer customerNumber = CommonInputHandler.getCustomerNumber();
        String username = CommonInputHandler.getUsername();
        String password = CommonInputHandler.getPassword();
        bankingInterface.createCustomer(firstName, lastName, address, customerNumber, username, password);
    }

    public void searchCustomer() {
        Integer customerNumber = CommonInputHandler.getCustomerNumber();

        try {
            CustomerDTO value = bankingInterface.searchCustomer(customerNumber);
            System.out.println(value);
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void buyStockForCustomer() {
        System.out.println("Buying Stock: ");
        String customerID = CommonInputHandler.getCustomerID();
        System.out.println("You should enter the full legal companyname i.e. Apple Inc: ");
        String stockName = CommonInputHandler.getStockName();
        Double amount = CommonInputHandler.getAmount();

        try {
            bankingInterface.buySockByISIN(new TradeDTO(customerID, stockName, amount));
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void sellStockForCustomer() {
        System.out.println("Selling Stock: ");
        String customerID = CommonInputHandler.getCustomerID();
        System.out.println("You should enter the symbol i.e. AAPL ");
        String stockName = CommonInputHandler.getStockSymbol();
        Double amount = CommonInputHandler.getAmount();

        try {
            bankingInterface.sellStockByISIN(new TradeDTO(customerID, stockName, amount));
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void displayDepotInfoOfCustomer() {
        System.out.println("Depot Info: ");
        String customerID = CommonInputHandler.getCustomerID();

        CommonInputHandler.printDepotInfo(Integer.valueOf(customerID), bankingInterface);
    }

    public void displayInvestableVolume() {
        System.out.println("Investible Volume: ");
        try {
            String volume = bankingInterface.getInvestableVolume();
            System.out.println(volume);
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void buyStock() {
        System.out.println("Buying Stock: ");
        System.out.println("You should enter the full legal companyname i.e. Apple Inc: ");
        String stock = CommonInputHandler.getStockName();
        Double amount = CommonInputHandler.getAmount();

        try {
            bankingInterface.buySockByISIN(new TradeDTO("1", stock, amount));
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void sellStock() {
        System.out.println("Selling Stock: ");
        System.out.println("You should enter the symbol i.e. AAPL ");
        String stock = CommonInputHandler.getStockSymbol();
        Double amount = CommonInputHandler.getAmount();

        try {
            bankingInterface.sellStockByISIN(new TradeDTO("1", stock, amount));
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void displayDepotInfo() {
        System.out.println("Depot Info: ");
        CommonInputHandler.printDepotInfo(1, bankingInterface);
    }

    public void searchCustomerByUsername() {
        String customerNumber = CommonInputHandler.getUsername();
        CustomerDTO value = bankingInterface.searchCustomerByUsername(customerNumber);

        if (value != null)
            System.out.println(value);
    }
}
