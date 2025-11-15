import java.util.Scanner;
import java.util.Stack;

public class BrowserHistoryCommand {
    private Stack<String> backStack;
    private Stack<String> forwardStack;
    private String currentPage;

    public BrowserHistoryCommand(String homepage) {
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

    public boolean back(int steps) {
        // edge case
        if(steps <= 0 || steps > backStack.size()) {
            System.out.println("Invalid action: cannot go back " + steps + " steps.");
            return false;
        }

        for(int i=0; i<steps; i++) {
            forwardStack.push(currentPage);
            currentPage = backStack.pop();
        }
        System.out.println("Went back to: " + currentPage);
        return true;
    }

    public boolean forward(int steps) {
        // edge case
        if(steps <= 0 || steps > forwardStack.size()) {
            System.out.println("Invalid action: cannot go forward " + steps + " steps.");
            return false;
        }

        for(int i=0; i<steps; i++) {
            backStack.push(currentPage);
            currentPage = forwardStack.pop();
        }
        System.out.println("Wen forward to: " + currentPage);
        return true;
    }
    public String getCurrentPage() {
        return currentPage;
    }
    
    public boolean showHistory() {
        System.out.println("\n=== FULL HISTORY VIEW ===");

        // Print backward stack (reverse order)
        for (int i= backStack.size()-1; i >= 0; i--) {
            int index = -(backStack.size()-i);
            System.out.println(index + ". " + backStack.get(i));  
        }

        // Current Page
        System.out.println("0. " + currentPage);

        // Print forward Stack
        for (int i= 0; i< forwardStack.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + forwardStack.get(i));
        }

        System.out.println("==========================\n");

        return true;
    }

    public boolean go(int steps) {
        if(steps == 0) {
            System.out.println("Reloaded: " + currentPage);
            return true;
        }

        if(steps < 0) { // backward
            int backsteps = -steps;
            return back(backsteps);
        } else {
            return forward(steps);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BrowserHistoryCommand history = new BrowserHistoryCommand("google.com");

        System.out.println("\n=== Browser ===");
        System.out.println("Type Commands Like: visit(url), back(), forward(), showHistory(), go(), exit");

        while (true) { 
            System.out.println("\n-------------------------------");
            System.out.println("Current Page: " + history.getCurrentPage());
            System.out.print("Action: ");

            String action = sc.nextLine().trim().toLowerCase();

            // visit(url)
            if(action.startsWith("visit(") && action.endsWith(")")) {
                String url = action.substring(6, action.length()-1).trim();
                if(!url.isEmpty()) {
                    history.visit(url);
                } else {
                    System.out.println("URL cannot be empty.");
                }
            } else if(action.equals("back()") || (action.startsWith("back(") && action.endsWith(")"))) { // back() or back(n)
                String str = action.substring(5, action.length()-1).trim();
                int steps = str.isEmpty() ? 1 : Integer.parseInt(str);

                history.back(steps);

            } else if(action.equals("forward()") || (action.startsWith("forward(") && action.endsWith(")"))) { // forward() or forward(n)
                String str = action.substring(8, action.length()-1).trim();
                int steps = str.isEmpty() ? 1 : Integer.parseInt(str);

                history.forward(steps);

            } else if (action.equals("showhistory()")) { // showHistory()

                history.showHistory();

            } else if (action.startsWith("go()") || (action.startsWith("go(") && action.endsWith(")"))) { // go() or go(n)
                String str = action.substring(3, action.length()-1).trim();
                int steps = str.isEmpty() ? 0 : Integer.parseInt(str);

                history.go(steps);

            } else if(action.equals("exit")) { // exit

                System.out.println("Exiting browser...");
                break;

            } else {
                System.out.println("Invalid action! Try again.");
            }
        }
    }
}