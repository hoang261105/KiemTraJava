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
public class SERVER_Bai1 {
     public static void main(String[] args) {
        try {
            // Tạo socket server
            DatagramSocket server = new DatagramSocket(9999);
            System.out.println("Server dang chay...");

            while (true) {
                // Nhận số n từ client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                server.receive(receivePacket);

                String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int n = Integer.parseInt(receivedString);

                // Tìm các số nguyên tố nhỏ hơn n và chia hết cho 5
                StringBuilder primeNumbers = new StringBuilder();
                for (int i = 2; i < n; i++) {
                    if (isPrime(i) && i % 5 == 0) {
                        primeNumbers.append(i).append(" ");
                    }
                }

                // Gửi kết quả lại cho client
                byte[] sendData = primeNumbers.toString().getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                server.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Loi: " + e.getMessage());
        }
    }

    // Hàm kiểm tra số nguyên tố
    private static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
