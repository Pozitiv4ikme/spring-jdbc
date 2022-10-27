package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.ExerciseController;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;
import ua.lviv.iot.jdbc.view.ExerciseView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class ExerciseViewImpl implements ExerciseView {

    private final ExerciseController exerciseController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    private final Exercise nullExercise = new Exercise(null, null, null, null, null, null);

    public ExerciseViewImpl (ExerciseController exerciseController) {
        this.exerciseController = exerciseController;

        menu = new LinkedHashMap<>();

        menu.put("4", "        4 - Table: Exercise");
        menu.put("41", "         41 - Create exercise");
        menu.put("42", "         42 - Update exercise");
        menu.put("43", "         43 - Delete exercise");
        menu.put("44", "         44 - Find all exercises");
        menu.put("45", "         45 - Find exercises by ID");
        menu.put("46", "         46 - Find by type of muscle load on");
        menu.put("47", "         47 - Find by personal exercise machine ID");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("41", this::createExercise);
        methodsMenu.put("42", this::updateExercise);
        methodsMenu.put("43", this::deleteExercise);
        methodsMenu.put("44", this::findAllExercises);
        methodsMenu.put("45", this::findExerciseByID);
        methodsMenu.put("46", this::findByTypeOfMuscleLoadOn);
        methodsMenu.put("47", this::findByPersonalExerciseMachineID);
    }

    private String getTypeOfMuscleLoadOnFromKeyboard() {
        System.out.println("Choose type of muscle load on (1 - one type, 2 - complex, 3 - all): ");
        String typeOfMuscleLoadOn = input.nextLine();

        String result = null;
        switch (typeOfMuscleLoadOn) {
            case "1" -> result = "one type";
            case "2" -> result = "complex";
            case "3" -> result = "all";
            default -> getTypeOfMuscleLoadOnFromKeyboard();
        }
        return result;
    }

    private void createExercise() {
        System.out.println("Input number of repetitions: ");
        Integer numberOfRepetitions = Integer.valueOf(input.nextLine());

        System.out.println("Input approach: ");
        Integer approach = Integer.valueOf(input.nextLine());

        System.out.println("Input complexity (optional): ");
        String complexityInput = input.nextLine();
        Integer complexity = (complexityInput.equals("") ? null : Integer.valueOf(complexityInput));

        System.out.println("Input personal exercise machine id: ");
        Integer personalExerciseMachineId = Integer.valueOf(input.nextLine());

        String typeOfMuscleLoadOn = getTypeOfMuscleLoadOnFromKeyboard();

        Exercise exercise = new Exercise(null, numberOfRepetitions, approach, complexity, personalExerciseMachineId, typeOfMuscleLoadOn);

        Exercise createdExercise = exerciseController.create(exercise);
        System.out.print("\nCreated exercise - " + createdExercise);
    }

    private void updateExercise() {
        System.out.println("Input exercise id to update: ");
        Integer exerciseId = Integer.valueOf(input.nextLine());

        System.out.println("Input number of repetitions: ");
        Integer newNumberOfRepetitions = Integer.valueOf(input.nextLine());

        System.out.println("Input approach: ");
        Integer newApproach = Integer.valueOf(input.nextLine());

        System.out.println("Input complexity (optional): ");
        String complexityInput = input.nextLine();
        Integer newComplexity = (complexityInput.equals("") ? null : Integer.valueOf(complexityInput));

        System.out.println("Input personal exercise machine id: ");
        Integer newPersonalExerciseMachineId = Integer.valueOf(input.nextLine());

        String newTypeOfMuscleLoadOn = getTypeOfMuscleLoadOnFromKeyboard();

        Exercise exercise = new Exercise(exerciseId, newNumberOfRepetitions, newApproach, newComplexity,
            newPersonalExerciseMachineId,
            newTypeOfMuscleLoadOn);

        Exercise updatedExercise = exerciseController.update(exercise, exerciseId);
        System.out.print("\nNew exercise - " + updatedExercise);
    }

    private void deleteExercise() {
        System.out.println("Input exercise id: ");
        Integer exerciseId = Integer.valueOf(input.nextLine());

        int count = exerciseController.delete(exerciseId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllExercises() {
        System.out.println("\nTable: exercise");
        List<Exercise> exercises = exerciseController.findALl();
        for (Exercise exercise : exercises) {
            System.out.println(exercise);
        }
    }

    private void findExerciseByID() {
        System.out.println("Input exercise id: ");
        Integer exerciseID = Integer.valueOf(input.nextLine());

        Optional<Exercise> exercise = exerciseController.findById(exerciseID);
        System.out.println(exercise.orElse(nullExercise));
    }

    private void findByTypeOfMuscleLoadOn() {
        String typeOfMuscleLoadOn = getTypeOfMuscleLoadOnFromKeyboard();

        Optional<List<Exercise>> exercises = exerciseController.findByTypeOfMuscleLoadOn(typeOfMuscleLoadOn);
        System.out.println(exercises.orElse(List.of()));
    }

    private void findByPersonalExerciseMachineID() {
        System.out.println("Input personal exercise machine id: ");
        Integer personalExerciseMachineId = Integer.valueOf(input.nextLine());

        Optional<List<ExerciseProjection>> exerciseProjections =
            exerciseController.findByPersonalExerciseMachineId(personalExerciseMachineId);
        System.out.println(exerciseProjections.orElse(List.of()));
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
