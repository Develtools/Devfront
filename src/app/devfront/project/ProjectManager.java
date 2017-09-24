package app.devfront.project;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import app.home.Scene;

public class ProjectManager {
	
	private static Project topProject;

	public static Project initScan() throws IOException {
		/* Get the absolute path we are currently present in */
		String topProjectPath = Util.Util.getJarFileAbsolutePath();
		String projectName;
		
		if(topProjectPath == null) {
			File f = new File(Scene.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			topProjectPath = f.getAbsolutePath();
			projectName = f.getName();
		}
		else {
			File f = new File(topProjectPath);
			topProjectPath = f.getParent();
			projectName = f.getParentFile().getName();
		}
		
		topProject = new Project(projectName, topProjectPath, ResourceBundle.getBundle("projectConfig.structure"));
		
		return topProject;
	}
}
