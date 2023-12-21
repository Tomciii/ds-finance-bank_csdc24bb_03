package net.froihofer.util.jboss;

import jakarta.xml.bind.JAXBException;
import net.froihofer.util.jboss.persistance.dao.*;
import net.froihofer.util.jboss.persistance.entity.Bank;
import net.froihofer.util.jboss.persistance.entity.Customer;
import net.froihofer.util.jboss.persistance.entity.Depot;
import net.froihofer.util.jboss.persistance.mapper.CustomerMapper;
import net.froihofer.util.jboss.persistance.mapper.DepotMapper;
import net.froihofer.util.jboss.persistance.mapper.EmployeeMapper;
import net.froihofer.util.jboss.soapclient.SoapClient;
import net.froihofer.util.jboss.soapclient.SoapClientProperties;
import net.froihofer.util.jboss.soapclient.model.FindStockQuotesByCompanyNameResponse;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;


public class BankService {


    @Inject
    CustomerDAO customerDAO;

    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    DepotDAO depotDAO;

    @Inject
    SharesDAO stockDAO;

    @Inject
    BankDAO bankDAO;



    @Inject
    CustomerMapper customerMapper;

    @Inject
    EmployeeMapper employeeMapper;

    @Inject
    DepotMapper depotMapper;

    public BankService() {
        SoapClientProperties.username = "csdc24bb_03";
        SoapClientProperties.password = "oF0Queuhae";

        depotDAO = new DepotDAO();
        customerDAO = new CustomerDAO();
        employeeDAO = new EmployeeDAO();
        stockDAO = new SharesDAO();
        bankDAO = new BankDAO();

        depotDAO.persist(new Depot(1,1,new ArrayList<>()));
        customerDAO.persist(new Customer(1,"test","test","test",1));
        bankDAO.persist(new Bank(1, "TestBank", 100000.0));
    }

    public FindStockQuotesByCompanyNameResponse getFindStockQuotesByCompanyNameResponse(String name) throws JAXBException, IOException {
        return SoapClient.findStockQuotesByCompanyName(name);
    }
}