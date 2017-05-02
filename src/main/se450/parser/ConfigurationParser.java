package main.se450.parser;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.ConfigurationManager;
import main.se450.utilities.Extractor;

/**
 * The Class ConfigurationParser to handle the parse of configurations stored in
 * JSON file.
 */
public class ConfigurationParser {

	/**
	 * Instantiates a new configuration parser.
	 */
	private ConfigurationParser() {
	}

	/**
	 * Load configuration from JSON file.
	 *
	 * @param fileName
	 *            The JSON file name
	 * @return true, if the parsing is successfully completed.
	 */
	public static boolean loadConfiguration(String fileName) {
		boolean bLoadConfiguration = false;

		try {
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray jsonArray = (JSONArray) jsonObject.get("game");

			Iterator<?> jsonIterator = jsonArray.iterator();
			while (jsonIterator.hasNext()) {
				JSONObject conf = (JSONObject) jsonIterator.next();
				if (conf.containsKey("framespersecond")) {
					IStrategy iStrategy = StrategyFactory.makeStrategy(conf.get("borders").toString(), new Dimension(
							Extractor.extractInteger(conf.get("width")), Extractor.extractInteger(conf.get("height"))));

					ConfigurationManager.getConfigurationManager().setConfiguration(
							Extractor.extractInteger(conf.get("framespersecond")),
							Extractor.extractInteger(conf.get("repeatkeyspeed")),
							Extractor.extractInteger(conf.get("width")), Extractor.extractInteger(conf.get("height")),
							Extractor.extractInteger(conf.get("shapes")),
							Extractor.extractInteger(conf.get("shipwidth")),
							Extractor.extractInteger(conf.get("shipheight")),
							Extractor.extractFloat(conf.get("shotspeed")),
							Extractor.extractFloat(conf.get("shotdiameter")),
							Extractor.extractInteger(conf.get("shotlifetime")),
							Extractor.extractFloat(conf.get("forwardthrust")),
							Extractor.extractFloat(conf.get("reversethrust")),
							Extractor.extractFloat(conf.get("friction")), Extractor.extractFloat(conf.get("leftright")),
							Extractor.extractColor(conf.get("color")), iStrategy,
							Extractor.extractInteger(conf.get("lives")), Extractor.extractInteger(conf.get("shields")));
				}
			}
		} catch (FileNotFoundException eFileNotFound) {
		} catch (IOException eIOException) {
		} catch (ParseException eParseException) {
		} catch (BadStrategyException eBadStrategy) {
		}

		return bLoadConfiguration;
	}
}
