package net.froihofer.util;

import common.bankingInterface.BankingInterface;
import common.bankingInterface.BankingInterfaceException;
import common.dto.DepotDTO;
import common.dto.ListStockDTO;

import java.util.Scanner;

public class CommonInputHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static String getStockName() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in stock name> ");
            stock = scanner.nextLine();
        }

        return stock;
    }

    public static String getStockSymbol() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in symbol name> ");
            stock = scanner.nextLine();
        }

        return stock;
    }


    public static String getUserName() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in user name> ");
            stock = scanner.nextLine();
        }

        return stock;
    }

    public static String getFirstName() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in first name> ");
            stock = scanner.nextLine();
        }

        return stock;
    }


    public static String getLastName() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in last name> ");
            stock = scanner.nextLine();
        }

        return stock;
    }

    public static String getPassword() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in password> ");
            stock = scanner.nextLine();
        }

        return stock;
    }

    public static void printDepotInfo(int customerNr, BankingInterface bankingInterface) {
        try {
            System.out.println("Loading Depot Info: ");
            DepotDTO depotDTO = bankingInterface.getDepot(customerNr);
        } catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }

    public static String getAddress() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in adress> ");
            stock = scanner.nextLine();
        }

        return stock;
    }

    public static Integer getCustomerNumber() {
        String stock = "";
        while (stock.isEmpty()){
            System.out.print("Type in customer number> ");
            stock = scanner.nextLine();

            try {
                return Integer.parseInt(stock);
            } catch (Exception e) {
                stock = "";
            }
        }

        return 0;
    }

    public static Double getAmount() {
        String stock = "";
        while (stock.isEmpty()){
            try {
                System.out.print("Type in amount> ");
                stock = scanner.nextLine();
                return Double.parseDouble(stock);
            } catch (Exception e) {
                stock = "";
            }
        }

        return 0.0;
    }

    public static void searchStockByName(BankingInterface bankingInterface) {
        try {
            String stock = getStockName();
            System.out.println("Searching for stock " + stock + "...");
            ListStockDTO result = bankingInterface.searchStockByName(stock);
            System.out.println("Results for stock " + stock +":" );
            System.out.println(result);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (BankingInterfaceException e) {
            e.printStackTrace();
        }
    }
}
