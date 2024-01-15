package net.froihofer.util.jboss;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.*;
import jakarta.xml.bind.JAXBException;
import net.froihofer.util.jboss.persistance.entity.*;
import net.froihofer.util.jboss.persistance.mapper.DepotMapper;
import net.froihofer.util.jboss.persistance.mapper.StockMapper;
import net.froihofer.util.jboss.soapclient.SoapClient;
import net.froihofer.util.jboss.soapclient.model.FindStockQuotesByCompanyNameResponse;
import net.froihofer.util.jboss.util.WildflyAuthDBHelper;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Stateless(name="BankingInterfaceService")
@PermitAll
public class BankingInterfaceImpl implements BankingInterface {

    @Inject
    StockMapper stockMapper;

    @Inject
    DepotMapper depotMapper;

    @Resource
    private SessionContext sessionContext;

    private final BankService bankService = new BankService();

    private final WildflyAuthDBHelper wildflyAuthDBHelper= new WildflyAuthDBHelper();

    public boolean login(String username, String password) throws BankingInterfaceException {
        System.out.println("TestA");
        System.out.println(username);
        if(username=="test" && password=="test"){
            try {
                bankService.getFindStockQuotesByCompanyNameResponse("Apple");
            } catch (JAXBException |IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }else{
            try {
                bankService.getFindStockQuotesByCompanyNameResponse("Apple");
            } catch (JAXBException |IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

    @Override
    public boolean isEmployee() throws BankingInterfaceException {
        Principal principal = sessionContext.getCallerPrincipal();
        String username = principal.getName();
        System.out.println("Logged-in User: " + username);
        // Check if the user is in a specific role
        if (sessionContext.isCallerInRole("employee") && bankService.employeeDAO.findByUsername(username)) {
            System.out.println("User is Employee Role");
            return true;
        } else {
            System.out.println("User is not in Customer Role");
            return false;
        }
    }

    @Override
    public boolean isCustomer() throws BankingInterfaceException {
        Principal principal = sessionContext.getCallerPrincipal();
        String username = principal.getName();
        System.out.println("Logged-in User: " + username);
        // Check if the user is in a specific role
        if (sessionContext.isCallerInRole("customer") && bankService.customerDAO.userExists(username)) {
            System.out.println("User is Customer Role");
            return true;
        } else {
            System.out.println("User is not in Customer Role");
            return false;
        }
    }

    @Override
    public ListStockDTO searchStockByISIN(String isin) throws BankingInterfaceException {
        try {
            return stockMapper.toStockDTOList(bankService.getFindStockQuotesByIsinResponse(isin));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListStockDTO searchStockByName(String name) throws BankingInterfaceException {

        try {
            return stockMapper.toStockDTOList(bankService.getFindStockQuotesByCompanyNameResponse(name));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getStockValue(String symbol) throws BankingInterfaceException{
        System.out.println(symbol);
        try {
            FindStockQuotesByCompanyNameResponse response = bankService.getFindStockQuotesByCompanyNameResponse(symbol);

            String fullcompanyName = null;

            for (var stock : response.getReturn()) {
                if (stock != null){
                   if(stock.getCompanyName().equals(symbol)){
                       fullcompanyName = stock.getCompanyName();
                   }
                }
            }

            return stockMapper.getPrice(bankService.getFindStockQuotesByCompanyNameResponse(fullcompanyName));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buySockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException {

        try{
            Bank bank = bankService.bankDAO.findById(1);

            if (bank == null || bank.getInvestableVolume() < tradeDTO.getAmount()) {
                return; // throw new InvalidArgumentException or something
            }

            Depot depot = bankService.depotDAO.findById(Integer.valueOf(tradeDTO.getCustomerID()));
            if (depot == null) {
                return; // throw NoDepotExistantException or NullPointer
            }

            FindStockQuotesByCompanyNameResponse response = null;

            try{
                System.out.println(tradeDTO.getStockName().toString());
                response = bankService.getFindStockQuotesByCompanyNameResponse(tradeDTO.getStockName());
                System.out.println("Got Stock Info");
            }catch(Exception e){
                System.out.println(e.toString());
                return;
            }

            System.out.println(response.toString());

            String symbol = null;
            String fullcompanyName = null;


            for (var stock : response.getReturn()) {
                if (stock != null){
                    if(stock.getCompanyName().equals(tradeDTO.getStockName())){
                        fullcompanyName = stock.getCompanyName();
                        symbol = stock.getSymbol();
                    }
                }
            }

            System.out.println("tradeDTO.getStockName()");
            System.out.println(tradeDTO.getStockName());
            System.out.println("Symbol");
            System.out.println(symbol);
            tradeDTO.setStockName(symbol);
            System.out.println("tradeDTO.getStockName()");
            System.out.println(tradeDTO.getStockName());


            if(symbol!=null){
                List<Shares> shares = bankService.stockDAO.findByStockName(tradeDTO.getStockName());
                if (shares == null || shares.isEmpty()) {
                    System.out.println("ifffff");
                    tradeDTO.setStockName(fullcompanyName);
                    buyNewShare(depot, tradeDTO);
                    System.out.println("merge depot");
                    bankService.depotDAO.merge(depot);
                    tradeDTO.setStockName(fullcompanyName);
                    removeFromInvestableVolume(bank, tradeDTO);
                    System.out.println("ifffff");
                    tradeDTO.setStockName(symbol);
                    callSoapClientBuyMethod(tradeDTO);
                    return;
                }

                Shares existingSharesEntry = findExistingShare(tradeDTO, shares);

                if (existingSharesEntry != null) {
                    buyMoreOfExistingShare(tradeDTO, depot, existingSharesEntry);
                } else {
                    buyNewShare(depot, tradeDTO);
                }

                callSoapClientBuyMethod(tradeDTO);


                tradeDTO.setStockName(fullcompanyName);
                removeFromInvestableVolume(bank, tradeDTO);

                tradeDTO.setStockName(symbol);
                bankService.depotDAO.merge(depot);
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void sellStockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException {
        try{

            Depot depot = bankService.depotDAO.findById(Integer.valueOf(tradeDTO.getCustomerID()));
            Bank bank = bankService.bankDAO.findById(1);

            if (depot == null || depot.getShares() == null || depot.getShares().isEmpty()) {
                return; // throw NoDepotExistantException or NullPointer
            }

            List<Shares> shares = bankService.stockDAO.findByStockName(tradeDTO.getStockName());
            Shares existingSharesEntry = findExistingShare(tradeDTO, shares);

            if (existingSharesEntry == null) {
                return;
            }

            existingSharesEntry.setStockShares(existingSharesEntry.getStockShares() - tradeDTO.getAmount()); // Throw "InvalidArgumentException" if negative number in total
            bankService.stockDAO.merge(existingSharesEntry);
            depot.getShares().removeIf(share -> tradeDTO.getStockName().equals(share.getStockName()));
            depot.getShares().add(existingSharesEntry);
            bankService.depotDAO.merge(depot);

            callSoapClientSellMethod(tradeDTO);

            tradeDTO.setStockName(existingSharesEntry.getstockname_realName());
            addToInvestableVolume(bank, tradeDTO);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void createCustomer(String name, String givenname, String address, int customerNumber, String username, String password) {
        try{
            if(isEmployee()==true){
                Depot depot = new Depot(customerNumber, customerNumber, new ArrayList<>());
                Customer customer = new Customer(customerNumber, name, address, givenname, customerNumber, username, password);
                bankService.depotDAO.persist(depot);
                bankService.customerDAO.persist(customer);
                try {
                    wildflyAuthDBHelper.addUser(username, password, new String[]{"customer"});
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Override
    public void createEmployee(int manr, String name, String givenname, String address, String username, String password) {
        try{
            if(isEmployee()==true){
                Employee employee = new Employee(manr,name,givenname, address, username, password);
                bankService.employeeDAO.persist(employee);
                try {
                    wildflyAuthDBHelper.addUser(username, password, new String[]{"employee"});
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public DepotDTO getDepot(int customerNr) throws BankingInterfaceException {
        try{
            System.out.println("getDepot");
            Depot depot = bankService.depotDAO.findById(customerNr);
            System.out.println("stockvalues");
            ArrayList<Double> stockValues = new ArrayList<Double>();
            System.out.println(depot.toString());
            for(int i=0; i<depot.getShares().size(); i++){
                System.out.println(depot.getShares().get(i).getstockname_realName());
                stockValues.add(getStockValue(depot.getShares().get(i).getstockname_realName()));
                System.out.print("Got Values");
            }
            System.out.println(stockValues.toString());
            return depotMapper.toDepotDTOwithPrice(bankService.depotDAO.findById(customerNr),stockValues);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


    @Override
    public CustomerDTO searchCustomer(Integer customerNr) throws BankingInterfaceException {
        var customer = bankService.customerDAO.findById(customerNr);
        return new CustomerDTO(customer.getCustomerNr(), customer.getName(), customer.getGivenname(), customer.getAddresse(), customer.getBankDepotID(), customer.getUsername(), customer.getPassword() );
    }

    @Override
    public CustomerDTO searchCustomerByUsername(String username) {
        var customer = bankService.customerDAO.findByUsername(username);

        if (customer == null) {
            return null;
        }

        return new CustomerDTO(customer.getCustomerNr(), customer.getName(), customer.getGivenname(), customer.getAddresse(), customer.getBankDepotID(), customer.getUsername(), customer.getPassword() );
    }

    @Override
    public String getInvestableVolume() throws BankingInterfaceException {
        try{
            return bankService.bankDAO.findById(1).getInvestableVolume().toString();
        }catch (Exception e){
            System.out.println(e);
        }

        return null;

    }

    @Override
    public long getVariable(String name) throws BankingInterfaceException {
        return 0;
    }

    private void buyNewShare(Depot depot, TradeDTO tradeDTO) {
        try{
            System.out.println("buyNewShare");

            FindStockQuotesByCompanyNameResponse response = null;
            try{
                System.out.println(tradeDTO.getStockName().toString());
                response = bankService.getFindStockQuotesByCompanyNameResponse(tradeDTO.getStockName());
            }catch(Exception e){
                System.out.println(e.toString());
            }

            System.out.println(response.toString());

            String symbol = null;
            String fullcompanyName = null;

            for (var stock : response.getReturn()) {
                if (stock != null){
                    if(stock.getCompanyName().equals(tradeDTO.getStockName())){
                        fullcompanyName = stock.getCompanyName();
                        symbol = stock.getSymbol();
                    }
                }
            }

            if( symbol != null && fullcompanyName!= null){
                Shares share = new Shares(depot, symbol, tradeDTO.getAmount(), fullcompanyName);
                bankService.stockDAO.persist(share);

                if (depot.getShares() == null) {
                    depot.setShares(new ArrayList<>());
                }

                depot.getShares().add(share);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void buyMoreOfExistingShare(TradeDTO tradeDTO, Depot depot, Shares existingSharesEntry) {
        try{
            existingSharesEntry.setStockShares(existingSharesEntry.getStockShares() + tradeDTO.getAmount());
            bankService.stockDAO.merge(existingSharesEntry);
            depot.getShares().removeIf(share -> tradeDTO.getStockName().equals(share.getStockName()));
            depot.getShares().add(existingSharesEntry);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private Shares findExistingShare(TradeDTO tradeDTO, List<Shares> shares) {
        try{
            return shares.stream()
                    .filter(share -> tradeDTO.getStockName().equals(share.getStockName()))
                    .findFirst()
                    .orElse(null);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    private void removeFromInvestableVolume(Bank bank, TradeDTO tradeDTO) {
        try{
            bank.setInvestableVolume(bank.getInvestableVolume() - tradeDTO.getAmount()*getStockValue(tradeDTO.getStockName()));
        }catch (Exception e){
            System.out.println(e);
        }
        bankService.bankDAO.persist(bank);
    }

    private void callSoapClientSellMethod(TradeDTO tradeDTO) {
        try {
            SoapClient.sell(tradeDTO.getStockName(), (int) tradeDTO.getAmount());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private void callSoapClientBuyMethod(TradeDTO tradeDTO) {
        try {
            SoapClient.buy(tradeDTO.getStockName(), (int) tradeDTO.getAmount());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addToInvestableVolume(Bank bank, TradeDTO tradeDTO) {
        try{
            bank.setInvestableVolume(bank.getInvestableVolume() + tradeDTO.getAmount()*getStockValue(tradeDTO.getStockName()));
        }catch(Exception e){
            System.out.println(e);
        }
        bankService.bankDAO.persist(bank);
    }
}