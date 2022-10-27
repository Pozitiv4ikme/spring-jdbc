package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.CityController;
import ua.lviv.iot.jdbc.domain.City;
import ua.lviv.iot.jdbc.view.CityView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class CityViewImpl implements CityView {

    private final CityController cityController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    private final City nullCity = new City(null, null);

    public CityViewImpl(CityController cityController) {
        this.cityController = cityController;

        menu = new LinkedHashMap<>();

        menu.put("2", "        2 - Table: City");
        menu.put("21", "         21 - Create City");
        menu.put("22", "         22 - Update City");
        menu.put("23", "         23 - Delete from City");
        menu.put("24", "         24 - Find all Cities");
        menu.put("25", "         25 - Find City by ID");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteCity);
        methodsMenu.put("24", this::findAllCities);
        methodsMenu.put("25", this::findCityById);
    }

    private void createCity() {
        System.out.println("Input city name: ");
        String name = input.nextLine();

        City city = new City(null, name);

        City createdCity = cityController.create(city);
        System.out.print("\nCreated client - " + createdCity);
    }

    private void updateCity() {
        System.out.println("Input city id to update: ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new name: ");
        String newName = input.nextLine();

        City city = new City(id, newName);

        City newCity = cityController.update(city, id);
        System.out.print("\nNew client" + newCity);
    }

    private void deleteCity() {
        System.out.println("Input city id: ");
        Integer cityId = Integer.valueOf(input.nextLine());

        int count = cityController.delete(cityId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCities() {
        System.out.println("\nTable: city");
        List<City> cities = cityController.findALl();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    private void findCityById() {
        System.out.println("Input city id: ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<City> city = cityController.findById(id);
        System.out.println(city.orElse(nullCity));
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
