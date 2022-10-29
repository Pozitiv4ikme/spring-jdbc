package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.FreeGroupProgramController;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;
import ua.lviv.iot.jdbc.view.FreeGroupProgramView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class FreeGroupProgramViewImpl implements FreeGroupProgramView {

    private final FreeGroupProgramController freeGroupProgramController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    private final FreeGroupProgram nullFreeGroupProgram = new FreeGroupProgram(null, null, null);

    public FreeGroupProgramViewImpl (FreeGroupProgramController freeGroupProgramController) {
        this.freeGroupProgramController = freeGroupProgramController;

        menu = new LinkedHashMap<>();

        menu.put("6", "        6 - Table: Free group program");
        menu.put("61", "         61 - Create free group program");
        menu.put("62", "         62 - Update free group program");
        menu.put("63", "         63 - Delete free group program");
        menu.put("64", "         64 - Find all free groups programs");
        menu.put("65", "         65 - Find free group program by ID");
        menu.put("66", "         66 - Find all free groups programs by exercise");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("61", this::createFreeGroupProgram);
        methodsMenu.put("62", this::updateFreeGroupProgram);
        methodsMenu.put("63", this::deleteFreeGroupProgram);
        methodsMenu.put("64", this::findAllFreeGroupsPrograms);
        methodsMenu.put("65", this::findFreeGroupProgramByID);
        methodsMenu.put("66", this::findAllFreeGroupsProgramsByExercise);
    }

    private void createFreeGroupProgram() {
        System.out.println("Input day for free group program: ");
        String day = input.nextLine();

        System.out.println("Input exercise for free group program: ");
        String exercise = input.nextLine();

        FreeGroupProgram freeGroupProgram = new FreeGroupProgram(null, day, exercise);

        FreeGroupProgram createdFreeGroupProgram = freeGroupProgramController.create(freeGroupProgram);
        System.out.print("\nCreated free group program - " + createdFreeGroupProgram);
    }

    private void updateFreeGroupProgram() {
        System.out.println("Input free group program id: ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new day for free group program: ");
        String newDay = input.nextLine();

        System.out.println("Input new exercise for free group program: ");
        String newExercise = input.nextLine();

        FreeGroupProgram freeGroupProgram = new FreeGroupProgram(null, newDay, newExercise);

        FreeGroupProgram updatedFreeGroupProgram = freeGroupProgramController.update(freeGroupProgram, id);
        System.out.print("\nNew free group program - " + updatedFreeGroupProgram);
    }

    private void deleteFreeGroupProgram() {
        System.out.println("Input free group program id: ");
        Integer freeGroupProgramId = Integer.valueOf(input.nextLine());

        int count = freeGroupProgramController.delete(freeGroupProgramId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllFreeGroupsPrograms() {
        System.out.println("\nTable: free group program");
        List<FreeGroupProgram> freeGroupPrograms = freeGroupProgramController.findALl();
        for (FreeGroupProgram freeGroupProgram : freeGroupPrograms) {
            System.out.println(freeGroupProgram);
        }
    }

    private void findFreeGroupProgramByID() {
        System.out.println("Input free group program id: ");
        Integer freeGroupProgramID = Integer.valueOf(input.nextLine());

        Optional<FreeGroupProgram> freeGroupProgram = freeGroupProgramController.findById(freeGroupProgramID);
        System.out.println(freeGroupProgram.orElse(nullFreeGroupProgram));
    }

    private void findAllFreeGroupsProgramsByExercise() {
        System.out.println("Input exercise: ");
        String exercise = input.nextLine();

        Optional<List<FreeGroupProgram>> freeGroupPrograms = freeGroupProgramController.findAllByExercise(exercise);
        System.out.println(freeGroupPrograms.orElse(List.of()));
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
