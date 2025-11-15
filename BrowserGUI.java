import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class BrowserGUI {
    private JFrame frame;
    private JTextField addressBar;
    private JButton backButton, forwardButton, visitButton;
    private JLabel pageDisplay;

    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = "https://www.google.com";

    // -----------------------------------------
    // 1. Load SF Pro Text (with fallback)
    // -----------------------------------------
    private Font loadAppleFont(float size) {
        try {
            // macOS hidden internal name for SF Pro
            Font sf = new Font(".SF NS Text", Font.PLAIN, (int) size);
            if (sf.getFamily().contains("SF")) return sf;

            // Fallback: Helvetica if SF not available
            return new Font("Helvetica", Font.PLAIN, (int) size);

        } catch (Exception e) {
            return new Font("Helvetica", Font.PLAIN, (int) size);
        }
    }

    public BrowserGUI() {

        // Main Window
        frame = new JFrame("Safari-Style Mini Browser");
        frame.setSize(450, 240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // -------------------------------------------------------
        // 2. Create SF Pro Text font sizes
        // -------------------------------------------------------
        Font uiFont = loadAppleFont(15);
        Font headerFont = loadAppleFont(18);

        // -------------------------------------------------------
        // 3. Top navigation bar (Apple-style)
        // -------------------------------------------------------
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(240, 240, 240)); // Light Mac gray

        // -------------------------------------------------------
        // 4. Replace arrows with icon images
        //    (These are Unicode icons similar to Safari)
        // -------------------------------------------------------
        backButton = new JButton("⟨");     // LEFT angle icon
        forwardButton = new JButton("⟩");  // RIGHT angle icon

        backButton.setFont(loadAppleFont(22));
        forwardButton.setFont(loadAppleFont(22));

        backButton.setFocusPainted(false);
        forwardButton.setFocusPainted(false);

        // -------------------------------------------------------
        // 5. Address bar (Safari style)
        // -------------------------------------------------------
        addressBar = new JTextField(20);
        addressBar.setFont(uiFont);

        visitButton = new JButton("Go");
        visitButton.setFont(uiFont);

        // Add components to top bar
        topPanel.add(backButton);
        topPanel.add(forwardButton);
        topPanel.add(addressBar);
        topPanel.add(visitButton);

        // -------------------------------------------------------
        // 6. Page display area like browser window text
        // -------------------------------------------------------
        pageDisplay = new JLabel("Current Page: " + currentPage, SwingConstants.CENTER);
        pageDisplay.setFont(headerFont);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(pageDisplay, BorderLayout.CENTER);

        // -------------------------------------------------------
        // 7. Button Actions
        // -------------------------------------------------------
        visitButton.addActionListener(e -> visit());
        backButton.addActionListener(e -> goBack());
        forwardButton.addActionListener(e -> goForward());

        // Press Enter to visit URL
        addressBar.addActionListener(e -> visit());

        frame.setVisible(true);
    }

    // -------------------------------------------------------
    // Visit a new page + auto-generate browser-style URL
    // -------------------------------------------------------
    void visit() {
        String url = addressBar.getText().trim();
        if (url.isEmpty()) return;

        // Make it look like a real browser URL
        url = convertToBrowserURL(url);

        backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear();

        updatePage();
    }

    // -------------------------------------------------------
    // Create real browser-looking URL:
    // e.g., user types: hello world
    // result: https://www.google.com/search?q=hello+world
    // -------------------------------------------------------
    String convertToBrowserURL(String text) {
        // if user already types a full URL, use it
        if (text.startsWith("http://") || text.startsWith("https://"))
            return text;

        // if user types something like yahoo.com
        if (text.contains(".")) {
            return "https://" + text;
        }

        // else treat it like a search request
        text = text.replace(" ", "+");
        return "https://www.google.com/search?q=" + text;
    }

    // -------------------------------------------------------
    // Go Back
    // -------------------------------------------------------
    void goBack() {
        if (backStack.empty()) return;

        forwardStack.push(currentPage);
        currentPage = backStack.pop();

        updatePage();
    }

    // -------------------------------------------------------
    // Go Forward
    // -------------------------------------------------------
    void goForward() {
        if (forwardStack.empty()) return;

        backStack.push(currentPage);
        currentPage = forwardStack.pop();

        updatePage();
    }

    // -------------------------------------------------------
    // Update display + address bar
    // -------------------------------------------------------
    void updatePage() {
        pageDisplay.setText("Current Page: " + currentPage);
        addressBar.setText(currentPage);
    }

    public static void main(String[] args) {
        new BrowserGUI();
    }
}