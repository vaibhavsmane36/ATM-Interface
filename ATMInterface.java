package atm;
import java.util.*;
/**
 *
 * @author Ananda
 */
public class ATMInterface {
    private String userId, userPin;
    private boolean loggedIn;
    List<String> transactionHistory = new ArrayList<>();
    double accountBalance = 1000.0;
    
    public ATMInterface() {
        this.userId = "302121";
        this.userPin = "7890";
        this.loggedIn = false;
    }
    
    public void start() {
        System.out.println("Welcome to the ATM!");
        authenticateUser();
        if(loggedIn) {
            showMenu();
            performOperations();
        }
        System.out.println("Thank you for using the ATM!");
    }
    
    private void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        String enteredUserId = scanner.nextLine();
        System.out.println("Enter User PIN: ");
        String enteredUserPin = scanner.nextLine();
        
        if(enteredUserId.equals(userId) && enteredUserPin.equals(userPin)) {
            System.out.println("Login Successful!");
            loggedIn = true;
        }
        else {
            System.out.println("Invalid User ID or PIN. Login Failed!");
        }
    }
    
    private void performOperations() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
        System.out.print("\nEnter your choice:");
        choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                displayTransactionHistory();
                break;
            case 2:
                performWithdrawl();
                break;
            case 3:
                performDeposite();
                break;
            case 4:
                performTransfer();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    } while(choice  != 5);
    }
    
    private void showMenu() {
       System.out.println("\nATM Menu: ");
       System.out.println("1. Transactions History: ");
       System.out.println("2. Withdraw: ");
       System.out.println("3. Deposite: ");
       System.out.println("4. Transfer: ");
       System.out.println("5. Quit: ");
    }
    
    private void displayTransactionHistory() {
        System.out.println("Displaying transactions history...");
        
        if(transactionHistory.isEmpty()) {
            System.out.println("No Transaction available.");
        }
        else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private void performWithdrawl() {
        System.out.println("Performing Withdrawl...");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter withdrawl amount: ");
                int amount = scanner.nextInt();
                
                if (amount <= 0) {
                    System.out.println("Invalid amount. Withdrawl failed!");
                } 
                else if (amount > accountBalance) {
                    System.out.println("Insufficient balance. Withdrawl failed!");
                }
                else {
                    accountBalance -=amount;
                    System.out.println("Withdrawl of Rs. "+ amount + " successful.");
                    
                    updateTransactinHistory("Withdrawl: Rs. "+ amount);
                    
                    System.out.println("Update account balance: Rs."+ accountBalance);
                }        
    }
    
    private void updateTransactinHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    private void performDeposite() {
        System.out.println("Performing Deposite...");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter deposite amount: ");
        int amount = scanner.nextInt();
        
        if (amount <= 0) {
                    System.out.println("Invalid amount. deposite failed!");
                }
                else {
                    accountBalance +=amount;
                    System.out.println("Deposite of Rs. "+ amount + " successful.");
                    
                    updateTransactinHistory("Deosite: Rs. "+ amount);
                    
                    System.out.println("Update account balance: Rs."+ accountBalance);
                }
        
    }

    private void performTransfer() {
        System.out.println("Performing Transfer...");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient's account number: ");
        String recipientAccountNumber = scanner.nextLine();
        
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        
        if (amount <=0) {
            System.out.println("Invalid amount. Transfer failed!");
        }
        else if (amount > accountBalance) {
            System.out.println("Insufficient balance. Transfer failed!");
        }
        else {
            if (validateRecipientAccount(recipientAccountNumber)) {
                accountBalance -= amount;
                System.out.println("Transfer of Rs. "+ amount + " to account " + recipientAccountNumber+ " successful!");
                
                updateTransactinHistory("Transfer: Rs. "+ amount + " to account "+recipientAccountNumber);
                
                System.out.println("Update account balance: Rs."+ accountBalance);
            }
        }
    }
    
        private boolean validateRecipientAccount(String recipientAccountNumber) {
            
            List<String> validRecipientAccounts = new ArrayList<>();
            validRecipientAccounts.add("01702210001");
            validRecipientAccounts.add("02602210004");
            validRecipientAccounts.add("05702210008");
            validRecipientAccounts.add("07702210009");
            validRecipientAccounts.add("08702210005");
            
        return validRecipientAccounts.contains(recipientAccountNumber);        
    }

    public static void main(String[] args) {
        ATMInterface atm = new ATMInterface();
        atm.start();
    }
}

