import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataCrud {
    public void createFile(Clients clients, Pets pets){
        try(FileOutputStream fileOut = new FileOutputStream("Dados.dat", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(clients);
            objectOut.writeObject(pets);
            System.out.println("Arquivo criado");
        } catch (IOException e){
            System.err.println("Erro na abertura do arquivo: "+ e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Clients> readFile(String path){
        ArrayList<Clients> clientsList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream("Dados.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            clientsList = (ArrayList<Clients>) objectIn.readObject();
            System.out.println("Client list deserialized.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: "+ e.getMessage());
        }
        return clientsList;
    }

    public void updateFile(String clientName, Clients updatedClient, ArrayList<Clients> clientsList){
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getName().equals(clientName)) {
                clientsList.set(i, updatedClient);  // Update with new client info
                System.out.println("Client updated: " + updatedClient.getName());
                break;
            }
        }
    }
    public void deleteFile(){

    }
}
