package advanced_java2_deitel.chapter7_security.jce__java_cryptography_extension;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;

// third-party packages

// Java extension package
import javax.swing.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// Displays a frame that allows users to specify a password and a file name.
// Contents written to an Editor Pane can be encrypted and written to a file,
// or encrypted contents can be read from a file and decrypted
public class Main_EncipherDecipher extends JFrame {

    // salt for password-based encryption-decryption algorithm
    private static final byte[] salt = {
            (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a,
            (byte) 0xb2, (byte) 0xcc, (byte) 0xe4, (byte) 0x7f
    };

    private int iterationCount = 100;

    private JTextField passwordTextField;
    private JTextField fileNameTextField;
    private JEditorPane fileContentsEditorPane;

    public Main_EncipherDecipher() {
        // set security provider
        // Security.addProvider(new com.sun.crypto.provider.SunJCE());

        // initialize main frame
        setSize(new Dimension(400, 400));
        setTitle("Encryption and Decryption Example");

        JPanel topPanel = new JPanel();
        {
            // panel where password and file name labels will be placed
            JPanel labelsPanel = new JPanel();
            labelsPanel.setLayout(new GridLayout(2, 1));
            labelsPanel.add(new JLabel(" File Name: "));
            labelsPanel.add(new JLabel(" Password: "));

            passwordTextField = new JPasswordField();
            fileNameTextField = new JTextField();

            // panel where password and file name textfields will be placed
            JPanel textFieldsPanel = new JPanel();
            textFieldsPanel.setLayout(new GridLayout(2, 1));
            textFieldsPanel.add(fileNameTextField);
            textFieldsPanel.add(passwordTextField);

            topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            topPanel.setLayout(new BorderLayout());
            topPanel.add(labelsPanel, BorderLayout.WEST);
            topPanel.add(textFieldsPanel, BorderLayout.CENTER);
        }

        // construct middle panel
        JPanel middlePanel = new JPanel();
        {
            // construct and place title label for contents pane
            JLabel fileContentsLabel = new JLabel();
            fileContentsLabel.setText(" File Contents");

            // initialize and place editor pane within scroll panel
            fileContentsEditorPane = new JEditorPane();

            middlePanel.setLayout(new BorderLayout());
            middlePanel.add(fileContentsLabel, BorderLayout.NORTH);
            middlePanel.add(new JScrollPane(fileContentsEditorPane), BorderLayout.CENTER);
        }

        // construct bottom panel
        JPanel bottomPanel = new JPanel();
        {
            // create encrypt button
            JButton encryptButton = new JButton("Encrypt and Write to File");
            encryptButton.addActionListener(event -> encryptAndWriteToFile());
            // create decrypt button
            JButton decryptButton = new JButton("Read from File and Decrypt");
            decryptButton.addActionListener(event -> readFromFileAndDecrypt());

            bottomPanel.add(encryptButton);
            bottomPanel.add(decryptButton);
        }

        // initialize main frame window
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(middlePanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    // obtain contents from editor pane and encrypt
    private void encryptAndWriteToFile() {
        String originalText = fileContentsEditorPane.getText();
        String password = passwordTextField.getText();
        String fileName = fileNameTextField.getText();

        // create secret key and get cipher instance
        Cipher cipher = cipher(Cipher.ENCRYPT_MODE, password);

        byte[] outputArray = originalText.getBytes(StandardCharsets.UTF_8);

        File file = new File(fileName);
        // write contents to file and close
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             CipherOutputStream out = new CipherOutputStream(fileOutputStream, cipher)) {
            out.write(outputArray);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        String encryptedText = null;
        try {
            encryptedText = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        // update Editor Pane contents
        fileContentsEditorPane.setText(encryptedText);
    }

    // obtain contents from file and decrypt
    private void readFromFileAndDecrypt() {
        String password = passwordTextField.getText();
        String fileName = fileNameTextField.getText();

        // create secret key
        Cipher cipher = cipher(Cipher.DECRYPT_MODE, password);

        // read and decrypt contents from file
        File file = new File(fileName);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             CipherInputStream in = new CipherInputStream(fileInputStream, cipher)) {
            for (int i = in.read(); i != -1; i = in.read()) {
                buf.write(i);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        try {
            fileContentsEditorPane.setText(buf.toString(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher cipher(int opmode, String password) {
        // create secret key
        Cipher cipher = null;
        try {
            // create password based encryption key object
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

            // obtain instance for secret key factory
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

            // generate secret key for encryption
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // specifies parameters used with password based encryption
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);

            // obtain cipher instance reference.
            cipher = Cipher.getInstance("PBEWithMD5AndDES");

            cipher.init(opmode, secretKey, parameterSpec);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return cipher;
    }

    public static void main(String[] args) {
        System.out.println(new String(salt, StandardCharsets.UTF_8));
        System.out.println(new String(salt, StandardCharsets.ISO_8859_1));
        System.out.println(new String(salt, StandardCharsets.US_ASCII));
        System.out.println(new String(salt, StandardCharsets.UTF_16));
        System.out.println(new String(salt, StandardCharsets.UTF_16BE));
        System.out.println(new String(salt, StandardCharsets.UTF_16LE));
        System.out.println("");

        for (Object provider : Security.getProviders()) {
            System.out.println(provider);
        }

        Main_EncipherDecipher crypto = new Main_EncipherDecipher();
        crypto.validate();
        crypto.setVisible(true);
    }

}