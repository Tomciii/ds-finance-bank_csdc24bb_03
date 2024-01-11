package net.froihofer.dsfinance.bank.client.customer;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.TradeDTO;
import net.froihofer.util.CommonInputHandler;
import net.froihofer.util.RmiProxyBuilder;

class BankClientInputHandler {
    private RmiProxyBuilder rmiProxyBuilder = new RmiProxyBuilder();
    BankingInterface bankingInterface = null;
    void searchStockByName() {
        CommonInputHandler.searchStockByName(bankingInterface);
    }

    public void login(String username, String password){
        bankingInterface = rmiProxyBuilder.getRmiProxy(username, password);
    }

    public void buyStock() {
        System.out.println("Buying Stock: ");
            String stock = CommonInputHandler.getStockSymbol();
            Double amount = CommonInputHandler.getAmount();

            try {
                bankingInterface.buySockByISIN(new TradeDTO("1", stock, amount));
            } catch (BankingInterfaceException e) {
                e.printStackTrace();
            }
    }

    public void sellStock() {
        System.out.println("Selling Stock: ");
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
}
