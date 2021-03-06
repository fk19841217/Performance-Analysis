package model.edges;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import model.nodes.Node;
import util.Constants;
import view.edges.AbstractEdgeView;

/**
 * Abstract Edge to hide some basic Edge-functionality.
 */
public abstract class AbstractEdge implements Edge, Serializable {

    private static int objectCount = 0;  //Used to ID instance
    private int id = 0;
    private static final long serialVersionUID = 1L;
    private int bandwidth;
	//private int cost;
	private double netdelay;
	private String title;
	private boolean startedge;
	private AbstractEdgeView abstractedgeview;
	private int network;
	// private double lowfail;
	  //  private double upfail;

    //Listened to by the view, is always fired.
    protected transient PropertyChangeSupport changes = new PropertyChangeSupport(this);
    //Listened to by the server/client, only fired when the change comes from local interaction.
    protected transient PropertyChangeSupport remoteChanges = new PropertyChangeSupport(this);


    protected Node startNode;
    protected Node endNode;
    protected double zoom;
    protected String startMultiplicity, endMultiplicity;

    public enum Direction {
        NO_DIRECTION, START_TO_END, END_TO_START, BIDIRECTIONAL
    }

    private Direction direction;

    public AbstractEdge(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        direction = Direction.NO_DIRECTION;

        id = ++objectCount;
    }
    
    public void setAbstractEdgeView(AbstractEdgeView pabstractedgeview){
    	this.abstractedgeview=pabstractedgeview;
    }
    
    public AbstractEdgeView getAbstractEdgeView(){
    	return abstractedgeview;
    }

    public void setstartedge(boolean b){
    	this.startedge=b;
    }
    
    public boolean getstartedge(){
    	return startedge;
    }
    
    public void setDirection(Direction pDirection) {
        direction = pDirection;
        changes.firePropertyChange(Constants.changeEdgeDirection, null, direction);
        remoteChanges.firePropertyChange(Constants.changeEdgeDirection, null, direction);
    }

    public void setStartMultiplicity(String pStartMultiplicity) {
        startMultiplicity = pStartMultiplicity;
        changes.firePropertyChange(Constants.changeEdgeStartMultiplicity, null, startMultiplicity);
        remoteChanges.firePropertyChange(Constants.changeEdgeStartMultiplicity, null, startMultiplicity);
    }

    public void setEndMultiplicity(String pEndMultiplicity) {
        endMultiplicity = pEndMultiplicity;
        changes.firePropertyChange(Constants.changeEdgeEndMultiplicity, null, endMultiplicity);
        remoteChanges.firePropertyChange(Constants.changeEdgeEndMultiplicity, null, endMultiplicity);
    }

    public void setStartNode(Node pNode) {
        this.startNode = pNode;
        changes.firePropertyChange(Constants.changeEdgeStartNode, null, startNode);
        remoteChanges.firePropertyChange(Constants.changeEdgeStartNode, null, startNode);
    }

    public void setEndNode(Node pNode) {
        endNode = pNode;
        changes.firePropertyChange(Constants.changeEdgeEndNode, null, endNode);
        remoteChanges.firePropertyChange(Constants.changeEdgeEndNode, null, endNode);
    }

    public void setZoom(double scale){
        zoom = scale;
        changes.firePropertyChange(Constants.changeEdgeZoom, null, zoom);
        remoteChanges.firePropertyChange(Constants.changeEdgeZoom, null, zoom);
    }

    public void remoteSetDirection(Direction pDirection) {
        this.direction = pDirection;
        changes.firePropertyChange(Constants.changeEdgeDirection, null, direction);
    }

    public void remoteSetStartMultiplicity(String pStartMultiplicity) {
        startMultiplicity = pStartMultiplicity;
        changes.firePropertyChange(Constants.changeEdgeStartMultiplicity, null, startMultiplicity);
    }

    public void remoteSetEndMultiplicity(String pEndMultiplicity) {
        endMultiplicity = pEndMultiplicity;
        changes.firePropertyChange(Constants.changeEdgeEndMultiplicity, null, endMultiplicity);
    }

    public void remoteSetStartNode(Node pNode) {
        this.startNode = pNode;
        changes.firePropertyChange(Constants.changeEdgeStartNode, null, startNode);
    }

    public void remoteSetEndNode(Node pNode) {
        this.endNode = pNode;
        changes.firePropertyChange(Constants.changeEdgeEndNode, null, endNode);
    }

    public void remoteSetZoom(double scale){
        zoom = scale;
        changes.firePropertyChange(Constants.changeEdgeZoom, null, zoom);
    }

    public String getStartMultiplicity() {
        return startMultiplicity;
    }

    public String getEndMultiplicity() {
        return endMultiplicity;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public double getZoom(){
        return zoom;
    }

    public Direction getDirection() {
        return direction;
    }


    @Override
    public String toString() {
        return super.toString() + this.getClass().toString() + " " + direction + getStartMultiplicity() + getEndMultiplicity();
    }

    /**
     * No-arg constructor for JavaBean convention
     */
    public AbstractEdge(){
    }

    public String getId(){
        return "EDGE_" + id;
    }

    public static void incrementObjectCount(){
        objectCount++;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
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

public void setNetwork(int pnetwork){
    network = pnetwork;
   changes.firePropertyChange(Constants.changeMessageNetwork, null, "Network: "+String.valueOf(network)+" bytes");
   remoteChanges.firePropertyChange(Constants.changeMessageNetwork, null, "Network: "+String.valueOf(network)+" bytes");
}

public void remoteSetNetwork(int pnetwork){
network = pnetwork;
changes.firePropertyChange(Constants.changeMessageNetwork, null, "Network: "+String.valueOf(network)+" bytes");
}

public int getNetwork(){
   return network;
}



//public void setLowfail(double plowfail){
//  	 lowfail = plowfail;
//     changes.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//     remoteChanges.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//  }
//
//  public void remotesetLowfail(double plowfail){
//	   	 lowfail = plowfail;
//	      changes.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//  }
//
//  public double getLowfail(){
//     return lowfail;
//  }
//  
//  public void setUpfail(double pupfail){
//	   	 upfail = pupfail;
//	      changes.firePropertyChange(Constants.changeMessageUpfail, null, "external port Uper fail rate: "+String.valueOf(upfail));
//	      remoteChanges.firePropertyChange(Constants.changeMessageUpfail, null, "external port Uper fail rate: "+String.valueOf(upfail));
//	   }
//
//	   public void remotesetUpfail(double pupfail){
//		   	 upfail = pupfail;
//		      changes.firePropertyChange(Constants.changeMessageUpfail, null, "external port Uper fail rate: "+String.valueOf(upfail));
//	   }
//
//	   public double getUpfail(){
//	      return upfail;
//	   }




}
