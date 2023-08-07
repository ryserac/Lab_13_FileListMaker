import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ArrayList<String> list = new ArrayList();
    public static void displayList(){
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
        if (list.size() != 0) {
            for (int i = 0; i <list.size(); i++) {
                System.out.printf("%3d%35s\n", i+1, list.get(i));
            }
        }
        else {
            System.out.println("+++           List is Empty          +++");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
    }
    public static void saveList() {
        String fileName = SafeInput.getNonZeroLenString(in, "Enter a name for this list");
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), fileName + ".txt");
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : list)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void openList() {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                list.clear();
                while (reader.ready()) {
                    rec = reader.readLine();
                    list.add(rec);
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file " + file.getFileName() + " read!");
            } else
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        boolean done = false;
        final String menu = "A - Add, D - Delete, V - View, O - Open, S - Save, C - Clear, Q - Quit";
        boolean response;
        boolean needsToBeSaved = false;
        do {
            displayList();
            String cmd = SafeInput.getRegExString(in, menu, "[AaDdVvQqOoSsCc]");
            cmd = cmd.toUpperCase();
            switch (cmd) {
                case "A":
                    String item = SafeInput.getNonZeroLenString(in, "Enter an item: ");
                    list.add(item);
                    needsToBeSaved = true;
                    break;
                case "D":
                    int deleteItem = SafeInput.getRangedInt(in,"Select the item number to delete:", 1, list.size());
                    deleteItem--;
                    list.remove(deleteItem);
                    needsToBeSaved = true;
                    break;
                case "V":
                    break;
                case "O":
                    if (needsToBeSaved) {
                        response = SafeInput.getYNConfirm(in, "The list was changed, would you like to save?");
                        if (response) {
                            saveList();
                        }
                    }
                    openList();
                    needsToBeSaved = false;
                    break;
                case "S":
                    saveList();
                    needsToBeSaved = false;
                    break;
                case "C":
                    response = SafeInput.getYNConfirm(in,"Are you sure you want to clear the list?");
                    if (response) {
                        list.clear();
                    }
                    needsToBeSaved = true;
                    break;
                case "Q":
                    if (needsToBeSaved) {
                        response = SafeInput.getYNConfirm(in, "The list was changed, would you like to save?");
                        if (response) {
                            saveList();
                        }
                    }
                    response = SafeInput.getYNConfirm(in,"Are you sure you want to quit?");
                    if (response) {
                        System.exit(0);
                    }
                    needsToBeSaved = false;
                    break;
            }
        }
        while (!done);

    }
}