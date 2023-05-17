package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static final String TARGET_URL = "https://plvr.land.moi.gov.tw//Download?fileName=h_lvr_land_a.csv";

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Task(), 0, 86400000); //每24小時執行一次
    }

    private static class Task extends TimerTask {
        public void run() {
            try {
                URL url = new URL(TARGET_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null) {
                    // 在這裡處理抓取到的房價資料
                    System.out.println(line);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
