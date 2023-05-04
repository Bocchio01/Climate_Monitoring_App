import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private Map<String, String> userCredentials = new HashMap<>();

    private static final String CREDENTIALS_FILE = "credentials.txt";

    public LoginWindow() {
        super("Login");

        // set up the window
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add components to the window
        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> onLoginButtonClicked());
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> onRegisterButtonClicked());

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loadUserCredentials();
    }

    private void onLoginButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void onRegisterButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username and password.");
        } else if (userCredentials.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
        } else {
            userCredentials.put(username, password);
            saveUserCredentials();
            JOptionPane.showMessageDialog(this, "User registered successfully.");
        }
    }

    private void saveUserCredentials() {
        try {
            FileWriter writer = new FileWriter(CREDENTIALS_FILE);
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user credentials.");
        }
    }

    private void loadUserCredentials() {
        try {
            File file = new File(CREDENTIALS_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        userCredentials.put(parts[0], parts[1]);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user credentials.");
        }
    }

    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }
}

class MainWindow extends JFrame {

    public MainWindow() {
        super("Main Window");

        // set up the window
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add components to the window
        JLabel label = new JLabel("Welcome to the Main Window");
        add(label);
    }
}
