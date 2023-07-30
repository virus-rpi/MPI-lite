package com.virusrpi;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Client implements ModInitializer {
	public static final Client INSTANCE = new Client();
	public Logger logger = LogManager.getLogger(Client.class);

	private static final MinecraftClient mc = MinecraftClient.getInstance();
	private static final API api = new API(mc);

	public static API getAPI() {
		return api;
	}

	@Override
	public void onInitialize(){
		logger.info("Startup...");
	}

	public void onTick(){
		if (api.getDisconnect()) {
			try {
				mc.disconnect(new MultiplayerScreen(new TitleScreen()));
			} catch (Exception ignored) {
			}
			api.resetDisconnect();
		}
		if (api.getMultiplayerScreen()) {
			try {
				mc.setScreen(new MultiplayerScreen(new TitleScreen()));
			} catch (Exception ignored) {
			}
			api.resetMultiplayerScreen();
		}
	}
}
