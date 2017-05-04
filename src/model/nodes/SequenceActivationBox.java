package model.nodes;

import javafx.scene.shape.Rectangle;

public class SequenceActivationBox extends AbstractNode{

    public final String TYPE = "ActionBar";
 //   public final double DEFAULT_WIDTH = 100;

    protected SequenceObject diagram;


    public SequenceActivationBox(double x, double y, double width, double height){
        super(x, y, width, height);

//		this.width = DEFAULT_WIDTH;
        this.x = x;
        this.y = y;
        this.height = height<LIFEBOX_MIN_HEIGHT?LIFEBOX_MIN_HEIGHT:height;
        this.width = LIFEBOX_DEFAULT_WIDTH;

    }

    public SequenceActivationBox(SequenceObject diagram){
        this.diagram = diagram;
    }


    public void setHeight(double height){
        this.height= height<LIFEBOX_MIN_HEIGHT?LIFEBOX_MIN_HEIGHT:height;
        super.setHeight(height);
    }

    public void setWidth(double width){
        this.width= LIFEBOX_DEFAULT_WIDTH;
        super.setHeight(LIFEBOX_DEFAULT_WIDTH);
    }

    public void remoteSetHeight(double height){
        this.height = height<LIFEBOX_MIN_HEIGHT?LIFEBOX_MIN_HEIGHT:height;
        super.remoteSetHeight(height);
    }

    public void remoteSetWidth(double width) {
        this.width=LIFEBOX_DEFAULT_WIDTH;
        super.remoteSetWidth(LIFEBOX_DEFAULT_WIDTH);
    }



    public SequenceActivationBox(){

    }

    public double getHeight() {
        // TODO Auto-generated method stub
        return height;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return TYPE;
    }

    //????????
    @Override
    public AbstractNode copy() {
        // TODO Auto-generated method stub
        return null;
    }



    public Rectangle getRectangleHandle() {
        // TODO Auto-generated method stub
        return null;
    }

}

