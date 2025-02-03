package com.example.demo;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainApplication extends Application {

    private int[] userArray = {};
    private int[] originalArray = {};

    private GraphicsContext gc;
    private TextField inputField;
    private Timeline timeline;

    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1000, 600);
        gc = canvas.getGraphicsContext2D();

        drawArray(gc, userArray);

        inputField = new TextField();
        inputField.setPromptText("Enter numbers separated by comas");
        inputField.setPrefWidth(350);
        inputField.setFont(new Font(17));

        Button shuffleButton = createButton("Shuffle", this::shuffleArray);
        Button saveButton = createButton("Save", this::saveArray);
        Button startButton = createButton("Start", this::startSorting);
        Button resetButton = createButton("Reset", this::resetSorting);
        Button five = createButton("Länge 5", this::saveArray5);
        Button ten = createButton("Länge 10", this::saveArray10);
        Button fifteen = createButton("Länge 15", this::saveArray15);
        Button twentyfive = createButton("Länge 25", this::saveArray25);
        Button fifty = createButton("Länge 50", this::saveArray50);
        Button houndred = createButton("Länge 100", this::saveArray100);

        HBox inputBox = new HBox(inputField, saveButton);
        inputBox.setSpacing(12);
        inputBox.setPadding(new Insets(0, 0, 10, 0));

        HBox buttonBox = new HBox(10, startButton, resetButton, shuffleButton);
        buttonBox.setSpacing(12);

        HBox numbersBox = new HBox(10, five, ten, fifteen, twentyfive, fifty, houndred);
        numbersBox.setSpacing(12);

        VBox root = new VBox(15, inputBox, buttonBox, numbersBox, canvas);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Merge Sort Visualizer using JavaFX");
        stage.show();
    }

    private Button createButton(String text, Runnable action){
        Button button = new Button(text);
        Font buttonFont = new Font(17);
        button.setFont(buttonFont);
        button.setOnAction(event -> action.run());
        return button;
    }

    private void drawArray(GraphicsContext gc, int[] array){
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        double barWidth = 40;

        gc.clearRect(0, 0, width, height);

        for(int i = 0; i < array.length; i++){
            double x = i * (barWidth + 5);
            double y = height - array[i] * 10;
            double barHeight = array[i] * 10;

            gc.setFill(Color.GRAY);
            gc.fillRect(x, y, barWidth, barHeight);
        }
    }

    private void saveArray() {
        String inputText = inputField.getText().trim();
        String[] inputArray = inputText.split(",");
        userArray = new int[inputArray.length];
        for(int i = 0; i < inputArray.length; i++){
            userArray[i] = Integer.parseInt(inputArray[i].trim());
        }

        originalArray = userArray.clone();

        drawArray(gc, userArray);
    }

    private int[] shuffle(int[] array) {
        List<Integer> umwandeln = new ArrayList<>();
        for (int num : array) {
            umwandeln.add(num);
        }

        Collections.shuffle(umwandeln);

        int[] shuffledArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            shuffledArray[i] = umwandeln.get(i);
        }
        return shuffledArray;
    }

    private void shuffleArray() {
        String inputText = inputField.getText().trim();
        String[] inputArray = inputText.split(",");

        userArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            userArray[i] = Integer.parseInt(inputArray[i].trim());
        }

        int[] weitergeben = shuffle(userArray);
        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < weitergeben.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(weitergeben[i]);
        }

        inputField.setText(outputText.toString());
        saveArray();
    }

    private void startSorting() {
        inputField.setDisable(true);
        System.out.println("Start sorting");

        Visualizer visualizer = new Visualizer(gc);
        List<ArrayState> states = new ArrayList<>();
        Sorter.mergeSort(userArray, states, 0, userArray.length - 1);

        timeline = new Timeline();
        Duration frameGap = Duration.millis(200);
        Duration frameTime = Duration.ZERO;

        for(ArrayState state : states){
            KeyFrame frame = new KeyFrame(frameTime, e -> visualizer.visualizeArray(state.getArray(), state.getFirstIndex(), state.getSecondIndex()));
            timeline.getKeyFrames().add(frame);
            frameTime = frameTime.add(frameGap);
        }
        timeline.setOnFinished(e -> inputField.setDisable(false));

        timeline.play();
    }

    private void resetSorting() {
        System.out.println("Reset sorting");
        if(timeline != null)
            timeline.stop();

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        inputField.setDisable(false);
        drawArray(gc, originalArray);

    }

    private void saveArray5() {
        int [] saveArray = new int[5];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    private void saveArray10() {
        int [] saveArray = new int[10];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    private void saveArray15() {
        int [] saveArray = new int[15];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    private void saveArray25() {
        int [] saveArray = new int[25];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    private void saveArray50() {
        int [] saveArray = new int[50];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    private void saveArray100() {
        int [] saveArray = new int[100];
        for(int i = 0; i < saveArray.length; i++){
            saveArray[i] = (int) (Math.random() * saveArray.length + 1);
        }

        StringBuilder outputText = new StringBuilder();
        for (int i = 0; i < saveArray.length; i++) {
            if (i > 0) outputText.append(", ");
            outputText.append(saveArray[i]);
        }

        inputField.setText(outputText.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}
