import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame("ShootingTargetApp");
        frame.setSize(770, 770);
        String path = Paths.get("").toAbsolutePath() + "/target.png";
        BufferedImage bufferedImage = ImageIO.read(new File(path));
        JPanel imagePanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (bufferedImage != null)
                {
                    g.drawImage(bufferedImage, 0, 0, this);
                }
            }
        };
        imagePanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                JOptionPane.showMessageDialog(frame, "Mouse Clicked at Coordinates: (" + x + ", " + y + ")");
            }
        });
        frame.add(imagePanel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
