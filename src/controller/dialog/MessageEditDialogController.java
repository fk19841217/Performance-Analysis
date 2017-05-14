package controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.edges.AbstractEdge;
import model.edges.MessageEdge;

public class MessageEditDialogController {

    @FXML
    private ChoiceBox directionBox;
    @FXML
    private ChoiceBox typeBox;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField networkTextField;
    @FXML
    private ChoiceBox exlowerfailport;
    @FXML
    private ChoiceBox exuperfailport;
    
    private AbstractEdge edge;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the classDiagramView.fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public ChoiceBox getDirectionBox() {
        return directionBox;
    }

    public ChoiceBox getexlowerfailport() {
        return exlowerfailport;
    }
    
    public ChoiceBox getexuperfailport() {
        return exuperfailport;
    }
    
    public ChoiceBox getTypeBox() {
        return typeBox;
    }
    

    public TextField getTitleTextField(){
        return titleTextField;
    }
    
    public TextField getNetworkTextField(){
        return networkTextField;
    }
    
   

    public void setEdge(MessageEdge edge) {
        this.edge = edge;
        //TODO Hardcoded values. Where to put them?
        typeBox.getItems().setAll(MessageEdge.MessageType.values());
        typeBox.getSelectionModel().select(edge.getMessageType());
        directionBox.getItems().setAll(AbstractEdge.Direction.START_TO_END, AbstractEdge.Direction.END_TO_START);
        directionBox.getSelectionModel().select(edge.getDirection());
        exlowerfailport.getItems().setAll("0.01","0.02","0.03");
        exlowerfailport.getSelectionModel().select(String.valueOf(edge.getLowfail()));
        exuperfailport.getItems().setAll("0.03","0.04","0.05");
        exuperfailport.getSelectionModel().select(String.valueOf(edge.getUpfail()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        return true; //Use if we want to
    }
}