package util.persistence;

import javafx.scene.control.Tab;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import model.*;
import model.edges.*;
import model.nodes.AbstractNode;
import model.nodes.ClassNode;
import model.nodes.PackageNode;
import model.nodes.SequenceActivationBox;
import model.nodes.SequenceObject;
import view.edges.AbstractEdgeView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.TabController;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class with static methods for importing and exporting xmi models.
 */
public class AqosamodelManager {

    public static void exportAqosa(TabController tc, String path){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(createAqosa(tc));

            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public static Document createAqosa(TabController tc){
        DocumentBuilder docBuilder = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("aqosa.ir:AQOSAModel");
        rootElement.setAttribute("xmi:version", "2.0");
        rootElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xmlns:aqosa.ir", "http://se.liacs.nl/aqosa/ir");
        doc.appendChild(rootElement);

        Element xmiassembly = doc.createElement("assembly");
        
        
        
        
        ArrayList<String> clist=tc.getComponentnamelist();
        Map<String,SequenceObject> cMap=tc.getComponentMap();
        
        
        for(int i=0;i<clist.size();i++){
        	
        	Element xmicomponent = doc.createElement("component");
        	xmicomponent.setAttribute("name", clist.get(i));
        	
        	ArrayList<SequenceActivationBox> boxlist= cMap.get(clist.get(i)).getChildNodes();
        	
        	for(int j=0;j<boxlist.size();j++){
        		Element xmiservice=doc.createElement("service");
        		xmiservice.setAttribute("name", boxlist.get(j).getTitle());
        		xmicomponent.appendChild(xmiservice);
        	}
        	
        	for(int j=0;j<boxlist.size();j++){
        		Element xmiinport=doc.createElement("inport");
        		xmiinport.setAttribute("name", boxlist.get(j).getInputport());
        		xmicomponent.appendChild(xmiinport);
        	}
        	
        	for(int j=0;j<boxlist.size();j++){
        		Element xmioutport=doc.createElement("outport");
        		xmioutport.setAttribute("name", boxlist.get(j).getOutputport());
        		xmicomponent.appendChild(xmioutport);
        	}
        	
        	xmiassembly.appendChild(xmicomponent);
        }
        
        ArrayList<String> flowsets = tc.getFlowsets();
        Map<String,AbstractNode> flowsetsMap =tc.getFlowsetsMap();
        
        Map<String,ArrayList<AbstractEdgeView>> amessageMap =tc.getMessageMap();
       // ArrayList<String> asequencetablist = tc.getSequencetablist();
        
        Map<String,Tab> astabMap =tc.getStabMap();
        //ArrayList<SequenceActivationBox> boxlist= cMap.get(clist.get(i)).getChildNodes();
        
        
        for(int i=0;i<flowsets.size();i++){
        	
        	Element xmiflow = doc.createElement("flow");
        	xmiflow.setAttribute("name",flowsets.get(i));
        	
        	ArrayList<AbstractEdgeView> edgelist = amessageMap.get(flowsets.get(i));
        	
        	Element xmicomputeaction = doc.createElement("action");
        	xmicomputeaction.setAttribute("xsi:type", "aqosa.ir:ComputeAction");
        	xmicomputeaction.setAttribute("service", "//@assembly/@component."+ clist.indexOf(edgelist.get(0).getEndNode().getRefNode().getSequenceObject().getTitle())+"/@service."+ edgelist.get(0).getEndNode().getRefNode().getSequenceObject().getChildNodes().indexOf(edgelist.get(0).getEndNode().getRefNode()));
        	xmiflow.appendChild(xmicomputeaction);
        	
        	for(int j=1;j<edgelist.size();j++){
        		
        		Element xmicommunicateaction = doc.createElement("action");
        		xmicommunicateaction.setAttribute("xsi:type", "aqosa.ir:CommunicateAction");
        		xmicommunicateaction.setAttribute("source","//@assembly/@component."+clist.indexOf(edgelist.get(j).getStartNode().getRefNode().getSequenceObject().getTitle())
        		+"/@outport."+edgelist.get(j).getStartNode().getRefNode().getSequenceObject().getChildNodes().indexOf(edgelist.get(j).getStartNode().getRefNode()));
        		xmicommunicateaction.setAttribute("destination","//@assembly/@component."+clist.indexOf(edgelist.get(j).getEndNode().getRefNode().getSequenceObject().getTitle())
        		+"/@inport."+edgelist.get(j).getEndNode().getRefNode().getSequenceObject().getChildNodes().indexOf(edgelist.get(j).getEndNode().getRefNode()));
        	    xmiflow.appendChild(xmicommunicateaction);
        	    
        	    Element xmicomputeaction1 = doc.createElement("action");
            	xmicomputeaction1.setAttribute("xsi:type", "aqosa.ir:ComputeAction");
            	xmicomputeaction1.setAttribute("service", "//@assembly/@component."+ clist.indexOf(edgelist.get(j).getEndNode().getRefNode().getSequenceObject().getTitle())
            	+"/@service."+ edgelist.get(j).getEndNode().getRefNode().getSequenceObject().getChildNodes().indexOf(edgelist.get(j).getEndNode().getRefNode()));
            	xmiflow.appendChild(xmicomputeaction1);
        	
        	
        	}
        	
        	xmiassembly.appendChild(xmiflow);
        	
        }
        	
        
        
        rootElement.appendChild(xmiassembly);
        
        Element xmiscenarios = doc.createElement("scenarios");
        
        Element xmiflowset= doc.createElement("flowset");
        xmiflowset.setAttribute("name", "Average");
        xmiflowset.setAttribute("completionTime", "10000");
        xmiflowset.setAttribute("missedPercentage", "0.05");
        xmiscenarios.appendChild(xmiflowset);
        
        
        ArrayList<AbstractEdgeView> externallist=tc.getExternalportedgelist();
        
        for(int i=0;i<flowsets.size();i++){
        	Element xmiflowinstance = doc.createElement("flowinstance");
        	xmiflowinstance.setAttribute("instance", "//@assembly/@flow."+i);
        	xmiflowinstance.setAttribute("start", String.valueOf(flowsetsMap.get(flowsets.get(i)).getStarttime()));
        	xmiflowinstance.setAttribute("trigger", String.valueOf(1000/(flowsetsMap.get(flowsets.get(i)).getFrequence())));
        	xmiflowinstance.setAttribute("deadline", String.valueOf(flowsetsMap.get(flowsets.get(i)).getDelay()));
        	xmiflowset.appendChild(xmiflowinstance);
        }
       
        rootElement.appendChild(xmiscenarios);
        
        Element xmirepository = doc.createElement("repository");
        
        double varincepercentage = 1.0/Double.valueOf(clist.size());
        
        for(int i=0;i<clist.size();i++){
        	Element xmicomponentinstance = doc.createElement("componentinstance");
        	 xmicomponentinstance.setAttribute("id",cMap.get(clist.get(i)).getTitle()+"_Instance");
        	 xmicomponentinstance.setAttribute("compatible","//@assembly/@component."+i);
        	 xmicomponentinstance.setAttribute("variancePercentage",String.valueOf(varincepercentage));
        	 
        	 ArrayList<SequenceActivationBox> boxlist = cMap.get(clist.get(i)).getChildNodes();
        	 
        	 for(int j=0;j<boxlist.size();j++){
        		 Element xmiserviceinstance= doc.createElement("service");
        		 xmiserviceinstance.setAttribute("instance", "//@assembly/@component."+i+"/@service."+j);
        		 xmiserviceinstance.setAttribute("cycles",String.valueOf(boxlist.get(j).getCycles()));
        		
        		 if(boxlist.get(j).getNetwork()>0)
        		 {
        		 xmiserviceinstance.setAttribute("networkUsage", String.valueOf(1000*boxlist.get(j).getNetwork()));
        		 Element xmiprovide= doc.createElement("provide");
        		 xmiprovide.setAttribute("connects", "//@assembly/@component."+i+"/@outport."+j);
        		 xmiserviceinstance.appendChild(xmiprovide);
        		 }
        		 
        		 Element xmidenpend=doc.createElement("depend");
        		 
        		 Element xmirequire= doc.createElement("require");
        		 if(boxlist.get(j).getExternalportedge()!=null)
        			 xmirequire.setAttribute("external", "//@repository/@externalport."+ externallist.indexOf(boxlist.get(j).getExternalportedge().getAbstractEdgeView()));	 
        		 else
        			 xmirequire.setAttribute("internal", "//@assembly/@component."+i+"/@inport."+j);
        		 
        		 xmidenpend.appendChild(xmirequire);
        		 
        		 xmiserviceinstance.appendChild(xmidenpend);
        		 
        		 xmicomponentinstance.appendChild(xmiserviceinstance);
        	 }
        	 xmirepository.appendChild(xmicomponentinstance);
        }
        
//    	private ArrayList<AbstractNode> deploymentboxlist =new ArrayList<>();
        
   	 //   private ArrayList<AbstractEdge> networkedgelit =new ArrayList<>();
        
        ArrayList<AbstractNode> processorlist=tc.getDeploymentboxlist();
        ArrayList<AbstractEdge> buslist=tc.getNetworkedgelit();
        
        for(int i=0;i<processorlist.size();i++){
        	Element xmiprocessor_h=doc.createElement("processor");
        	xmiprocessor_h.setAttribute("id", processorlist.get(i).getTitle()+"-h");
        	xmiprocessor_h.setAttribute("clock", String.valueOf(processorlist.get(i).getClock()));
        	xmiprocessor_h.setAttribute("cost", String.valueOf(processorlist.get(i).getHighstatus_cost()));
        	xmiprocessor_h.setAttribute("internalBusBandwidth", String.valueOf(processorlist.get(i).getInternalBusBandwidth()));
        	xmiprocessor_h.setAttribute("internalBusDelay",String.valueOf(processorlist.get(i).getInternalBusdelay()));
        	xmiprocessor_h.setAttribute("lowerFail", String.valueOf(processorlist.get(i).getHighstatus_lowfail()));
        	xmiprocessor_h.setAttribute("upperFail", String.valueOf(processorlist.get(i).getHighstatus_highfail()));
        	xmirepository.appendChild(xmiprocessor_h);
        	
        	Element xmiprocessor_l=doc.createElement("processor");
        	xmiprocessor_l.setAttribute("id", processorlist.get(i).getTitle()+"-l");
        	xmiprocessor_l.setAttribute("clock", String.valueOf(processorlist.get(i).getClock()));
        	xmiprocessor_l.setAttribute("cost", String.valueOf(processorlist.get(i).getLowstatus_cost()));
        	xmiprocessor_l.setAttribute("internalBusBandwidth", String.valueOf(processorlist.get(i).getInternalBusBandwidth()));
        	xmiprocessor_l.setAttribute("internalBusDelay",String.valueOf(processorlist.get(i).getInternalBusdelay()));
        	xmiprocessor_l.setAttribute("lowerFail", String.valueOf(processorlist.get(i).getLowstatus_lowfail()));
        	xmiprocessor_l.setAttribute("upperFail", String.valueOf(processorlist.get(i).getLowstatus_highfail()));
        	xmirepository.appendChild(xmiprocessor_l);
        }
        
        for(int i=0;i<buslist.size();i++){
        	Element xmibus=doc.createElement("bus");
        	xmibus.setAttribute("id", buslist.get(i).getTitle());
        	xmibus.setAttribute("bandwidth",String.valueOf(buslist.get(i).getBandwidth()));
        	xmibus.setAttribute("delay",String.valueOf(buslist.get(i).getNetdelay()));
        	xmibus.setAttribute("cost",String.valueOf(buslist.get(i).getCost()));
        	xmirepository.appendChild(xmibus);
        }
       
        
        
        
        for(int i=0;i<externallist.size();i++){
        	Element xmiexternalport=doc.createElement("externalport");
        	xmiexternalport.setAttribute("id", externallist.get(i).getRefEdge().getTitle());
        	xmiexternalport.setAttribute("lowerFail",String.valueOf(externallist.get(i).getRefEdge().getLowfail()));
        	xmiexternalport.setAttribute("upperFail",String.valueOf(externallist.get(i).getRefEdge().getUpfail()));
        	xmirepository.appendChild(xmiexternalport);
        }
        
        rootElement.appendChild(xmirepository);
        
        Element xmiobjectives=doc.createElement("objectives");
        
        Element xmisetting=doc.createElement("settings");
        xmisetting.setAttribute("noRun", "3");
        xmisetting.setAttribute("noSampling", "50");
        xmisetting.setAttribute("noDuplicate", "1");
        xmisetting.setAttribute("minCost", "200");
        xmisetting.setAttribute("maxCost", "10000");
        
        
        
        
        String[] string = {"ResponseTime","CPUUtilization","BusUtilization","Safety","Cost"};
        
        for(int i=0;i<string.length;i++){
        Element xmievaluation=doc.createElement("evaluations");
        xmievaluation.setTextContent(string[i]);
        xmisetting.appendChild(xmievaluation);
        }
        
        xmiobjectives.appendChild(xmisetting);
        
        rootElement.appendChild(xmiobjectives);
        
        
        
        return doc;
    }


    //------------------------------------------ IMPORT -----------------------------------------------
    public static Graph importXMIFromPath(String path){
        File xmiFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmiFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return importXMI(doc);
    }
    public static Graph importXMI(Document doc){

        Graph graph = new Graph();
        //IDs are generated when AbstractNodes are created, we need to keep track of the nodes IDs in the xmi were when referenced elsewhere.
        Map<String, AbstractNode> idMap = new HashMap<>();

        NodeList nList = doc.getElementsByTagName("UML:Model");
        Element umlModel = ((Element)nList.item(0));
        graph.setName(umlModel.getAttribute("name"));
        String modelNamespace = umlModel.getAttribute("xmi.id");

        //Import packages
        nList = doc.getElementsByTagName("UML:Package");
        for(int i = 0; i < nList.getLength(); i++){
            Element modelElement = ((Element)nList.item(i));
            NodeList viewList = doc.getElementsByTagName("UML:DiagramElement");
            for(int j = 0; j < viewList.getLength(); j++){ //Find its corresponding view
                Element viewElement = ((Element)viewList.item(j));
                if(viewElement.getAttribute("subject").equals(modelElement.getAttribute("xmi.id"))){
                    Boolean isChild = !modelElement.getAttribute("namespace").equals(modelNamespace);
                    AbstractNode node = createAbstractNode(viewElement, modelElement, isChild, true);
                    idMap.put(modelElement.getAttribute("xmi.id"), node);
                    graph.addNode(node, false);
                }
            }
        }

        //Import classes
        nList = doc.getElementsByTagName("UML:Class");
        for(int i = 0; i < nList.getLength(); i++){
            Element modelElement = ((Element)nList.item(i));
            NodeList viewList = doc.getElementsByTagName("UML:DiagramElement");
            for(int j = 0; j < viewList.getLength(); j++){ //Find its corresponding view
                Element viewElement = ((Element)viewList.item(j));
                if(viewElement.getAttribute("subject").equals(modelElement.getAttribute("xmi.id"))){
                    Boolean isChild = !modelElement.getAttribute("namespace").equals(modelNamespace);
                    AbstractNode node = createAbstractNode(viewElement, modelElement, isChild, false);
                    idMap.put(modelElement.getAttribute("xmi.id"), node);
                    graph.addNode(node, false);
                }
            }
        }

        //Import associations
        nList = doc.getElementsByTagName("UML:Association");
        for(int i = 0; i < nList.getLength(); i++){
            Element associationElement = (Element) nList.item(i);
            String startNodeId = ((Element)associationElement.getChildNodes().item(0).getChildNodes().item(0)).getAttribute("type");
            String endNodeId = ((Element)associationElement.getChildNodes().item(0).getChildNodes().item(1)).getAttribute("type");

            AbstractEdge edge;
            String relation = associationElement.getAttribute("relation");
            String direction = associationElement.getAttribute("direction");
            if (relation.equals("Association")){
                edge = new AssociationEdge(idMap.get(startNodeId), idMap.get(endNodeId));
                edge.setDirection(AbstractEdge.Direction.valueOf(direction));
            } else if (relation.equals("Inheritance")){
                edge = new InheritanceEdge(idMap.get(startNodeId), idMap.get(endNodeId));
                edge.setDirection(AbstractEdge.Direction.valueOf(direction));
            } else if (relation.equals("Aggregation")){
                edge = new AggregationEdge(idMap.get(startNodeId), idMap.get(endNodeId));
                edge.setDirection(AbstractEdge.Direction.valueOf(direction));
            } else if (relation.equals("Composition")){
                edge = new CompositionEdge(idMap.get(startNodeId), idMap.get(endNodeId));
                edge.setDirection(AbstractEdge.Direction.valueOf(direction));
            } else { //Standard is Assocation
                edge = new AssociationEdge(idMap.get(startNodeId), idMap.get(endNodeId));
            }
            graph.addEdge(edge, false);
        }




        //Import sketches
        nList = doc.getElementsByTagName("Sketch");
        for(int i = 0; i < nList.getLength(); i++) {

            Sketch sketch = new Sketch();

            Element sketchElement = (Element) nList.item(i);
            Element pathElement = (Element) sketchElement.getChildNodes().item(0);
            NodeList pathList = pathElement.getChildNodes();
            for (int j = 0; j < pathList.getLength(); j++) {
                Element point = (Element) pathList.item(j);
                if (point.getTagName().equals("MoveTo")) {
                    sketch.setStart(Double.parseDouble(point.getAttribute("xPoint")),
                            Double.parseDouble(point.getAttribute("yPoint")));
                } else if (point.getTagName().equals("LineTo")) {
                    sketch.addPoint(Double.parseDouble(point.getAttribute("xPoint")),
                            Double.parseDouble(point.getAttribute("yPoint")));
                }
            }
            graph.addSketch(sketch, false);
        }
        return graph;
    }

    private static AbstractNode createAbstractNode(Element view, Element model, boolean isChild, boolean isPackage){
        String[] geometry = view.getAttribute("geometry").split(",");
        double x = Double.parseDouble(geometry[0]);
        double y = Double.parseDouble(geometry[1]);
        double width = Double.parseDouble(geometry[2]) - x;
        double height = Double.parseDouble(geometry[3]) - y;

        AbstractNode abstractNode;
        if(!isPackage){
            abstractNode = new ClassNode(x, y, width, height);
            NodeList attsOps = model.getChildNodes().item(0).getChildNodes();
            String attributes = "";
            String operations = "";
            for(int i = 0; i < attsOps.getLength(); i++){
                Element item = ((Element)attsOps.item(i));
                if(item.getNodeName().equals("UML:Attribute")){
                    String att = item.getAttribute("name");
                    attributes = attributes + att + System.getProperty("line.separator");
                } else if(item.getNodeName().equals("UML:Operation")){
                    String op = item.getAttribute("name");
                    operations = operations + op + System.getProperty("line.separator");
                }
            }
            ((ClassNode)abstractNode).setAttributes(attributes);
            ((ClassNode)abstractNode).setOperations(operations);
        } else {
            abstractNode = new PackageNode(x, y, width, height);
        }
        abstractNode.setTitle(model.getAttribute("name"));
        abstractNode.setIsChild(isChild);

        return abstractNode;
    }
}
