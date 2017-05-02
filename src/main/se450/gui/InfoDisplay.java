package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import main.se450.interfaces.IGameCompleteObservable;
import main.se450.interfaces.IGameOverObservable;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartEndlessObservable;
import main.se450.interfaces.IStartObservable;
import main.se450.observable.GameComplete;
import main.se450.observable.GameOver;
import main.se450.observable.Motion;
import main.se450.observable.Start;
import main.se450.observable.StartEndless;
import main.se450.singletons.InformationManager;

/**
 * The Class InfoDisplay defines and manages the two information display area on
 * the top portion of the main GUI.
 */
public class InfoDisplay extends JPanel implements IObservable, IStartObservable, IStartEndlessObservable,
		IGameOverObservable, IGameCompleteObservable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The score text field. */
	private JTextField scoreTextField = null; // score texts

	/** The ship text field. */
	private JTextField shipTextField = null; // ships text

	/**
	 * Instantiates a new information display area.
	 */
	public InfoDisplay() {
		scoreTextField = new JTextField("");
		shipTextField = new JTextField("");

		scoreTextField.setPreferredSize(new Dimension(705, 80));
		scoreTextField.setFont(scoreTextField.getFont().deriveFont(72f));
		scoreTextField.setBackground(Color.BLACK);
		scoreTextField.setForeground(new Color(224, 224, 224));

		shipTextField.setPreferredSize(new Dimension(705, 80));
		shipTextField.setFont(scoreTextField.getFont().deriveFont(72f));
		shipTextField.setBackground(Color.BLACK);
		shipTextField.setForeground(new Color(224, 224, 224));
		shipTextField.setHorizontalAlignment(JTextField.RIGHT);

		SpringLayout movePanelLayout = new SpringLayout();

		// now lets set up the controls and how they will be position
		// in accordance to one another

		// start button is 5 below top of panel
		movePanelLayout.putConstraint(SpringLayout.NORTH, scoreTextField, 5, SpringLayout.NORTH, this);

		// start button 5 above botton of panel
		movePanelLayout.putConstraint(SpringLayout.SOUTH, this, 10, SpringLayout.SOUTH, scoreTextField);

		// up button top adjust horizontally
		movePanelLayout.putConstraint(SpringLayout.WEST, scoreTextField, 15, SpringLayout.WEST, this);

		// down buttons left side with up button left side
		movePanelLayout.putConstraint(SpringLayout.EAST, scoreTextField, 720, SpringLayout.EAST, this);

		movePanelLayout.putConstraint(SpringLayout.NORTH, shipTextField, 5, SpringLayout.NORTH, this);

		// start button 5 above botton of panel
		movePanelLayout.putConstraint(SpringLayout.SOUTH, this, 10, SpringLayout.SOUTH, shipTextField);

		// down buttons left side with up button left side
		movePanelLayout.putConstraint(SpringLayout.EAST, shipTextField, -15, SpringLayout.EAST, this);

		movePanelLayout.putConstraint(SpringLayout.EAST, scoreTextField, -720, SpringLayout.EAST, this);

		this.setLayout(movePanelLayout);

		// add the buttons to the panel
		this.add(shipTextField);
		this.add(scoreTextField);

		// give the panel an nice etched and titled border with the heading of
		// "Observe"
		this.setBorder(new TitledBorder(new EtchedBorder(), "Information"));

		scoreTextField.setEnabled(false);
		scoreTextField.setFocusable(false);
		shipTextField.setEnabled(false);
		shipTextField.setFocusable(false);

		scoreTextField.setText("Press 1: Normal");
		shipTextField.setText("Press 2: Endless");

		Start.startObserving(this);
		StartEndless.startObserving(this);
		GameOver.startObserving(this);
		GameComplete.startObserving(this);
	}

	/**
	 * Updates both the left and the right information display text fields.
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public void update() {
		scoreTextField.setText("Score: " + Integer.toString(InformationManager.getInformationManager().getScore()));
		shipTextField.setText("Shields: " + Integer.toString(InformationManager.getInformationManager().getShields())
				+ "  Ship: " + Integer.toString(InformationManager.getInformationManager().getLives()));
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IStartObservable#start()
	 */
	@Override
	public void start() {
		Motion.startObserving(this);
	}

	/**
	 * Display game over message when the player has no more lives.
	 * 
	 * @see main.se450.interfaces.IGameOverObservable#gameOver()
	 */
	@Override
	public void gameOver() {
		shipTextField.setText("Game Over");
		Motion.stopObserving(this);
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IStartEndlessObservable#startEndless()
	 */
	@Override
	public void startEndless() {
		Motion.startObserving(this);
	}

	/**
	 * Display game complete message when the player has destroyed all the
	 * shapes in normal mode.
	 * 
	 * @see main.se450.interfaces.IGameCompleteObservable#gameComplete()
	 */
	@Override
	public void gameComplete() {
		shipTextField.setText("Game Complete");
		Motion.stopObserving(this);
		repaint();
	}

}
