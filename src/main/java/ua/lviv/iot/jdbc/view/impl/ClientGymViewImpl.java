package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.ClientGymController;
import ua.lviv.iot.jdbc.domain.ClientGym;
import ua.lviv.iot.jdbc.domain.projection.ClientGymProjection;
import ua.lviv.iot.jdbc.view.ClientGymView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class ClientGymViewImpl implements ClientGymView {
    private final ClientGymController clientGymController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);


    public ClientGymViewImpl(ClientGymController clientGymController) {
        this.clientGymController = clientGymController;

        menu = new LinkedHashMap<>();

        menu.put("3", "        3 - Table: Client-Gym");
        menu.put("31", "         31 - Add client to gym");
        menu.put("32", "         32 - Delete client from gym");
        menu.put("33", "         33 - Find all clients and their gyms");
        menu.put("34", "         34 - Find clients by Gym ID");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("31", this::addClientToGym);
        methodsMenu.put("32", this::deleteClientFromGym);
        methodsMenu.put("33", this::findAllClientsWithGyms);
        methodsMenu.put("34", this::findAllClientsInGym);
    }

    private void addClientToGym() {
        System.out.println("Input client id: ");
        Integer clientId = Integer.valueOf(input.nextLine());

        System.out.println("Input gym id: ");
        Integer gymId = Integer.valueOf(input.nextLine());

        ClientGym clientGym = new ClientGym(gymId, clientId);

        ClientGym createdClientGym = clientGymController.create(clientGym);
        System.out.print("\nResult - " + createdClientGym);
    }

    private void deleteClientFromGym() {
        System.out.println("Input client id to delete from gym: ");
        Integer clientId = Integer.valueOf(input.nextLine());

        int count = clientGymController.deleteByClientId(clientId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllClientsWithGyms() {
        System.out.println("\nTable: client -> gym");
        List<ClientGym> clientsForGyms = clientGymController.findALl();
        for (ClientGym clientGym : clientsForGyms) {
            System.out.println(clientGym);
        }
    }

    private void findAllClientsInGym() {
        System.out.println("Input gym id: ");
        Integer gymId = Integer.valueOf(input.nextLine());

        Optional<List<ClientGymProjection>> clients = clientGymController.findClientsByGymId(gymId);
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
