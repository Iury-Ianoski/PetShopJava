import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Services> servicesList = DataCrud.readServices("Services.dat");
    static ArrayList<Clients> clientsList = DataCrud.readClients("Dados.dat");
    static ArrayList<Pets> petsList = DataCrud.readPets(clientsList);
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Menus.callMenus(clientsList, petsList, servicesList, input);
    }

    public static int selecionarCliente() {
        try {
            if (clientsList.isEmpty()) {
                System.out.println("Não há clientes cadastrados. Cadastre um cliente primeiro.");
                return 0;
            }

            System.out.println("Selecione o cliente");
            for (int i = 0; i < clientsList.size(); i++) {
                System.out.println((i + 1) + ". " + clientsList.get(i).getName());
            }

            int opcCli = -1;
            while (true) {
                try {
                    System.out.print("Digite a opção do cliente: ");
                    opcCli = input.nextInt() - 1;
                    input.nextLine();

                    if (opcCli >= 0 && opcCli < clientsList.size()) {
                        break;
                    } else {
                        System.out.println("Opção inválida! Digite um número válido.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    input.next();
                }
            }
            return opcCli;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        return 0;
    }

    public static int selecionarServico(){
        try {
            System.out.println("Selecione o cliente");
            for (int i = 0; i < servicesList.size(); i++) {
                System.out.println((i + 1) + ". " + servicesList.get(i).getServiceName() + ";" + servicesList.get(i).getPrice());
            }

            int opcService = -1;
            while (true) {
                try {
                    System.out.print("Digite a opção do serviço: ");
                    opcService = input.nextInt() - 1;
                    input.nextLine();

                    if (opcService >= 0 && opcService < clientsList.size()) {
                        break;
                    } else {
                        System.out.println("Opção inválida! Digite um número válido.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    input.next();
                }
            }
            return opcService;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        return 0;
    }

    public static int selecionarPet(int clientIndex){
        ArrayList<Pets> clientPets = clientsList.get(clientIndex).getPets();
        if (clientsList.get(clientIndex).getPets().isEmpty()) {
            System.out.println("Não há pets cadastrados. Cadastre um pet primeiro.");
            return 0;
        }
        try {
            System.out.println("Selecione o Pet");
            for (int i = 0; i < clientPets.size(); i++) {
                System.out.println((i + 1) + ". " + clientPets.get(i).getName());
            }

            int opcPet = -1;
            while (true) {
                try {
                    System.out.print("Digite a opção do pet: ");
                    opcPet = input.nextInt() - 1;
                    input.nextLine();

                    if (opcPet >= 0 && opcPet < clientPets.size()) {
                        break;
                    } else {
                        System.out.println("Opção inválida! Digite um número válido.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    input.next();
                }
            }
            return opcPet;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        return 0;
    }


    public static void incluirClientes() {
        try {
            System.out.println("Informações do Cliente");
            input.next();
            System.out.print("Nome: ");
            String nomeCli = input.nextLine();
            System.out.print("Endereço: ");
            String enderecoCli = input.nextLine();
            System.out.print("Telefone: ");
            String telefoneCli = input.nextLine();
            double saldoCli = 0;
            while (true) {
                try {
                    System.out.print("Saldo: ");
                    saldoCli = input.nextDouble();
                    input.nextLine();
                    if (saldoCli >= 0) break; // Saldo válido
                    System.out.println("O saldo deve ser um número não negativo.");
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um valor numérico.");
                    input.next();
                }
            }
            ArrayList<Pets> listaPets = new ArrayList<>();
            char adicionaPet;
            while (true) {
                try {
                    System.out.print("\nEsse cliente possui um pet para cadastrar? (s/n): ");
                    adicionaPet = input.next().toLowerCase().charAt(0);
                    input.nextLine();
                    if (adicionaPet == 's' || adicionaPet == 'n') {
                        break;
                    }
                    System.out.println("Entrada inválida! Digite 's' para sim ou 'n' para não.");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro! Tente novamente.");
                }
            }
            // para incluir mais de um pet, se desejado
            while (adicionaPet == 's') {
                try {
                    System.out.println("\nInformações do Pet");

                    System.out.print("Nome: ");
                    String nomePet = input.nextLine();

                    int idadePet = -1;
                    while (true) {
                        try {
                            System.out.print("Idade: ");
                            idadePet = input.nextInt();
                            input.nextLine();

                            if (idadePet >= 0) break;
                            System.out.println("A idade deve ser um número não negativo.");
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, insira um número.");
                            input.next();
                        }
                    }

                    System.out.print("Espécie: ");
                    String especiePet = input.nextLine();
                    System.out.print("Raça: ");
                    String racaPet = input.nextLine();
                    System.out.print("Descrição: ");
                    String descPet = input.nextLine();

                    Pets pet = new Pets(especiePet, racaPet, nomePet, idadePet, descPet);
                    listaPets.add(pet);

                    System.out.print("\nIncluir mais Pets para este cliente? (s/n): ");
                    adicionaPet = input.next().toLowerCase().charAt(0);
                    input.nextLine();

                    if (adicionaPet != 's' && adicionaPet != 'n') {
                        System.out.println("Entrada inválida! Considerando como 'n'.");
                        adicionaPet = 'n';
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar pet: " + e.getMessage());
                }
            }

            Clients cliente = new Clients(nomeCli, enderecoCli, telefoneCli, saldoCli, listaPets);
            clientsList.add(cliente);
            System.out.println("\nCliente cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public static void incluirPets(int clientIndex) {
        System.out.println("Informações do Pet:");
        System.out.print("Nome: ");
        String nomePet = input.nextLine();

        int idadePet = -1;
        while (true) {
            try {
                System.out.print("Idade: ");
                idadePet = input.nextInt();
                input.nextLine();

                if (idadePet >= 0) {
                    break;
                }
                System.out.println("A idade do pet deve ser um número não negativo.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                input.next();
            }
        }

        System.out.print("Espécie: ");
        String especiePet = input.nextLine();
        System.out.print("Raça: ");
        String racaPet = input.nextLine();
        System.out.print("Descrição: ");
        String descPet = input.nextLine();

        Pets novoPet = new Pets(especiePet, racaPet, nomePet, idadePet, descPet);
        clientsList.get(clientIndex).getPets().add(novoPet);
        petsList.add(novoPet);

        System.out.println("Pet adicionado com sucesso ao cliente " + clientsList.get(clientIndex).getName() + "!");

    }
}



