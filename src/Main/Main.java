package Main;

import Util.CMDLine;
import Util.Dbg;

import gui.JFX;

public class Main {
	public static void main(String[] args) {
		CMDLine.addFlags("dbg", "Enable debugging");
		CMDLine.parse(args);
		
		Dbg.setDebugging(CMDLine.hasOption("dbg"));
		Dbg.setPromptPrefix("");

		JFX.initialize(args);
		Dbg.log("Welcome to " + JFX.APP_NAME);
	}
}
