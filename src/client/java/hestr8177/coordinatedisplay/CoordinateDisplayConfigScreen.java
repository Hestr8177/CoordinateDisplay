package hestr8177.coordinatedisplay;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;


public class CoordinateDisplayConfigScreen extends OptionsSubScreen {

    // Create reference to the singleton INSTANCE of config
    private final CoordinateDisplayConfig config = CoordinateDisplayConfig.INSTANCE;


    // Constructor that sets the parent screen and title
    public CoordinateDisplayConfigScreen(Screen parent) {
        super(
                parent, // The parent screen to go back to once the player exits
                Minecraft.getInstance().options,
                Component.literal("Coordinate Display Settings")
        );
    }


    // Function where options are added
    @Override
    protected void addOptions() {
        assert this.list != null;

        // ===== Definitions of the buttons =====

        // Background shadow ON/OFF
        OptionInstance<Boolean> showCoordinates = OptionInstance.createBoolean(
                "Show Coordinates", config.showCoordinates,
                value -> config.showCoordinates = value);
        // Background shadow ON/OFF
        OptionInstance<Boolean> backgroundShadow = OptionInstance.createBoolean(
                "Background Shadow", config.showBackground,
                value -> config.showBackground = value);
        // Text shadow ON/OFF
        OptionInstance<Boolean> textShadow = OptionInstance.createBoolean(
                "Text Drop Shadow",
                config.showTextShadow,
                value -> config.showTextShadow = value
        );
        // Background alpha SLIDER
        OptionInstance<Integer> backgroundAlpha = new OptionInstance<>(
                "Background Alpha",
                OptionInstance.noTooltip(),
                (component, value) ->
                        Component.literal("Background Alpha: " + value),
                new OptionInstance.IntRange(0, 255),
                config.backgroundAlpha,
                value -> config.backgroundAlpha = value
        );
        // Text Alpha SLIDER
        OptionInstance<Integer> textAlpha = new OptionInstance<>(
                "Text Alpha",
                OptionInstance.noTooltip(),
                (component, value) ->
                        Component.literal("Text Alpha: " + value),
                new OptionInstance.IntRange(0, 255),
                config.textAlpha,
                value -> config.textAlpha = value
        );
        // Background red SLIDER
        OptionInstance<Integer> backgroundRed = new OptionInstance<>(
                "Background Red",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Background Red: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.backgroundRed,
                value -> config.backgroundRed = value
        );
        // Text red SLIDER
        OptionInstance<Integer> textRed = new OptionInstance<>(
                "Text Red",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Text Red: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.textRed,
                value -> config.textRed = value
        );
        // Background green SLIDER
        OptionInstance<Integer> backgroundGreen = new OptionInstance<>(
                "Background Green",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Background Green: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.backgroundGreen,
                value -> config.backgroundGreen = value
        );
        // Text green SLIDER
        OptionInstance<Integer> textGreen = new OptionInstance<>(
                "Text Green",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Text Green: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.textGreen,
                value -> config.textGreen = value
        );
        // Background blue SLIDER
        OptionInstance<Integer> backgroundBlue = new OptionInstance<>(
                "Background Blue",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Background Blue: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.backgroundBlue,
                value -> config.backgroundBlue = value
        );
        // Text blue SLIDER
        OptionInstance<Integer> textBlue = new OptionInstance<>(
                "Text Blue",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Text Blue: " + value)),
                new OptionInstance.IntRange(0, 255),
                config.textBlue,
                value -> config.textBlue = value
        );
        // Background padding SLIDER
        OptionInstance<Integer> backgroundPadding = new OptionInstance<>(
                "Background Padding",
                OptionInstance.noTooltip(),
                ((component, value) -> {
                    if (value == 255) {
                        return Component.literal("Padding: I can't see!");
                    } else {
                        return Component.literal("Padding: " + value);
                    }
                }),
                new OptionInstance.IntRange(0, 255),
                config.backgroundPadding,
                value -> config.backgroundPadding = value
        );
        // Position X SLIDER
        OptionInstance<Integer> posX = new OptionInstance<>(
                "Position X",
                OptionInstance.noTooltip(),
                ((component, value) -> {
                    if (value == (int) Math.floor((double) (Minecraft.getInstance().getWindow().getGuiScaledWidth() - (Hestr8177CoordinateDisplayClient.textWidth - 1)) /2)) {
                        return Component.literal("Position X: Center");
                    } else {
                        return Component.literal("Position X: " + value);
                    }
                }),
                new OptionInstance.IntRange(0, Minecraft.getInstance().getWindow().getGuiScaledWidth()),
                config.posX,
                value -> config.posX = value
        );
        // Position Y SLIDER
        OptionInstance<Integer> posY = new OptionInstance<>(
                "Position Y",
                OptionInstance.noTooltip(),
                ((component, value) -> {
                    if (value == (int) Math.floor((double) (Minecraft.getInstance().getWindow().getGuiScaledHeight() - (Hestr8177CoordinateDisplayClient.textHeight - 1)) /2)) {
                        return Component.literal("Position Y: Center");
                    } else {
                        return Component.literal("Position Y: " + value);
                    }
                }),
                new OptionInstance.IntRange(0, Minecraft.getInstance().getWindow().getGuiScaledHeight()),
                config.posY,
                value -> config.posY = value
        );
        // Amount decimal points SLIDER
        OptionInstance<Integer> textDecimals = new OptionInstance<>(
                "Decimal Points",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Decimal Points : " + value)),
                new OptionInstance.IntRange(0, 14),
                config.textDecimals,
                value -> config.textDecimals = value
        );
        // Text style SLIDER
        OptionInstance<Integer> textStyle = new OptionInstance<>(
                "Text Style",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Text Style: " + value)),
                new OptionInstance.IntRange(0, 5),
                config.textStyle,
                value -> config.textStyle = value
        );
        // Text spacing SLIDER
        OptionInstance<Integer> textSpacing = new OptionInstance<>(
                "Text Spacing",
                OptionInstance.noTooltip(),
                ((component, value) -> Component.literal("Text Spacing: " + value)),
                new OptionInstance.IntRange(0, 10),
                config.textSpacing,
                value -> config.textSpacing = value
        );
        // Center lock horizontally ON/OFF
        OptionInstance<Boolean> centerHorizontally = OptionInstance.createBoolean(
                "Horizontal Center Lock", config.centerHorizontally,
                value -> config.centerHorizontally = value);
        // Center lock vertically ON/OFF
        OptionInstance<Boolean> centerVertically = OptionInstance.createBoolean(
                "Vertical Center Lock", config.centerVertically,
                value -> config.centerVertically = value);


        // Positions of the buttons in the settings menu
        this.list.addBig(showCoordinates);                        // Toggles the display ON/OFF
        this.list.addSmall(backgroundShadow, textShadow);         // Background Shadow and Text Shadow
        this.list.addSmall(backgroundAlpha, textAlpha);           // Slider:  Alpha (0–255)
        this.list.addSmall(backgroundRed, textRed);               // Slider: Red (0-255)
        this.list.addSmall(backgroundGreen, textGreen);           // Slider: Green (0-255)
        this.list.addSmall(backgroundBlue, textBlue);             // Slider: Blue (0-255)
        this.list.addSmall(backgroundPadding, textSpacing);       // Slider: Padding and spacing
        this.list.addSmall(textDecimals, textStyle);              // Slider: Decimal Points & Text Style
        this.list.addSmall(posX, posY);                           // Slider: Position
        this.list.addSmall(centerHorizontally, centerVertically); // Centering lock options
    }


    @Override
    public void onClose() {
        config.save();   // Save current config when the screen is closed
        super.onClose(); // Call super to go back to the parent screen
    }


    // Init that calls super.init() - creates list & calls addOptions()
    @Override
    protected void init() {
        super.init();

        // Button to restore defaults
        this.addRenderableWidget(
                Button.builder(Component.literal("Restore Defaults"), button -> {
                            config.loadDefaults(); // Load defaults
                            config.save();         // Save to config
                            Minecraft.getInstance().setScreen(new CoordinateDisplayConfigScreen(this.lastScreen)); // Update the settings screen
                        }).bounds(25, this.height - 17 - this.font.lineHeight, 100, 20)                 // x, y, width, height
                        .build()
        );
    }

}
