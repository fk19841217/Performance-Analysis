package model.nodes;

import util.Constants;

public class LinkedSequenceNode extends AbstractNode {
	
	public static final String TYPE = "NODE";
	
	int delay;
	
	 public  LinkedSequenceNode(double x, double y, double width, double height)
	    {
		    super(x, y, width, height);
	        //Don't accept nodes with size less than minWidth * minHeight.
	        this.width = width < CLASS_MIN_WIDTH ? CLASS_MIN_WIDTH : width;
	        this.height = height < CLASS_MIN_HEIGHT ? CLASS_MIN_HEIGHT : height;
	    }
	 
	 public void setDelay(int pdelay){
	     delay = pdelay;
        changes.firePropertyChange(Constants.changeLinkedSequenceDelay, null, "Delay: "+String.valueOf(delay)+" /s");
        remoteChanges.firePropertyChange(Constants.changeLinkedSequenceDelay, null, "Delay: "+String.valueOf(delay)+" /s");
    }
 
 public void remoteSetDelay(int pdelay){
	 delay = pdelay;
        changes.firePropertyChange(Constants.changeLinkedSequenceDelay, null,  "Frequency: "+String.valueOf(delay)+" /s");
    }
 
 public int getDelay(){
        return delay;
    }

	 @Override
	    public ClassNode copy(){
	        ClassNode newCopy = new ClassNode(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	        newCopy.setTranslateX(this.getTranslateX());
	        newCopy.setTranslateY(this.getTranslateY());
	        newCopy.setScaleX(this.getScaleX());
	        newCopy.setScaleY(this.getScaleY());

	        if(this.getTitle() != null){
	            newCopy.setTitle(this.getTitle());

	        }
	       /* if(this.attributes != null){
	            newCopy.setAttributes(this.attributes);
	        }
	        if(this.operations != null){
	            newCopy.setOperations(operations);
	        }*/
	        newCopy.setTranslateX(this.getTranslateX());
	        newCopy.setTranslateY(this.getTranslateY());
	        return newCopy;
	    }

	    @Override
	    public void setHeight(double height) {
	        this.height = height < CLASS_MIN_HEIGHT ? CLASS_MIN_HEIGHT : height;
	        super.setHeight(height);
	    }

	    @Override
	    public void setWidth(double width) {
	        this.width = width < CLASS_MIN_WIDTH ? CLASS_MIN_WIDTH : width;
	        super.setWidth(width);
	    }

	    @Override
	    public void remoteSetHeight(double height) {
	        this.height = height < CLASS_MIN_HEIGHT ? CLASS_MIN_HEIGHT : height;
	        super.remoteSetHeight(height);
	    }

	    @Override
	    public void remoteSetWidth(double width) {
	        this.width = width < CLASS_MIN_WIDTH ? CLASS_MIN_WIDTH : width;
	        super.remoteSetWidth(width);
	    }

	    /**
	     * No-arg constructor for JavaBean convention
	     */
	    public LinkedSequenceNode(){
	    }

	    public String getType(){
	        return TYPE;
	    }
}
