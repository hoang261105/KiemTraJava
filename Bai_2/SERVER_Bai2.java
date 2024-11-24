/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai_2;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
public class SERVER_Bai2 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Server đang cho ket noi...");

        while (true) {
            Socket client = server.accept();
            System.out.println("Ket noi server thanh cong!");

            new Thread(() -> {
                try {
                    DataInputStream din = new DataInputStream(client.getInputStream());
                    DataOutputStream dout = new DataOutputStream(client.getOutputStream());

                    String studentId = din.readUTF();
                    System.out.println("Ma sinh vien nhan duoc: " + studentId);

                    int sumEven = 0;
                    for (char c : studentId.toCharArray()) {
                        if (Character.isDigit(c)) {
                            int digit = Character.getNumericValue(c);
                            if (digit % 2 == 0) {
                                sumEven += digit;
                            }
                        }
                    }



                    dout.writeInt(sumEven);


                    din.close();
                    dout.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
