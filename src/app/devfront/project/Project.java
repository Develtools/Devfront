package app.devfront.project;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Util.Tree;

public class Project {

	public class FileEntry {
		public String name;
		public BasicFileAttributes attribute;
		
		public FileEntry(String name, BasicFileAttributes attrs) {
			this.name = name;
			this.attribute = attrs;
		}
		
		public String toString() {
			return name;
		}
	}
	
	private String name;
	private String absolutePath;
	private ResourceBundle structure;
	private Tree<FileEntry> directoryRoot = null;
	private List<Tree<FileEntry>> lastNodes = null;
	private Tree<FileEntry> lastNode = null;

	public Project(String name, String absolutePath, ResourceBundle structure) throws IOException {
		this.name = name;
		this.absolutePath = absolutePath;
		this.structure = structure;

		lastNodes = new ArrayList<Tree<FileEntry>>();
		
		Files.walkFileTree(Paths.get(absolutePath), new SimpleFileVisitor<Path>(){ 
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException
			{
				String relPath = file.toString();
				relPath = relPath.substring(relPath.lastIndexOf(name));
				
				if(directoryRoot == null)
					lastNodes.add(directoryRoot = lastNode = new Tree<FileEntry>(new FileEntry(relPath, attrs)));
				else
					lastNode.addChild(new FileEntry(relPath, attrs));
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs)
				throws IOException
			{
				String relPath = file.toString();
				relPath = relPath.substring(relPath.lastIndexOf(name));
				
				if(directoryRoot == null)
					lastNodes.add(directoryRoot = lastNode = new Tree<FileEntry>(new FileEntry(relPath, attrs)));
				else
					lastNodes.add(lastNode = lastNode.addChild(new FileEntry(relPath, attrs)));
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path file, IOException attrs)
				throws IOException
			{
				if(lastNodes.size() > 1) {
					int idx = lastNodes.size() - 1;
					lastNodes.remove(idx);
					lastNode = lastNodes.get(idx - 1);
				}
				
				return FileVisitResult.CONTINUE;
			}
		}); 
	}

	@SuppressWarnings("unchecked")
	public <T> T getProperty(String propertyName) {
		switch(propertyName) {
		case "name": return (T)name;
		case "absolutePath": return (T)absolutePath;
		case "directoryRoot": return (T)directoryRoot;
		default: return (T)structure.getString(propertyName);
		}
	}

	public String toString() {
		return name;
	}
}
