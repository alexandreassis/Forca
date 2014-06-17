import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String file = scanner.next();
        if (!read(file)) {
            System.out.println("Verifique o nome do arquivo. Ele realmente existe?");
        }
    }

    private static void parser(String line) {
        String[] word;

        if (line.contains("\t")) { // tab
            word = line.split("\t");
        } else { // espaço
            word = line.split(" ");
        }

        // contém a operação (0 ou 1)
        if (word[0].equals("")) {
            return;
        }
        int operation = Integer.parseInt(word[0]);

        String[] input = word[1].split(":", -1);
        
        if (operation == 0) { // registrar
            System.out.println(register(input[0], input[1], input[2]));
        } else { // jogar
            System.out.println(play(input[0], input[1], input[2]));
        }
    }
    
    private static boolean read(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            // número de operações - não precisamos disso!
            reader.readLine();
            
            String line = reader.readLine();
            while (line != null) {
                parser(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            return false;
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    private static String play(java.lang.String name, java.lang.String id, java.lang.String input) {
        forca.Forca_Service service = new forca.Forca_Service();
        forca.Forca port = service.getForcaPort();
        return port.play(name, id, input);
    }

    private static String register(java.lang.String name, java.lang.String id, java.lang.String word) {
        forca.Forca_Service service = new forca.Forca_Service();
        forca.Forca port = service.getForcaPort();
        return port.register(name, id, word);
    }
}