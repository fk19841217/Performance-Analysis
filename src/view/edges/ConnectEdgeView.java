package view.edges;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.edges.AbstractEdge;
import util.Constants;
import view.nodes.AbstractNodeView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * The Graphical Representation of a AssociationEdge.
 */
public class ConnectEdgeView extends AbstractEdgeView {
    private AbstractEdge refEdge;
    private AbstractNodeView startNode;
    private AbstractNodeView endNode;
    private ArrayList<Line> arrowHeadLines = new ArrayList<>();


    
    public ConnectEdgeView(AbstractEdge edge, AbstractNodeView startNode, AbstractNodeView endNode) {
        super(edge, startNode, endNode);
        this.refEdge = edge;
        this.startNode = startNode;
        this.endNode = endNode;
        this.setStrokeWidth(super.STROKE_WIDTH);
        this.setStroke(Color.BLACK);
        setPosition();
        draw();
    }

    protected void draw() {
        AbstractEdge.Direction direction = refEdge.getDirection();
        getChildren().clear();
        getChildren().add(getStartLine());
        getChildren().add(getMiddleLine());
        getChildren().add(getEndLine());
        super.draw();
        this.getChildren().add(super.getEndMultiplicity());
        this.getChildren().add(super.getStartMultiplicity());

        //Draw arrows.
       
    }

    public void setSelected(boolean selected){
        super.setSelected(selected);
        if(selected){
            for(Line l : arrowHeadLines){
                l.setStroke(Constants.selected_color);
            }
        } else {
            for (Line l : arrowHeadLines) {
                l.setStroke(Color.BLACK);
            }
        }
    }

    /**
     * Draws an ArrowHead and returns it in a group.
     * Based on code from http://www.coderanch.com/t/340443/GUI/java/Draw-arrow-head-line
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return Group.
     */
    

    public void propertyChange(PropertyChangeEvent evt){
        super.propertyChange(evt);
        if(evt.getPropertyName().equals(Constants.changeNodeTranslateX) || evt.getPropertyName().equals(Constants.changeNodeTranslateY) ||
                evt.getPropertyName().equals(Constants.changeEdgeDirection)) {
            draw();
        }
    }
}
