package net.froihofer.dsfinance.bank.client.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class BankClientConsole {
    private static Logger log = LoggerFactory.getLogger(BankClientConsole.class);
    private Scanner scanner = new Scanner(System.in);
    private BankClientInputHandler inputHandler = new BankClientInputHandler();

    void processInput(){
        System.out.println("Welcome to the Banking Client Application for customers.");
        boolean isProcessing = true;

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        System.out.println("Type in your username - ie. customer");
        String username = scanner.nextLine();
        System.out.println("Type in your password - ie. customerpass");
        String password = scanner.nextLine();

        inputHandler.login(username, password);


        while (isProcessing) {
            this.displayActions();
            System.out.println(" ");
            System.out.print("Input> ");
            String input = scanner.nextLine();

        switch (input) {
            case "1": inputHandler.searchStockByName(); break;
            case "2": inputHandler.buyStock(); break;
            case "3": inputHandler.sellStock(); break;
            case "4": inputHandler.displayDepotInfo(); break;
            case "HELP":  this.displayActions(); break;
            case "EXIT": isProcessing = false; break;
            default: break;
         }
        }
    }

    private void displayActions(){
        System.out.println("Enter '1' to search for a stock");
        System.out.println("Enter '2' to buy a stock");
        System.out.println("Enter '3' to sell a stock");
        System.out.println("Enter '4' to display details of your depot");
        System.out.println("Enter 'HELP' to display all possible actions");
        System.out.println("Enter 'EXIT' to exit");
    }
}
