package view.nodes;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.nodes.DeploymentNode;
import model.nodes.LinkedDeploymentNode;
import model.nodes.LinkedSequenceNode;
import model.nodes.NodeNode;
import model.nodes.UsecaseNode;
import util.Constants;

import java.beans.PropertyChangeEvent;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LinkedSequenceNodeView extends AbstractNodeView implements NodeView {
	
    private StackPane stackPane;
	
	private  Rectangle rectangle;
	 
	 private Text title1;
	 private Label title;
	 private Label delay;
	 private Image icon ;
	 
	public LinkedSequenceNodeView(LinkedSequenceNode node) {
		super(node);
		 
		
		
		stackPane = new StackPane();
		rectangle = new Rectangle();
		title=new Label();
		delay=new Label();
		icon = new Image("/icons/linkedsequence.png");
		
        
        title1 = new Text(node.getTitle());
        title1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //TODO Ugly solution, hardcoded value.
        title1.setWrappingWidth(node.getWidth() - 7);
        title1.setText("Sequence");
        
        
        
        initTitle();
		createRectangle();
		changeHeight(node.getHeight());
        changeWidth(node.getWidth());
		initLooks();
		
	
	        
	        StackPane.setAlignment(title1, Pos.TOP_CENTER);
		       // title.resize(circle.getRadius(), circle.getRadius());
		        if(node.getTitle() != null) {
		            title1.setText(node.getTitle());
		        }
        
       stackPane.getChildren().addAll(rectangle,title,title1,delay);
      
        
        this.getChildren().add(stackPane);
        this.setTranslateX(node.getTranslateX());
        this.setTranslateY(node.getTranslateY());
	}
	
	private void createRectangle(){
		LinkedSequenceNode node = (LinkedSequenceNode) getRefNode();
		rectangle = new Rectangle();
		rectangle.setHeight(node.getHeight());
		rectangle.setWidth(node.getWidth());
	        //StackPane.setAlignment(body, Pos.BOTTOM_LEFT);
	    //    top.setX(getRefNode().getX());
	     //   top.setY(getRefNode().getY());
	      //  top.setFill(Color.LIGHTSKYBLUE);
	       // top.setStroke(Color.BLACK);
	       
		rectangle.setX(getRefNode().getX());
		rectangle.setY(getRefNode().getY());
    	
    }
	
	 private void initTitle(){
	        LinkedSequenceNode node = (LinkedSequenceNode) getRefNode();

	        title = new Label();
	        title.setFont(Font.font("Verdana", 12));
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
	        title.setAlignment(Pos.CENTER);
	        
	        StackPane.setMargin(delay, new Insets(rectangle.getHeight()/4 , 0.0, 0.0, 0.0));
	        
	        delay = new Label();
	        delay.setFont(Font.font("Verdana", 12));
	        if(node.getDelay()>0) {
	        	delay.setText(String.valueOf(node.getDelay()));
	        }
	        delay.setAlignment(Pos.TOP_CENTER);
	        
	       StackPane.setMargin(title, new Insets( 0.0, 0.0,rectangle.getHeight()/4, 0.0));
	        
	        
	    }
	
	
	 private void initLooks(){
		 StackPane.setAlignment(rectangle, javafx.geometry.Pos.CENTER);
	        StackPane.setAlignment(rectangle, Pos.CENTER);
	        
	        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
	      
	        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
	        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	        
	        StackPane.setAlignment(title, Pos.CENTER);
	        StackPane.setAlignment(delay, Pos.CENTER);
	       
	       
	    }
	 private void changeHeight(double height){
	        setHeight(height);
	       
	        stackPane.setPrefHeight(height);
	        rectangle.setHeight(height);
			
            
	        StackPane.setMargin(delay, new Insets(rectangle.getHeight()/4 , 0.0, 0.0, 0.0));
	        
	      
	        
	       StackPane.setMargin(title, new Insets( 0.0, 0.0,rectangle.getHeight()/4, 0.0));
	        
	       StackPane.setAlignment(title1, Pos.TOP_CENTER);
	    }

	    private void changeWidth(double width){
	        setWidth(width);
	       
	        StackPane.setAlignment(title1, Pos.TOP_CENTER);
	        stackPane.setPrefWidth(width);
	        rectangle.setWidth(width);
	       
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
	        }else if (evt.getPropertyName().equals(Constants.changeLinkedSequenceDelay)) {
	            delay.setText((String) evt.getNewValue());
	        }
	    }

}
