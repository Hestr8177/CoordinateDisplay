package hestr8177.coordinatedisplay;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

import java.util.Locale;


public class Hestr8177CoordinateDisplayClient implements ClientModInitializer {

	// Public variables
	public static double x,y,z = 0;
	public static int textWidth = 0;
	public static int textHeight = 10;

	// Reference to the singleton INSTANCE of config
	private final CoordinateDisplayConfig config = CoordinateDisplayConfig.INSTANCE;


	@Override
	public void onInitializeClient() {
		config.load(); // Load values stored in config

		// Register a tick event listener
		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			// Everything inside this lambda function gets called every tick
			LocalPlayer player = client.player;
			if (player != null) { 				// This runs every tick while the player exists
				x = player.getX();
				y = player.getY();
				z = player.getZ();
			}
		});

		// Attach rendering code right before the chat HUD
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath(Hestr8177CoordinateDisplay.MOD_ID, "before_chat"),
				this::render);
		}

	// Function that renders the graphics
	private void render(GuiGraphics context, DeltaTracker tickCounter) {
		Minecraft minecraft = Minecraft.getInstance(); // Get Minecraft instance

		// General settings
		boolean showCoordinates = config.showCoordinates;

		// Text formatting
		int decimals = config.textDecimals;
		int textStyle = config.textStyle;
		int textSpacing = config.textSpacing;
		String char1 = "", char2 = "", char3 = "", dividerString, coordinateText;

		// Switch cases for text styles
		switch (textStyle) {
			case 1 -> dividerString = " ".repeat(textSpacing) + "/" + " ".repeat(textSpacing);
			case 2 -> {
				char1 = "XYZ:" + " ".repeat(textSpacing);
				dividerString = "," + " ".repeat(textSpacing);
			}
			case 3 -> dividerString = "," + " ".repeat(textSpacing);
			case 4 -> dividerString = " ".repeat(textSpacing);
			case 5 -> {
				char1 = "X: ";
				char2 = "Y: ";
				char3 = "Z: ";
				dividerString = " ".repeat(textSpacing);
			}
			default -> {
				char1 = "XYZ:" + " ".repeat(textSpacing);
				dividerString = " ".repeat(textSpacing) + "/" + " ".repeat(textSpacing);
			}
		}

		// Reassign the value of coordinateText to use the different symbols based on the selected style.
		coordinateText = String.format(Locale.ROOT, char1 + "%." + decimals + "f" + dividerString + char2 + "%." + decimals + "f" + dividerString + char3 + "%." + decimals + "f", x, y, z);

		// Load text color and textShadow
		int textColor = ARGB.color(config.textAlpha, config.textRed, config.textGreen, config.textBlue);
		boolean textShadow = config.showTextShadow;

		// Positioning
		textWidth = minecraft.font.width(coordinateText);
		textHeight = minecraft.font.lineHeight;
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int posX = config.posX;
		int posY = config.posY;
		int drawX;
		int drawY;

		// Check for centering locks
		if (config.centerHorizontally) { // Horizontal lock
			drawX = (minecraft.getWindow().getGuiScaledWidth() - (Hestr8177CoordinateDisplayClient.textWidth - 1)) / 2;
		} else {
			drawX = Math.max(0, Math.min(posX, screenWidth - textWidth + 1));
		}
		if (config.centerVertically) { // Vertical lock
			drawY = (minecraft.getWindow().getGuiScaledHeight() - (Hestr8177CoordinateDisplayClient.textHeight - 1)) / 2;
		} else {
			drawY = Math.max(0, Math.min(posY, screenHeight - textHeight + 2));
		}

		// Background coloring
		int backgroundColor = ARGB.color(config.backgroundAlpha, config.backgroundRed, config.backgroundGreen, config.backgroundBlue);

		// Render the background shadow if ON
		int padding = config.backgroundPadding;

		if (showCoordinates) { // Only draw the coordinates if the user has it enabled (enabled by default)
			if (config.showBackground) {
				if (config.showTextShadow) { // Fill the background shadow slightly larger if textShadow is on (to make it feel centered)
					context.fill(drawX - config.backgroundPadding, drawY - padding, drawX + textWidth + (padding), drawY + textHeight + (padding - 1), backgroundColor);
				} else { // Else, draw it fill it in normally
					context.fill(drawX - config.backgroundPadding, drawY - padding, drawX + textWidth + (padding - 1), drawY + textHeight + (padding - 2), backgroundColor);
				}
			}

			// Render the text
			context.drawString(minecraft.font, coordinateText, drawX, drawY, textColor, textShadow);
		}
	}
}

