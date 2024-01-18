package org.example.functions;

import org.example.classes.Motion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    public static String getBodyRequest(double timestamp){
        String body = "";

        try {
            URL url = new URL("http://192.168.147.131:8080/ords/capteurs/motion/"+timestamp);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try(InputStream is = con.getInputStream()){
                body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }

        }catch (Exception e) {
            System.out.println("Error in getting details : " + e);
        }
        return body;
    }

    public static List<Motion> parseMotions(String json){
        List<Motion> listmotion = new ArrayList<>();
        JSONArray parsed = new JSONObject(json).getJSONArray("items");

        for(int i = 0; i < parsed.length(); i++){
            JSONObject motionObject = parsed.getJSONObject(i);
            Motion motion = new Motion(
                    motionObject.getDouble("accx"),
                    motionObject.getDouble("accy"),
                    motionObject.getDouble("accz"),
                    motionObject.getDouble("gyrox"),
                    motionObject.getDouble("gyroy"),
                    motionObject.getDouble("gyroz"),
                    motionObject.getString("class"),
                    motionObject.getDouble("timestamp")
            );
            listmotion.add(motion);
        }
        return listmotion;
    }

    public static void sendImage(byte[] image,int timestamp,String etat, Date date){
        String urlString = "http://192.168.147.131:8080/ords/capteurs/motion/image";

        try{
            //File fi = new File("\\C:\\Users\\Maelm\\Pictures\\Screenshots\\20200911_185037.png\\");
            //byte[] byteArray = Files.readAllBytes(fi.toPath());
            //String encoded = Base64.getEncoder().encodeToString(byteArray);
            String encoded = Base64.getEncoder().encodeToString(image);

            JSONObject json = new JSONObject();
            json.put("img", encoded);
            json.put("timestamp", timestamp);
            json.put("date", date);
            json.put("etat", etat);

            String urlParameters = json.toString();
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
            int postDataLength = postData.length;

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST"); //Verbe POST
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            con.setUseCaches(false);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(postData);

            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            rd.close();
            wr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Motion> filtrerListeMotion(List<Motion> listeMotions, double firstTimestamp, double leastTimestamp) {
        // Trier la liste par timestamp
        //listeMotions.sort(Comparator.comparingDouble(Motion::getTimestamp));

        // Filtrer la liste pour ne conserver que les éléments entre firstTimestamp et leastTimestamp
        List<Motion> listTriee = listeMotions.stream()
                .filter(motion -> motion.getTimestamp() >= firstTimestamp && motion.getTimestamp() <= leastTimestamp)
                .collect(Collectors.toList());

        return listTriee;
    }

    public static Motion getMotionByTimestamp(List<Motion> motions, double timestamp) {
        for (Motion motion : motions) {
            if (motion.getTimestamp() == timestamp) {
                return motion;
            }
        }
        return null; // Retourner null si l'objet avec le timestamp n'est pas trouvé
    }

    public static void main(String[] args) {
        String bodyRequest = Functions.getBodyRequest(818982);

        List<Motion> li =  Functions.parseMotions(bodyRequest);

        for(var i : li)
            System.out.println(i);
    }
}
