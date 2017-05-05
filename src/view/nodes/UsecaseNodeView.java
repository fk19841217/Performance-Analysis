package view.nodes;

import java.beans.PropertyChangeEvent;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.nodes.ActorNode;
import model.nodes.UsecaseNode;
import util.Constants;

public class UsecaseNodeView   extends AbstractNodeView implements NodeView {
	
	private StackPane stackPane;
	
	private  Ellipse ellipse;
	 private Label title;
	 private Label frequen;
	
	
	/*public UsecaseNodeView(UsecaseNode node) {
		super(node);
		 
		
		double x=node.getX();
		double y=node.getY();
		double height=node.getHeight();
		double width=node.getWidth();
		stackPane = new StackPane();
		ellipse = new Ellipse();
		
		stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
        stackPane.setPrefHeight(height);
        stackPane.setPrefWidth(width);
        
        
        StackPane.setAlignment(ellipse, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(ellipse, Pos.CENTER);
        ellipse.setFill(javafx.scene.paint.Color.DODGERBLUE);
        ellipse.setRadiusX(width/2);
        ellipse.setRadiusY(height/2);
        ellipse.setStroke(javafx.scene.paint.Color.BLACK);
        ellipse.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        
        stackPane.getChildren().add(ellipse);
        
        this.getChildren().add(stackPane);
		
		
	}*/

	public UsecaseNodeView(UsecaseNode node) {
		super(node);
		 
		
		
		stackPane = new StackPane();
		ellipse = new Ellipse();
		
		
		title = new Label();
		frequen =new Label();
		
		initTitle();
		createEllipse();
		//setLayout();
		//stackPane.setLayoutX(node.getX());
       // stackPane.setLayoutY(node.getY());
		changeHeight(node.getHeight());
        changeWidth(node.getWidth());
        
        initLooks();
     
        
        stackPane.getChildren().add(ellipse);
        stackPane.getChildren().add(title);
        stackPane.getChildren().add(frequen);
        
       
        if(node.getTitle() != null) {
            title.setText(node.getTitle());
        }
        
        if(node.getFrequence() >0  ) {
            frequen.setText(String.valueOf(node.getFrequence()));
        }
      
        
        this.getChildren().add(stackPane);
        
       this.setTranslateX(node.getTranslateX());
       this.setTranslateY(node.getTranslateY());
		
		
	}
	
	private void createEllipse(){
    	UsecaseNode node = (UsecaseNode) getRefNode();
    	 ellipse.setRadiusX(node.getWidth()/2);
         ellipse.setRadiusY(node.getHeight()/2);
    	
    }
	
	 private void initLooks(){
		    StackPane.setAlignment(ellipse, javafx.geometry.Pos.CENTER);
	        StackPane.setAlignment(ellipse, Pos.CENTER);
	        ellipse.setFill(javafx.scene.paint.Color.DODGERBLUE);
	       
	        ellipse.setStroke(javafx.scene.paint.Color.BLACK);
	        ellipse.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
	        
	        StackPane.setAlignment(title, Pos.CENTER);
	        StackPane.setAlignment(frequen, Pos.CENTER);
	       
	    }
	 
	 private void initTitle(){
	        UsecaseNode node = (UsecaseNode) getRefNode();

	        title = new Label();
	        title.setFont(Font.font("Verdana", 12));
	        if(node.getTitle() != null) {
	            title.setText(node.getTitle());
	        }
	        title.setAlignment(Pos.CENTER);
	        
	        StackPane.setMargin(frequen, new Insets(ellipse.getRadiusY()/2 , 0.0, 0.0, 0.0));
	        
	        frequen = new Label();
	        frequen.setFont(Font.font("Verdana", 12));
	        if(node.getFrequence()>0) {
	        	frequen.setText(String.valueOf(node.getFrequence()));
	        }
	        frequen.setAlignment(Pos.TOP_CENTER);
	        
	       StackPane.setMargin(title, new Insets( 0.0, 0.0, ellipse.getRadiusY()/2, 0.0));
	        
	        
	    }
	 
	 private void changeHeight(double height){
	        setHeight(height);
	       
	        stackPane.setPrefHeight(height);
	       
	        UsecaseNode node = (UsecaseNode) getRefNode();
	    	
	         ellipse.setRadiusY(node.getHeight()/2);
	        
	        
	        StackPane.setMargin(frequen, new Insets(ellipse.getRadiusY()/2 , 0.0, 0.0, 0.0));
	        StackPane.setMargin(title, new Insets( 0.0, 0.0,ellipse.getRadiusY()/2, 0.0));
	        
	    }

	    private void changeWidth(double width){
	        setWidth(width);
	       
	        
	        stackPane.setPrefWidth(width);
	        UsecaseNode node = (UsecaseNode) getRefNode();
	        ellipse.setRadiusX(node.getWidth()/2);
	       
	    }
	    
	    private void setLayout() {
	    	 UsecaseNode node = (UsecaseNode) getRefNode();
	    	 stackPane.setLayoutX(node.getX());
	    	 stackPane.setLayoutY(node.getY());
	    	
	    }

	    public void setStrokeWidth(double scale) {
	    	ellipse.setStrokeWidth(scale);
	        
	    }

	    public void setFill(Paint p) {
	    	ellipse.setFill(p);
	    }    

	    public void setStroke(Paint p) {
	    	ellipse.setStroke(p);
	       
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
			 return ellipse.getBoundsInParent();
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
	        } else if (evt.getPropertyName().equals(Constants.changeUsecaseFrequence)) {
				frequen.setText((String) evt.getNewValue());
	        } 
	        
	    }
}
