package com.sigachev;

import com.sigachev.utils.StringAlignUtils;

import java.util.Scanner;

import static com.sigachev.utils.StringAlignUtils.Alignment.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        {
            String sampleText = "Java is a class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA),[16] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[17] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. ";
            String st1 = "This text should be left aligned";
            for (int i = 1; i <= 30; i++) {
                System.out.print(i % 10);
            }
            System.out.println();
            /*StringAlignUtils util = new StringAlignUtils(30, StringAlignUtils.Alignment.RIGHT);
            System.out.println(util.format(sampleText));*/


            // Create a tool for reading user input and name it scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter text: ");
            String text = scanner.nextLine();

            System.out.println("Choose alignment:");
            System.out.println("1. LEFT");
            System.out.println("2. RIGHT");
            System.out.println("3. CENTER");

            StringAlignUtils.Alignment alignment;
            Integer selection = Integer.parseInt(scanner.nextLine());
            switch (selection) {
                case 1:
                    alignment = LEFT;
                    break;
                case 2:
                    alignment = RIGHT;
                    break;
                case 3:
                    alignment = CENTER;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selection);
            }

            System.out.println("Output width:");
            Integer width = 20;
            try {
                width = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e)  {
            System.out.println("Invalid width.");
        }
            StringAlignUtils util = new StringAlignUtils(width, alignment);
            System.out.println(util.format(text));
        }
    }
}
