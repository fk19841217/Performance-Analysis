package model.nodes;

public class NodeNode extends AbstractNode {
	
	public static final String TYPE = "NODE";
	
	
	 public  NodeNode(double x, double y, double width, double height)
	    {
		    super(x, y, width, height);
	        //Don't accept nodes with size less than minWidth * minHeight.
	        this.width = width < CLASS_MIN_WIDTH ? CLASS_MIN_WIDTH : width;
	        this.height = height < CLASS_MIN_HEIGHT ? CLASS_MIN_HEIGHT : height;
	    }


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AbstractNode copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
