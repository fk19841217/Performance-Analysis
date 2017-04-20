package view.nodes;


import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.nodes.ActorNode;
import model.nodes.DeploymentNode;
import model.nodes.PackageNode;
import util.Constants;

import java.beans.PropertyChangeEvent;

/**
 * Visual representation of PackageNode-class.
 */
public class DeploymentNodeView extends AbstractNodeView {

    private DeploymentNode refNode;
    private Text title;
    private VBox container;
    private StackPane stackPane;
   
    private Rectangle body;
    
    private Line line;

	private  Line line0;
	private  Line line1;
	private  Line line2;
	private  Line line3;
	
  

    Line shortHandleLine;
    Line longHandleLine;

    public DeploymentNodeView(DeploymentNode node) {
        super(node);
        refNode = node;
       
        container = new VBox();
        stackPane = new StackPane();
        
        
        
     
        line = new Line();
       
        line0 = new Line();
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        
        
        title = new Text(node.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //TODO Ugly solution, hardcoded value.
        title.setWrappingWidth(node.getWidth() - 7);

        //container.setSpacing(0);

        createRectangles();
       createLines();
       changeHeight(node.getHeight());
       changeWidth(node.getWidth());
       initLooks();

        
        stackPane.getChildren().addAll(body, title,line,line1,line0,line2,line3);
        //container.getChildren().addAll(bodyStackPane);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
	       // title.resize(circle.getRadius(), circle.getRadius());
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
	        
       
        //setTitleSize();
        this.getChildren().add(stackPane);
        this.setTranslateX(node.getTranslateX());
        this.setTranslateY(node.getTranslateY());

       // createHandles();
    }

   

    private void createRectangles(){
    	 
    	DeploymentNode node = (DeploymentNode) getRefNode();
    	
        body = new Rectangle();
        body.setHeight(node.getHeight()-10);
        body.setWidth(node.getWidth()-10);
        //StackPane.setAlignment(body, Pos.BOTTOM_LEFT);
    //    top.setX(getRefNode().getX());
     //   top.setY(getRefNode().getY());
      //  top.setFill(Color.LIGHTSKYBLUE);
       // top.setStroke(Color.BLACK);
       
        body.setX(getRefNode().getX());
        body.setY(getRefNode().getY());
       
        
       

    }
    
    private void createLines(){
    	
    	 DeploymentNode node = (DeploymentNode) getRefNode();
 	      double x=node.getX();
		  double y=node.getY();
		  double height=node.getHeight();
		  double width=node.getWidth();
		  
		  StackPane.setAlignment(line, Pos.TOP_LEFT);
	        line.setEndX(x);
	        line.setEndY(y+10);
	        line.setStartX(x+10);
	        line.setStartY(y);
	        
	        StackPane.setAlignment(line0, Pos.TOP_RIGHT);
	        line0.setEndX(x+10);
	        line0.setEndY(y);
	        line0.setStartX(x+width);
	        line0.setStartY(y);
	        
	        StackPane.setAlignment(line1, Pos.TOP_RIGHT);
	        line1.setEndX(x+width-10);
	        line1.setEndY(y+10);
	        line1.setStartX(x+width);
	        line1.setStartY(y);
	        
	        StackPane.setAlignment(line2, Pos.TOP_RIGHT);
	        line2.setEndX(x+width);
	        line2.setEndY(y+height-10);
	        line2.setStartX(x+width);
	        line2.setStartY(y);
	        
	      
	        StackPane.setAlignment(line3, Pos.BOTTOM_RIGHT);
	        line3.setEndX(x+width);
	        line3.setEndY(y+height-10);
	        line3.setStartX(x+width-10);
	        line3.setStartY(y+height);
	        
    	
    }
    
    private void initLooks(){
        StackPane.setAlignment(body, javafx.geometry.Pos.BOTTOM_LEFT);
        StackPane.setAlignment(body, Pos.BOTTOM_LEFT);
        body.setFill(Color.LIGHTSKYBLUE);
       
        body.setStroke(javafx.scene.paint.Color.BLACK);
        body.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        
      
    }

    /*
    private void createHandles(){

        shortHandleLine = new Line();
        longHandleLine = new Line();

        shortHandleLine.startXProperty().bind(body.widthProperty().subtract(7));
     //   shortHandleLine.startYProperty().bind(body.heightProperty().add(top.heightProperty().subtract(3)));
        shortHandleLine.endXProperty().bind(body.widthProperty().subtract(3));
       // shortHandleLine.endYProperty().bind(body.heightProperty().add(top.heightProperty().subtract(7)));
        longHandleLine.startXProperty().bind(body.widthProperty().subtract(15));
       // longHandleLine.startYProperty().bind(body.heightProperty().add(top.heightProperty().subtract(3)));
        longHandleLine.endXProperty().bind(body.widthProperty().subtract(3));
        //longHandleLine.endYProperty().bind(body.heightProperty().add(top.heightProperty().subtract(15)));

        this.getChildren().addAll(shortHandleLine, longHandleLine);
    }*/

    @Override
    public boolean contains(double x, double y) {
        //If there is a childNode inside this PackageNode, we should return false.
        if (refNode.findNode(new Point2D(x, y)) != null) {
            return false;
        }
        return super.contains(x, y);
    }

    public void setStrokeWidth(double scale) {
       // top.setStrokeWidth(scale);
        body.setStrokeWidth(scale);
    }

    public void setFill(Paint p) {
        //top.setFill(p);
        body.setFill(p);
    }

    public void setStroke(Paint p) {
    //    top.setStroke(p);
        body.setStroke(p);
    }

    public void setSelected(boolean selected){
        if(selected){
            setStroke(Constants.selected_color);
            setStrokeWidth(2);
        } else {
            setStroke(Color.BLACK);
            setStrokeWidth(1);
        }
    }

    public Bounds getBounds(){
        return body.getBoundsInParent();
    }

    private void changeHeight(double height){
        setHeight(height);
        stackPane.setPrefHeight(height);
      //  top.setHeight(Math.min(TOP_MAX_HEIGHT, height*TOP_HEIGHT_RATIO));
        body.setHeight(height - 10);        
        StackPane.setAlignment(line2, Pos.TOP_RIGHT);
        line2.setEndX(stackPane.getLayoutX()+stackPane.getWidth());
        line2.setEndY(stackPane.getLayoutY()+height-10);
        line2.setStartX(stackPane.getLayoutX()+stackPane.getWidth());
        line2.setStartY(stackPane.getLayoutY());
    }

    private void changeWidth(double width){
        setWidth(width);
        stackPane.setPrefWidth(width);
       
        //top.setWidth(Math.min(TOP_MAX_WIDTH, width*TOP_WIDTH_RATIO));
      body.setWidth(width-10);
      StackPane.setAlignment(line0, Pos.TOP_RIGHT);
      line0.setEndX(stackPane.getLayoutX()+10);
      line0.setEndY(stackPane.getLayoutY());
      line0.setStartX(stackPane.getLayoutX()+width);
      line0.setStartY(stackPane.getLayoutY());
      //  title.setWrappingWidth(width - 1);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (evt.getPropertyName().equals(Constants.changeNodeX)) {
            setX((double) evt.getNewValue());
        } else if (evt.getPropertyName().equals(Constants.changeNodeY)) {
            setY((double) evt.getNewValue());
        } else if (evt.getPropertyName().equals(Constants.changeNodeWidth)) {
            changeWidth((double) evt.getNewValue());
        } else if (evt.getPropertyName().equals(Constants.changeNodeHeight)) {
            changeHeight((double) evt.getNewValue());
        } else if (evt.getPropertyName().equals(Constants.changeNodeTitle)) {
            title.setText((String) evt.getNewValue());

        }
    }
}