package controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.nodes.ClassNode;
import model.nodes.UsecaseNode;

/**
 * Dialog to edit details of a node.
 *
 * @author Marco Jakob
 */
public class UsecaseEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField frequenceField;
    
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;


    private UsecaseNode node;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the classDiagramView.fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }


    /**
     * Sets the node to be edited in the dialog.
     *
     * @param node
     */
    public void setNode(UsecaseNode node) {
        this.node = node;

        titleField.setText(this.node.getTitle());
        frequenceField.setText(String.valueOf(this.node.getFrequence()));
        //operationsArea.setText(this.node.getOperations());
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public String getTitle() {
        return titleField.getText();
    }

    public int getfrequence(){
        return Integer.parseInt(frequenceField.getText());
    }

  //  public String getOperations() {
   //     return operationsArea.getText();
   // }

    public boolean hasTitledChanged(){
        if(this.node.getTitle() == null){
            return titleField.getText() != null;
        } else {
            return !this.node.getTitle().equals(titleField.getText());
        }
    }

    public boolean hasfrequenceChanged(){
        if(this.node.getFrequence() == -1){
            return frequenceField.getText() != null;
        } else {
            return this.node.getFrequence()!=(Integer.parseInt(frequenceField.getText()));
        }
    }

   

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    private void handleOk() {
        if (isInputValid()) {
            okClicked = true;
        }
    }

    /**
     * Called when the user clicks cancel.
     */

    private void handleCancel() {
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