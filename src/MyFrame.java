import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private JTextField amountTextField;
    private JLabel amountLabel;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JButton convertButton;
    private JLabel resultLabel;
    private JLabel convertedAmount;


    MyFrame() {

        ImageIcon img = new ImageIcon("logo.png");
        this.setIconImage(img.getImage());

        this.setTitle("Currency Convertor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,150);
        this.setLayout(new FlowLayout());

        amountLabel = new JLabel("Enter Amount: ");
        this.add(amountLabel);

        amountTextField = new JTextField(10);
        this.add(amountTextField);

        fromLabel = new JLabel("From:");
        this.add(fromLabel);

        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "EGP", "KRW"});
        this.add(fromCurrency);

        toLabel = new JLabel("To:");
        this.add(toLabel);

        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "EGP", "KRW"});
        this.add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.setFocusable(false);
        this.add(convertButton);

        convertedAmount = new JLabel("Converted Amount:");
        this.add(convertedAmount);

        resultLabel = new JLabel("");
        this.add(resultLabel);

        convertButton.addActionListener(_ -> performConversion());

        this.getContentPane().setBackground(Color.BLACK);

        amountLabel.setForeground(Color.WHITE);
        fromLabel.setForeground(Color.WHITE);
        toLabel.setForeground(Color.WHITE);
        resultLabel.setForeground(Color.WHITE);
        convertedAmount.setForeground(Color.WHITE);

        convertButton.setForeground(Color.WHITE);
        convertButton.setBackground(Color.BLACK);

        this.setResizable(false);
        this.setVisible(true);
    }

    private void performConversion() {
        String fromCurrencySelected = (String) fromCurrency.getSelectedItem();
        String toCurrencySelected = (String) toCurrency.getSelectedItem();
        String amountText = amountTextField.getText();
        try {
            double amount = Double.parseDouble(amountText);
            double rate = api.getExchangeRate(fromCurrencySelected, toCurrencySelected);
            double convertedAmount = amount * rate;
            resultLabel.setText(String.format("%.2f %s", convertedAmount, toCurrencySelected));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

}
