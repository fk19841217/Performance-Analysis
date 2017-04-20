package model.nodes;

public class UsecaseNode extends AbstractNode {
	
	public static final String TYPE = "Usecase";
	
	 public  UsecaseNode(double x, double y, double width, double height)
	    {
		    super(x, y, width, height);
	        //Don't accept nodes with size less than minWidth * minHeight.
	        this.width = width < ACTOR_MIN_WIDTH ? ACTOR_MIN_WIDTH : width;
	        this.height = height < ACTOR_MIN_HEIGHT ? ACTOR_MIN_HEIGHT : height;
	    }

	 
	 public UsecaseNode(){
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
