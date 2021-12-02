package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Mountain;
import java.io.IOException;


public class MainWindowController {

    private final String FOLDER = "fxml/";

    private Mountain mountain;
    private EmergentWindowController EWC;

    @FXML
    private TextField NTxt;
    @FXML
    private Label warningLbl;
    @FXML
    private TextArea contentTxt;
    @FXML
    private Button calcBtn;
    @FXML
    private TextField friendsTxt;
    private int N;
    private String[] contentArray;
    private String[] friendsArray;

    public MainWindowController(Mountain mountain) {
        this.mountain = mountain;
        EWC = new EmergentWindowController(mountain);
    }

    @FXML
    public void calcEnergy() {
        contentArray = contentTxt.getText().split("\n");
        friendsArray = friendsTxt.getText().split(" ");
        mountain.insertLandMarks(N);
        for(int i = 0; i < contentArray.length; i ++)
            mountain.insertLandMarks(mountain.parseStringArray(contentArray[i].split(" ")));
        mountain.addFriends(mountain.parseStringArray(friendsArray));
        int energy = mountain.calcMinEnergy();
        showAlert("La energía requrida es de " + energy, null, null);
        mountain.clear();
        config();
        contentTxt.clear();
        friendsTxt.clear();
        NTxt.clear();
    }

    private void showAlert(String msg, String header, String title) {
        Alert feedback = new Alert(Alert.AlertType.INFORMATION);
        feedback.setTitle(title);
        feedback.setHeaderText(header);
        feedback.setContentText(msg);
        feedback.showAndWait();
    }

    @FXML
    public void showImportWindow(ActionEvent e) throws IOException {
        EWC.showWindow();
    }

    @FXML
    public void validateLandMarks() {
        try {
            N = Integer.parseInt(NTxt.getText());
            if(N < 2) {
                warningLbl.setVisible(true);
                warningLbl.setText("La entrada de be ser un número mayor a 1");
                calcBtn.setDisable(true);
                return;
            }
            calcBtn.setDisable(false);
            warningLbl.setVisible(false);
            contentTxt.setDisable(false);
        } catch(NumberFormatException e) {
            warningLbl.setVisible(true);
            warningLbl.setText("La entrada de be ser un número mayor a 1");
            calcBtn.setDisable(true);
        }
    }

    public void config() {
        warningLbl.setVisible(false);
        calcBtn.setDisable(true);
    }

}
