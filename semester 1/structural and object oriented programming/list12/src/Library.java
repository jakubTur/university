import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Library
{
    ArrayList<Book> books;
    public Library()
    {
        books = new ArrayList<>();
    }
    public void add(Book book)
    {
        books.add(book);
    }

    public int getSize()
    {
        return books.size();
    }

    public String remove(String title)
    {
        for (Book book : books) {
            if (book.title.equals(title))
                return title + " was removed from the library";
            break;
        }
        return title + " was not found in the library";
    }
    public String display()
    {
        String list = "";
        for(Book book: books)
        {
            list += (book.title + " by " + book.author+", published by " + book.publication + " in " + book.year + "\n");
        }
        return list;
    }

    public String search(String query)
    {
        String result = "";
        for (Book book : books)
        {
            if(book.year.equals(query) || book.author.equals(query) || book.publication.equals(query) || book.title.equals(query))
            {
                result = book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year + "\n" + result;
            }
            else if(book.year.contains(query) || book.author.contains(query) || book.publication.contains(query) ||
                    book.title.contains(query))
            {
                result += book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year + "\n";
            }
        }
        return result;
    }

    public boolean search(Book book)
    {
        for(Book b : books)
        {
            if(b.title.equals(book.title) && b.year.equals(book.year) && b.publication.equals(book.publication) && b.author.equals(book.author))
            {
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String fileName) throws IOException
    {
        File save = new File(Paths.get("").toAbsolutePath() + "/" + fileName + ".txt");
        FileWriter saver = new FileWriter(save);
        for (Book book : books)
        {
            saver.write(book.title + ";" + book.author + ";" + book.publication + ";" + book.year + "\n");
        }
        saver.close();
    }

    public void loadFromFile(String fileName) throws FileNotFoundException
    {
        File load = new File(Paths.get("").toAbsolutePath() + "/" + fileName + ".txt");
        Scanner reader;
        try
        {
            reader = new Scanner(load);
        }
        catch (FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(), "File not found. Please make sure it's in the project folder and that you type the name without the file type.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(ex);
        }
        StringBuilder title = new StringBuilder();
        StringBuilder author= new StringBuilder();
        StringBuilder publication= new StringBuilder();
        StringBuilder year= new StringBuilder();
        while (reader.hasNextLine())
        {
            int startingIndex = 0;
            String data = reader.nextLine();
            String[] array = data.split("");
            for(int i = startingIndex; !array[i].equals(";"); i++)
            {
                title.append(array[i]);
            }
            startingIndex += title.length() + 1;
            for(int i = startingIndex; !array[i].equals(";"); i++)
            {
                author.append(array[i]);
            }
            startingIndex += author.length() + 1;
            for(int i = startingIndex; !array[i].equals(";"); i++)
            {
                publication.append(array[i]);
            }
            startingIndex += publication.length() + 1;
            for(int i = startingIndex; i<data.length(); i++)
            {
                year.append(array[i]);
            }
        }
        books.add(new Book(title.toString(), author.toString(), publication.toString(), year.toString()));
    }

}