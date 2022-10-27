package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.ClientController;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;
import ua.lviv.iot.jdbc.view.ClientView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class ClientViewImpl implements ClientView {
    private final ClientController clientController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    private final Client nullClient = new Client(null, null, null, null, null, null);

    public ClientViewImpl (ClientController clientController) {
        this.clientController = clientController;

        menu = new LinkedHashMap<>();

        menu.put("1", "        1 - Table: Client");
        menu.put("11", "         11 - Create Client");
        menu.put("12", "         12 - Update Client");
        menu.put("13", "         13 - Delete from Client");
        menu.put("14", "         14 - Find all Clients");
        menu.put("15", "         15 - Find Client by ID");
        menu.put("16", "         16 - Find Client by gender");
        menu.put("17", "         17 - Find Client by name");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("11", this::createClient);
        methodsMenu.put("12", this::updateClient);
        methodsMenu.put("13", this::deleteClient);
        methodsMenu.put("14", this::findAllClients);
        methodsMenu.put("15", this::findClientById);
        methodsMenu.put("16", this::findClientByGender);
        methodsMenu.put("17", this::findClientsByName);
    }

    private void createClient() {
        System.out.println("Input client name: ");
        String clientName = input.nextLine();

        System.out.println("Input client surname: ");
        String clientSurname = input.nextLine();

        System.out.println("Input client birthday (example: 1999-01-12): ");
        String clientBirthday = input.nextLine();

        System.out.println("Input client phone (example: 380670000000): ");
        String clientPhone = input.nextLine();

        System.out.println("Choose client gender (1 - male, 2 - female, 3 - non-binary, 4 - transgender): ");
        Integer clientGenderId = Integer.parseInt(input.nextLine());

        Client client = new Client(null, clientName, clientSurname, clientBirthday,
            clientPhone, clientGenderId);

        Client createdClient = clientController.create(client);
        System.out.print("\nCreated client - " + createdClient);
    }

    private void updateClient() {
        System.out.println("Input client id to update: ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new name: ");
        String newName = input.nextLine();

        System.out.println("Input new surname: ");
        String newSurname = input.nextLine();

        System.out.println("Input new birthday (example: 1999-01-12): ");
        String newBirthday = input.nextLine();

        System.out.println("Input new phone (example: 380670000000): ");
        String newPhone = input.nextLine();

        System.out.println("Choose client gender (1 - male, 2 - female, 3 - non-binary, 4 - transgender): ");
        Integer newGender = Integer.valueOf(input.nextLine());

        Client client = new Client(id, newName, newSurname, newBirthday, newPhone, newGender);

        Client newClient = clientController.update(client, id);
        System.out.print("\nNew client" + newClient);
    }

    private void deleteClient() {
        System.out.println("Input client id: ");
        Integer clientId = Integer.valueOf(input.nextLine());

        int count = clientController.delete(clientId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllClients() {
        System.out.println("\nTable: client");
        List<Client> clients = clientController.findALl();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void findClientById() {
        System.out.println("Input client id: ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Client> client = clientController.findById(id);
        System.out.println(client.orElse(nullClient));
    }

    private void findClientByGender() {
        System.out.println("Input gender for client (1 - male, 2 - female, 3 - non-binary, 4 - transgender): ");
        Integer clientGender = Integer.valueOf(input.nextLine());

        Optional<List<ClientProjection>> clients = clientController.findByGender(clientGender);
        System.out.println(clients.orElse(List.of()));
    }

    private void findClientsByName() {
        System.out.println("Input client name: ");
        String name = input.nextLine();

        Optional<List<Client>> clients = clientController.findByName(name);
        System.out.println(clients.orElse(List.of()));
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }
}
