package com.yahoo.weather.client.data.adapter;

/**
 * Created by dgaglani on 6/10/14.
 */


import java.io.BufferedInputStream;
import java.util.*;

/**
 * Created by dgaglani on 6/10/14.
 */

public class CalvinLaneHackerRank {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        stdin.nextLine();
        while(stdin.hasNextLine()) {
            printDecentNumber(Integer.parseInt(stdin.nextLine()));
        }
        //printDecentNumber(11);
    }

    public static void printDecentNumber(int N) {
        if(N < 3) {
            System.out.println("-1");
        }
        else if(N%3 == 0){
            //All 5s
            printnums(N, 5);
            System.out.println();
        }
        else if(N%3 == 1 && N/3 >= 3) {
            printnums(((N/3) - 3)*3,5);
            printnums(N - ((N/3) - 3)*3, 3);
            System.out.println();
        }
        else if(N%3 == 1 && N/3 < 3) {
            System.out.println("-1");
        }
        else if(N%3 == 2 && N/3 > 1) {
            printnums(((N/3) - 1)*3,5);
            printnums(N - ((N/3) - 1)*3, 3);
            System.out.println();
        }
        else if(N%3 == 2 && N/3 == 1) {
            //only 3s
            printnums(N, 3);
        }
    }

    public static void printnums(int times, int num) {
        while(times < 6) {
            System.out.print(111111 * num);
            times = times - 6;
        }
        while (times != 0) {
            System.out.print(num);
            times--;
        }
    }



}
