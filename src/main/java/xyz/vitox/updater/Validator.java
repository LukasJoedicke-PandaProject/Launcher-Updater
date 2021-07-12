package xyz.vitox.updater;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Validator {

    /**
     * Check if a update for the launcher is needed
     * If yes: Download the update and restart the launcher
     * If not: Continue
     * @param apiResponse
     */
    public void updateIfNeeded(String apiResponse) {

        JsonObject responseJson = new Gson().fromJson(apiResponse, JsonObject.class);
        String newVersion = responseJson.get("check_launcher_version").getAsString();
        boolean updateNeeded = responseJson.get("need_update").getAsBoolean();

        if (updateNeeded) {
            try {
                System.out.println("INFO: Found a new Launcher Version (" + newVersion + ")");
                Runtime.getRuntime().exec("taskkill /F /IM PandaLauncher.exe");
                Thread.sleep(500);
                DeviceUtil.downloadFile("https://localhost/files/PandaLauncher.exe", "PandaLauncher.exe", "PandaLauncher");
                System.out.println("INFO: Download succesfull.");
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "cmd", "/k", "PandaLauncher.exe"});
                System.out.println("INFO: Restart required");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("INFO: Found newest Launcher version.");
        }
    }

}
