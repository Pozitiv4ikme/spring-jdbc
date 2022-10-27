package ua.lviv.iot.jdbc.view.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.lviv.iot.jdbc.controller.ExercisePersonalTrainingController;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.projection.ExercisePersonalTrainingProjection;
import ua.lviv.iot.jdbc.view.ExercisePersonalTrainingView;
import ua.lviv.iot.jdbc.view.Printable;

@Component
public class ExercisePersonalTrainingViewImpl implements ExercisePersonalTrainingView {
    private final ExercisePersonalTrainingController exercisePersonalTrainingController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);


    public ExercisePersonalTrainingViewImpl (ExercisePersonalTrainingController exercisePersonalTrainingController) {
        this.exercisePersonalTrainingController = exercisePersonalTrainingController;

        menu = new LinkedHashMap<>();

        menu.put("5", "        5 - Table: Exercise personal training");
        menu.put("51", "         51 - Create exercise for personal training");
        menu.put("52", "         52 - Delete exercise for personal training");
        menu.put("53", "         53 - Find all exercises for personal trainings");
        menu.put("54", "         54 - Find exercises for personal training");

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("51", this::createExercisePersonalTraining);
        methodsMenu.put("52", this::deleteByExerciseId);
        methodsMenu.put("53", this::findALlExercisesForPersonalTrainings);
        methodsMenu.put("54", this::findByPersonalTrainingId);
    }

    private void createExercisePersonalTraining() {
        System.out.println("Input exercise ID: ");
        Integer exerciseId = Integer.valueOf(input.nextLine());

        System.out.println("Input personal training ID: ");
        Integer personalTrainingId = Integer.valueOf(input.nextLine());

        ExercisePersonalTraining exercisePersonalTraining = new ExercisePersonalTraining(exerciseId, personalTrainingId);

        ExercisePersonalTraining createdExercisePersonalTraining =
            exercisePersonalTrainingController.create(exercisePersonalTraining);
        System.out.print("\nCreated exercise for personal training - " + createdExercisePersonalTraining);
    }

    private void deleteByExerciseId() {
        System.out.println("Input exercise id: ");
        Integer exerciseId = Integer.valueOf(input.nextLine());

        System.out.println("Input personal training id: ");
        Integer personalTrainingId = Integer.valueOf(input.nextLine());

        int count = exercisePersonalTrainingController.deleteExerciseForPersonalTraining(exerciseId,
            personalTrainingId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findALlExercisesForPersonalTrainings() {
        System.out.println("\nTable: exercise personal training");
        List<ExercisePersonalTraining> exercisePersonalTrainings = exercisePersonalTrainingController.findALl();
        for (ExercisePersonalTraining exercisePersonalTraining : exercisePersonalTrainings) {
            System.out.println(exercisePersonalTraining);
        }
    }

    private void findByPersonalTrainingId() {
        System.out.println("Input personal training id: ");
        Integer personalTrainingId = Integer.valueOf(input.nextLine());

        Optional<List<ExercisePersonalTrainingProjection>> exercisePersonalTrainingProjections =
            exercisePersonalTrainingController.findByPersonalTrainingId(personalTrainingId);
        System.out.println(exercisePersonalTrainingProjections.orElse(List.of()));
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
