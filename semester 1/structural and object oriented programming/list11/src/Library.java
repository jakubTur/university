import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Library {
    static ArrayList<Book> library = new ArrayList<>();
    static void add()
    {
        Scanner data = new Scanner(System.in);
        System.out.println("What's the title of the book?");
        String title = data.nextLine();
        System.out.println("Who's the author?");
        String author = data.nextLine();
        System.out.println("What's the publication?");
        String publication = data.nextLine();
        System.out.println("And when was it published?");
        String year = data.nextLine();
        Book book = new Book(title, author, publication, year);
        library.add(book);
        System.out.println(title+" was added to the library");
    }

    static void remove()
    {
        boolean removed = false;
        Scanner data = new Scanner(System.in);
        System.out.println("Name the book you'd like to remove");
        String title = data.nextLine();
        for(Book book : library)
        {
            if(book.title.equals(title))
            {
                library.remove(book);
                removed = true;
                System.out.println(title+" was removed from the library");
                break;
            }
        }
        if(!removed) { System.out.println("That book was not a part of the library to begin with"); }
    }
    static void display()
    {
        System.out.println("We currently have "+library.size()+" in our library:");
        for(Book book: library)
        {
            System.out.println(book.title+" by "+book.author+", published by "+book.publication+" in "+book.year);
        }
    }
    static void search()
    {
        while(true)
        {
            boolean found = false;
            System.out.println("Would you like to search by year, author, publication or title?");
            Scanner component = new Scanner(System.in);
            Scanner value = new Scanner(System.in);
            String thing = component.nextLine();
            if (!"year".equals(thing) && !"title".equals(thing) && !"publication".equals(thing) && !"author".equals(thing)) {
                System.out.println("Invalid input");
                break;
            }
            System.out.println("And what is it?");
            String looking = value.nextLine();
            for (Book book : library)
            {
                if ("year".equals(thing))
                {
                    if (book.year.equals(looking))
                    {
                        System.out.println(book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year);
                        found = true;
                    }
                }
                if ("author".equals(thing))
                {
                    if (book.author.equals(looking))
                    {
                        System.out.println(book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year);
                        found = true;
                    }
                }
                if ("publication".equals(thing))
                {
                    if (book.publication.equals(looking))
                    {
                        System.out.println(book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year);
                        found = true;
                    }
                }
                if ("title".equals(thing))
                {
                    if (book.title.equals(looking))
                    {
                        System.out.println(book.title + " by " + book.author + ", published by " + book.publication + " in " + book.year);
                        found = true;
                    }
                }
            }
            if (!found)
            {
                System.out.println("We weren't able to find any books with that property");
            }
            break;
        }
    }
    static void saveToFile(String fileName) throws IOException
    {
        File save = new File("C:/Users/leesz/IdeaProjects/list11/"+fileName+".txt");
        FileWriter saver = new FileWriter(save);
        for (Book book : library)
        {
            saver.write(book.title + ";" + book.author + ";" + book.publication + ";" + book.year + "\n");
        }
        saver.close();
    }
    static void loadFromFile(String fileName) throws FileNotFoundException
    {
        File load = new File("C:/Users/leesz/IdeaProjects/list11/"+fileName+".txt");
        Scanner reader = new Scanner(load);
        String title = "";
        String author="";
        String publication="";
        String year="";
        while (reader.hasNextLine())
        {
            String data = reader.nextLine();
            String[] array = data.split("");
            for(int i =0; !array[i].equals(";");i++)
            {
                title=title+array[i];
            }
            for(int i = title.length()+1; !array[i].equals(";");i++)
            {
                author=author+array[i];
            }
            for(int i = author.length()+title.length()+2; !array[i].equals(";");i++)
            {
                publication=publication+array[i];
            }
            for(int i = publication.length()+author.length()+title.length()+3; i<data.length();i++)
            {
                year=year+array[i];
            }
        }
        Book book = new Book(title, author, publication, year);
        library.add(book);
    }
    public static void main(String[] args) throws IOException
    {
        System.out.println("Welcome to the library.");
            while (true)
            {
                System.out.println("What would you like to do? Press:\n1 to add a book to the library" +
                        "\n2 to remove a book from the library\n3 to display the list of books in the library" +
                        "\n4 to search the library for a specific book" +
                        "\n5 to save the current library\n6 to load a library" +
                        "\n7 to exit the library\n");
                Scanner scanner = new Scanner(System.in);
                String action = scanner.nextLine();
                if ("1".equals(action))
                {
                    add();
                }
                else if ("2".equals(action))
                {
                    remove();
                }
                else if ("3".equals(action))
                {
                    display();
                }
                else if ("4".equals(action))
                {
                    search();
                }
                else if ("5".equals(action))
                {
                    System.out.println("What would you like to name your file?");
                    String name = scanner.nextLine();
                    saveToFile(name);
                }
                else if ("6".equals(action))
                {
                    System.out.println("What's the name of the file you'd like to load?");
                    String name=scanner.nextLine();
                    loadFromFile(name);
                }
                else if("7".equals(action)) { break; }
                else
                {
                    System.out.println("Input not recognized");
                }
            }

        }


}
