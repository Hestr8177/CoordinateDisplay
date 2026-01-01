package hestr8177.coordinatedisplay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CoordinateDisplayConfig {

    // Singleton-style access to Config
    public static final CoordinateDisplayConfig INSTANCE = new CoordinateDisplayConfig();

    // =====SETTINGS=====

    // General settings
    public boolean showCoordinates = true;

    // Text settings
    public int textAlpha = 255;
    public int textRed = 255;
    public int textGreen = 255;
    public int textBlue = 255;
    public boolean showTextShadow = true;
    public int textDecimals = 0;
    public int textStyle = 0;
    public int textSpacing = 1;

    // Background settings
    public boolean showBackground = true;
    public int backgroundAlpha = 100;
    public int backgroundRed = 75;
    public int backgroundGreen = 75;
    public int backgroundBlue = 75;
    public int backgroundPadding = 2;

    // Positional settings
    public int posX = 2;
    public int posY = 2;
    public Boolean centerHorizontally = false;
    public Boolean centerVertically = false;

    // Initialization of File and Gson
    private final transient File configFile = new File("config/hestr8177_coordinate_display_config.json");
    private transient final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    // Private constructor to ensure we only ever have 1 CoordinateDisplayConfig object, used by everything
    private CoordinateDisplayConfig() {
    }

    // Load config from JSON and update INSTANCE directly
    public void load() {
        if (!configFile.exists()) {
            loadDefaults(); // Load defaults and-
            save(); // -save them if no file exists
            return;
        }

        try (FileReader reader = new FileReader(configFile)) {
            CoordinateDisplayConfig loadedConfig = gson.fromJson(reader, CoordinateDisplayConfig.class); // Read JSON into a new object
            if (loadedConfig != null) {
                // General
                showCoordinates = loadedConfig.showCoordinates;

                // Text
                textAlpha = loadedConfig.textAlpha;
                textRed = loadedConfig.textRed;
                textGreen = loadedConfig.textGreen;
                textBlue = loadedConfig.textBlue;
                showTextShadow = loadedConfig.showTextShadow;
                textDecimals = loadedConfig.textDecimals;
                textStyle = loadedConfig.textStyle;
                textSpacing = loadedConfig.textSpacing;

                // Background
                showBackground = loadedConfig.showBackground;
                backgroundAlpha = loadedConfig.backgroundAlpha;
                backgroundRed = loadedConfig.backgroundRed;
                backgroundGreen = loadedConfig.backgroundGreen;
                backgroundBlue = loadedConfig.backgroundBlue;
                backgroundPadding = loadedConfig.backgroundPadding;

                // Positioning
                posX = loadedConfig.posX;
                posY = loadedConfig.posY;
                centerHorizontally = loadedConfig.centerHorizontally;
                centerVertically = loadedConfig.centerVertically;

            }
        } catch (IOException e) {
            Hestr8177CoordinateDisplay.LOGGER.error("Failed to load coordinate display config, using defaults", e);
            loadDefaults();
        }
    }

    // Save current INSTANCE to JSON
    public void save() {
        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            Hestr8177CoordinateDisplay.LOGGER.error("Failed to save coordinate display settings to config", e);
        }
    }

    // Reset all fields to default values
    public void loadDefaults() {
        // General
        showCoordinates = true;

        // Text
        textAlpha = 255;
        textRed = 255;
        textGreen = 255;
        textBlue = 255;
        showTextShadow = true;
        textDecimals = 0;
        textStyle = 0;
        textSpacing = 1;

        // Background
        showBackground = true;
        backgroundAlpha = 100;
        backgroundRed = 75;
        backgroundGreen = 75;
        backgroundBlue = 75;
        backgroundPadding = 2;

        // Positioning
        posX = 2;
        posY = 2;
        centerHorizontally = false;
        centerVertically = false;
    }
}
