package atm;

import java.util.Scanner;

public class ATM {
    private User user;
    private Account account;

    public ATM(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        if (userId.equals(user.getUserId()) && userPin.equals(user.getUserPin())) {
            System.out.println("Authentication successful!");
            showMenu(scanner);
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private void showMenu(Scanner scanner) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Account Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful!");
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter the recipient's account ID: ");
                    scanner.nextLine(); // consume newline character
                    String recipientId = scanner.nextLine();
                    System.out.print("Enter the transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    Account recipientAccount = new Account(recipientId, 0.0);
                    account.transfer(recipientAccount, transferAmount);
                    break;
                case 5:
                    account.printTransactionHistory();
                    break;
                case 6:
                    quit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        User user = new User("1234", "5678");
        Account account = new Account("A123456", 1000.0);
        ATM atm = new ATM(user, account);
        atm.start();
    }
}
