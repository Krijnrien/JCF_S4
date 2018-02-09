package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Eén, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        ArrayList<String> list = stringToWordList(taInput.getText());

        Set<String> unique = new HashSet<>(list);
        HashSet noDupSet = new HashSet(list);

        taOutput.setText("Totaal aantal " + noDupSet.size());
        taOutput.appendText("\nTotaal aantal " + list.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        ArrayList<String> words = stringToWordList(taInput.getText());
        Collections.sort(words, Collections.reverseOrder());
        for (String word : words) {
            taOutput.appendText(word + "\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        ArrayList<String> list = stringToWordList(taInput.getText());

        taOutput.setText(btAantal.getText());
        Set<String> unique = new HashSet<>(list);
        String output = "";
        for (String key : unique) {
            output += key + ": " + Collections.frequency(list, key) + "\n";
        }
        taOutput.setText(output);
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        taOutput.setText(characterConcordance(taInput.getText()).toString());
    }


    private ArrayList<String> stringToWordList(String _taInput) {
        ArrayList<String> wordArrayList = new ArrayList<>();
        wordArrayList.addAll(Arrays.asList(_taInput.split("\\s*(=>|,|\\s)\\s*")));
        return wordArrayList;
    }

    private static Map<Character, Set<Integer>> characterConcordance(final String input) {
        Map<Character, Set<Integer>> concordance = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (charAt == ' ') {
                continue;
            }
            Set<Integer> set = concordance.get(charAt);
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(i + 1);
            concordance.put(charAt, set);
        }

        return concordance;
    }

}
