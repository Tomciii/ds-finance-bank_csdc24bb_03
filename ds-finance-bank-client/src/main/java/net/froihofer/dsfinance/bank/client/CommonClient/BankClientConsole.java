package net.froihofer.dsfinance.bank.client.CommonClient;

import common.bankingInterface.BankingInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class BankClientConsole {
    private static Logger loyeepasog = LoggerFactory.getLogger(BankClientConsole.class);
    private Scanner scanner = new Scanner(System.in);
    private BankClientInputHandler inputHandler = new BankClientInputHandler();


    void processInput() throws BankingInterfaceException {
            System.out.println("Welcome to the Banking Client Application for employees.");
            boolean isProcessing = true;

            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
            System.out.println("Type in your username - ie. employee // customer // username (for user not in Bank db, but in Serverdb)");
            String username = scanner.nextLine();
            System.out.println("Type in your password - ie. employeepass //customerpass // password");
            String password = scanner.nextLine();

            inputHandler.login(username, password);

            System.out.println("INFO: Your role: "+inputHandler.getRole());

            if(inputHandler.getRole()=="employee"){
                while (isProcessing){
                    try {
                        displayActions();
                        System.out.println(" ");
                        System.out.print("Input> ");
                        String input = scanner.nextLine();

                        switch (input) {
                            case "1":
                                inputHandler.addCustomer();
                                break;
                            case "2":
                                inputHandler.searchCustomer();
                                break;
                            case "3":
                                inputHandler.searchStockByName();
                                break;
                            case "4":
                                inputHandler.buyStockForCustomer();
                                break;
                            case "5":
                                inputHandler.sellStockForCustomer();
                                break;
                            case "6":
                                inputHandler.displayDepotInfoOfCustomer();
                                break;
                            case "7":
                                inputHandler.displayInvestableVolume();
                                break;
                            case "8":
                                inputHandler.searchCustomerByUsername();
                                break;
                            case "HELP":
                                this.displayActions();
                                break;
                            case "EXIT":
                                isProcessing = false;
                                break;
                            default:
                                break;
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }else{
                if(inputHandler.getRole()=="customer"){
                        while (isProcessing) {
                            try{
                            this.displayActionsCustomer();
                            System.out.println(" ");
                            System.out.print("Input> ");
                            String input = scanner.nextLine();

                            switch (input) {
                                case "1":
                                    inputHandler.searchStockByName();
                                    break;
                                case "2":
                                    inputHandler.buyStock();
                                    break;
                                case "3":
                                    inputHandler.sellStock();
                                    break;
                                case "4":
                                    inputHandler.displayDepotInfo();
                                    break;
                                case "HELP":
                                    this.displayActions();
                                    break;
                                case "EXIT":
                                    isProcessing = false;
                                    break;
                                default:
                                    break;
                            }

                        }catch(Exception e){
                            System.out.println(e);
                        }
                        }
                    }else{
                    System.out.println("Username not in DB");
                }
            }

    }

    private void displayActions(){
        System.out.println("Enter '1' to add a new customer");
        System.out.println("Enter '2' to search a customer via customer number");
        System.out.println("Enter '3' to search for available stocks");
        System.out.println("Enter '4' to buy stocks for a customer");
        System.out.println("Enter '5' to sell stocks for a customer");
        System.out.println("Enter '6' to display depot details of a customer");
        System.out.println("Enter '7' to search the investable volume");
        System.out.println("Enter '8' to search a customer via username");
        System.out.println("Enter 'HELP' to display all possible actions");
        System.out.println("Enter 'EXIT' to exit");
    }

    private void displayActionsCustomer(){
        System.out.println("Enter '1' to search for a stock");
        System.out.println("Enter '2' to buy a stock");
        System.out.println("Enter '3' to sell a stock");
        System.out.println("Enter '4' to display details of your depot");
        System.out.println("Enter 'HELP' to display all possible actions");
        System.out.println("Enter 'EXIT' to exit");
    }
}
