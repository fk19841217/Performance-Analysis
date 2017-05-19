package model.nodes;

import javafx.geometry.Point2D;
import util.Constants;

import java.util.ArrayList;

/**
 * Represents a UML-package.
 */
public class DeploymentNode extends AbstractNode
{
    private static final String type = "DEPLOYMENT";
    private ArrayList<AbstractNode> childNodes = new ArrayList<>();
    private int clock;
	//private int lowstatus_cost;
	//private int highstatus_cost;
	private int internalBusBandwidth;
	private double internalBusdelay;
	private double lowstatus_lowfail;
	//private double lowstatus_highfail;
	

    public DeploymentNode(double x, double y, double width, double height)
    {
        super(x, y, width, height );

        //Don't accept nodes with size less than minWidth * minHeight.
        this.width = width < PACKAGE_MIN_WIDTH ? PACKAGE_MIN_WIDTH : width;
        this.height = height < PACKAGE_MIN_HEIGHT ? PACKAGE_MIN_HEIGHT : height;
    }

    public ArrayList<AbstractNode> getChildNodes() {
        return childNodes;
    }

    public void removeChild(AbstractNode childNode){
        childNode.setIsChild(false);
        this.childNodes.remove(childNode);
    }

    /**
     * Finds a node in this package from a Point2D.
     * @param point
     * @return the node if found, otherwise null.
     */
    public AbstractNode findNode(Point2D point) {
        for (AbstractNode node : childNodes) {
            if (point.getX() >= node.getX() && point.getX() <= node.getX()+ node.getWidth()
                    && point.getY() >= node.getY() && point.getY() <= node.getY() + node.getHeight()) {
                return node;
            }
        }
        return null;
    }

    public void addChild(AbstractNode childNode) {
        if (!childNodes.contains(childNode)) {
            childNode.setIsChild(true);
            this.childNodes.add(childNode);
        }
    }
    
    
    public void setClock(int pclock){
	     clock = pclock;
       changes.firePropertyChange(Constants.changeLinkedDepoloymentClock, null, "Clock: "+String.valueOf(clock)+" /s");
       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentClock, null, "Clock: "+String.valueOf(clock)+" /s");
   }

     public void remoteSetClock(int pclock){
   	  clock = pclock;
         changes.firePropertyChange(Constants.changeLinkedDepoloymentClock, null, "Clock: "+String.valueOf(clock)+" /s");
       }

    public int getClock(){
        return clock;
       }
    
//    public void setLowstatus_cost(int plowstatus_cost){
//   	 lowstatus_cost = plowstatus_cost;
//       changes.firePropertyChange(Constants.changeLinkedDepoloymentLowstatusCost, null, "LowstatusCost: "+String.valueOf(lowstatus_cost)+" $");
//       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentLowstatusCost, null, "LowstatusCost: "+String.valueOf(lowstatus_cost)+" $");
//   }
//
//     public void remoteSetLowstatus_cost(int plowstatus_cost){
//   	  lowstatus_cost = plowstatus_cost;
//         changes.firePropertyChange(Constants.changeLinkedDepoloymentLowstatusCost, null, "LowstatusCost: "+String.valueOf(lowstatus_cost)+" $");
//       }
//
//    public int getLowstatus_cost(){
//        return lowstatus_cost;
//       }
    
//    public void setHighstatus_cost(int phighstatus_cost){
//   	 highstatus_cost = phighstatus_cost;
//       changes.firePropertyChange(Constants.changeLinkedDepoloymentHighstatusCost, null, "HighstatusCost: "+String.valueOf(highstatus_cost)+" $");
//       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentHighstatusCost, null, "HighstatusCost: "+String.valueOf(highstatus_cost)+" $");
//   }
//
//     public void remoteSetHighstatus_cost(int phighstatus_cost){
//   	  highstatus_cost = phighstatus_cost;
//         changes.firePropertyChange(Constants.changeLinkedDepoloymentHighstatusCost, null, "HighstatusCost: "+String.valueOf(highstatus_cost)+" $");
//       }
//
//    public int getHighstatus_cost(){
//        return highstatus_cost;
//       }
    
    
    public void setInternalBusBandwidth(int pinternalBusBandwidth){
   	 internalBusBandwidth = pinternalBusBandwidth;
       changes.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusBandwidth, null, "InternalBusBandwidth: "+String.valueOf(internalBusBandwidth)+" m");
       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusBandwidth, null, "InternalBusBandwidth: "+String.valueOf(internalBusBandwidth)+" m");
   }

     public void remoteSetInternalBusBandwidth(int pinternalBusBandwidth){
   	  internalBusBandwidth = pinternalBusBandwidth;
         changes.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusBandwidth, null, "InternalBusBandwidth: "+String.valueOf(internalBusBandwidth)+" m");
       }

    public int getInternalBusBandwidth(){
        return internalBusBandwidth;
       }
    
    public void setInternalBusdelay(double pinternalBusdelay){
   	 internalBusdelay = pinternalBusdelay;
       changes.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusdelay, null, "InternalBusdelay: "+String.valueOf(internalBusdelay)+" s");
       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusdelay, null, "InternalBusdelay: "+String.valueOf(internalBusdelay)+" s");
   }

     public void remoteSetInternalBusdelay(double pinternalBusdelay){
    	 internalBusdelay = pinternalBusdelay;
        changes.firePropertyChange(Constants.changeLinkedDepoloymentinternalBusdelay, null, "InternalBusdelay: "+String.valueOf(internalBusdelay)+" s");
       }

    public double getInternalBusdelay(){
        return internalBusdelay;
       }
    
    public void setLowstatus_lowfail(double plowstatus_lowfail){
   	 lowstatus_lowfail = plowstatus_lowfail;
       changes.firePropertyChange(Constants.changeLinkedDepoloymentlowstatus_lowfail, null, "Lowstatus_lowfail: "+String.valueOf(lowstatus_lowfail));
       remoteChanges.firePropertyChange(Constants.changeLinkedDepoloymentlowstatus_lowfail, null, "Lowstatus_lowfail: "+String.valueOf(lowstatus_lowfail));
   }

     public void remoteSetLowstatus_lowfail(double plowstatus_lowfail){
    	 lowstatus_lowfail = plowstatus_lowfail;
        changes.firePropertyChange(Constants.changeLinkedDepoloymentlowstatus_lowfail, null, "Lowstatus_lowfail: "+String.valueOf(lowstatus_lowfail));
       }

    public double getLowstatus_lowfail(){
        return lowstatus_lowfail;
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

    @Override
    public DeploymentNode copy(){
        DeploymentNode newCopy = new DeploymentNode(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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

    /**
     * No-arg constructor for JavaBean convention
     */
    public DeploymentNode(){
    }

    public String getType(){
        return type;
    }
}