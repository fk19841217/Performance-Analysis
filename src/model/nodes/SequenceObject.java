package model.nodes;

import javafx.geometry.Point2D;
import util.Constants;

import java.util.ArrayList;

/**
 * Created by Marcus on 2016-09-01.
 */
public class SequenceObject extends AbstractNode {

    public static final String TYPE = "LIFELINE";
    public final double LIFELINE_DEFAULT_LENGTH = 500;
    private ArrayList<SequenceActivationBox> activationBoxes = new ArrayList<>();

    private double lifelineLength = LIFELINE_DEFAULT_LENGTH;

    public SequenceObject(double x, double y, double width, double height)
    {
        super(x, y, width, height );
        //Don't accept nodes with size less than minWidth * minHeight.
        this.width = width < LIFELINE_MIN_WIDTH ? LIFELINE_MIN_WIDTH : width;
        this.height = height < LIFELINE_MIN_HEIGHT ? LIFELINE_MIN_HEIGHT : height;
    }

    @Override
    public AbstractNode copy() {
        return null;
    }

    /**
     * No-arg constructor for JavaBean convention
     */
    public SequenceObject(){
    }

    public ArrayList<SequenceActivationBox> getChildNodes() {
        return activationBoxes;
    }

    public void removeChild(SequenceActivationBox childNode){
        childNode.setIsChild(false);
        this.activationBoxes.remove(childNode);
    }

    /**
     * Finds a node in this sequence object from a Point2D.
     * @param point
     * @return the node if found, otherwise null.
     */
    public SequenceActivationBox findNode(Point2D point) {
        for (SequenceActivationBox node : activationBoxes) {
            if (point.getX() >= node.getX() && point.getX() <= node.getX()+ node.getWidth()
                    && point.getY() >= node.getY() && point.getY() <= node.getY() + node.getHeight()) {
                return node;
            }
        }
        return null;
    }

    public void addChild(SequenceActivationBox childNode) {
        if (!activationBoxes.contains(childNode)) {
            childNode.setIsChild(true);
            this.activationBoxes.add(childNode);
           
        }
    }



    @Override
    public void setHeight(double height) {
        this.height = height < LIFELINE_MIN_HEIGHT ? LIFELINE_MIN_HEIGHT : height;
        super.setHeight(height);
    }

    @Override
    public void setWidth(double width) {
        this.width = width < LIFELINE_MIN_WIDTH ? LIFELINE_MIN_WIDTH : width;
        super.setWidth(width);
    }

    @Override
    public void remoteSetHeight(double height) {
        this.height = height < LIFELINE_MIN_HEIGHT ? LIFELINE_MIN_HEIGHT : height;
        super.remoteSetHeight(height);
    }

    @Override
    public void remoteSetWidth(double width) {
        this.width = width < LIFELINE_MIN_WIDTH ? LIFELINE_MIN_WIDTH : width;
        super.remoteSetWidth(width);
    }


    @Override
    public String getType() {
        return TYPE;
    }

    public double getLifelineLength() {
        return lifelineLength;
    }

    public void setLifelineLength(double lifelineLength) {
        this.lifelineLength = lifelineLength;
        changes.firePropertyChange(Constants.changeLifelineLength, null, lifelineLength);
        remoteChanges.firePropertyChange(Constants.changeLifelineLength, null, lifelineLength);
    }

    public void remoteSetLifelineLength(double lifelineLength) {
        this.lifelineLength = lifelineLength;
        changes.firePropertyChange(Constants.changeLifelineLength, null, lifelineLength);
    }
}
