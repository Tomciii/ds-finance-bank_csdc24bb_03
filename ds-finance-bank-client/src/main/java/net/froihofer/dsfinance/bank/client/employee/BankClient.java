package net.froihofer.dsfinance.bank.client.employee;

import common.bankingInterface.BankingInterfaceException;

public class BankClient {
  public static void main(String[] args) throws BankingInterfaceException {
    BankClientConsole bankClientConsole = new BankClientConsole();
    bankClientConsole.processInput();
  }
}