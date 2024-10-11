import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataCrud {
    public void createFile(){
        try(FileWriter writer = new FileWriter("Dados.dat", true)) {
            System.out.println("Arquivo criado");
        } catch (IOException e){
            System.err.println("Erro na abertura do arquivo: "+ e.getMessage());
        }
    }

    public StringBuilder readFile(String path){
        StringBuilder fileContent = new StringBuilder();
        try(Scanner scanner = new Scanner(new File(path))){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e){
            System.err.println("Arquivo não encontrado: "+ e.getMessage());
        }
        return fileContent;
    }

    public void updateFile(StringBuilder fileContent){
        Scanner input = new Scanner(System.in);
        System.out.println("Conteúdo do arquivo:");
        System.out.println(fileContent.toString());
        System.out.println("Digite o novo texto (pressione Enter para manter o texto atual):");
        String updatedText = input.nextLine();
        if(!updatedText.isEmpty()){
            
        }
        input.close();
    }
    public void deleteFile(){

    }
}
