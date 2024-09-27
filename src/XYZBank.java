import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Interface representing checker and printer functionalities
interface CheckerPrinter {
    // Method to check a customer's eligibility
    boolean checkEligibility(Customer customer);

    // Method to print customer's details
    void printCustomerDetails(Customer customer);
}

// Abstract class representing a loan
abstract class Loan {
    // Fields representing the attributes of a loan
    protected int recordID;
    protected String loanType;
    protected double interestRate;
    protected double loanTermLeft;

    // Constructor to initialize loan with provided values
    public Loan(int recordID, String loanType, double interestRate, double loanTermLeft) {
        this.recordID = recordID;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.loanTermLeft = loanTermLeft;
    }

    // Abstract method to get the amount left to pay
    public abstract double getAmountLeftToPay();

    // Method to print loan details
    public void printLoanDetails() {
        System.out.println("Record ID: " + recordID);
        System.out.println("Loan Type: " + loanType);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Loan Term Left (in years): " + loanTermLeft);
    }
}

// Class representing an auto loan
class AutoLoan extends Loan {
    private double amountLeftToPay;

    // Constructor to initialize auto loan
    public AutoLoan(int recordID, double interestRate, double loanTermLeft, double amountLeftToPay) {
        super(recordID, "Auto", interestRate, loanTermLeft);
        this.amountLeftToPay = amountLeftToPay;
    }

    // Method to get the amount left to pay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }
}

// Class representing a builder loan
class BuilderLoan extends Loan {
    private double overpayment; // Overpayment option
    private double amountLeftToPay;

    // Constructor to initialize builder loan
    public BuilderLoan(int recordID, double interestRate, double loanTermLeft, double overpayment, double amountLeftToPay) {
        super(recordID, "Builder", interestRate, loanTermLeft);
        this.overpayment = overpayment;
        this.amountLeftToPay = amountLeftToPay;
    }

    // Method to get the amount left to pay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }

    // Method to print loan details along with overpayment option
    public void printLoanDetails() {
        super.printLoanDetails();
        System.out.println("Overpayment Option: " + overpayment + "%");
    }
}

// Class representing a mortgage loan
class MortgageLoan extends Loan {
    private double overpayment; // Overpayment option
    private double amountLeftToPay;

    // Constructor to initialize mortgage loan
    public MortgageLoan(int recordID, double interestRate, double loanTermLeft, double overpayment, double amountLeftToPay) {
        super(recordID, "Mortgage", interestRate, loanTermLeft);
        this.overpayment = overpayment;
        this.amountLeftToPay = amountLeftToPay;
    }

    // Method to get the amount left to pay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }

    // Method to print loan details along with overpayment option
    public void printLoanDetails() {
        super.printLoanDetails();
        System.out.println("Overpayment Option: " + overpayment + "%");
    }
}

// Class representing a personal loan
class PersonalLoan extends Loan {
    private double amountLeftToPay;

    // Constructor to initialize personal loan
    public PersonalLoan(int recordID, double interestRate, double loanTermLeft, double amountLeftToPay) {
        super(recordID, "Personal", interestRate, loanTermLeft);
        this.amountLeftToPay = amountLeftToPay;
    }

    // Method to get the amount left to pay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }
}

// Class representing other types of loans
class OtherLoan extends Loan {
    private double amountLeftToPay;

    // Constructor to initialize other loan
    public OtherLoan(int recordID, double interestRate, double loanTermLeft, double amountLeftToPay) {
        super(recordID, "Other", interestRate, loanTermLeft);
        this.amountLeftToPay = amountLeftToPay;
    }

    // Method to get the amount left to pay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }
}

// Class representing a customer
class Customer implements CheckerPrinter {
    private String customerID;
    private int customerIncome;
    private boolean eligibilityStatus;
    private ArrayList<Loan> creditRecords;

    // Default constructor
    public Customer() {
        this.customerID = "";
        this.customerIncome = 0;
        this.eligibilityStatus = true;
        this.creditRecords = new ArrayList<>();
    }

    // Parameterized constructor with default parameters
    public Customer(String customerID, int customerIncome) {
        this.customerID = customerID;
        this.customerIncome = customerIncome;
        this.eligibilityStatus = true;
        this.creditRecords = new ArrayList<>();
    }

    // Method to fill in necessary information
    public void fillInformation() {
        // Code to fill in customer information
    }

    // Method to read necessary information
    public void readInformation() {
        // Code to read customer information
    }

    // Method to print customer details in the specified format
    public void printCustomerDetails() {
        System.out.println("================================");
        System.out.println("CustomerID\t" + customerID);
        System.out.println("Eligible to arrange new loans -\t" + (checkEligibility(this) ? "YES" : "NO"));
        System.out.println("RecordID\tLoanType\tIntRate\tAmountLeft\tTimeLeft");

        for (Loan creditRecord : creditRecords) {
            System.out.printf("%06d\t\t%s\t\t%.2f\t\t%.2f\t\t%.2f%n", creditRecord.recordID, creditRecord.loanType,
                    creditRecord.interestRate, creditRecord.getAmountLeftToPay(), creditRecord.loanTermLeft);
        }
    }

    // Implementing methods from CheckerPrinter interface
    @Override
    public boolean checkEligibility(Customer customer) {
        double totalAmount = 0;
        for (Loan creditRecord : customer.creditRecords) {
            totalAmount += creditRecord.getAmountLeftToPay();
        }
        return totalAmount <= 4 * customer.customerIncome;
    }

    @Override
    public void printCustomerDetails(Customer customer) {
        // Method to print customer details
        customer.printCustomerDetails();
    }

    // Getters and setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getCustomerIncome() {
        return customerIncome;
    }

    public void setCustomerIncome(int customerIncome) {
        this.customerIncome = customerIncome;
    }

    public boolean isEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(boolean eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    public ArrayList<Loan> getCreditRecords() {
        return creditRecords;
    }

    public void setCreditRecords(ArrayList<Loan> creditRecords) {
        this.creditRecords = creditRecords;
    }
}
// Main class representing the XYZ Bank Management System
public class XYZBank {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    //test data
    static {
        // Initialize bank records with sample data
        Customer customer1 = new Customer("AAA001", 50000);
        Customer customer2 = new Customer("BBB002", 75000);

        // Add some loans for customer 1
        customer1.getCreditRecords().add(new AutoLoan(1001, 5.5, 3, 15000));
        customer1.getCreditRecords().add(new MortgageLoan(1002, 4.0, 20, 10, 200000));
        customer1.getCreditRecords().add(new PersonalLoan(1003, 8.0, 2, 5000));

        // Add some loans for customer 2
        customer2.getCreditRecords().add(new BuilderLoan(2001, 6.25, 5, 5, 100000));
        customer2.getCreditRecords().add(new OtherLoan(2002, 7.5, 1, 7000));

        customers.add(customer1);
        customers.add(customer2);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nXYZ Bank Management System");
            System.out.println("1. Register a new customer");
            System.out.println("2. Update information about existing customer");
            System.out.println("3. Register a credit record");
            System.out.println("4. Print information about a particular customer and their loans");
            System.out.println("5. Print information about all customers");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewCustomer();
                    break;
                case 2:
                    updateCustomerInfo();
                    break;
                case 3:
                    registerLoan();
                    break;
                case 4:
                    printCustomerAndLoanInfo();
                    break;
                case 5:
                    printAllCustomers();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close(); // Close the scanner explicitly
    }

    // Method to register a new customer
    private static void registerNewCustomer() {
        System.out.print("Enter Customer ID (in the format AAAXXX): ");
        String customerID = scanner.nextLine();

        // Validate Customer ID format
        if (!isValidCustomerID(customerID)) {
            System.out.println("Invalid Customer ID format. Please use the format AAAXXX.");
            return;
        }

        System.out.print("Enter Annual Income: ");
        int annualIncome = getIntInput();

        Customer customer = new Customer(customerID, annualIncome);
        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    // Method to update information about an existing customer
    // Method to update information about an existing customer
    private static void updateCustomerInfo() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }

        System.out.print("Enter Customer ID: ");
        String customerID = scanner.nextLine();

        Customer customer = findCustomer(customerID);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Update Options:");
        System.out.println("1. Update Annual Income");
        System.out.println("2. Update Eligibility Status");
        System.out.println("3. Add a new Loan Record");
        System.out.println("4. Remove an old Loan Record");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter New Annual Income: ");
                int newIncome = getIntInput();
                customer.setCustomerIncome(newIncome);
                System.out.println("Annual Income updated successfully.");
                break;
            case 2:
                System.out.print("Enter New Eligibility Status (true/false): ");
                boolean newStatus = getBooleanInput();
                customer.setEligibilityStatus(newStatus);
                System.out.println("Eligibility Status updated successfully.");
                break;
            case 3:
                registerLoanForCustomer(customer);
                break;
            case 4:
                removeLoanForCustomer(customer);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        // Save changes to the main data
        saveChanges();
    }

    // Method to save changes to the main data
    private static void saveChanges() {
        // Print out updated customer and loan information
        System.out.println("Updated Customer and Loan Information:");
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Eligible to arrange new loans - " + (customer.isEligibilityStatus() ? "YES" : "NO"));
            System.out.println("RecordID | LoanType | IntRate | AmountLeft | TimeLeft");
            System.out.println("------- | -------- | -------- | ---------- | --------");
            for (Loan loan : customer.getCreditRecords()) {
                System.out.printf("%07d | %-8s | %.2f | %.0f | %.0f%n",
                        loan.recordID, loan.loanType, loan.interestRate,
                        loan.getAmountLeftToPay(), loan.loanTermLeft);
            }
            System.out.println(); //an empty line between customers
        }
    }


    // Method to register a loan for a customer
    private static void registerLoanForCustomer(Customer customer) {
        if (!customer.isEligibilityStatus()) {
            System.out.println("Customer is not eligible for a new loan.");
            return;
        }


        System.out.print("Record ID: ");
        int recordID = getIntInput();
        scanner.nextLine();

        // Check if the record already exists
        for (Loan loan : customer.getCreditRecords()) {
            if (loan.recordID == recordID) {
                System.out.println("A loan with the specified Record ID already exists for this customer.");
                return;
            }
        }

        String loanType;
        do {
            System.out.print("Loan Type (Auto/Builder/Mortgage/Personal/Other): ");
            loanType = scanner.nextLine();
            if (!isValidLoanType(loanType)) {
                System.out.println("Invalid Loan Type. Must be: Auto, Builder, Mortgage, Personal, or Other");
            }
        } while (!isValidLoanType(loanType));

        System.out.print("Interest Rate: ");
        double interestRate = getDoubleInput();

        System.out.print("Loan Term Left (in years): ");
        double loanTermLeft = getDoubleInput();

        System.out.print("Amount Left to Pay: ");
        double amountLeftToPay = getDoubleInput();

        Loan loan;
        switch (loanType) {
            case "Auto":
                loan = new AutoLoan(recordID, interestRate, loanTermLeft, amountLeftToPay);
                break;
            case "Builder":
                System.out.print("Overpayment Option (%): ");
                double overpayment = getDoubleInput();
                loan = new BuilderLoan(recordID, interestRate, loanTermLeft, overpayment, amountLeftToPay);
                break;
            case "Mortgage":
                System.out.print("Overpayment Option (%): ");
                double mortgageOverpayment = getDoubleInput();
                loan = new MortgageLoan(recordID, interestRate, loanTermLeft, mortgageOverpayment, amountLeftToPay);
                break;
            case "Personal":
                loan = new PersonalLoan(recordID, interestRate, loanTermLeft, amountLeftToPay);
                break;
            case "Other":
                loan = new OtherLoan(recordID, interestRate, loanTermLeft, amountLeftToPay);
                break;
            default:
                System.out.println("Invalid loan type.");
                return;
        }

        customer.getCreditRecords().add(loan);
        System.out.println("Loan registered successfully.");
    }

    // Method to remove a loan for a customer
    private static void removeLoanForCustomer(Customer customer) {
        if (customer.getCreditRecords().isEmpty()) {
            System.out.println("No loans to remove for this customer.");
            return;
        }

        System.out.print("Enter Record ID of the Loan to Remove: ");
        int recordID = getIntInput();
        scanner.nextLine();

        // Find and remove the loan with the specified record ID
        ArrayList<Loan> creditRecords = customer.getCreditRecords();
        ArrayList<Loan> loansToRemove = new ArrayList<>();
        for (Loan loan : creditRecords) {
            if (loan.recordID == recordID) {
                loansToRemove.add(loan);
            }
        }

        if (!loansToRemove.isEmpty()) {
            creditRecords.removeAll(loansToRemove);
            System.out.println("Loan(s) removed successfully.");
        } else {
            System.out.println("Loan with the specified Record ID not found.");
        }
    }
    // Method to register a loan
    private static void registerLoan() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }

        System.out.print("Enter Customer ID: ");
        String customerID = scanner.nextLine();

        Customer customer = findCustomer(customerID);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        registerLoanForCustomer(customer);
    }

    // Method to print information about a particular customer and their loans
    public static void printAllCustomers() {
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Eligible to arrange new loans - YES");
            System.out.println("RecordID | LoanType | IntRate | AmountLeft | TimeLeft");
            System.out.println("------- | -------- | -------- | ---------- | --------");
            for (Loan loan : customer.getCreditRecords()) {
                System.out.printf("%07d | %-8s | %.2f | %.0f | %.0f%n",
                        loan.recordID, loan.loanType, loan.interestRate,
                        loan.getAmountLeftToPay(), loan.loanTermLeft);
            }
            System.out.println(); //an empty line between customers
        }
    }


    public static void printCustomerAndLoanInfo() {
        System.out.print("Enter Customer ID: ");
        String customerID = scanner.nextLine();

        Customer customer = findCustomer(customerID);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Customer ID: " + customer.getCustomerID());
        System.out.println("Eligible to arrange new loans - YES");
        System.out.println("RecordID | LoanType | IntRate | AmountLeft | TimeLeft");
        System.out.println("------- | -------- | -------- | ---------- | --------");
        for (Loan loan : customer.getCreditRecords()) {
            System.out.printf("%07d | %-8s | %.2f | %.0f | %.0f%n",
                    loan.recordID, loan.loanType, loan.interestRate,
                    loan.getAmountLeftToPay(), loan.loanTermLeft);
        }
    }

    // Method to find a customer by their ID
    private static Customer findCustomer(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    // Method to validate the Customer ID format
    private static boolean isValidCustomerID(String customerID) {
        return customerID.matches("[A-Z]{3}[0-9]{3}");
    }

    // Method to validate the Loan Type
    private static boolean isValidLoanType(String loanType) {
        // Convert the input loan type to lowercase and then check for validity
        String lowercaseLoanType = loanType.toLowerCase();
        return lowercaseLoanType.equals("auto") || lowercaseLoanType.equals("builder") || lowercaseLoanType.equals("mortgage")
                || lowercaseLoanType.equals("personal") || lowercaseLoanType.equals("other");
    }

    // Helper methods to handle different types of user inputs
    private static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }
    // Method to handle double input from the user
    private static double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Method to handle boolean input from the user
    private static boolean getBooleanInput() {
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

}