package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import woordenapplicatie.util;

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
    private TextArea taInput;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction() {
        taOutput.setText(null);
        ArrayList<String> list = util.stringToWordList(taInput.getText());
        taOutput.setText("Unique words: " + util.removeDuplicates(list).size());
        taOutput.appendText("\nTotal words: " + list.size());
    }

    @FXML
    private void sorteerAction() {
        taOutput.setText(null);
        ArrayList<String> words = util.stringToWordList(taInput.getText());
        words.sort(Collections.reverseOrder());
        for (String word : words) {
            taOutput.appendText(word + "\n");
        }
    }

    @FXML
    private void frequentieAction() {
        taOutput.setText(null);
        taOutput.setText(util.getWordFrequency(taInput.getText()).toString());
        Map<String, Integer> concordance = util.getWordFrequency(taInput.getText());
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Integer> entry : concordance.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            output.append(key).append(": ").append(value).append("\n");
        }
        taOutput.setText(output.toString());
    }

    @FXML
    private void concordatieAction() {
        taOutput.setText(util.wordConcordance(taInput.getText()).toString());
    }

}
