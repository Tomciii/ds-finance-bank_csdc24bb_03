package net.froihofer.dsfinance.bank.client.employee;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.DepotDTO;
import common.dto.StockDTO;
import common.dto.TradeDTO;
import net.froihofer.util.CommonInputHandler;
import net.froihofer.util.RmiProxyBuilder;

import java.util.List;
import java.util.Scanner;

class BankClientInputHandler {
    private Scanner scanner = new Scanner(System.in);
    private RmiProxyBuilder rmiProxyBuilder = new RmiProxyBuilder();
    BankingInterface bankingInterface = rmiProxyBuilder.getRmiProxy("customer", "customerpass");;

    // TODO Implement all the person persistance logic
    void searchStockByName() {
        CommonInputHandler.searchStockByName(bankingInterface);
    }

    public void addCustomer() {
        String firstName = CommonInputHandler.getFirstName();
        String lastName = CommonInputHandler.getLastName();
        String address = CommonInputHandler.getAddress();
        Integer customerNumber = CommonInputHandler.getCustomerNumber();

       String returnValue = bankingInterface.createCustomer(firstName, lastName, address, customerNumber, null, null);
        System.out.println(returnValue);
    }

    public void searchCustomer() {
        Integer customerNumber = CommonInputHandler.getCustomerNumber();

        try {
            String value = bankingInterface.searchCustomer(customerNumber);
            System.out.println(value);
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public void buyStockForCustomer() {
        System.out.println("Buying Stock: ");
        String customerID = CommonInputHandler.getCustomerID();
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
        String stockName = CommonInputHandler.getStockName();
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
}
