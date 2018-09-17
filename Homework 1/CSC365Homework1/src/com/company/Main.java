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
        File fileName = null;
        System.out.println("Which algorithm: sundaram or eratosthenes?");
        cmd = scan.next();
        if (cmd.contains("sundaram")) {
            fileName = new File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 1\\out\\production\\CSC365Homework1\\com\\company\\outputSundaram.txt");
            SieveSundaram sundaram = new SieveSundaram(fileName);
            sundaram.makeFile();
            System.out.println("What size of the array");
            size = scan.nextInt();
            sundaram.compute(size);

        } else if (cmd.contains("eratosthenes")) {
            fileName = new  File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 1\\out\\production\\CSC365Homework1\\com\\company\\outputEratosthenes.txt");
            SieveEratosthenes eratosthenes = new SieveEratosthenes(fileName);
            eratosthenes.makeFile();
            System.out.println("What is the size of the array");
            size = scan.nextInt();
            eratosthenes.compute(size);

        } else {
            System.out.println("Incorrect output, ending program");
        }

        }
    }





