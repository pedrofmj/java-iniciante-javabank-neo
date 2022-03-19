package com.javabank.com.javabank.core.transaction.console;

import com.javabank.com.javabank.core.transaction.Transaction;
import com.javabank.core.account.Account;
import com.javabank.core.account.AccountStatementEntry;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TransactionConsole {

    protected Account currentAccount = null;
    protected Scanner scanner = null;

    public TransactionConsole() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
    }

    public void execute() {

        String operation = "";

        boolean running = true;

        while (running) {
            if (currentAccount == null) {
                System.out.println("WELCOME TO JAVABANK !!!!");
                System.out.println();
                System.out.println("ENTER YOUR ACCOUNT BRAND: ");
                String accountBrand = scanner.nextLine();
                System.out.println("ENTER YOUR ACCOUNT NAME: ");
                String accountName = scanner.nextLine();
                Account account = Account.get(accountBrand, accountName);
                if (account != null) {
                    currentAccount = account;
                    System.out.println(String.format(Locale.US, "WELCOME TO YOUR ACCOUNT, %s", account.getDisplayName()));
                }
            } else {
                System.out.println();
                System.out.println("ENTER YOUR OPERATION - ENTER ? TO LIST ALL");
                operation = scanner.nextLine();
                switch (operation) {
                    case "1": // GET BALLANCE
                        {
                            System.out.println(String.format(Locale.US, "CURRENT BALLANCE: %.2f", currentAccount.getBallance()));
                        }
                        break;
                    case "2": // GET STATEMENT
                        {
                            System.out.println();
                            System.out.println("ACCOUNT STATEMENT");
                            System.out.println();
                            for (AccountStatementEntry entry : currentAccount.getStatement().getEntries()) {
                                System.out.println(String.format("%s\t%s\t%.2f", entry.getDate().toString(), entry.getDescription(), entry.getValue()));
                            }
                        }
                        break;
                    case "3": // WITHDRAW
                        {
                            System.out.println("ENTER AMMOUT:");
                            double ammount = scanner.nextDouble();
                            scanner.nextLine();
                            currentAccount.operationWithdraw(ammount);
                        }
                        break;
                    case "4": // DEPOSIT
                        {
                            System.out.println("ENTER AMMOUT:");
                            double ammount = scanner.nextDouble();
                            scanner.nextLine();
                            currentAccount.operationDeposit(ammount);
                        }
                        break;
                    case "5": // TRASFER TEF
                        {
                            System.out.println();
                            System.out.println("ENTER DESTINATION ACCOUNT BRAND: ");
                            String destinationAccountBrand = scanner.nextLine();
                            System.out.println("ENTER DESTINATION ACCOUNT NAME: ");
                            String destinationAccountName = scanner.nextLine();
                            Account destinationAccount = Account.get(destinationAccountBrand, destinationAccountName);
                            if (destinationAccount != null) {
                                System.out.println("ENTER AMMOUNT: ");
                                double ammount = scanner.nextDouble();
                                scanner.nextLine();
                                currentAccount.operationTransferTEF(destinationAccount, ammount);
                            } else {
                                System.out.println("DESTINATION ACCOUNT NOT FOUND!");
                            }
                        }
                        break;
                    case "6": // TRANSFER PIX
                        break;
                    case "7": // LOGOUT
                        {
                            currentAccount = null;
                        }
                        break;
                    case "8": // EXIT
                        {
                            running = false;
                        }
                        break;
                    case "?": // LIST OPERATIONS
                        {
                            System.out.println();
                            System.out.println("1 - BALLANCE");
                            System.out.println("2 - STATEMENT");
                            System.out.println("3 - WITHDRAW");
                            System.out.println("4 - DEPOSIT");
                            System.out.println("5 - TRANSFER TEF");
                            System.out.println("6 - TRANSFER PIX");
                            System.out.println("7 - LOGOUT");
                            System.out.println("8 - EXIT");
                        }
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {

        TransactionConsole console = new TransactionConsole();
        console.execute();

    }

}
