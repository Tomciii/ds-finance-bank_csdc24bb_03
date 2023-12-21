package net.froihofer.util.jboss;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.*;
import jakarta.xml.bind.JAXBException;
import net.froihofer.util.jboss.persistance.entity.Bank;
import net.froihofer.util.jboss.persistance.entity.Customer;
import net.froihofer.util.jboss.persistance.entity.Depot;
import net.froihofer.util.jboss.persistance.entity.Shares;
import net.froihofer.util.jboss.persistance.mapper.DepotMapper;
import net.froihofer.util.jboss.persistance.mapper.StockMapper;
import net.froihofer.util.jboss.soapclient.SoapClient;

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
        if (sessionContext.isCallerInRole("employee")) {
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
        if (sessionContext.isCallerInRole("customer")) {
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

    // TODO - Return something like a StockDTO which is in the commons so that client can also access the dto (Like The "PersonTranslator" class)
    // TODO - Catch a BankingInterfaceException here (And throw one inside the logic where needed)
    @Override
    public ListStockDTO searchStockByName(String name) throws BankingInterfaceException {

        try {
            return stockMapper.toStockDTOList(bankService.getFindStockQuotesByCompanyNameResponse(name));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buySockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException {

        Bank bank = bankService.bankDAO.findById(1);

        if (bank == null || bank.getInvestableVolume() < tradeDTO.getAmount()) {
            return; // throw new InvalidArgumentException or something
        }

        Depot depot = bankService.depotDAO.findById(Integer.valueOf(tradeDTO.getCustomerID()));
        if (depot == null) {
            return; // throw NoDepotExistantException or NullPointer
        }

        List<Shares> shares = bankService.stockDAO.findByStockName(tradeDTO.getStockName());
        if (shares == null || shares.isEmpty()) {
            buyNewShare(depot, tradeDTO);
            bankService.depotDAO.merge(depot);
            removeFromInvestableVolume(bank, tradeDTO);

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

        removeFromInvestableVolume(bank, tradeDTO);
            bankService.depotDAO.merge(depot);
    }

    @Override
    public void sellStockByISIN(TradeDTO tradeDTO) throws BankingInterfaceException {
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

        addToInvestableVolume(bank, tradeDTO);

        callSoapClientSellMethod(tradeDTO);
    }

    @Override
    public void createPerson(String name, String givenname, String address, int svnr, String username, String password) {
      //  bankService.createPerson(name, givenname, address, svnr, username, password);
    }

    @Override
    public String createCustomer(String name, String givenname, String address, int customerNumber, String username, String password) {
        Depot depot = new Depot(customerNumber, customerNumber, new ArrayList<>());
        Customer customer = new Customer(customerNumber, name, address, givenname, customerNumber);
        bankService.depotDAO.persist(depot);
        bankService.customerDAO.persist(customer);
        return customer.toString();
    }

    @Override
    public void createEmployee(int svnr) {

    }

    @Override
    public DepotDTO getDepot(int customerNr) throws BankingInterfaceException {
        return depotMapper.toDepotDTO(bankService.depotDAO.findById(customerNr));
    }


    @Override
    public PersonDTO searchCustomer(Integer customerNr) throws BankingInterfaceException {
        var customer = bankService.customerDAO.findById(customerNr);
        return new CustomerDTO(customer.getCustomerNr(), customer.getName(), customer.getGivenname(), customer.getAddresse(), customer.getBankDepotID());
    }

    @Override
    public String getInvestableVolume() throws BankingInterfaceException {
        return bankService.bankDAO.findById(1).getInvestableVolume().toString();
    }

    @Override
    public long getVariable(String name) throws BankingInterfaceException {
        return 0;
    }

    private void buyNewShare(Depot depot, TradeDTO tradeDTO) {
        Shares share = new Shares(depot, tradeDTO.getStockName(), tradeDTO.getAmount());
        bankService.stockDAO.persist(share);

        if (depot.getShares() == null) {
            depot.setShares(new ArrayList<>());
        }

        depot.getShares().add(share);
    }

    private void buyMoreOfExistingShare(TradeDTO tradeDTO, Depot depot, Shares existingSharesEntry) {
        existingSharesEntry.setStockShares(existingSharesEntry.getStockShares() + tradeDTO.getAmount());
        bankService.stockDAO.merge(existingSharesEntry);
        depot.getShares().removeIf(share -> tradeDTO.getStockName().equals(share.getStockName()));
        depot.getShares().add(existingSharesEntry);
    }

    private Shares findExistingShare(TradeDTO tradeDTO, List<Shares> shares) {
        return shares.stream()
                .filter(share -> tradeDTO.getStockName().equals(share.getStockName()))
                .findFirst()
                .orElse(null);
    }

    private void removeFromInvestableVolume(Bank bank, TradeDTO tradeDTO) {
        bank.setInvestableVolume(bank.getInvestableVolume() - tradeDTO.getAmount());
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
        bank.setInvestableVolume(bank.getInvestableVolume() + tradeDTO.getAmount());
        bankService.bankDAO.persist(bank);
    }
}