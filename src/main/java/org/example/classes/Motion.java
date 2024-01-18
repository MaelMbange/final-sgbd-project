package org.example.classes;

import org.json.JSONObject;

public class Motion {
    final double accx;
    final double accy;
    final double accz;
    final double gyrox;
    final double gyroy;
    final double gyroz;
    final String type;
    final double timestamp;

    public Motion(double accx, double accy, double accz, double gyrox, double gyroy, double gyroz, String type, double timestamp) {
        this.accx = accx;
        this.accy = accy;
        this.accz = accz;

        this.gyrox = gyrox;
        this.gyroy = gyroy;
        this.gyroz = gyroz;

        this.type = type;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Motion{" +
                "accx=" + accx +
                ", accy=" + accy +
                ", accz=" + accz +
                ", gyrox=" + gyrox +
                ", gyroy=" + gyroy +
                ", gyroz=" + gyroz +
                ", class='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("accx", accx);
        json.put("accy", accy);
        json.put("accz", accz);
        json.put("gyrox", gyrox);
        json.put("gyroy", gyroy);
        json.put("gyroz", gyroz);
        json.put("class", type);
        json.put("timestamp", timestamp);

        return json.toString();
    }

    public static Motion fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);

        double accx = jsonObject.getDouble("accx");
        double accy = jsonObject.getDouble("accy");
        double accz = jsonObject.getDouble("accz");
        double gyrox = jsonObject.getDouble("gyrox");
        double gyroy = jsonObject.getDouble("gyroy");
        double gyroz = jsonObject.getDouble("gyroz");
        String type = jsonObject.getString("class");
        double timestamp = jsonObject.getDouble("timestamp");

        return new Motion(accx, accy, accz, gyrox, gyroy, gyroz, type, timestamp);
    }

    public double getAccx() {
        return accx;
    }

    public double getAccy() {
        return accy;
    }

    public double getAccz() {
        return accz;
    }

    public double getGyrox() {
        return gyrox;
    }

    public double getGyroy() {
        return gyroy;
    }

    public double getGyroz() {
        return gyroz;
    }

    public String getType() {
        return type;
    }

    public double getTimestamp() {
        return timestamp;
    }
}