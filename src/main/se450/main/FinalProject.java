package main.se450.main;
/*
 * Name     : Mingfei Shao
 * Depaul#  : 1807687
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class FinalProject
 *
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import main.se450.gui.InfoDisplay;
import main.se450.gui.ShapeDisplay;
import main.se450.keyboard.Keyboard;
import main.se450.model.Configuration;
import main.se450.singletons.ConfigurationManager;

/**
 * The Class FinalProject, which is the main class of the entire program.
 */
public class FinalProject extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;// I added this to remove
													// the warning the Eclipse
													// generates

	/** The content. */
	private Container content;// used for the content pane

	/** The info output. */
	private InfoDisplay infoOutput;// contains score / ships

	/** The shape output. */
	private ShapeDisplay shapeOutput; // the output for the shape data

	/**
	 * Instantiates a new final project.
	 */
	public FinalProject() {
		// ConfigurationParser.loadConfiguration("configuration.json");
		ConfigurationManager.getConfigurationManager().loadConfigurationFromParser();
		Configuration configuration = ConfigurationManager.getConfigurationManager().getConfiguration();

		setSize(configuration.getWidth(), configuration.getHeight());// set the
																		// size
																		// of
																		// the
																		// window
																		// to
																		// 1440
																		// x 840
		setTitle("Final Project");// set the applications title
		setResizable(false);

		// centers application on desktop / parent container
		Dimension windowSize = getSize();
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

		setLocation(centerPoint.x - windowSize.width / 2, centerPoint.y - windowSize.height / 2);

		// Now create a panel to hold the move combo box and move description
		infoOutput = new InfoDisplay();

		// create the text area for displaying the shape(s) location
		shapeOutput = new ShapeDisplay();

		// give the panel an nice etched and titled border with the heading of
		// "Observe"
		infoOutput.setBorder(new TitledBorder(new EtchedBorder(), "Score"));

		// get the content pane, as it is were we'll place our controls
		content = getContentPane();

		// Attach the move panel to the content pane
		content.add(infoOutput);

		content.add(shapeOutput);

		// lets create a final spring layout to include the other layout
		// and add the rest of the controls
		SpringLayout contentLayout = new SpringLayout();

		// move panel setup
		// set move panels west border 30 pixels away from content panes west
		// border
		contentLayout.putConstraint(SpringLayout.WEST, infoOutput, 20, SpringLayout.WEST, content);

		// fix move panels east border -30 pixels away from content panes east
		// border
		contentLayout.putConstraint(SpringLayout.EAST, infoOutput, -20, SpringLayout.EAST, content);

		// fix move panels north border 10 pixels away from content panes north
		// border
		contentLayout.putConstraint(SpringLayout.NORTH, content, 10, SpringLayout.SOUTH, infoOutput);

		// fix scroll panes north border 10 pixels away from down buttons south
		// border
		contentLayout.putConstraint(SpringLayout.NORTH, shapeOutput, 50, SpringLayout.SOUTH, infoOutput);

		// fix scroll panes south border -10 pixels away from content panes
		// south border
		contentLayout.putConstraint(SpringLayout.SOUTH, shapeOutput, -10, SpringLayout.SOUTH, content);

		// fix scroll panes west border 30 pixels away from content panes west
		// border
		contentLayout.putConstraint(SpringLayout.WEST, shapeOutput, 25, SpringLayout.WEST, content);

		// fix scroll panes east border -30 pixels away from content panes east
		// border
		contentLayout.putConstraint(SpringLayout.EAST, shapeOutput, -25, SpringLayout.EAST, content);

		// set the content panes layout
		content.setLayout(contentLayout);

		// set the app to exit when the user presses the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setFocusable(true);

		// set the app to be visible
		setVisible(true);

		// final ArrayList<IShape> iShapes =
		// JSONFileShapeListFactory.makeShape("shapes.json");
		// ShapeList.getShapeList().addShapes(iShapes);
		// RandomShapeFactory.fillupShapeList();

		shapeOutput.setModels();

		Keyboard.bindGameKeys(shapeOutput);

	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	// the main entry point for the application
	public static void main(String[] args) {
		// create a new final project application
		new FinalProject();
	}
}
