package view.nodes;

import java.beans.PropertyChangeEvent;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.scene.control.Label;
import model.nodes.AbstractNode;
import model.nodes.ActorNode;
import model.nodes.SequenceObject;
import util.Constants;

public class ActorNodeView extends AbstractNodeView implements NodeView {
     
	private StackPane stackPane;
	private Line line;
	private  Circle circle;
	private  Line line0;
	private  Line line1;
	private  Line line2;
	 
	 private Label title;
	    
	
	/*public ActorNodeView(ActorNode node) {
		super(node);
		 
		title = new Text(node.getTitle());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //TODO Ugly solution, hardcoded value.
        title.setWrappingWidth(node.getWidth() - 7);
		
		double x=node.getX();
		double y=node.getY();
		double height=node.getHeight();
		double width=node.getWidth();
		  stackPane = new StackPane();
	        line = new Line();
	        circle = new Circle();
	        line0 = new Line();
	        line1 = new Line();
	        line2 = new Line();
	        
	        
	        stackPane.setLayoutX(x);
	        stackPane.setLayoutY(y);
	        stackPane.setPrefHeight(height);
	        stackPane.setPrefWidth(width);
	        
	      
	       
	        
	       // double radius = Math.min(height/6,width/4);
	        double radius = height/6;
	        
	        stackPane.setAlignment(circle, javafx.geometry.Pos.TOP_CENTER);
	        stackPane.setAlignment(circle, Pos.TOP_CENTER);
	        circle.setFill(javafx.scene.paint.Color.DODGERBLUE);
	        circle.setRadius(radius);
	        circle.setStroke(javafx.scene.paint.Color.BLACK);
	        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	        
	        
	        StackPane.setAlignment(title, Pos.TOP_CENTER);
	       // title.resize(circle.getRadius(), circle.getRadius());
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
	        
	        line.setEndX(x+width/2);
	        line.setEndY(y+height/3);
	        line.setStartX(x+width/2);
	        line.setStartY(y+2*height/3);
	        
	        line0.setEndX(x+width);
	        line0.setEndY(height/2);
	        line0.setStartX(x);
	        line0.setStartY(height/2);
	        
	        
	        StackPane.setAlignment(line1, javafx.geometry.Pos.BOTTOM_LEFT);
	        StackPane.setAlignment(line1, Pos.BOTTOM_LEFT);
	        line1.setEndX(x+width/2);
	        line1.setEndY(y+2*height/3);
	        line1.setStartX(x);
	        line1.setStartY(y+height);
	        
	        
	        StackPane.setAlignment(line2, javafx.geometry.Pos.BOTTOM_RIGHT);
	        StackPane.setAlignment(line2, Pos.BOTTOM_RIGHT);
	        line2.setEndX(x+width/2);
	        line2.setEndY(y+2*height/3);
	        line2.setStartX(x+width);
	        line2.setStartY(y+height);
	        
	        this.getChildren().add(stackPane);
	       // this.setTranslateX(node.getTranslateX());
	        //this.setTranslateY(node.getTranslateY());
	        
	        stackPane.getChildren().add(title);
	        stackPane.getChildren().add(circle);
	        stackPane.getChildren().add(line);
	        stackPane.getChildren().add(line0);
	        stackPane.getChildren().add(line1);
	        stackPane.getChildren().add(line2);
	       
	        
	        
	        
	        
	       //this.setTranslateX(node.getTranslateX());
		   //this.setTranslateY(node.getTranslateY());
		   // createHandles();
	        
		// TODO Auto-generated constructor stub
	}*/

	
	/*private void createHandles(){

        shortHandleLine = new Line();
        longHandleLine = new Line();

        shortHandleLine.startXProperty().bind(circle.radiusProperty().subtract(7));
        shortHandleLine.startYProperty().bind(circle.radiusProperty().subtract(3));
        shortHandleLine.endXProperty().bind(circle.radiusProperty().subtract(3));
        shortHandleLine.endYProperty().bind(circle.radiusProperty().subtract(7));
        longHandleLine.startXProperty().bind(circle.radiusProperty().subtract(15));
        longHandleLine.startYProperty().bind(circle.radiusProperty().subtract(3));
        longHandleLine.endXProperty().bind(circle.radiusProperty().subtract(3));
        longHandleLine.endYProperty().bind(circle.radiusProperty().subtract(15));

        this.getChildren().addAll(shortHandleLine, longHandleLine);
    }*/
	    
	    public ActorNodeView(ActorNode node) {
			super(node);
			 stackPane = new StackPane();
		        line = new Line();
		        circle =new Circle();
		        line0 = new Line();
		        line1 = new Line();
		        line2 = new Line();
		        
		        
		        title = new Label();
	       
			
		
			  
		        
		        
		        //stackPane.setLayoutX(x);
		       //stackPane.setLayoutY(y);
		       // stackPane.setPrefHeight(height);
		       // stackPane.setPrefWidth(width);
	        
	       // createPane();
	        initTitle();
		        createCircle();
		        createLines();
		        changeHeight(node.getHeight());
		        changeWidth(node.getWidth());
		        initLooks();
		       
		        stackPane.getChildren().addAll(circle,line,line0,line1,line2,title);
		        
		        
		        
		        //StackPane.setAlignment(title, Pos.TOP_CENTER);
		       // StackPane.setMargin(title, new Insets(0.0, node.getWidth()/2, 0.0, 0.0));
		       // title.resize(circle.getRadius(), circle.getRadius());
		        if(node.getTitle() != null) {
		            title.setText(node.getTitle());
		        }
		        
		       
		        
		       
		        
		        this.getChildren().add(stackPane);
		        
		        this.setTranslateX(node.getTranslateX());
		        this.setTranslateY(node.getTranslateY());
		       
		        
		}
	    
	  
	  
	    
	    private void createCircle(){
	    	ActorNode node = (ActorNode) getRefNode();
	    	 circle.setRadius(Math.min(node.getWidth()/2,node.getHeight()/6));
	         //changeRadius(node.getWidth()/6);
	         circle.setCenterX(node.getX());
	         circle.setCenterY(node.getY());
	    	
	    }
	    
	    private void createLines(){
	    	ActorNode node = (ActorNode) getRefNode();
	    	  double x=node.getX();
			  double y=node.getY();
			  double height=node.getHeight();
			  double width=node.getWidth();
			  
			  
			    line.setEndX(x+width/2);
		        line.setEndY(y+height/3);
		        line.setStartX(x+width/2);
		        line.setStartY(y+2*height/3);
		        
		        line0.setEndX(x+width);
		        line0.setEndY(height/2);
		        line0.setStartX(x);
		        line0.setStartY(height/2);
		        
		        
		        stackPane.setAlignment(line1, javafx.geometry.Pos.BOTTOM_LEFT);
		        stackPane.setAlignment(line1, Pos.BOTTOM_LEFT);
		      
		        line1.setEndX(x+width/2);
		        line1.setEndY(y+2*height/3);
		        line1.setStartX(x);
		        line1.setStartY(y+height);
		        

		        stackPane.setAlignment(line2, javafx.geometry.Pos.BOTTOM_RIGHT);
		        stackPane.setAlignment(line2, Pos.BOTTOM_RIGHT);
		        line2.setEndX(x+width/2);
		        line2.setEndY(y+2*height/3);
		        line2.setStartX(x+width);
		        line2.setStartY(y+height);
		        
	    }
	    
	    private void initLooks(){
	        StackPane.setAlignment(circle, javafx.geometry.Pos.TOP_CENTER);
	        StackPane.setAlignment(circle, Pos.TOP_CENTER);
	        circle.setFill(javafx.scene.paint.Color.DODGERBLUE);
	        
	        circle.setStroke(javafx.scene.paint.Color.BLACK);
	        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	        
	        StackPane.setAlignment(title, Pos.TOP_CENTER);
	        
	       
	    }
	    
	    private void initTitle(){
	        ActorNode node = (ActorNode) getRefNode();

	        title = new Label();
	        title.setFont(Font.font("Verdana", 12));
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
	        title.setAlignment(Pos.CENTER);
	        
	        StackPane.setMargin(title, new Insets(circle.getRadius()-10, 0.0, 0.0, 0.0));
	    }
	
	 public void setStrokeWidth(double scale) {
	        circle.setStrokeWidth(scale);
	        
	    }

	    public void setFill(Paint p) {
	        circle.setFill(p);
	    }    

	    public void setStroke(Paint p) {
	        circle.setStroke(p);
	       
	    }
	    private void changeHeight(double height){
	        setHeight(height);
	        stackPane.setPrefHeight(height);
	        
	        StackPane.setMargin(title, new Insets(circle.getRadius()-10, 0.0, 0.0, 0.0));
	        
	        ActorNode node = (ActorNode) getRefNode();
	    	 circle.setRadius(Math.min(node.getWidth()/2,node.getHeight()/6));
	        
	        
	    	  double x=node.getX();
			  double y=node.getY();
			  
	        
	      
	        line.setEndY(y+height/3);
	        
	        line.setStartY(y+2*height/3);
	        
	        
	        line0.setEndY(height/2);
	       
	        line0.setStartY(height/2);
	        
	        
	        stackPane.setAlignment(line1, javafx.geometry.Pos.BOTTOM_LEFT);
	        stackPane.setAlignment(line1, Pos.BOTTOM_LEFT);
	      
	       
	        line1.setEndY(y+2*height/3);
	        
	        line1.setStartY(y+height);
	        

	        stackPane.setAlignment(line2, javafx.geometry.Pos.BOTTOM_RIGHT);
	        stackPane.setAlignment(line2, Pos.BOTTOM_RIGHT);
	        
	        line2.setEndY(y+2*height/3);
	      
	        line2.setStartY(y+height);
	        
	    }

	    private void changeWidth(double width){
	        setWidth(width);
	        stackPane.setPrefWidth(width);
	        
	        StackPane.setMargin(title, new Insets(circle.getRadius()-10, 0.0, 0.0, 0.0));
	        
	        title.setMaxWidth(circle.getRadius()*2);
	        title.setPrefWidth(circle.getRadius()*2);
	        
	        ActorNode node = (ActorNode) getRefNode();
	    	 circle.setRadius(Math.min(node.getWidth()/2,node.getHeight()/6));
	    	  double x=node.getX();
			  double y=node.getY();
			 
	        
	        line.setEndX(x+width/2);
	       
	        line.setStartX(x+width/2);
	        
	        
	        line0.setEndX(x+width);
	        
	        line0.setStartX(x);
	        
	        
	        
	        stackPane.setAlignment(line1, javafx.geometry.Pos.BOTTOM_LEFT);
	        stackPane.setAlignment(line1, Pos.BOTTOM_LEFT);
	      
	        line1.setEndX(x+width/2);
	        
	        line1.setStartX(x);
	      
	        

	        stackPane.setAlignment(line2, javafx.geometry.Pos.BOTTOM_RIGHT);
	        stackPane.setAlignment(line2, Pos.BOTTOM_RIGHT);
	        line2.setEndX(x+width/2);
	       
	        line2.setStartX(x+width);
	        
	       
	    }
	    
	    private void changeRadius(double radius){
	        setRadius(radius);
	       
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
		 return circle.getBoundsInParent();
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
	        } 
	        else if (evt.getPropertyName().equals(Constants.changeNodeTitle)) {
	            title.setText((String) evt.getNewValue());

	        }
	    }
}
