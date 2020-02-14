
/**
 * AUTHOR: Justin Nichols
 * FILE: PA6Main.java
 * ASSIGNMENT: Programming Assignment 6 - Crispr
 * COURSE: CSC210 Spring 2019, Section D
 * PURPOSE: simulates an ecosystem to study the effects of releasing mosquitos 
 * with CRISPR genes
 *              
 * 
 * USAGE: 
 * java PA6Main infileName
 * 
 * where: infileName is the path to a file. This file will need to provide 
 * well-formed info on how to populate and manipulate the ecosystem
 *     
 *  EXAMPLE INPUT (CREATED BY INSTRUCTORS, NOT BY ME)--
 *      Input File:                       
 *   
 * -----------------------------------
 * | rows: 10                        | 
 * | cols: 10                        |
 * |                                 |
 * | CREATE (1,1) lion female left   |
 * | CREATE (1,1) bee male true      |
 * | REPRODUCE                       |
 * | PRINT                           |
 * | MOVE                            |
 * | PRINT                           |
 * -----------------------------------
 *  
 * Input-file format must match that shown above.
 * No support exists for any further commands
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PA6Main {
    public static void main(String[] args) {
        Scanner infile = getInfile(args);
        Ecosys ecosys = mkEcosys(infile);

        String line = infile.nextLine();
        while (infile.hasNextLine()) {
            line = infile.nextLine();
            String[] infoArray = line.split(" ");
            String cmd = infoArray[0].toUpperCase();

            switch (cmd) {
            case "CREATE":
                ecosys.mkInhab(infoArray);
                break;
            case "MOVE":
                System.out.println("> MOVE " + line.substring(4).toLowerCase());
                ecosys.mv(infoArray);
                System.out.println();
                break;
            case "REPRODUCE":
                System.out.println(
                        "> REPRODUCE" + line.substring(9).toLowerCase());
                ecosys.breed(infoArray);
                System.out.println();
                break;
            case "PRINT":
                System.out.println("> PRINT" + line.substring(5).toLowerCase());
                ecosys.print();
                System.out.println();
            }
        }
    }

    /*
     * returns the infile in Scanner-form
     * 
     * @Param String[] args, the command-line arguments. Args[0] is the
     * infile's name
     * 
     * @Return Scanner infile. The infile in Scanner-form
     */
    public static Scanner getInfile(String[] args) {
        // retrieving the infile
        String infileName = args[0];
        Scanner infile = null;

        try {
            infile = new Scanner(new File(infileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        return infile;
    }

    /*
     * initializes the ecosys
     * 
     * @Param Scanner infile. The infile in Scanner-form
     * 
     * @Return void
     */
    public static Ecosys mkEcosys(Scanner infile) {
        String line = infile.nextLine();
        int nRows = Integer.parseInt(line.split(" ")[1]);

        line = infile.nextLine();
        int nCols = Integer.parseInt(line.split(" ")[1]);

        return new Ecosys(nRows, nCols);
    }
}

