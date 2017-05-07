package model.nodes;

import javafx.scene.shape.Rectangle;
import util.Constants;

public class SequenceActivationBox extends AbstractNode{

    public final String TYPE = "ActionBar";
 //   public final double DEFAULT_WIDTH = 100;

    protected SequenceObject diagram;
    private String inputport;
    private String outputport;
    private int cycles;


    public SequenceActivationBox(double x, double y, double width, double height){
        super(x, y, width, height);

//		this.width = DEFAULT_WIDTH;
        this.x = x;
        this.y = y;
        this.height = height<LIFEBOX_MIN_HEIGHT?LIFEBOX_MIN_HEIGHT:height;
        this.width = LIFEBOX_DEFAULT_WIDTH;

    }

//    public SequenceActivationBox(SequenceObject diagram){
//        this.diagram = diagram;
//    }

    public void setCycles(int pcycles){
    	cycles = pcycles;
       changes.firePropertyChange(Constants.changeSequenceCycles, null, "Cycles: "+String.valueOf(cycles)+" /s");
       remoteChanges.firePropertyChange(Constants.changeSequenceCycles, null, "Cycles: "+String.valueOf(cycles)+" /s");
   }

public void remoteSetCycles(int pcycles){
	cycles = pcycles;
    changes.firePropertyChange(Constants.changeSequenceCycles, null, "Cycles: "+String.valueOf(cycles)+" /s");
   }

public int getCycles(){
       return cycles;
   }

public void setInputport(String pinputport){
	inputport = pinputport;
   changes.firePropertyChange(Constants.changeSequenceInputport, null, "inputport: " + inputport);
   remoteChanges.firePropertyChange(Constants.changeSequenceInputport, null, "inputport: " + inputport);
}

public void remoteInputport(String pinputport){
	inputport = pinputport;
	   changes.firePropertyChange(Constants.changeSequenceInputport, null, "inputport: " + inputport);
}

public String getInputport(){
   return inputport;
}

public void setOutputport(String poutputport){
	outputport = poutputport;
   changes.firePropertyChange(Constants.changeSequenceOutputport, null, "outputport: " + outputport);
   remoteChanges.firePropertyChange(Constants.changeSequenceOutputport, null, "outputport: " + outputport);
}

public void remoteOutputport(String poutputport){
	outputport = poutputport;
	   changes.firePropertyChange(Constants.changeSequenceOutputport, null, "outputport: " + outputport);
}

public String getOutputport(){
   return outputport;
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

