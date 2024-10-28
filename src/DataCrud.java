import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataCrud {
    public static void createFile(ArrayList<Clients> clientsList, boolean appendMode) {
        try (FileOutputStream fileOut = new FileOutputStream("Dados.dat", appendMode);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(clientsList);
            System.out.println("Arquivo criado");
        } catch (IOException e) {
            System.err.println("Erro na criação do arquivo: " + e.getMessage());
        }
    }

    public static void createServiceFile(ArrayList<Services> servicesList) {
        try (FileOutputStream fileOut = new FileOutputStream("Services.dat", false);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(servicesList);
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
                    clientsList.add((Clients) obj);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return clientsList;
    }

    public static ArrayList<Pets> readPets(ArrayList<Clients> clientsList) {
        ArrayList<Pets> petsList = new ArrayList<>();

        for (Clients clients : clientsList) {
            petsList.addAll(clients.getPets());
        }
        System.out.println(petsList);
        return petsList;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Services> readServices(String path) {
        ArrayList<Services> servicesList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            servicesList = (ArrayList<Services>) objectIn.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return servicesList;
    }

    public static void deleteClient(int clientIndex, ArrayList<Clients> clientsList) {
        clientsList.remove(clientIndex);
        System.out.println("Client deleted");
        return;
    }

    public static void deletePet(int clientIndex, int petIndex, ArrayList<Pets> petsList, ArrayList<Clients> clientsList) {
        try {
            Clients client = clientsList.get(clientIndex);
            ArrayList<Pets> clientPets = client.getPets();
            String petName = clientPets.get(petIndex).getName();
            for (int i = 0; i < petsList.size(); i++){
                if (petsList.get(i).getName().equals(petName)){
                    clientPets.remove(i);
                    petsList.remove(petIndex);
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("O usuário não possui pets para serem deletados");
        }
        return;
    }

    public static void deleteAllPets(int clientIndex, ArrayList<Pets> petsList, ArrayList<Clients> clientsList) {
        try {
            Clients client = clientsList.get(clientIndex);
            ArrayList<Pets> clientPets = client.getPets();

            for (int i = 0; i < petsList.size(); i++) {
                Pets pet = petsList.get(i);
                if (clientPets.contains(pet)) {
                    petsList.remove(i);
                }
                clientPets.clear();
                break;
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("O usuário não possui pets para serem deletados");
        }
        return;
    }

    public static void launchService(ArrayList<Services> servicesList, ArrayList<Clients> clientsList, int clientIndex, int serviceIndex){
        Services service = servicesList.get(serviceIndex);
        Clients client = clientsList.get(clientIndex);
        client.setBalance(client.getBalance() - service.getPrice());
        System.out.println("O valor de R$" + service.getPrice() + " foi debitado da conta do cliente " + client.getName());
        return;
    }

    public static void launchPayment(ArrayList<Clients> clientsList, int clientIndex, Scanner input){
        Clients client = clientsList.get(clientIndex);
        System.out.println("Insira o valor do pagamento: ");
        double payment = input.nextDouble();
        client.setBalance(client.getBalance() + payment);
        System.out.println("O valor de R$" + payment + " foi debitado da conta do cliente " + client.getName());
        return;
    }

    public static void customerBalances(ArrayList<Clients> clientsList){
        System.out.println("══════════════ Saldo dos Clientes  ══════════════");
        for (int i = 0; i < clientsList.size(); i++){
            Clients client = clientsList.get(i);
            System.out.println("Cliente: " + client.getName() + "\nTelefone: " + client.getPhoneNumber() + "\nSaldo: " + client.getBalance() + "\n");
        }
    }

    public static void negativeBalances(ArrayList<Clients> clientsList){
        System.out.println("══════════════ Saldos Negativos  ══════════════");
        for (int i = 1; i < clientsList.size(); i++){
            Clients client = clientsList.get(i);
            if (client.getBalance() < 0){
                System.out.println("Cliente: " + client.getName() + "\nTelefone: " + client.getPhoneNumber() + "\nSaldo: " + client.getBalance() + "\n");
            }
        }
    }

}
