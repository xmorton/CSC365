package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String quit = new String();
        Scanner scan = new Scanner(System.in);

        try {
            File fileName =  new File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 1\\out\\production\\CSC365Homework1\\com\\company\\output.txt");
            /*If file gets created then the createNewFile()
             * method would return true or if the file is
             * already present it would return false
             */
            boolean fvar = fileName.createNewFile();
            if (fvar){
                System.out.println("File has been created successfully");
            }
            else{
                System.out.println("File already present at the specified location");
            }

            while (!quit.equals("quit")) {
                System.out.println("Enter an input int");
                String str = String.valueOf(scan.nextInt());
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                writer.append(' ');
                writer.append(str);

                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }

    }
}
