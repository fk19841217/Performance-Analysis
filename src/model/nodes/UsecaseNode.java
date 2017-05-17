package model.nodes;

import util.Constants;

public class UsecaseNode extends AbstractNode {
	
	public static final String TYPE = "Usecase";
	 private int frequence;
	
	 public  UsecaseNode(double x, double y, double width, double height)
	    {
		    super(x, y, width, height);
	        //Don't accept nodes with size less than minWidth * minHeight.
	        this.width = width < ACTOR_MIN_HEIGHT ? ACTOR_MIN_HEIGHT : width;
	        this.height = width/3 <ACTOR_MIN_HEIGHT/3 ?ACTOR_MIN_HEIGHT/3 : width/3;
	        //this.height = height < ACTOR_MIN_WIDTH ? ACTOR_MIN_WIDTH : height;
	    }

	 
	 public UsecaseNode(){
	    }
	 
	 public void setFrequence(int pfrequence){
		     frequence = pfrequence;
	        changes.firePropertyChange(Constants.changeUsecaseFrequence, null, "Frequency: "+String.valueOf(frequence)+" /s");
	        remoteChanges.firePropertyChange(Constants.changeUsecaseFrequence, null, "Frequency: "+String.valueOf(frequence)+" /s");
	    }
	 
	 public void remoteSetFrequence(int pfrequence){
		 frequence = pfrequence;
	        changes.firePropertyChange(Constants.changeUsecaseFrequence, null,  "Frequency: "+String.valueOf(frequence)+" /s");
	    }
	 
	 public int getFrequence(){
	        return frequence;
	    }
	 
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	 @Override
	    public UsecaseNode copy(){
		 UsecaseNode newCopy = new UsecaseNode(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	        newCopy.setTranslateX(this.getTranslateX());
	        newCopy.setTranslateY(this.getTranslateY());
	        newCopy.setScaleX(this.getScaleX());
	        newCopy.setScaleY(this.getScaleY());

	        if(this.getTitle() != null){
	            newCopy.setTitle(this.getTitle());

	        }
	        /*if(this.getChildNodes() != null){
	            for(AbstractNode child : this.getChildNodes()){
	                newCopy.addChild(child.copy());
	            }
	        }*/
	        return newCopy;
	    }


}