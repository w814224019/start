package com.net.start.controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TimerTask extends java.util.TimerTask {
//    private Position position;
    @Override
    public void run() {
        //随机生成坐标
        //lon的取值范围是121.4600-121.4800
        //lat的取值范围是31.2200-31.2800
        Random randomLon = new Random();
        Random randomLat = new Random();
        double lon = ((double)(1214600+randomLon.nextInt(200)))/10000;
        double lat = ((double)(312200+randomLon.nextInt(600)))/10000;
//        this.position.setLon(lon);
//        this.position.setLat(lat);
        //把他写到文件里//D:\\开发工具\\tts9\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller
        File positionFile = new File("D:\\开发工具\\tts9\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller\\position.txt");//文件路径
        try{
            FileWriter fw = new FileWriter(positionFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf(lon)+"\n");//这里要写String类型,先写经度
                bw.write(String.valueOf(lat)+"\n");//写纬度
                bw.flush();
            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
