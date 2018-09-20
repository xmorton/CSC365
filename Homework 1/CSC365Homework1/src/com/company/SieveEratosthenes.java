package com.company;

import java.io.*;
import java.util.BitSet;

public class SieveEratosthenes {

    private File output;
    private BufferedWriter writer;
    private String index = null;

    public SieveEratosthenes(File f) throws IOException {
        output = f;
        writer = new BufferedWriter(new FileWriter(output));
    }

    // Makes the file that the output will be put on
    public void makeFile() {
        try {

            boolean fileMade = output.createNewFile();
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

    //Method to compute the primes
    public void compute(int input) throws IOException {

        Long startTime = System.currentTimeMillis();
        BitSet primes = new BitSet(input + 1);
        int counter = 1;
        //Set all values to true
        for (int i = 0; i < input; i++) {
            primes.set(i);
        }

        for (int p = 2; p * p <= input; p++) {
            //If the value at p is true then turn all multiples of p to false
            if (primes.get(p)) {
                for (int j = p * 2; j <= input; j += p ) {
                    primes.set(j, false);
                }
            }
        }
        long endTime = System.currentTimeMillis();

        long fileStartTime = System.currentTimeMillis();
        //Add the primes to the output file
        for (int t = 2; t <= input; t++) {
            if (primes.get(t)) {
                index = String.valueOf(t);
                writer = new BufferedWriter(new FileWriter(output, true));
                writer.append(index);
                System.out.print(t);
                System.out.print(" ");

                if (counter % 9 == 0) {
                    writer.append("\n");
                    System.out.print("\n");
                } else {
                    writer.append(' ');
                }
                writer.close();
                counter++;

            }
        }
        long fileEndTime = System.currentTimeMillis();
        long fileTime = fileEndTime - fileStartTime;
        String time = String.valueOf((endTime - startTime));
        writer = new BufferedWriter(new FileWriter(output, true));
        writer.append("\n");
        writer.append("The Sieve of Eratosthenes took " + time + " milliseconds");
        writer.close();
        System.out.println("The Sieve of Eratosthenes took " + time + " milliseconds");
        System.out.println("The Sieve of Eratosthenes' output took " + fileTime + " milliseconds");
    }

}