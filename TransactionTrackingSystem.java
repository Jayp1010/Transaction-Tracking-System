import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionTrackingSystem {
    private List<Transaction> transactions;

    public TransactionTrackingSystem() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void generateReport() {
        System.out.println("Transaction Report:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("Total Income: " + getTotalIncome());
        System.out.println("Total Expenses: " + getTotalExpenses());
        System.out.println("Net Balance: " + (getTotalIncome() - getTotalExpenses()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionTrackingSystem system = new TransactionTrackingSystem();

        while (true) {
            System.out.println("1. Add Transaction");
            System.out.println("2. Generate Report");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("Enter type (INCOME/EXPENSE): ");
                String typeStr = scanner.nextLine();
                TransactionType type = TransactionType.valueOf(typeStr.toUpperCase());

                System.out.println("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                System.out.println("Enter description: ");
                String description = scanner.nextLine();

                Transaction transaction = new Transaction(type, amount, description);
                system.addTransaction(transaction);

                System.out.println("Transaction added!");
            } else if (choice == 2) {
                system.generateReport();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
