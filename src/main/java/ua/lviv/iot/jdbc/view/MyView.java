package ua.lviv.iot.jdbc.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class MyView {

    private final List<GeneralView> views;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;

    private final Scanner input = new Scanner(System.in);

    private final ClientView clientView;
    private final CityView cityView;
    private final ClientGymView clientGymView;
    private final ExerciseView exerciseView;
    private final ExercisePersonalTrainingView exercisePersonalTrainingView;


    public MyView(ClientView clientView, CityView cityView, ClientGymView clientGymView, ExerciseView exerciseView,
        ExercisePersonalTrainingView exercisePersonalTrainingView) {
        this.clientView = clientView;
        this.cityView = cityView;
        this.clientGymView = clientGymView;
        this.exerciseView = exerciseView;
        this.exercisePersonalTrainingView = exercisePersonalTrainingView;

        views = new ArrayList<>();
        views.add(clientView);
        views.add(cityView);
        views.add(clientGymView);
        views.add(exerciseView);
        views.add(exercisePersonalTrainingView);

        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        insertInfoIntoMenu();
        insertInfoIntoMethodsMenu();
    }

    private void insertInfoIntoMenu() {
        menu.put("A", "  --|A - choose all tables|--");
        views.forEach((view) -> menu.putAll(view.getMenu()));
        menu.put("Q", "  --|       Q - exit      |--");
    }

    private void insertInfoIntoMethodsMenu() {
        views.forEach((view) -> methodsMenu.putAll(view.getMethodsMenu()));
        methodsMenu.put("A", this::printAllTables);
        methodsMenu.put("Q", this::endApp);
    }

    private void printAllTables() {
        clientView.getMethodsMenu().get("14").print();
        cityView.getMethodsMenu().get("24").print();
        clientGymView.getMethodsMenu().get("33").print();
        exerciseView.getMethodsMenu().get("44").print();
        exercisePersonalTrainingView.getMethodsMenu().get("53").print();
    }

    private void endApp() {
        System.out.println("Thank you for using our service!");
    }

    private void outputMenu() {
        System.out.println("\n|---Menu---|");
        for (String key : menu.keySet())
            if (key.length() == 1)
                System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\n|------sub Menu------|");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig))
                System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }
}