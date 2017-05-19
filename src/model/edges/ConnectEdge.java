package model.edges;

import model.nodes.AbstractNode;
import model.nodes.Node;
import util.Constants;

/**
 * Represents an associate relationship between two UML-classes.
 */
public class ConnectEdge extends AbstractEdge {
	
	private int bandwidth;
//	private int cost;
	private double netdelay;
	private String title;

    public ConnectEdge(Node startNode, Node endNode) {
        super(startNode, endNode);
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        changes.firePropertyChange(Constants.changeMessageTitle, title, pTitle);
        remoteChanges.firePropertyChange(Constants.changeMessageTitle, title, pTitle);
        title = pTitle;
    }

    public void remoteSetTitle(String pTitle){
        changes.firePropertyChange(Constants.changeMessageTitle, title, pTitle);
        title = pTitle;
    }
    
	 public void setBandwidth(int pbandwidth){
		 bandwidth = pbandwidth;
        changes.firePropertyChange(Constants.changeConnectbandwidth, null, "BandWidth: "+String.valueOf(bandwidth));
        remoteChanges.firePropertyChange(Constants.changeConnectbandwidth, null, "BandWidth: "+String.valueOf(bandwidth));
    }
 
 public void remoteSetBandwidth(int pbandwidth){
	 bandwidth = pbandwidth;
     changes.firePropertyChange(Constants.changeConnectbandwidth, null, "BandWidth: "+String.valueOf(bandwidth));
    }
 
 public int getBandwidth(){
        return bandwidth;
    }
 
// public void setCost(int pcost){
//	 cost = pcost;
//    changes.firePropertyChange(Constants.changeConnectcost, null, "Cost: "+String.valueOf(cost)+" $");
//    remoteChanges.firePropertyChange(Constants.changeConnectcost, null, "Cost: "+String.valueOf(cost)+" $");
//}
//
//public void remoteSetCost(int pcost){
//	cost = pcost;
//    changes.firePropertyChange(Constants.changeConnectcost, null, "Cost: "+String.valueOf(cost)+" $");
//}
//
//public int getCost(){
//    return cost;
//}

public void setNetdelay(double pnetdelay){
	 netdelay = pnetdelay;
   changes.firePropertyChange(Constants.changeConnectdelay, null, "Cost: "+String.valueOf(netdelay)+" s");
   remoteChanges.firePropertyChange(Constants.changeConnectdelay, null, "Cost: "+String.valueOf(netdelay)+" s");
}

public void remoteNetdelay(double pnetdelay){
	 netdelay = pnetdelay;
	   changes.firePropertyChange(Constants.changeConnectdelay, null, "Cost: "+String.valueOf(netdelay)+" s");
}

public double getNetdelay(){
   return netdelay;
}
    //TODO IMPLEMENT:

    @Override
    public void setTranslateX(double x) {

    }

    @Override
    public void setTranslateY(double y) {

    }

    @Override
    public void setScaleX(double x) {

    }

    @Override
    public void setScaleY(double y) {

    }

    @Override
    public double getTranslateX() {
        return 0;
    }

    @Override
    public double getTranslateY() {
        return 0;
    }

    @Override
    public double getScaleX() {
        return 0;
    }

    @Override
    public double getScaleY() {
        return 0;
    }

    /**
     * No-arg constructor for JavaBean convention
     */
    public ConnectEdge(){
    }
    
    public ConnectEdge copy(AbstractNode startNodeCopy, AbstractNode endNodeCopy){
        return new ConnectEdge(startNodeCopy, endNodeCopy);
    }

    public String getType(){
        return "Connect";
    }
}
