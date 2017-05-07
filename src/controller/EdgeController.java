package controller;

import controller.dialog.EdgeEditDialogController;
import controller.dialog.MessageEditDialogController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.edges.*;
import util.commands.DirectionChangeEdgeCommand;
import util.commands.MoveMessageCommand;
import util.commands.ReplaceEdgeCommand;
import view.edges.AbstractEdgeView;
import view.edges.MessageEdgeView;
import view.nodes.AbstractNodeView;
import view.nodes.DeploymentNodeView;
import view.nodes.SequenceActivationBoxView;
import view.nodes.SequenceObjectView;

import java.io.IOException;

/**
 * 
 * Controller class for Edges.
 */
public class EdgeController {
    private double dragStartX, dragStartY;
    private Line dragLine;
    private Pane aDrawPane;
    private AbstractDiagramController diagramController;
    private AbstractNodeView startNodeView;
    private AbstractNodeView endNodeView;

    public EdgeController(Pane pDrawPane, AbstractDiagramController diagramController) {
        aDrawPane = pDrawPane;
        dragLine = new Line();
        dragLine.setStroke(Color.DARKGRAY);
        dragLine.setStrokeWidth(2);
        this.diagramController = diagramController;
    }

    public void onMousePressedOnNode(MouseEvent event) {
        dragStartX = event.getX() + ((AbstractNodeView) event.getSource()).getTranslateX();
        dragStartY = event.getY() + ((AbstractNodeView) event.getSource()).getTranslateY();
        startNodeView = (AbstractNodeView) event.getSource();
        aDrawPane.getChildren().add(dragLine);
    }

    public void onMousePressedOnCanvas(MouseEvent event){
        dragStartX = event.getX();
        dragStartY = event.getY();
         if(diagramController instanceof SequenceDiagramController){
            for(AbstractNodeView node : diagramController.getAllNodeViews()){
                double middleOfNode = (node.getX() + (node.getX() + node.getWidth())) /2;
                if(event.getX() > middleOfNode - 20 && event.getX() < middleOfNode + 20
                        && event.getY() > node.getY()){
                    startNodeView = node;
                }
            }
         }
       /* else if(diagramController instanceof PerformanceController){
            for(AbstractNodeView node : diagramController.getAllNodeViews()){
                double middleOfNode = (node.getX() + (node.getX() + node.getWidth())) /2;
                if(event.getX() > middleOfNode - 20 && event.getX() < middleOfNode + 20
                        && event.getY() > node.getY()){
                    startNodeView = node;
                }
            }
         }*/
        aDrawPane.getChildren().add(dragLine);
    }

    public void onMouseDragged(MouseEvent event){
        dragLine.setStartX(dragStartX);
        dragLine.setStartY(dragStartY);
        
        if(event.getSource() instanceof AbstractNodeView) {
            dragLine.setEndX(event.getX() + ((AbstractNodeView) event.getSource()).getTranslateX());
            dragLine.setEndY(event.getY() + ((AbstractNodeView) event.getSource()).getTranslateY());
        } else {
            dragLine.setEndX(event.getX());
            dragLine.setEndY(event.getY());
        }
    }

    /*
    * Used for sequence diagrams
    */
    public void onMouseReleasedSequence(){
        for(AbstractNodeView nodeView : diagramController.getAllNodeViews()){ //TODO implement getAllLifelines
//            if(nodeView instanceof SequenceObjectView && ((SequenceObjectView) nodeView).isOnLifeline(getEndPoint())){
//                endNodeView = nodeView;
//            } else
            	if(nodeView instanceof SequenceActivationBoxView  && ((SequenceActivationBoxView ) nodeView).isOnActivity(getEndPoint())){
                endNodeView = nodeView;
            }
        }
        if(endNodeView != null){
            if(startNodeView != null){
                MessageEdge edge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
                ((SequenceDiagramController)diagramController).createEdgeView(edge, startNodeView, endNodeView);
            } else {
                MessageEdge edge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(endNodeView));
                ((SequenceDiagramController)diagramController).createEdgeView(edge, null, endNodeView);
            }
        }
        finishCreateEdge();
    }
    
    /*
     * Used for performance
     */
     public void onMouseReleasedPerformance(){
    	 
         for(AbstractNodeView nodeView : diagramController.getAllNodeViews()){ //TODO implement getAllLifelines
             if(nodeView instanceof SequenceObjectView && ((SequenceObjectView) nodeView).isOnLifeline(getEndPoint())){
                 endNodeView = nodeView;
             }
             else if(nodeView.contains(getEndPoint())){
                 endNodeView = nodeView;
             }
         }
         
         if(endNodeView != null){
             if(startNodeView != null){
                 MessageEdge medge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
                 CompositionEdge aedge = new CompositionEdge(diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
                if (diagramController.getNodeMap().get(startNodeView).getType()=="LIFELINE")
                 ((PerformanceController)diagramController).createEdgeView(medge, startNodeView, endNodeView);
                else  
                ((PerformanceController)diagramController).createEdgeView(aedge, startNodeView, endNodeView);
             } /*else {
                 MessageEdge edge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(endNodeView));
                 ((PerformanceController)diagramController).createEdgeView(edge, null, endNodeView);
             }*/
         }
         finishCreateEdge();
     }
     
     
 public void onMouseReleasedDeployment(){
    	 
	 for(AbstractNodeView nodeView : diagramController.getAllNodeViews()){ //TODO implement getAllLifelines
//       if(nodeView instanceof SequenceObjectView && ((SequenceObjectView) nodeView).isOnLifeline(getEndPoint())){
//           endNodeView = nodeView;
//       } else
       	if(nodeView instanceof DeploymentNodeView){
           endNodeView = nodeView;
       }
   }
         
         if(endNodeView != null){
             if(startNodeView != null){
                 //MessageEdge medge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
                 ConnectEdge aedge = new ConnectEdge(diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
               
                // if (diagramController.getNodeMap().get(startNodeView).getType()=="LIFELINE")
                 //((PerformanceController)diagramController).createEdgeView(medge, startNodeView, endNodeView);
               // else  
                ((DeploymentController)diagramController).createEdgeView(aedge, startNodeView, endNodeView);
             } /*else {
                 MessageEdge edge = new MessageEdge(dragStartX, dragStartY, diagramController.getNodeMap().get(endNodeView));
                 ((PerformanceController)diagramController).createEdgeView(edge, null, endNodeView);
             }*/
         }
         finishCreateEdge();
     }
     
    /* public void onMouseReleasedPerformance(){
    	 for(AbstractNodeView nodeView : diagramController.getAllNodeViews()){
             if(nodeView.contains(getEndPoint())){
                 endNodeView = nodeView;
             }
         }
         if(endNodeView != null){
             AssociationEdge edge = new AssociationEdge(diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
             diagramController.createEdgeView(edge, startNodeView, endNodeView);
         }
         finishCreateEdge();
     }*/
    /*
    * Used for class diagrams
    */
    public void onMouseReleasedRelation() {
        for(AbstractNodeView nodeView : diagramController.getAllNodeViews()){
            if(nodeView.contains(getEndPoint())){
                endNodeView = nodeView;
            }
        }
        if(endNodeView != null){
            AssociationEdge edge = new AssociationEdge(diagramController.getNodeMap().get(startNodeView), diagramController.getNodeMap().get(endNodeView));
            diagramController.createEdgeView(edge, startNodeView, endNodeView);
        }
        finishCreateEdge();
    }

    private void finishCreateEdge() {
        dragLine.setStartX(0);
        dragLine.setStartY(0);
        dragLine.setEndX(0);
        dragLine.setEndY(0);
        aDrawPane.getChildren().remove(dragLine);
        startNodeView = null;
        endNodeView = null;
    }

    private double previousEdgeStartY;
    private double dragStart;
    protected void onMousePressDragEdge(MouseEvent event){
        diagramController.mode = AbstractDiagramController.Mode.DRAGGING_EDGE;
        previousEdgeStartY = event.getY();
        dragStartY = event.getY();
    }

    protected void onMouseDragEdge(MouseEvent event){
        double offsetY = (event.getY() - previousEdgeStartY)*(1/diagramController.drawPane.getScaleY());
        previousEdgeStartY = event.getY();
        for(AbstractEdgeView edgeView : diagramController.selectedEdges){
            if(edgeView instanceof MessageEdgeView){
                MessageEdge edge = (MessageEdge)edgeView.getRefEdge();
                edge.setStartY(edge.getStartY() + offsetY);
            }
        }
    }

    protected void onMouseReleaseDragEdge(MouseEvent event){
        diagramController.mode = AbstractDiagramController.Mode.NO_MODE;
        for(AbstractEdgeView edgeView : diagramController.selectedEdges) {
            diagramController.undoManager.add(new MoveMessageCommand((MessageEdge)edgeView.getRefEdge(), 0, edgeView.getStartY() - dragStartY));
        }
        previousEdgeStartY = 0;
    }

    public Point2D getStartPoint() {
        return new Point2D(dragStartX, dragStartY);
    }

    public Point2D getEndPoint() {
        return new Point2D(dragLine.getEndX(), dragLine.getEndY());
    }

    public boolean showEdgeEditDialog(AbstractEdge edge) {
    	if(!(diagramController instanceof DeploymentController)){
        try {
            // Load the classDiagramView.fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/edgeEditDialog.fxml"));
            AnchorPane dialog = loader.load();
            dialog.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(1), null)));
            dialog.setStyle("-fx-border-color: black");
            //Set location for "dialog".
            dialog.setLayoutX((edge.getStartNode().getTranslateX() + edge.getEndNode().getTranslateX())/2);
            dialog.setLayoutY((edge.getStartNode().getTranslateY() + edge.getEndNode().getTranslateY())/2);

            EdgeEditDialogController controller = loader.getController();
            controller.setEdge(edge);
            ChoiceBox directionBox = controller.getDirectionBox();
            ChoiceBox typeBox = controller.getTypeBox();
            controller.getOkButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //If no change in type of edge we just change direction of old edge
                    if(typeBox.getValue().equals(edge.getType()) || typeBox.getValue() == null){

                        edge.setStartMultiplicity(controller.getStartMultiplicity());
                        edge.setEndMultiplicity(controller.getEndMultiplicity());
                        if (directionBox.getValue() != null) {
                            diagramController.getUndoManager().add(new DirectionChangeEdgeCommand(edge, edge.getDirection(),
                                    AbstractEdge.Direction.valueOf(directionBox.getValue().toString())));
                            edge.setDirection(AbstractEdge.Direction.valueOf(directionBox.getValue().toString()));
                        }


                    } else { //Else we create a new one to replace the old
                        AbstractEdge newEdge = null;
                        if (typeBox.getValue().equals("Inheritance")) {
                            newEdge = new InheritanceEdge(edge.getStartNode(), edge.getEndNode());
                        } else if (typeBox.getValue().equals("Association") ) {
                            newEdge = new AssociationEdge(edge.getStartNode(), edge.getEndNode());
                        } else if (typeBox.getValue().equals("Aggregation")) {
                            newEdge = new AggregationEdge(edge.getStartNode(), edge.getEndNode());
                        } else if (typeBox.getValue().equals("Composition")) {
                            newEdge = new CompositionEdge(edge.getStartNode(), edge.getEndNode());
                        }
                        newEdge.setDirection(AbstractEdge.Direction.valueOf(directionBox.getValue().toString()));
                        newEdge.setStartMultiplicity(controller.getStartMultiplicity());
                        newEdge.setEndMultiplicity(controller.getEndMultiplicity());
                        replaceEdge(edge, newEdge);
                    }



                    aDrawPane.getChildren().remove(dialog);
                    diagramController.removeDialog(dialog);
                }
            });
            controller.getCancelButton().setOnAction(event -> {
                aDrawPane.getChildren().remove(dialog);
                diagramController.removeDialog(dialog);
            });
            diagramController.addDialog(dialog);
            aDrawPane.getChildren().add(dialog);

            return controller.isOkClicked();

        } catch (IOException e) {
            // Exception gets thrown if the classDiagramView.fxml file could not be loaded
            e.printStackTrace();
            return false;
        }
    	}
    	else{
    		
    		  // Load the classDiagramView.fxml file and create a new stage for the popup
       	 VBox group = new VBox();
            TextField input = new TextField();
            input.setText(edge.getTitle());
            TextField bandwidth = new TextField();
            if(edge.getBandwidth()>0)
            bandwidth.setText(String.valueOf(edge.getBandwidth()));
            TextField netdelay = new TextField();
            if(edge.getNetdelay()>0)
            netdelay.setText(String.valueOf(edge.getNetdelay()));
            TextField cost = new TextField();
            if(edge.getCost()>0)
            cost.setText(String.valueOf(edge.getCost()));


            Button okButton = new Button("Ok");
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    edge.setTitle(input.getText());
                    
                    edge.setBandwidth(Integer.valueOf(bandwidth.getText()));
                    
                    edge.setNetdelay(Double.valueOf(netdelay.getText()));
                  
                    edge.setCost(Integer.valueOf(cost.getText()));
                    
                    
                    aDrawPane.getChildren().remove(group);
                }
            });

            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    aDrawPane.getChildren().remove(group);
                }
            });

            Label label = new Label("Choose title");
            group.getChildren().add(label);
            group.getChildren().add(input);
            Label label1 = new Label("Input Bandwidth ");
            group.getChildren().add(label1);
            group.getChildren().add(bandwidth);
            Label label2 = new Label("Input Network Delay");
            group.getChildren().add(label2);
            group.getChildren().add(netdelay);
            Label label3 = new Label("Input Cost");
            group.getChildren().add(label3);
            group.getChildren().add(cost);
            HBox buttons = new HBox();
            buttons.getChildren().add(okButton);
            buttons.getChildren().add(cancelButton);
            buttons.setPadding(new Insets(15, 0, 0, 0));
            group.getChildren().add(buttons);
            group.setLayoutX(edge.getStartNode().getX()+edge.getStartNode().getWidth()+5);
            group.setLayoutY(edge.getStartNode().getY()+5);
            group.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(1), null)));
            group.setStyle("-fx-border-color: black");
            group.setPadding(new Insets(15, 12, 15, 12));
            aDrawPane.getChildren().add(group);
               
    		
    		return false;
    		
    		
    		
    	}
    }

    public boolean showMessageEditDialog(MessageEdge edge) {
    	//if(diagramController instanceof DeploymentController)
        try {
            // Load the classDiagramView.fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/messageEditDialog.fxml"));
            AnchorPane dialog = loader.load();
            dialog.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(1), null)));
            dialog.setStyle("-fx-border-color: black");
            //Set location for "dialog".
            if(edge.getStartNode() != null) {
                dialog.setLayoutX((edge.getStartNode().getTranslateX() + edge.getEndNode().getTranslateX()) / 2);
                dialog.setLayoutY((edge.getStartNode().getTranslateY() + edge.getEndNode().getTranslateY()) / 2);
            } else {
                dialog.setLayoutX((edge.getStartX() + edge.getEndNode().getTranslateX()) / 2);
                dialog.setLayoutY((edge.getStartY() + edge.getEndNode().getTranslateY()) / 2);
            }

            MessageEditDialogController controller = loader.getController();
            controller.setEdge(edge);
            ChoiceBox directionBox = controller.getDirectionBox();
            ChoiceBox typeBox = controller.getTypeBox();
            TextField titleTextField = controller.getTitleTextField();
            titleTextField.setText(edge.getTitle());
            TextField netTextField = controller.getNetworkTextField(); 
            if(edge.getNetwork()>0)
            	netTextField.setText(String.valueOf(edge.getNetwork()));
                 
            
            controller.getOkButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    edge.setMessageType((MessageEdge.MessageType)typeBox.getValue());
                    if (directionBox.getValue() != null) {
                        diagramController.getUndoManager().add(new DirectionChangeEdgeCommand(edge, edge.getDirection(),
                                AbstractEdge.Direction.valueOf(directionBox.getValue().toString())));
                        edge.setDirection(AbstractEdge.Direction.valueOf(directionBox.getValue().toString()));
                    }
                    if(titleTextField.getText() != null){
                        edge.setTitle(titleTextField.getText());
                    }
                    if(netTextField.getText() != null){
                        edge.setNetwork(Integer.valueOf(netTextField.getText())); 
                    }
                    
                    aDrawPane.getChildren().remove(dialog);
                    diagramController.removeDialog(dialog);
                }
            });
            controller.getCancelButton().setOnAction(event -> {
                aDrawPane.getChildren().remove(dialog);
                diagramController.removeDialog(dialog);
            });
            diagramController.addDialog(dialog);
            aDrawPane.getChildren().add(dialog);

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean replaceEdge(AbstractEdge oldEdge, AbstractEdge newEdge) {
        AbstractEdgeView oldEdgeView = null;
        for (AbstractEdgeView edgeView : diagramController.getAllEdgeViews()) {
            if (edgeView.getRefEdge().equals(oldEdge)) {
                oldEdgeView = edgeView;
                break;
            }
        }
        if (oldEdgeView == null) {
            return false;
        }
        diagramController.deleteEdgeView(oldEdgeView, null, true, false);

        AbstractEdgeView newEdgeView = diagramController.createEdgeView(newEdge, oldEdgeView.getStartNode(), oldEdgeView.getEndNode());

        diagramController.getUndoManager().add(
                new ReplaceEdgeCommand(oldEdge, newEdge, oldEdgeView, newEdgeView, diagramController, diagramController.getGraphModel())
        );

        return true;
    }
}
