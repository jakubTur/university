import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args)
    {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JFrame frame = new JFrame("Event-Driven App");
        JButton button = new JButton("Click me");
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JTextField text = new JTextField("type sth");
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char letter = e.getKeyChar();
                if (letter == KeyEvent.VK_BACK_SPACE && !label2.getText().isEmpty())
                {
                    label2.setText(label2.getText().substring(0, label2.getText().length() - 1));
                }
                else
                {
                    label2.setText(label2.getText() + letter);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        Font font = new Font("Arial", Font.PLAIN, 50);
        label.setFont(font);
        button.setFont(font);
        text.setFont(font);
        label2.setFont(font);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String labelText = text.getText();
                label.setText(labelText);
                if(labelText.equals("hello")) { label.setText("hello to you too"); }
                if(labelText.equals("how are you?")) { label.setText("good, and you?"); }
                if(labelText.equals("also good")) { label.setText("that's great"); }
            }});
        panel.add(label);
        panel.add(text);
        panel.add(button);
        panel.add(label2);
        frame.getContentPane().add(panel);
        frame.setSize(1200, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}