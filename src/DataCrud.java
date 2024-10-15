import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataCrud {
    public static void createFile(ArrayList<Clients> clientsList, ArrayList<Pets> petsList, boolean appendMode) {
        try (FileOutputStream fileOut = new FileOutputStream("Dados.dat", appendMode);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(clientsList);  // Serialize the list
            System.out.println("Clients list serialized.");
            objectOut.writeObject(petsList);
            System.out.println("Pets list serialized.");
            System.out.println("Arquivo criado");
        } catch (IOException e) {
            System.err.println("Erro na criação do arquivo: " + e.getMessage());
        }
    }

    public static void createServiceFile(ArrayList<Services> servicesList) {
        try (FileOutputStream fileOut = new FileOutputStream("Services.dat", false);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(servicesList);
            System.out.println("Service list serialized.");
            System.out.println("Arquivo de serviços criado");
        } catch (IOException e) {
            System.err.println("Erro na criação do arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Clients> readClients(String path) {
        ArrayList<Clients> clientsList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            ArrayList<Object> objects = (ArrayList<Object>) objectIn.readObject();

            for (Object obj : objects) {
                if (obj instanceof Clients) {
                    clientsList = (ArrayList<Clients>) objectIn.readObject();
                }
            }
            System.out.println("Client list deserialized.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return clientsList;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Pets> readPets(String path) {
        ArrayList<Pets> petsList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            ArrayList<Object> objects = (ArrayList<Object>) objectIn.readObject();

            for (Object obj : objects) {
                if (obj instanceof Pets) {
                    petsList = (ArrayList<Pets>) objectIn.readObject();
                }
            }
            System.out.println("Pets list deserialized.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return petsList;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Services> readServices(String path) {
        ArrayList<Services> servicesList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            servicesList = (ArrayList<Services>) objectIn.readObject();

            System.out.println("Services list deserialized.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return servicesList;
    }

    public static void deleteClient(String clientName, ArrayList<Clients> clientsList) {
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getName().equals(clientName)) {
                clientsList.remove(i);
                System.out.println("Client deleted");
                break;
            }
        }
    }

    public void deletePet(Clients client, Pets pet, ArrayList<Pets> petsList) {
        ArrayList<Pets> clientPets = client.getPets();

        clientPets.remove(pet);
        petsList.remove(pet);

    }

    public void deleteAllPets(Clients client, ArrayList<Pets> petsList) {
        ArrayList<Pets> clientPets = client.getPets();

        for (int i = petsList.size() - 1; i >= 0; i--) {
            Pets pet = petsList.get(i);
            if (clientPets.contains(pet)) {
                clientPets.remove(pet);
                petsList.remove(i);
            }
        }
    }
}
