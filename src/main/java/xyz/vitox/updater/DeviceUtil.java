package xyz.vitox.updater;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class DeviceUtil {

    /**
     * Download a file from an URL
     * @param fromUrl
     * @param localFileName
     * @param agent
     */
    public static void downloadFile(String fromUrl, String localFileName, String agent) {
        try {
            File localFile = new File(localFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
            localFile.createNewFile();
            URL url = new URL(fromUrl);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(localFileName));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", agent);
            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[25 * 1024 * 1024];

            int numRead;
            int totalProccessed = 0;
            while ((numRead = in.read(buffer)) != -1) {
                totalProccessed += numRead;
                out.write(buffer, 0, numRead);
            }
            in.close();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
