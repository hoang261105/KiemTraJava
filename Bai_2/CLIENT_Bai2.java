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
import java.util.Scanner;
public class CLIENT_Bai2 {
    public static void main(String[] args) throws IOException {
      
        Socket client = new Socket("localhost", 9999);
        Scanner sc = new Scanner(System.in);
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());

        String studentId;
        boolean isValid = false;


        while (!isValid) {
            System.out.print("Nhap ma sinh vien: ");
            studentId = sc.nextLine().toUpperCase();
            if (studentId.matches("[A-Z0-9]+")) { // Regex kiểm tra chỉ gồm chữ và số
                isValid = true;
                dout.writeUTF(studentId); 
            } else {
                System.out.println("Ma sinh vien khong hop le. Vui long nhap lai.");
            }
        }


        int sumEven = din.readInt();
        String primeNumbers = din.readUTF();

        System.out.println("Tong cac so chan trong ma sinh vien la: " + sumEven);


     
        din.close();
        dout.close();
        client.close();
    }
}
