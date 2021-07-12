package xyz.vitox;

import xyz.vitox.updater.RequestAPI;
import xyz.vitox.updater.Validator;

public class Main {

    /**
     * The Launcher is handing over the LAUNCHER_VERSION variable to this program
     * This program checks if a new version is available on the server
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Checking for updates...");

        RequestAPI requestAPI = new RequestAPI();
        String apiResponse = requestAPI.checkLauncherVersion(args[0]);

        Validator validator = new Validator();
        validator.updateIfNeeded(apiResponse);

    }
}
