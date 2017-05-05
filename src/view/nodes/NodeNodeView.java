package view.nodes;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.nodes.DeploymentNode;
import model.nodes.NodeNode;
import model.nodes.UsecaseNode;
import util.Constants;

import java.beans.PropertyChangeEvent;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class NodeNodeView extends AbstractNodeView implements NodeView {
	
    private StackPane stackPane;
	
	private  Rectangle rectangle,rectangle1,rectangle2;
	 private Text title;
	
	public NodeNodeView(NodeNode node) {
		super(node);
		 
		
		
		stackPane = new StackPane();
		rectangle = new Rectangle();
		rectangle1 = new Rectangle();
		rectangle2 = new Rectangle();
		
		title = new Text(node.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //TODO Ugly solution, hardcoded value.
        title.setWrappingWidth(node.getWidth() - 7);
		
		createRectangle();
		changeHeight(node.getHeight());
        changeWidth(node.getWidth());
		initLooks();
		
		 StackPane.setAlignment(title, Pos.TOP_CENTER);
	       // title.resize(circle.getRadius(), circle.getRadius());
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
        
    stackPane.getChildren().addAll(rectangle,rectangle1,rectangle2,title);
        
        this.getChildren().add(stackPane);
        this.setTranslateX(node.getTranslateX());
        this.setTranslateY(node.getTranslateY());
	}
	
	private void createRectangle(){
		NodeNode node = (NodeNode) getRefNode();
		rectangle = new Rectangle();
		rectangle.setHeight(node.getHeight());
		rectangle.setWidth(node.getWidth()-15);
	   
		rectangle.setX(getRefNode().getX()+15);
		rectangle.setY(getRefNode().getY());
		
		 StackPane.setAlignment(rectangle1, javafx.geometry.Pos.CENTER_LEFT);
	        StackPane.setAlignment(rectangle1, Pos.CENTER_LEFT);
		rectangle1.setHeight(15);
		rectangle1.setWidth(30);
	     
		//rectangle1.setX(getRefNode().getX());
		//rectangle1.setY(getRefNode().getY()+node.getHeight()/3);
		
		StackPane.setMargin(rectangle1, new Insets(node.getHeight()/4, 0.0, 0.0, 0.0));
		
		 StackPane.setAlignment(rectangle2, javafx.geometry.Pos.CENTER_LEFT);
	    StackPane.setAlignment(rectangle2, Pos.CENTER_LEFT);
		rectangle2.setHeight(15);
		rectangle2.setWidth(30);
	     
		//rectangle1.setX(getRefNode().getX());
		//rectangle1.setY(getRefNode().getY()+node.getHeight()/3);
		
		StackPane.setMargin(rectangle2, new Insets(0.0, 0.0, node.getHeight()/4, 0.0));
    	
    }
	
	
	 private void initLooks(){
		 StackPane.setAlignment(rectangle, javafx.geometry.Pos.CENTER_RIGHT);
	        StackPane.setAlignment(rectangle, Pos.CENTER_RIGHT);
	        
	       
	        
	        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
	      
	        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
	        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	        
	        rectangle1.setFill(javafx.scene.paint.Color.DODGERBLUE);
		      
	        rectangle1.setStroke(javafx.scene.paint.Color.BLACK);
	        rectangle1.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	       
	        
	        rectangle2.setFill(javafx.scene.paint.Color.DODGERBLUE);
		      
	        rectangle2.setStroke(javafx.scene.paint.Color.BLACK);
	        rectangle2.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	       
	    }
	 private void changeHeight(double height){
		 NodeNode node = (NodeNode) getRefNode();
	        setHeight(height);
	       
	        stackPane.setPrefHeight(height);
	        rectangle.setHeight(height);
	        
	        StackPane.setMargin(rectangle1, new Insets(node.getHeight()/4, 0.0, 0.0, 0.0));
	        
	        StackPane.setMargin(rectangle2, new Insets(0.0, 0.0, node.getHeight()/4, 0.0));
	        
	    }

	    private void changeWidth(double width){
	        setWidth(width);
	       
	        
	        stackPane.setPrefWidth(width);
	        rectangle.setWidth(width-15);
	       
	    }
	    
	    private void setLayout() {
	    	 UsecaseNode node = (UsecaseNode) getRefNode();
	    	 stackPane.setLayoutX(node.getX());
	    	
		        stackPane.setLayoutY(node.getY());
	    	
	    }

	    public void setStrokeWidth(double scale) {
	    	rectangle.setStrokeWidth(scale);
	        
	    }

	    public void setFill(Paint p) {
	    	rectangle.setFill(p);
	    }    

	    public void setStroke(Paint p) {
	    	rectangle.setStroke(p);
	       
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


	    public Bounds getBounds() {
			// TODO Auto-generated method stub
			 return rectangle.getBoundsInParent();
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
