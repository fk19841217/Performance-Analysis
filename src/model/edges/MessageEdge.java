package model.edges;

import model.nodes.AbstractNode;
import model.nodes.SequenceObject;
import model.nodes.Node;
import util.Constants;

/**
 * Represents an associate relationship between two UML-classes.
 */
public class MessageEdge extends AbstractEdge {

    private double startX;
    private double startY;
    private String title;
    private int network;
    private boolean startedge;
   // private double lowfail;
   // private double upfail;
    
    public enum MessageType {
        REQUEST, RESPONSE;
    }

    private MessageType messageType = MessageType.REQUEST;

    public MessageEdge(double pStartX, double pStartY, Node endNode) {
        super(null, endNode);
        startX = pStartX;
        startY = pStartY;
        setDirection(Direction.START_TO_END);
        this.setstartedge(true);
    }
    
  

    public MessageEdge(double pStartX, double pStartY, Node startNode, Node endNode){
        super(startNode, endNode);
        startX = pStartX;
        startY = pStartY;
        setDirection(Direction.START_TO_END);
        this.setstartedge(false);
    }
    
//    public void setLowfail(double plowfail){
//   	 lowfail = plowfail;
//      changes.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//      remoteChanges.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//   }
//
//   public void remotesetLowfail(double plowfail){
//	   	 lowfail = plowfail;
//	      changes.firePropertyChange(Constants.changeMessageLowfail, null, "external port lower fail rate: "+String.valueOf(lowfail));
//   }
//
//   public double getLowfail(){
//      return lowfail;
//   }
//   
//   public void setUpfail(double pupfail){
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
    
    
    public void setstartedge(boolean b){
    	this.startedge=b;
    }
    
    public boolean getstartedge(){
    	return startedge;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartX(double pStartX) {
        changes.firePropertyChange(Constants.changeMessageStartX, startX, pStartX);
        remoteChanges.firePropertyChange(Constants.changeMessageStartX, startX, pStartX);
        startX = pStartX;

    }

    public void setStartY(double pStartY) {
        SequenceObject lowestNode;
        if(startNode != null && startNode.getY() > endNode.getY()){
            lowestNode = (SequenceObject)startNode;
        } else {
            lowestNode = (SequenceObject)endNode;
        }
        double highestLifelineY = ((SequenceObject)endNode).getLifelineLength() + endNode.getHeight() + endNode.getY();
        if(startNode != null){
            double startNodeLifelineY = ((SequenceObject)startNode).getLifelineLength() + startNode.getHeight() + startNode.getY();
            if(startNodeLifelineY < highestLifelineY){
                highestLifelineY = startNodeLifelineY;
            }
        }

        if(pStartY >= lowestNode.getY() + lowestNode.getHeight() &&
                pStartY <= highestLifelineY){
            changes.firePropertyChange(Constants.changeMessageStartY, startY, pStartY);
            remoteChanges.firePropertyChange(Constants.changeMessageStartY, startY, pStartY);
            startY = pStartY;
        }
    }

    public void remoteSetStartX(double pStartX) {
        changes.firePropertyChange(Constants.changeMessageStartX, startX, pStartX);
        startX = pStartX;

    }

    public void remoteSetStartY(double pStartY) {
        SequenceObject lowestNode;
        if(startNode != null && startNode.getY() > endNode.getY()){
            lowestNode = (SequenceObject)startNode;
        } else {
            lowestNode = (SequenceObject)endNode;
        }
        if(pStartY >= lowestNode.getY() + lowestNode.getHeight()){
            changes.firePropertyChange(Constants.changeMessageStartY, startY, pStartY);
            startY = pStartY;
        }
    }

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
    public void setScaleY(double y) {}

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

    /**
     * No-arg constructor for JavaBean convention
     */
    public MessageEdge(){
    }
    
    public MessageEdge copy(int pStartX, int pStartY, AbstractNode endNodeCopy){
        return new MessageEdge(pStartX, pStartY, endNodeCopy);
    }

    //TODO needs startX and Y but needs to implement this
    public MessageEdge copy(AbstractNode startNodeCopy, AbstractNode endNodeCopy) {
        return null;
    }

    public String getType(){
        return "Message";
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType pMessageType) {
        changes.firePropertyChange(Constants.changeMessageType, messageType, pMessageType);
        remoteChanges.firePropertyChange(Constants.changeMessageType, messageType, pMessageType);
        messageType = pMessageType;
    }

    public void remoteSetMessagesType(MessageType pMessageType){
        changes.firePropertyChange(Constants.changeMessageType, messageType, pMessageType);
        remoteChanges.firePropertyChange(Constants.changeMessageType, messageType, pMessageType);
        messageType = pMessageType;
    }
}
