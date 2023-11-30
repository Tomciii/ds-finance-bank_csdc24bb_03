package net.froihofer.dsfinance.bank.client;

import common.BankingInterface;
import common.BankingInterfaceException;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.froihofer.util.AuthCallbackHandler;
import net.froihofer.util.WildflyJndiLookupHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for starting the bank client.
 *
 */
public class BankClient {
  private static Logger log = LoggerFactory.getLogger(BankClient.class);

  /**
   * Skeleton method for performing an RMI lookup
   */
  private BankingInterface getRmiProxy() {
    AuthCallbackHandler.setUsername("customer");
    AuthCallbackHandler.setPassword("customerpass");
    Properties props = new Properties();
    props.put(Context.SECURITY_PRINCIPAL,AuthCallbackHandler.getUsername());
    props.put(Context.SECURITY_CREDENTIALS,AuthCallbackHandler.getPassword());
    try {
      WildflyJndiLookupHelper jndiHelper = new WildflyJndiLookupHelper(new InitialContext(props), "ds-finance-bank-ear", "ds-finance-bank-ejb", "");
      //Name of EJB => BankingInterfaceService
      //TODO: Lookup the proxy and assign it to some variable or return it by changing the
      //      return type of this method
      BankingInterface bankingInterface = jndiHelper.lookup("BankingInterfaceService", BankingInterface.class);
      System.out.println("Successfully connected to proxy");

      return bankingInterface;
    }
    catch (NamingException e) {
      log.error("Failed to initialize InitialContext.",e);
    }

    return null;
  }

  private void run() {
    BankingInterface bankingInterface = getRmiProxy();

    try{
        assert bankingInterface != null;
        System.out.println(bankingInterface.getInvestableVolume());
        System.out.println(bankingInterface.login("test","test"));
    }catch(BankingInterfaceException be){
      log.error("BankingInterface threw Exception: "+be.getMessage());
    }catch(Exception e){
      log.error("Something went wrong: "+e.getMessage());
    }

  }

  public static void main(String[] args) {
    BankClient client = new BankClient();
    client.run();
  }
}
