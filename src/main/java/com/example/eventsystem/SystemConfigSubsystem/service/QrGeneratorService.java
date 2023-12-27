package com.example.eventsystem.SystemConfigSubsystem.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;


// category event date area seat
@Service
public class QrGeneratorService {


    public BufferedImage generateQRCode(String eventName, String date, String areaName,String seatName) throws Exception {
        return generateQRCodeImage(eventName,date,areaName,seatName);


    }

    public static BufferedImage generateQRCodeImage(String eventName, String date, String areaName, String seatName) throws Exception {
        StringBuilder str = new StringBuilder();
        str = str.append("Event:").append(eventName).append("\n")
                .append("Date:").append(date).append("\n").append("Area:").append(areaName).append("\n").append("Seat:").append(seatName);
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(str.toString(), BarcodeFormat.QR_CODE, 300, 300);

        var bufferedImage =  MatrixToImageWriter.toBufferedImage(bitMatrix);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight() + 100; // Adjust this value based on your subtext size
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combinedImage.createGraphics();

        // Draw the QR code image
        g.drawImage(bufferedImage, 0, 0, null);

        // Draw the subtext below the QR code
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font and size as needed
//        g.drawString(String.valueOf(str), 5, height - 5);

        g.drawString("Event:"+eventName,20,height-80);
        g.drawString("Date:"+date,20,height-60);
        g.drawString("Area:"+areaName,20,height-40);
        g.drawString("Seat:"+seatName,20,height-20);

        g.dispose();

        return combinedImage;
    }





}
