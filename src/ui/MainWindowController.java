package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.Mountain;
import java.io.IOException;


public class MainWindowController {

    private Mountain mountain;

    @FXML
    private TextField NTxt;
    @FXML
    private Label warningLbl;
    @FXML
    private TextArea contentTxt;
    @FXML
    private Button calcBtn;

    public MainWindowController(Mountain mountain) {
        this.mountain = mountain;
    }

    @FXML
    public void calcEnergyNeeded(ActionEvent e) {

    }

    @FXML
    public void showImportWindow(ActionEvent e) throws IOException {

    }

    @FXML
    public void setWarningLbl(ActionEvent e) {

    }

    public void config() {
        warningLbl.setVisible(false);
        calcBtn.setDisable(true);
    }

}
