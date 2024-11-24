/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai1;

/**
 *
 * @author Admin
 */

import java.net.*;
import java.util.Scanner;
public class CLIENT_Bai1 {
     public static void main(String[] args) {
        try {

            DatagramSocket client = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            
            int n = 0;
            boolean isValidInput = false;


            while (!isValidInput) {
                try {
                    System.out.print("Nhap so nguyen n: ");
                    n = sc.nextInt();
                    if (n <= 0) {
                        System.out.println("So n phai lon hon 0. Vui long nhap lai");
                    } else {
                        isValidInput = true;
                    }
                } catch (Exception e) {
                    System.out.println("Du lieu khong hop le. Vui long nhap so nguyen");
                    sc.nextLine(); 
                }
            }


            byte[] sendData = String.valueOf(n).getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9999;

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            client.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receivePacket);

            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Cac so nguyen to nho hon " + n + " va chia het cho 5 la " + result);

            client.close();
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}
