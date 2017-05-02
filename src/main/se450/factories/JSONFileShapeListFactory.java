package main.se450.factories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.constants.ShapeSize;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.BadStrategyException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.DisplayManager;
import main.se450.utilities.Extractor;

/**
 * A factory for creating ShapeList objects by using the shapes.json file.
 */
public class JSONFileShapeListFactory {

	/**
	 * Instantiates a new JSON file shape list factory.
	 */
	private JSONFileShapeListFactory() {
	}

	/**
	 * Make new shapes from a given JSON file.
	 *
	 * @param fileName
	 *            The file name of the JSON file where contains the definition
	 *            of shapes to be created.
	 * @return the array list This returns the ArrayList that contains all the
	 *         created shapes.
	 */
	public static ArrayList<IShape> makeShape(String fileName) {
		ArrayList<IShape> iShapes = new ArrayList<IShape>();

		try {
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray jsonArray = (JSONArray) jsonObject.get("shapes");

			Iterator<?> jsonIterator = jsonArray.iterator();
			while (jsonIterator.hasNext()) {
				JSONObject someShape = (JSONObject) jsonIterator.next();
				if (someShape.containsKey("type")) {
					try {
						IStrategy iStrategy = StrategyFactory.makeStrategy(someShape.get("borders").toString(),
								DisplayManager.getDisplayManager().getDimension());

						IShape iShape = ShapeFactory.makeShape(someShape.get("type").toString().toUpperCase(),
								Extractor.extractFloat(someShape.get("left")),
								Extractor.extractFloat(someShape.get("top")),
								Extractor.extractFloat(someShape.get("right")),
								Extractor.extractFloat(someShape.get("bottom")),
								Extractor.extractFloat(someShape.get("x")), Extractor.extractFloat(someShape.get("y")),
								Extractor.extractFloat(someShape.get("rotation")),
								Extractor.extractColor(someShape.get("color")), iStrategy,
								ShapeSize.valueOf(someShape.get("size").toString().toUpperCase()),
								Extractor.extractInteger(someShape.get("score")),
								Extractor.extractInteger(someShape.get("multiplier")),
								Extractor.extractInteger(someShape.get("children")));
						ShapeFactory.moveShapeToBorderRandom(iShape);
						iShapes.add(iShape);
					} catch (BadStrategyException e) {
						System.out.println(e);
					} catch (BadShapeException e) {
						System.out.println(e);
					} catch (UnsupportedShapeException e) {
						System.out.println(e);
					}
				}
			}
		} catch (FileNotFoundException eFileNotFound) {
		} catch (IOException eIOException) {

		} catch (ParseException eParseException) {
		}

		return iShapes;
	}
}
