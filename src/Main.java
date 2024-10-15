import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Services> servicesList = DataCrud.readServices("Services.dat");
    static ArrayList<Clients> clientsList = DataCrud.readClients("Dados.dat");
    static ArrayList<Pets> petsList = DataCrud.readPets("Dados.dat");
    public static void main(String[] args) {
        System.out.println(servicesList);
        System.out.println(clientsList);
        System.out.println(petsList);
        int opcMenu;
        while (true){
            DataCrud.createFile((ArrayList<Clients>) clientsList, (ArrayList<Pets>) petsList, false);
            opcMenu = menuPrincipal();
            if (opcMenu == 1) {
                opcMenu = menuClientes();
                if (opcMenu == 1) {
                    incluirClientes();
                } else if (opcMenu == 2) {
                    System.out.println("\nLista de Clientes e seus Pets:");
                    int k = 0;
                    for (Clients client : clientsList) {
                        System.out.println("\n══════════════ Cliente "+ ++k +" ══════════════");
                        System.out.println(client);
                    }
                } else if (opcMenu == 3) {
                    //DataCrud.deleteClient(client, clientsList);
                } else if (opcMenu == 0) {
                    continue;
                }
            } else if (opcMenu == 2) {
                opcMenu = menuPets();
                if (opcMenu == 1) {
                    int clientIndex = selecionarCliente();
                    incluirPets(clientIndex);
                } else if (opcMenu == 2) {
                    // DataCrud.deletePet(pet, petsList);
                } else if (opcMenu == 0) {
                    continue;
                }
            } else if (opcMenu == 3) {
                opcMenu = menuServicos();
                if (opcMenu == 1) {
                    // lancarServico();
                } else if (opcMenu == 2) {
                    // lancarPagamento();
                } else if (opcMenu == 3) {
                    // listarSaldos();
                } else if (opcMenu == 4) {
                    // listarSaldosNegativos();
                } else if (opcMenu == 0) {
                    continue;
                }
            }
        }
    }

    public static int selecionarCliente(){
        Scanner input = new Scanner(System.in);

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

        public static int menuPrincipal() {
            Scanner input = new Scanner(System.in);
            String opcMenu;
            int opc = -1;
            do {
                try {
                    System.out.println("╔══════════════════════════════════╗");
                    System.out.println("║   \uD83D\uDC3E  BEM-VINDO AO PETSHOP  \uD83D\uDC3E   ║");
                    System.out.println("╚══════════════════════════════════╝\n");

                    System.out.println("╔══════════════ MENU ══════════════╗");
                    System.out.println("║                                  ║");
                    System.out.println("║ 1. Cadastro de Clientes          ║");
                    System.out.println("║ 2. Cadastro de Pets              ║");
                    System.out.println("║ 3. Serviços e controle de caixa  ║");
                    System.out.println("║ S. Sair                          ║");
                    System.out.println("║                                  ║");
                    System.out.println("╚══════════════════════════════════╝");
                    System.out.print("Digite sua opção: ");

                    opcMenu = input.next().toUpperCase();

                    if (opcMenu.charAt(0) == 'S') {
                        System.out.println("Saindo do programa...");
                        System.exit(0);
                    }

                    opc = Integer.parseInt(opcMenu);  // Tenta converter para número

                    if (opc < 1 || opc > 3) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número válido ou 'S' para sair.");
                }
            } while (opc < 1 || opc > 3);
            return opc;
        }

        public static int menuClientes() {
            Scanner input = new Scanner(System.in);
            int opcMenu = -1;
            do {
                try {
                    System.out.println("╔════════════ Clientes ════════════╗");
                    System.out.println("║                                  ║");
                    System.out.println("║ 1. Incluir Cliente               ║");
                    System.out.println("║ 2. Listar Clientes               ║");
                    System.out.println("║ 3. Excluir Cliente               ║");
                    System.out.println("║ 0. Voltar                        ║");
                    System.out.println("║                                  ║");
                    System.out.println("╚══════════════════════════════════╝");
                    System.out.print("Digite sua opção: ");
                    opcMenu = input.nextInt();
                    if (opcMenu < 0 || opcMenu > 3) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    input.next(); // Limpa o buffer
                }
            } while (opcMenu < 0 || opcMenu > 3);
            return opcMenu;
        }

        public static int menuPets() {
            Scanner input = new Scanner(System.in);
            int opcMenu = -1;
            do {
                try {
                    System.out.println("╔══════════════ Pets ══════════════╗");
                    System.out.println("║                                  ║");
                    System.out.println("║ 1. Incluir Pet                   ║");
                    System.out.println("║ 2. Excluir Pet                   ║");
                    System.out.println("║ 0. Voltar                        ║");
                    System.out.println("║                                  ║");
                    System.out.println("╚══════════════════════════════════╝");
                    System.out.print("Digite sua opção: ");
                    opcMenu = input.nextInt();
                    if (opcMenu < 0 || opcMenu > 2) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número válido.");
                    input.next();
                }
            } while (opcMenu < 0 || opcMenu > 2);
            return opcMenu;
        }

        public static int menuServicos() {
            Scanner input = new Scanner(System.in);
            int opcMenu = -1;
            do {
                try {
                    System.out.println("╔═════ Serviços e Controle de Caixa ═════╗");
                    System.out.println("║                                        ║");
                    System.out.println("║ 1. Lançar Serviço                      ║");
                    System.out.println("║ 2. Lançar pagamento                    ║");
                    System.out.println("║ 3. Listar Saldo dos Clientes           ║");
                    System.out.println("║ 4. Listar clientes com saldo negativo  ║");
                    System.out.println("║ 0. Voltar                              ║");
                    System.out.println("║                                        ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    System.out.print("Digite sua opção: ");
                    opcMenu = input.nextInt();
                    if (opcMenu < 0 || opcMenu > 4) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número válido.");
                    input.next(); // Limpa o buffer
                }
            } while (opcMenu < 0 || opcMenu > 4);
            return opcMenu;
        }

        public static void incluirClientes() {
            Scanner input = new Scanner(System.in);

            try {
                System.out.println("Informações do Cliente");

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
            Scanner input = new Scanner(System.in);

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

                System.out.println("Pet adicionado com sucesso ao cliente " + clientsList.get(clientIndex).getName() + "!");

        }

    }