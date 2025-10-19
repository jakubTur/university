import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Main extends JFrame {

public Main()
{
    Library library = new Library();
    JPanel mainPanel = new JPanel();
    JButton add = new JButton("Add a book");
    add.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            JTextField titleField = new JTextField("title", 10);
            JTextField authorField = new JTextField("author", 10);
            JTextField publicationField = new JTextField("publication", 10);
            JTextField yearField = new JTextField("year", 5);
            JButton add = new JButton("add");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String author = authorField.getText();
                    String title = titleField.getText();
                    String publication = publicationField.getText();
                    String year = yearField.getText();
                    if(author.isEmpty() || title.isEmpty() || publication.isEmpty() || year.isEmpty())
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(!year.matches("\\d{4}"))
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Please make sure you entered the correct year - it must be 4 digits long", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        Book book = new Book(title, author, publication, year);
                        if(library.search(book))
                        {
                            JOptionPane.showMessageDialog(new JFrame(), "Book is already in the library", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            library.add(book);
                            JOptionPane.showMessageDialog(new JFrame(), "Book was added", "Book added", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            });
            JPanel panel = new JPanel();
            panel.add(titleField);
            panel.add(authorField);
            panel.add(publicationField);
            panel.add(yearField);
            panel.add(add);
            JOptionPane.showMessageDialog(frame, panel, "Add a book", JOptionPane.PLAIN_MESSAGE);
        }
    });

    JButton search = new JButton("Search");
    search.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame popup = new JFrame("Search by year, author, publication or title");
            JTextField searchBox = new JTextField("", 20);
            JButton search = new JButton("search");
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame entriesFrame = new JFrame("books matching your query");
                    String result = library.search(searchBox.getText());
                    if(!result.isEmpty())
                    {
                        JOptionPane.showMessageDialog(entriesFrame, result, "Books found", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(entriesFrame, "We weren't able to find any books with that property", "Book not found", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            JPanel panel = new JPanel();
            panel.add(searchBox);
            panel.add(search);
            JOptionPane.showMessageDialog(popup, panel, "Find a book", JOptionPane.PLAIN_MESSAGE);
        }
    });

    JButton list = new JButton("List of books");
    list.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String header = ("We currently have "+ library.getSize()+" books in our library:\n");
            String list = library.display();
            JOptionPane.showMessageDialog(null, list, header, JOptionPane.PLAIN_MESSAGE);
        }
    });
    JButton remove = new JButton("Remove a book");
    remove.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame popup = new JFrame();
            JTextField searchBox = new JTextField("what title do you want removed?");
            JButton remove = new JButton("remove");
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String title = searchBox.getText();
                    JOptionPane.showMessageDialog(null, library.remove(title));
                }
            });
            JPanel panel = new JPanel();
            panel.add(searchBox);
            panel.add(remove);
            JOptionPane.showMessageDialog(popup, panel);
        }
    });
    JButton save = new JButton("Save library");
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("What name do you want the file to have?");
            try
            {
                library.saveToFile(name);
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    });
    JButton load = new JButton("Load a library");
    load.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("What's the name of your save?");
            try
            {
                library.loadFromFile(name);
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(new JFrame(), "File not found. Please make sure it's in the project folder and that you typed the name without the file type.", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        }
    });
    mainPanel.add(search);
    mainPanel.add(add);
    mainPanel.add(remove);
    mainPanel.add(list);
    mainPanel.add(save);
    mainPanel.add(load);
    JFrame frame = new JFrame("Library");
    frame.setSize(300,200);
    frame.add(mainPanel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
    public static void main(String[] args)
    {
        new Main();
    }
}