import java.util.Scanner;
import java.util.Stack;

public class BrowserHistory{
    private Stack<String> backStack;
    private Stack<String> forwardStack;
    private String currentPage;

    public BrowserHistory(String homepage) {
        backStack = new Stack<>();
        forwardStack = new Stack<>();
        currentPage = homepage;
    }

    public void visit(String url) {
        backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear(); // clear forward history
        System.out.println("Visited: " + currentPage);
    }

    public void back() {
        // edge case
        if(backStack.peek().equalsIgnoreCase("homepage")) {
            System.out.println("No, previous pages.");
            return;
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        System.out.println("Went back to: " + currentPage);
    }

    public void forward() {
        // edge case
        if(forwardStack.empty()) {
            System.out.println("No, forward pages.");
            return;
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        System.out.println("Wen forward to: " + currentPage);
    }
    public String getCurrentPage() {
        return currentPage;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BrowserHistory history = new BrowserHistory("google.com");

        while (true) { 
            System.out.println("\n=== Browser ===");
            System.out.println("Current Page: " + history.getCurrentPage());
            System.out.println("1. Visit New Page");
            System.out.println("2. Back");
            System.out.println("3. Forward");
            System.out.println("4. Exit");
            System.out.print("5. Choose Option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch(choice) {
                case 1: {
                    System.out.print("Enter url: ");
                    String url = sc.nextLine();
                    history.visit(url);
                    break;
                }
                case 2: {
                    history.back();
                    break;
                }
                case 3: {
                    history.forward();
                    break;
                }
                case 4: {
                    System.out.println("Exiting Browser...");
                    return;
                }
                default: {
                    System.out.println("Invalid option");
                }
            }
        }
    }
}