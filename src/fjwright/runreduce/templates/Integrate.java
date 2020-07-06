package fjwright.runreduce.templates;

import fjwright.runreduce.RunREDUCE;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Integrate extends Template {
    @FXML
    private HBox symText, numText;
    @FXML
    private RadioButton numRadioButton;
    @FXML
    private TextField integrandTextField;
    @FXML
    private TextField xIntVarTextField, yIntVarTextField, zIntVarTextField;
    @FXML
    private TextField xLowLimTextField, yLowLimTextField, zLowLimTextField;
    @FXML
    private TextField xUpLimTextField, yUpLimTextField, zUpLimTextField;
    @FXML
    private VBox yIntVBox, zIntVBox;
    @FXML
    private Label yDLabel, zDLabel;
    @FXML
    private CheckBox algintCheckBox;
    @FXML
    private GridPane numGridPane;
    @FXML
    private TextField accuracyTextBox, iterationsTextBox;

    private TextField[] intVarTextFields;
    private TextField[] lowLimTextFields;
    private TextField[] upLimTextFields;

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        intVarTextFields = new TextField[]{xIntVarTextField, yIntVarTextField, zIntVarTextField};
        lowLimTextFields = new TextField[]{xLowLimTextField, yLowLimTextField, zLowLimTextField};
        upLimTextFields = new TextField[]{xUpLimTextField, yUpLimTextField, zUpLimTextField};
    }

    @FXML
    private void symRadioButtonAction() {
        numRadioButtonAction();
    }

    @FXML
    private void numRadioButtonAction() {
        numText.setVisible(numRadioButton.isSelected());
        symText.setVisible(!numRadioButton.isSelected());
        numGridPane.setVisible(numRadioButton.isSelected());
        algintCheckBox.setVisible(!numRadioButton.isSelected());
    }

    @FXML
    private void yIntVarKeyTyped(KeyEvent keyEvent) {
        varCheckKeyTyped(keyEvent);
        boolean visible = !yIntVarTextField.getText().isEmpty();
        yIntVBox.setVisible(visible);
        yDLabel.setVisible(visible);
    }

    @FXML
    private void zIntVarKeyTyped(KeyEvent keyEvent) {
        varCheckKeyTyped(keyEvent);
        boolean visible = !zIntVarTextField.getText().isEmpty();
        zIntVBox.setVisible(visible);
        zDLabel.setVisible(visible);
    }

    @Override
    String result() throws Template.EmptyFieldException {
        final String integrand = integrandTextField.getText();
        if (integrand.isEmpty() || xIntVarTextField.getText().isEmpty()) {
            RunREDUCE.errorMessageDialog("Integrate Template Error",
                    "The integrand and integration variable are both required.");
            throw new EmptyFieldException();
        }
        final var text = new StringBuilder();
        if (numRadioButton.isSelected()) {
            preamble("load_package numeric;\n");
            // Begin numerical integration
            text.append("num_int(").append(integrand);
            for (int i = 0; i < 3; i++) {
                String intVar = intVarTextFields[i].getText().trim();
                if (!intVar.isEmpty()) {
                    text.append(", ").append(intVar).append(" = (")
                            .append(getTextCheckNonEmpty(lowLimTextFields[i])).append(" .. ")
                            .append(getTextCheckNonEmpty(upLimTextFields[i])).append(")");
                }
            }
            var s = accuracyTextBox.getText().trim();
            if (!s.isEmpty()) text.append(", accuracy = ").append(s);
            s = iterationsTextBox.getText().trim();
            if (!s.isEmpty()) text.append(", iterations = ").append(s);
            text.append(")");
            switchNameOnOff("rounded");
            // End numerical integration
        } else {
            // Begin symbolic integration
            text.append(integrand);
            // Wrap integrand in nested integrals:
            for (int i = 0; i < 3; i++) {
                String intVar = intVarTextFields[i].getText().trim();
                if (!intVar.isEmpty()) {
                    text.insert(0, "int(");
                    final String lowLim = lowLimTextFields[i].getText(), upLim = upLimTextFields[i].getText();
                    final boolean indefInt;
                    if ((indefInt = lowLim.isEmpty()) ^ upLim.isEmpty()) {
                        RunREDUCE.errorMessageDialog("Integrate Template Error",
                                "The limits must be both empty or both specified.");
                        throw new EmptyFieldException();
                    }
                    text.append(", ").append(intVar);
                    if (!indefInt) text.append(", ").append(lowLim).append(", ").append(upLim);
                    text.append(")");
                }
            }
            switchCheckBoxesOnOff(algintCheckBox);
            // End symbolic integration
        }
        return text.toString();
    }
}
