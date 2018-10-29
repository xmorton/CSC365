package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    String cmd = new String();
        Scanner scan = new Scanner(System.in);
        int size = 0;
        int[][] storage;
        File fileName = new File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 2\\src\\com\\company\\input.txt");
        BufferedWriter writer;
        BPTree bTree;
        System.out.println("Enter the size of the nodes in the B+Tree");
        size = scan.nextInt();
        bTree = new BPTree(size);
        try {

            boolean fileMade = fileName.createNewFile();
            if (fileMade) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }

        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
        System.out.println("Enter the number of files");
        size = scan.nextInt();
        writer = new BufferedWriter(new FileWriter(fileName, true));
    }



}
