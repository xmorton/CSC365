package com.company;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static BPTree bTree;
    public static void main(String[] args) throws IOException {
        File inputFile = new File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 2\\src\\com\\company\\input.txt");
        makeFile(inputFile);
	    String cmd = new String();
        Scanner scan = new Scanner(System.in);
        int tsize = 0;
        int isize = 0;
        int patentId = 0;
        int index = 0;
        String[][] storage;
        System.out.println("Enter the size of the nodes in the B+Tree");
        tsize = scan.nextInt();
        bTree = new BPTree(tsize);
        System.out.println("Enter how many scans will be entered.");
        isize = scan.nextInt();
        makeInput(isize, inputFile);
        storage = new String[isize][isize];
        storage = popTree(storage, inputFile);
        while (!cmd.equals("yes")) {
            System.out.println("Enter patent Id");
            patentId = scan.nextInt();
           index = bTree.search(patentId);
           if (index != -1) {
               for (int j = 0; j < storage.length; j++) {
                   if (storage[index][j] != null) {
                       System.out.println(storage[index][j]);
                   } else {
                       break;
                   }
               }
           } else {
               System.out.println("That patent does not exist.");
           }
            System.out.println("Wish to quit?");
            cmd = scan.next();
        }
    }

    /*This method makes the input file that holds no data*/
    public static void makeFile(File input) {
        try {

            boolean fileMade = input.createNewFile();
            if (fileMade) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }

        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }

    }

    /*This method populates the empty file with random data while insuring that there is some repeat patent Ids */
    public static void makeInput(int inputSize, File input) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(input));
        Random random = new Random();
        String repeatId = "123456789";
        String id = null;
        String rest = null;
        int id1 = 0, id2 = 0, id3 = 0, id4 = 0, id5 = 0, id6 = 0, id7 = 0, id8 = 0, id9 = 0;
        int mm = 0, dd = 0, yy = 0, hh = 0, min= 0, ss = 0;
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0, c7 = 0;
        for (int i = 0; i < inputSize; i++) {
            //This allows for there to be repeat Ids
            if (i % 4 == 0) {
                id = repeatId;
            } else {
                id1 = random.nextInt(10);
                id2 = random.nextInt(10);
                id3 = random.nextInt(10);
                id4 = random.nextInt(10);
                id5 = random.nextInt(10);
                id6 = random.nextInt(10);
                id7 = random.nextInt(10);
                id8 = random.nextInt(10);
                id9 = random.nextInt(10);
                id = String.valueOf(id1) + String.valueOf(id2) + String.valueOf(id3) + String.valueOf(id4) + String.valueOf(id5)
                        + String.valueOf(id6) + String.valueOf(id7) + String.valueOf(id8) + String.valueOf(id9);
            }
            mm = random.nextInt(13);
            if (mm == 0) {
                mm = mm+1;
            }
            //Making sure that the number of days in the month corresponds to real life
            if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
                dd = random.nextInt(32);
            } else if (mm == 2) {
                dd = random.nextInt(29);
            } else {
                dd = random.nextInt(31);
            }
            if (dd == 0) {
                dd = dd + 1;
            }
            yy = random.nextInt(19);

            //Adding zeros to the front of the single digit numbers so that all of the scan Ids are the same length
            if (mm < 10 && dd < 10 && yy < 10) {
                rest = "0" + String.valueOf(mm) + "0" + String.valueOf(dd) + "0" + String.valueOf(yy);
            } else if (mm < 10 && dd < 10) {
                rest = "0" + String.valueOf(mm) + "0" + String.valueOf(dd) + String.valueOf(yy);
            } else if (mm < 10 && yy < 10) {
                rest = "0" + String.valueOf(mm) + String.valueOf(dd) + "0" + String.valueOf(yy);
            } else if (mm < 10) {
                rest = "0" + String.valueOf(mm) + String.valueOf(dd) + String.valueOf(yy);
            } else if (dd < 10 && yy < 10) {
                rest = String.valueOf(mm) + "0" + String.valueOf(dd) + "0" + String.valueOf(yy);
            } else if (dd < 10) {
                rest = String.valueOf(mm) + "0" + String.valueOf(dd) + String.valueOf(yy);
            } else if (yy < 10) {
                rest = String.valueOf(mm) + String.valueOf(dd) + "0" + String.valueOf(yy);
            } else {
                rest = String.valueOf(mm) + String.valueOf(dd) + String.valueOf(yy);
            }
            hh = random.nextInt(13);
            //Doing the same as above but simpler
            if (hh == 0) {
                hh = hh + 1;
            }
            if (hh < 10) {
                rest = rest + "0" + String.valueOf(hh);
            } else {
                rest = rest + String.valueOf(hh);
            }
            min = random.nextInt(61);
            if (min < 10) {
                rest = rest + "0" + String.valueOf(min);
            } else {
                rest = rest + String.valueOf(min);
            }
            ss = random.nextInt(61);
            if (ss < 10) {
                rest = rest + "0" + String.valueOf(ss);
            } else {
                rest = rest + String.valueOf(ss);
            }
            c1 = random.nextInt(10);
            c2 = random.nextInt(10);
            c3 = random.nextInt(10);
            c4 = random.nextInt(10);
            c5 = random.nextInt(10);
            c6 = random.nextInt(10);
            c7 = random.nextInt(10);
            rest = rest + "." + String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + String.valueOf(c5) +
                    String.valueOf(c6) + String.valueOf(c7);
            //System.out.println(id + rest);
            //Writing to the input file
            writer = new BufferedWriter(new FileWriter(input, true));
            writer.append(id+rest);
            writer.append("\n");
            writer.close();

        }
    }

    /*This method populates both the B+Tree as well as the Double String array used for storage.
     * The method returns the array to be used to find the inputted scan Ids */
    public static String[][] popTree(String [][] storage, File input) throws IOException {
        FileReader reader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String id = null;
        String line = null;
        int count = 0;
        int intId = 0;
        while ((line = bufferedReader.readLine()) != null) {
            id = line.substring(0,9);
            intId = Integer.parseInt(id);
            //Checking if the id is already in the tree, if check = -1 then it is not, else it already exists in the tree.
            int check = bTree.search(intId);
            if(check == -1) {
                for (int i = 0; i < storage.length; i++) {
                    if (storage[i][0] == null) {
                        storage[i][0] = line;
                        bTree.insert(intId, i);
                        break;
                    }
                }
            } else {
                /*Since the patent id is already in the tree, add the new scan id to the column of the array that
                corresponds to that patent id*/
                for (int j = 0; j < storage.length; j++) {
                    if (storage[check][j] == null) {
                        storage[check][j] = line;
                        break;
                    }
                }
            }
        }
        return storage;
    }





}
