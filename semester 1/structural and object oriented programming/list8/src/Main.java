import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel name = new JLabel("first name:");
        JTextField nameField = new JTextField();
        name.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lastName = new JLabel("last name:");
        JTextField lastNameField = new JTextField();
        lastName.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel pesel = new JLabel("PESEL:");
        JTextField peselField = new JTextField();
        pesel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel email = new JLabel("e-mail:");
        JTextField emailField = new JTextField();
        email.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel result1 = new JLabel();
        JButton Hello = new JButton("Hello");
        Hello.setSize(100,30);

        JLabel result2 = new JLabel();
        JButton validatePesel = new JButton("validate pesel");
        validatePesel.setSize(100,30);

        JLabel result3 = new JLabel();
        JButton validateEmail = new JButton("validate e-mail");
        validateEmail.setSize(100,30);

        Hello.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = nameField.getText();
                String lastName = lastNameField.getText();
                String message = ("Hello, " + firstName + " " + lastName);
                JOptionPane.showMessageDialog(popup, message);
                }
            });

        validatePesel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pesel = peselField.getText();
                if(validatePesel(pesel))
                {
                    result2.setText("pesel is correct");
                }
                else
                {
                    result2.setText("pesel is not correct");
                }
            }
        });
        validateEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if(ValidateEMail(email))
                {
                    result3.setText("e-mail is correct");
                }
                else
                {
                    result3.setText("e-mail is not correct");
                }
            }
        });

        panel.add(name);
        panel.add(nameField);
        panel.add(lastName);
        panel.add(lastNameField);
        panel.add(Hello);
        panel.add(result1);
        panel.add(pesel);
        panel.add(peselField);
        panel.add(validatePesel);
        panel.add(result2);
        panel.add(email);
        panel.add(emailField);
        panel.add(validateEmail);
        panel.add(result3);
        frame.getContentPane().add(panel);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
    public static Boolean validatePesel(String pesel) //validity according to - https://en.wikipedia.org/wiki/PESEL
    {
        if (pesel.length() != 11)
        {
            return false;
        }
        String[] arr = pesel.split("");
        int[] array = new int[11];
        for(int i=0; i < 11; i++)
        {
            array[i] = Integer.valueOf(arr[i]);
        }
        int month = array[2] * 10 + array[3]; //this condition is wrong for newer pesels :/
        int day = array[4] * 10 + array[5];
        if(month > 12 || day > 31)
        {
            return false;
        }
        int sum = array[0] + array[4] + array[8] + 3 * (array[1] + array[5] + array[9]) + 7 * (array[2] + array[6]) + 9 * (array[3] + array[7]);
        int modulo = sum % 10;
        return 10 - modulo == array[10];
    }
    public static boolean ValidateEMail(String email)
    {
        String[] array = email.split("");
        boolean atNotPassed = true;
        boolean dotNotPassed = true;
        if((Objects.equals(array[0], "_") || Objects.equals(array[0], ".") || Objects.equals(array[0], "-") || //checking first and last characters
                array[0].equals("@")) || (array[array.length - 1].equals("_") || array[array.length - 1].equals(".") ||
                array[array.length - 1].equals("-") || array[array.length - 1].equals("@")))
        {
            return false;
        }
        for(int i=1; i < array.length - 1; i++)
        {
            if((array[i].equals("_") || array[i].equals(".") || array[i].equals("-") || array[i].equals("@")) &&  //no consecutive special characters
                    (array[i-1].equals("_") || array[i-1].equals(".") || array[i-1].equals("-") || array[i-1].equals("@")))
            {
                return false;
            }
            if(array[i].equals("@"))
            {
                atNotPassed = false;
            }
            if(!atNotPassed)
            {
                if(array[i].equals("_"))
                {
                    return false;
                }
                if(array[i].equals("."))
                {
                    dotNotPassed = false;
                    if((array.length - 1 - i) < 2)
                    {
                        return false;
                    }
                }
            }
        }
        if(atNotPassed||dotNotPassed) { return false; }
        return true;
    }

}