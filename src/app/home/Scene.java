package app.home;

import java.util.ArrayList;
import java.util.List;

import Util.Tree;
import app.devfront.project.Project;
import app.devfront.project.ProjectManager;
import app.devfront.project.Project.FileEntry;
import gui.GenericScene;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class Scene extends GenericScene {
	
	private Project currentProject;

	private TreeItem<String> directoryRootPopulate() {
		TreeItem<String> rootItem = new TreeItem<String>(currentProject.toString());
				
		Tree<FileEntry> tree = currentProject.getProperty("directoryRoot");
		TreeItem<String> itemRoot = rootItem;
		
		List<Tree<FileEntry>> treeFileEntryList = new ArrayList<Tree<FileEntry>>();
		List<TreeItem<String>> treeItemEntryList = new ArrayList<TreeItem<String>>();
		
		for(int idx = -1; idx < treeFileEntryList.size();) {
			for(Tree<FileEntry> treeEntry : tree) {
				String fileName = ((Project.FileEntry)treeEntry.data).name;

				TreeItem<String> treeItem = new TreeItem<String>(fileName.substring(fileName.lastIndexOf("\\") + 1));
				itemRoot.getChildren().add(treeItem);
				treeItemEntryList.add(treeItem);
				treeFileEntryList.add(treeEntry);
			}
			
			if(idx >= treeItemEntryList.size() - 1)
				break;
			
			itemRoot = treeItemEntryList.get(++idx);
			tree = treeFileEntryList.get(idx);
		}
		
		return rootItem;
	}

	public Scene(Stage stage) throws Exception {
		super(stage);
		
		/* Scan the current directory for project(s) */
		currentProject = ProjectManager.initScan();

		/* Update window title */
		stage.setTitle(stage.getTitle() + " - " + currentProject);

		/* Set project label title */
		((Label)getComponent("labelProjectName")).setText(currentProject.toString());

		/* Fill up the file browser */
		TreeView<String> treeviewProjectDirectory = getComponent("treeviewProjectDirectory");
		treeviewProjectDirectory.setRoot(directoryRootPopulate());
		treeviewProjectDirectory.setShowRoot(false);
		
		TableView<String> tableviewSettingsStructure = getComponent("tableviewSettingsStructure");
		
		List<String> l = new ArrayList<String>();
		/* TODO */
		tableviewSettingsStructure.setItems(FXCollections.observableList(l));
	}
}
