import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {

    public static void callMenus(ArrayList<Clients> clientsList, ArrayList<Pets> petsList, ArrayList<Services> serviceList, Scanner input) {
        int opcMenu = -1;
        while (true) {
            DataCrud.createFile(clientsList, false);
            opcMenu = menuPrincipal(input);
            if (opcMenu == 1) {
                opcMenu = menuClientes(input);
                if (opcMenu == 1) {
                    Main.incluirClientes();
                } else if (opcMenu == 2) {
                    System.out.println("\nLista de Clientes e seus Pets:");
                    int k = 0;
                    for (Clients client : clientsList) {
                        System.out.println("\n══════════════ Cliente " + ++k + " ══════════════");
                        System.out.println(client);
                    }
                } else if (opcMenu == 3) {
                    int clientIndex = Main.selecionarCliente();
                    DataCrud.deleteClient(clientIndex, clientsList);
                } else if (opcMenu == 0) {
                    continue;
                }
            } else if (opcMenu == 2) {
                opcMenu = menuPets(input);
                if (opcMenu == 1) {
                    int clientIndex = Main.selecionarCliente();
                    Main.incluirPets(clientIndex);
                } else if (opcMenu == 2) {
                    int clientIndex = Main.selecionarCliente();
                    int petIndex = Main.selecionarPet(clientIndex);
                    DataCrud.deletePet(clientIndex, petIndex, petsList, clientsList);
                } else if (opcMenu == 3) {
                    int clientIndex = Main.selecionarCliente();
                    DataCrud.deleteAllPets(clientIndex, petsList, clientsList);
                } else if (opcMenu == 0) {
                    continue;
                }
            } else if (opcMenu == 3) {
                opcMenu = menuServicos(input);
                if (opcMenu == 1) {
                    int clientIndex = Main.selecionarCliente();
                    int serviceIndex = Main.selecionarServico();
                    DataCrud.launchService(serviceList, clientsList, clientIndex, serviceIndex);
                } else if (opcMenu == 2) {
                    int clientIndex = Main.selecionarCliente();
                    DataCrud.launchPayment(clientsList, clientIndex, input);
                } else if (opcMenu == 3) {
                    DataCrud.customerBalances(clientsList);
                } else if (opcMenu == 4) {
                    DataCrud.negativeBalances(clientsList);
                } else if (opcMenu == 0) {
                    continue;
                }
            }
        }
    }

    public static int menuPrincipal(Scanner input) {
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

    public static int menuClientes(Scanner input) {
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

    public static int menuPets(Scanner input) {
        int opcMenu = -1;
        do {
            try {
                System.out.println("╔══════════════ Pets ══════════════╗");
                System.out.println("║                                  ║");
                System.out.println("║ 1. Incluir Pet                   ║");
                System.out.println("║ 2. Excluir Pet                   ║");
                System.out.println("║ 3. Excluir todos os Pets         ║");
                System.out.println("║ 0. Voltar                        ║");
                System.out.println("║                                  ║");
                System.out.println("╚══════════════════════════════════╝");
                System.out.print("Digite sua opção: ");
                opcMenu = input.nextInt();
                if (opcMenu < 0 || opcMenu > 3) {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                input.next();
            }
        } while (opcMenu < 0 || opcMenu > 3);
        return opcMenu;
    }

    public static int menuServicos(Scanner input) {
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
}
