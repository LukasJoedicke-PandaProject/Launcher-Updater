package xyz.vitox.updater;

import okhttp3.*;

import java.util.Arrays;

public class RequestAPI {

    OkHttpClient client = new OkHttpClient().newBuilder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).build();

    /**
     * Request to the API to check the launcher version
     * @param launcherVersion
     * @return
     */
    public String checkLauncherVersion(String launcherVersion) {
        return makePOSTRequest("/v1/checkLauncherVersion" +
                        "",
                "{\n" +
                        "   \"validation\":\"PandaUpdater-v1\",\n" +
                        "   \"launcherVersion\":\""+ launcherVersion +"\"\n" +
                        "}");
    }

    public String makePOSTRequest(String restRoute, String bodyText) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, bodyText);
            Request request = new Request.Builder()
                    .url("https://api.pandautils.com" + restRoute)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "PandaLauncher")
                    .addHeader("Authorization", "Basic cGFuZGFsYXVuY2hlcjpid3RlcENvZUU0M2xjSWw2b21XelR2c2tjbUhXUzFEOUl5bk9jSmRoNkQySEZTUWwxSXZNVkZKcg==")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

}
