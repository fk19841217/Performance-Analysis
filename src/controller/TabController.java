package controller;

import controller.dialog.GithubLoginDialogController;
import controller.dialog.GithubRepoDialogController;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.edges.AbstractEdge;
import model.edges.MessageEdge;
import model.nodes.AbstractNode;
import model.nodes.LinkedDeploymentNode;
import model.nodes.LinkedSequenceNode;
import model.nodes.NodeNode;
import model.nodes.SequenceActivationBox;
import model.nodes.SequenceObject;
import util.persistence.AqosamodelManager;
import util.persistence.PersistenceManager;
import view.edges.AbstractEdgeView;
import view.edges.MessageEdgeView;
import view.nodes.AbstractNodeView;
import view.nodes.DeploymentNodeView;
import view.nodes.LinkedDeploymentNodeView;
import view.nodes.LinkedSequenceNodeView;
import view.nodes.SequenceObjectView;
import view.nodes.UsecaseNodeView;

import org.controlsfx.control.Notifications;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The class controlling the top menu and the tabs.
 */
public class TabController {
	 private AbstractDiagramController diagramController;
	    private Pane aDrawPane;

	

    @FXML
    private CheckMenuItem umlMenuItem, sketchesMenuItem, mouseMenuItem, gridMenuItem, snapToGridMenuItem, snapIndicatorsMenuItem;

    @FXML
    private TabPane tabPane;
    private Stage stage;
    private Map<Tab, AbstractDiagramController> tabMap = new HashMap<>();
    private Map<String,Tab> stabMap =new HashMap<>();
    
    private Map<String,SequenceObject> componentMap =new HashMap<>();
    private ArrayList<String> componentnamelist = new ArrayList<>();
    
    private ArrayList<String> flowsets = new ArrayList<>();
    private Map<String,AbstractNode> flowsetsMap =new HashMap<>();
   
    private Map<String,ArrayList<AbstractEdgeView>> messageMap =new HashMap<>();
    private ArrayList<String> sequencetablist = new ArrayList<>();
    
    private ArrayList<AbstractEdgeView> externalportedgelist = new ArrayList<>();
    //private Map<String,AbstractEdgeView> externalportedgeMap =new HashMap<>();
    
    private ArrayList<AbstractNode> deploymentboxlist =new ArrayList<>();
    
    private ArrayList<AbstractEdge> networkedgelit =new ArrayList<>();

    public static final String CLASS_DIAGRAM_VIEW_PATH = "view/fxml/classDiagramView.fxml";
    public static final String SEQUENCE_DIAGRAM_VIEW_PATH = "view/fxml/sequenceDiagramView.fxml";
    public static final String PERFORMANCE_VIEW_PATH = "view/fxml/performance.fxml";
    public static final String DEPLOYMENT_VIEW_PATH = "view/fxml/deployment.fxml";

   
    
    @FXML
    public void initialize() {
    }
    
    //public TabController(Pane pDrawPane, AbstractDiagramController pDiagramController){
     //   diagramController = pDiagramController;
     //   aDrawPane = pDrawPane;
    //}
    
    public ArrayList<AbstractEdgeView> getExternalportedgelist(){
    	return  externalportedgelist; 
    	
    } 
    
    public ArrayList<String> getFlowsets(){
    	return flowsets; 
    	
    } 
    
    public Map<String,AbstractNode> getFlowsetsMap(){
    	return  flowsetsMap; 
    	
    } 
    
    
    
    public Map<String,SequenceObject> getComponentMap(){
    	return componentMap;
    }
    
    public Map<String,Tab> getStabMap(){
    	return stabMap;
    } 
    
    public Map<String,ArrayList<AbstractEdgeView>> getMessageMap(){
    	return messageMap;
    }

    public TabPane getTabPane(){
        return tabPane;
    }
    
    public ArrayList<String> getSequencetablist(){
        return sequencetablist;
    }
    
    public ArrayList<AbstractNode> getDeploymentboxlist(){
    	return deploymentboxlist;
    }
    
    public ArrayList<AbstractEdge> getNetworkedgelit(){
    	return networkedgelit;
    }
    
    
    
    
    public ArrayList<String> getComponentnamelist(){
        return componentnamelist;
    }

    public void setStage(Stage pStage){
        stage = pStage;
    }

    public Tab addTab(String pathToDiagram){
        BorderPane canvasView = null;
        AbstractDiagramController diagramController = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToDiagram));
            canvasView = loader.load();
            diagramController = loader.getController();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Tab tab = new Tab();

        tab.setContent(canvasView);
        tabMap.put(tab, diagramController);
         
        
        
        
        if(diagramController instanceof ClassDiagramController){
            tab.setText("Class Diagram " + tabMap.size());
        } else if(diagramController instanceof SequenceDiagramController) {
            tab.setText("Sequence Diagram " + tabMap.size());
        } else  if(diagramController instanceof PerformanceController){
        	tab.setText("Performance analysis " + tabMap.size());
        } else{
        	tab.setText("deployment " + tabMap.size());
        }
        
        
        tabPane.getTabs().add(tab);
        diagramController.setStage(stage);
        return tab;
    }
    
    public void addTab1(){
    	
    	AbstractDiagramController pc= tabMap.get(tabPane.getSelectionModel().getSelectedItem());
    	
        BorderPane canvasView = null;
        AbstractDiagramController diagramController = null;
        FXMLLoader loader;
        

        //if(!stabMap.containsKey(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle())){
        //if(diagramController.selectedNodes.size()==1 && diagramController.selectedNodes.get(0) instanceof LinkedSequenceNodeView )
        if(pc instanceof PerformanceController && pc.selectedNodes.size()==1 && pc.selectedNodes.get(0) instanceof LinkedSequenceNodeView)
        {
        	if(!stabMap.containsKey(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle())){
        	try {
                loader = new FXMLLoader(getClass().getClassLoader().getResource(SEQUENCE_DIAGRAM_VIEW_PATH));
                canvasView = loader.load();
                diagramController = loader.getController();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            Tab tab = new Tab();
        	
        	tab.setContent(canvasView);
             tabMap.put(tab, diagramController);
        	tab.setText(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle());
        	tabPane.getTabs().add(tab);
            diagramController.setStage(stage);
            diagramController.settabname(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle());
        	}
        	else
            	{
            		 tabPane.getTabs().add(stabMap.get(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle()));
            		 diagramController.setStage(stage); 
            	}
        	
        	}
        	
        else if(pc instanceof PerformanceController && pc.selectedNodes.size()==1 &&  pc.selectedNodes.get(0) instanceof LinkedDeploymentNodeView)
        {
        	if(!stabMap.containsKey(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle())){
        	try {
                loader = new FXMLLoader(getClass().getClassLoader().getResource(DEPLOYMENT_VIEW_PATH));
                canvasView = loader.load();
                diagramController = loader.getController();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            Tab tab = new Tab();
        	
        	
        	 tab.setContent(canvasView);
            tabMap.put(tab, diagramController);
            
       	   tab.setText(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle());
       	   tabPane.getTabs().add(tab);
           diagramController.setStage(stage); 
           diagramController.settabname(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle());
           //pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle();
        	}
        	else
        	{
        		 tabPane.getTabs().add(stabMap.get(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle()));
        		 diagramController.setStage(stage); 
        	}
        }
        
        	
        	
        }  
       
        
       // else
    	//{
    	//	 tabPane.getTabs().add(stabMap.get(pc.nodeMap.get(pc.selectedNodes.get(0)).getTitle()));
    	//	 diagramController.setStage(stage); 
    	//}
        //System.out.println(pc.selectedNodes.size());        
       
        
        
    //}

    public void handleMenuActionUML() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionUML();
    }
    public void handleMenuActionSketches() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionSketches();
    }
    public void handleMenuActionGrid() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionGrid();
    }
    public void handleMenuActionMouse() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionMouse();
    }
    public void handleMenuActionExit() {
        Platform.exit();
    }

    public void handleMenuActionSave() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionSave();
    }
    
    public void handleMenuActionAQOSA(){
    	
    	 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Save Diagram");
         fileChooser.setInitialFileName("myAqosa.xml");
         
         File file = fileChooser.showSaveDialog(tabMap.get(tabPane.getSelectionModel().getSelectedItem()).getStage());
         String graphName = file.getName().subSequence(0, file.getName().indexOf('.')).toString();
         
         AqosamodelManager.exportAqosa(this, file.getAbsolutePath());
    }
    
    
//    public void handleMenuActionSave() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save Diagram");
//        if (!graph.getName().equals("")) {
//            fileChooser.setInitialFileName(graph.getName() + ".xml");
//        } else {
//            fileChooser.setInitialFileName("mydiagram.xml");
//        }
//        File file = fileChooser.showSaveDialog(getStage());
//        String graphName = file.getName().subSequence(0, file.getName().indexOf('.')).toString();
//        graph.setName(graphName);
//        PersistenceManager.exportXMI(graph, file.getAbsolutePath());
//    }
    
    public void handleMenuActionLoad() {
        Tab tab = addTab(CLASS_DIAGRAM_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
        tabMap.get(tab).handleMenuActionLoad();
        tab.setText(tabMap.get(tab).getGraphModel().getName());
    }
    public void handleMenuActionNewClassDiagram() {
        Tab tab = addTab(CLASS_DIAGRAM_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
    }
    public void handleMenuActionNewSequenceDiagram() {
        Tab tab = addTab(SEQUENCE_DIAGRAM_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
    }
    
    public void handleMenuActionNewSequenceDiagram1() {
         addTab1();
       // tabPane.getSelectionModel().select(tab);
    }
    
    public void handleMenuActionPerformance() {
        Tab tab = addTab(PERFORMANCE_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
    }
    
    public void handleMenuActionNewDeploymentDiagram() {
        Tab tab = addTab(DEPLOYMENT_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
    }
    
    

    public void handleMenuActionServer(){
        Tab tab = addTab(CLASS_DIAGRAM_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionServer();
    }

    public void handleMenuActionClient(){
        Tab tab = addTab(CLASS_DIAGRAM_VIEW_PATH);
        tabPane.getSelectionModel().select(tab);
        if(!tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionClient()){
            tabPane.getTabs().remove(tab);
        }
    }

    public void handleMenuActionImage(){
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionImage();
    }

    public void handleMenuActionSnapToGrid() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionSnapToGrid(snapToGridMenuItem.isSelected());
    }

    public void handleMenuActionSnapIndicators() {
        tabMap.get(tabPane.getSelectionModel().getSelectedItem()).handleMenuActionSnapIndicators(snapIndicatorsMenuItem.isSelected());
    }

    public void stop(){
        for(AbstractDiagramController mc : tabMap.values()){
            mc.closeServers();
            mc.closeClients();
            mc.closeLog();
        }
    }
    
    public void handleMenuActionsaving(){
    	stabMap.put(tabMap.get(tabPane.getSelectionModel().getSelectedItem()).gettabname(),tabPane.getSelectionModel().getSelectedItem());
    	
    	AbstractDiagramController pc= tabMap.get(tabPane.getSelectionModel().getSelectedItem());
    	
    	if(pc instanceof SequenceDiagramController){
    		
    		for(int i=0;i<pc.getAllNodeViews().size();i++){
    			if(pc.getAllNodeViews().get(i) instanceof SequenceObjectView){
    			    if(!componentnamelist.contains(pc.getNodeMap().get(pc.getAllNodeViews().get(i)).getTitle())){
    			    	SequenceObject so =(SequenceObject) pc.getNodeMap().get(pc.getAllNodeViews().get(i));
    			    	componentMap.put(so.getTitle(), so);
    			    	componentnamelist.add(so.getTitle());
    			    }
    			    else{
    			    	SequenceObject so =(SequenceObject) pc.getNodeMap().get(pc.getAllNodeViews().get(i));
    			    	SequenceObject soinmap = componentMap.get(pc.getNodeMap().get(pc.getAllNodeViews().get(i)).getTitle());
    			    	ArrayList<SequenceActivationBox> sobox =so.getChildNodes();
    			    	ArrayList<SequenceActivationBox> soinmapbox =soinmap.getChildNodes();
    			    	
    			    	 for(int j=0;j<sobox.size();j++){
    			    		 if(!soinmapbox.contains(sobox.get(j))){
    			    			 soinmapbox.add(sobox.get(j));
    			    		 }
    			    		 
    			    		
    			    	 }
    			    	
    			    	
    			    	//soinmapbox.addAll(sobox);
    			    	soinmap.setService(soinmapbox);
    			    	//SequenceObject sonew = soinmap.setService(soinmap.getChildNodes().add .add(so.getChildNodes()));
    			    	
    			    	componentMap.put(soinmap.getTitle(),soinmap);
    			    	//componentnamelist.add(soinmap.getTitle());
    			    }
    			}
    		}
    		
    		ArrayList<AbstractEdgeView> messagelist=pc.getAllEdgeViews();
    		ArrayList<AbstractEdgeView> newmessagelist= new ArrayList<>();
    		
    	 for(int i=0;i<messagelist.size();i++){
    		 
    		 if(!messagelist.get(i).getRefEdge().getstartedge())
    			 messagelist.get(i).getStartNode().getRefNode().setNetwork(messagelist.get(i).getRefEdge().getNetwork());
    		 
    		 
    		 
    		 if(messagelist.get(i) instanceof MessageEdgeView && messagelist.get(i).getRefEdge().getstartedge()){
    			 
    			 AbstractEdgeView  m=(AbstractEdgeView) messagelist.get(i);
    			 externalportedgelist.add(messagelist.get(i));
    			 m.getEndNode().getRefNode().setExternalportedge(m.getRefEdge());
    			 //externalportedgeMap.put(m.getRefEdge().getTitle(), m);
    			//m.getEndNode().getRefNode().setExternalportedge(m.getRefEdge());
    				newmessagelist.add(m);
    				// messageMap.put(tabPane.getSelectionModel().getSelectedItem(),newmessagelist);
    				 messagelist.remove(m);
    				 break;
    		 }
    	
    		  
    	 }
    	 
    	while(messagelist.size()>0){
    		 for(int i=0;i<messagelist.size();i++){
    			 if(newmessagelist.get(newmessagelist.size()-1).getEndNode() == messagelist.get(i).getStartNode()){
    				 
    				 newmessagelist.add(messagelist.get(i));
    				 messagelist.remove(messagelist.get(i));
    			 }
    		 }
    		 
    		 for(int i=0;i<newmessagelist.size();i++){
    			 if(!newmessagelist.get(i).getRefEdge().getstartedge())
    				 newmessagelist.get(i).getStartNode().getRefNode().setNetwork(newmessagelist.get(i).getRefEdge().getNetwork());
    		 }
    		 
    		 
    		 messageMap.put(tabMap.get(tabPane.getSelectionModel().getSelectedItem()).gettabname(),newmessagelist);
    		 
    	 }
    	     	
    	 System.out.println(externalportedgelist.size());
    	 
    	 
    	}
    	
    	
    	else if(pc instanceof PerformanceController){
    		
    		flowsets.clear();
    		flowsetsMap.clear();
    		
    		ArrayList<AbstractEdgeView> adgelist = pc.getAllEdgeViews();
    		
    		for(int j=0;j<adgelist.size();j++){
    			if(adgelist.get(j).getStartNode() instanceof UsecaseNodeView && adgelist.get(j).getEndNode() instanceof LinkedSequenceNodeView)
    				adgelist.get(j).getEndNode().getRefNode().setFrequence(adgelist.get(j).getStartNode().getRefNode().getFrequence());
    		}
    		
    		
    		for(int i=0;i<pc.getAllNodeViews().size();i++){
    			if(pc.getAllNodeViews().get(i) instanceof LinkedSequenceNodeView){
    				flowsets.add(pc.getAllNodeViews().get(i).getRefNode().getTitle());
    				flowsetsMap.put(pc.getAllNodeViews().get(i).getRefNode().getTitle(), pc.getAllNodeViews().get(i).getRefNode());
    			}
    		}
    		
    		
    	}
    	
    	else if (pc instanceof DeploymentController){
    		deploymentboxlist.clear();
    		networkedgelit.clear();
    	
    		
    		for(int i=0;i<pc.getAllNodeViews().size();i++)
    			if(pc.getAllNodeViews().get(i) instanceof DeploymentNodeView)
    				deploymentboxlist.add(pc.getAllNodeViews().get(i).getRefNode());
    			
    		
    		
    		
    		for(int i=0;i<pc.getAllEdgeViews().size();i++)
    			if(pc.getAllEdgeViews().get(i).getStartNode() instanceof DeploymentNodeView && pc.getAllEdgeViews().get(i).getEndNode() instanceof DeploymentNodeView)
    				networkedgelit.add(pc.getAllEdgeViews().get(i).getRefEdge());
    	}
    	
    	
    }

    public void handleMenuActionGit(){
        try {
            File localPath = File.createTempFile("GitRepository", "");
            localPath.delete();

            GithubRepoDialogController gitRepoController = showGithubRepoDialog();

            if(gitRepoController != null && gitRepoController.isOkClicked()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Please wait");
                alert.setHeaderText("Please wait while the repository is being downloaded");
                alert.show();
                Git git = Git.cloneRepository()
                        .setURI(gitRepoController.urlTextField.getText())
                        .setDirectory(localPath)
                        .call();
                alert.close();

                AbstractDiagramController diagramController = tabMap.get(tabPane.getSelectionModel().getSelectedItem());

                if(gitRepoController.imageCheckBox.isSelected()){
                    WritableImage image = diagramController.getSnapShot();
                    String imageFileName = gitRepoController.imageNameTextField.getText() + ".png";
                    File imageFile = new File(localPath + "/" + imageFileName);
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imageFile);

                    git.add().addFilepattern(imageFileName).call();
                }

                if(gitRepoController.xmiCheckBox.isSelected()) {
                    String xmiFileName = gitRepoController.xmiNameTextField.getText() + ".xmi";
                    diagramController.createXMI(localPath + "/" + xmiFileName);
                    git.add().addFilepattern(xmiFileName).call();

                }

                git.commit().setMessage(gitRepoController.commitTextField.getText()).call();
                PushCommand pushCommand = git.push();
                GithubLoginDialogController gitLoginController = showGithubLoginDialog();
                if(gitLoginController != null && gitLoginController.isOkClicked() &&
                        (gitRepoController.imageCheckBox.isSelected() || gitRepoController.xmiCheckBox.isSelected())){
                    pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitLoginController.nameTextField.getText(),
                            gitLoginController.passwordField.getText()));
                    pushCommand.call();
                    Notifications.create()
                            .title("Upload succesfull!")
                            .text("Your diagram has been uploaded successfully.")
                            .showInformation();
                } else {
                    Notifications.create()
                            .title("Nothing was uploaded!")
                            .text("Either you cancelled or didn't select anything to upload.")
                            .showError();
                }
            }

        } catch (InvalidRemoteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Remote not found");
            alert.setContentText("The URL was incorrect.");
            alert.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No diagram open");
            alert.setContentText("There is no diagram to upload.");
            alert.showAndWait();
        } catch (TransportException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not authorized");
            alert.setContentText("You are not authorized to modify repository");
            alert.showAndWait();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public GithubRepoDialogController showGithubRepoDialog(){
        GithubRepoDialogController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/githubRepoDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.stage);
            dialogStage.setScene(new Scene(page));

            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
        return controller;

    }

    public GithubLoginDialogController showGithubLoginDialog(){
        GithubLoginDialogController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/fxml/githubLoginDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.stage);
            dialogStage.setScene(new Scene(page));

            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }

        return controller;

    }
}
