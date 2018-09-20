package com.company;

import java.io.*;
import java.util.BitSet;

public class SieveSundaram {

    private File output;
    private BufferedWriter writer;
    private String index = null;
    private int outputNum = 0;
    public SieveSundaram(File f) throws IOException {
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

    public void compute(int input) throws IOException {

        long startTime = System.currentTimeMillis();
        int counter = 1;
        // Because the number can be 2n+2 for a given n
        // and we want a prime number less than n,
        // we reduce it to half
        int setSize = (input - 2) / 2;

        //All values are set to false
        BitSet primes = new BitSet(input);

        //This is the main logic of the Sieve
        //Set all values of index of (i+j+2*i*j) to true
        for (int i = 1; i <= setSize; i++) {
            for (int j = i; j <= (setSize)/(2*i+1); j++) {
                primes.set(i + j + 2 * i * j);
            }
        }
        Long timeEnd = System.currentTimeMillis();
        // 2 is prime as well

        if (input > 2)
            index = String.valueOf(2);
            writer.append(index);
            writer.append(' ');
            writer.close();
            System.out.print("2\t");
            counter++;

        long fileStartTime = System.currentTimeMillis();
        // Add all indexes with true value to the file
        for (int i = 1; i <= setSize; i++) {
            if (!primes.get(i)) {
                outputNum = 2 * i +1;
                index = String.valueOf(outputNum);
                writer = new BufferedWriter(new FileWriter(output, true));
                writer.append(index);
                System.out.print((2 * i + 1));
                System.out.print(' ');

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
        String time = String.valueOf((timeEnd - startTime));
        writer = new BufferedWriter(new FileWriter(output, true));
        writer.append("\n");
        writer.append("The Sieve of Sundaram took " + time + " milliseconds");
        writer.close();
        System.out.println("The Sieve of Sundaram took " + time + " milliseconds");
        System.out.println("The Sieve of Sundaram's output took " + fileTime + " milliseconds");
    }

}
