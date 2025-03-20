import java.io.*;
import java.util.*;

public class crossReference {

    // Regular expression pattern to split Java statements into words
    private static final String DELIMITER = "\"(?:\\\\\"|[^\"])*?\"|[\\s.,;:+*/|!=><@?#%&(){}\\-\\^\\[\\]\\&&]+";

    // Array containing reserved Java keywords
    private static final String[] RESERVED_WORDS = {
            "abstract", "default", "package", "synchronized", "this", "const",
            "assert", "do", "if", "private", "throw", "goto",
            "boolean", "double", "implements", "protected", "throws", "true",
            "break", "else", "import", "public", "transient", "false",
            "byte", "enum", "instanceof", "return", "try", "null",
            "case", "extends", "int", "short", "void",
            "catch", "final", "interface", "static", "volatile",
            "char", "finally", "long", "strictfp", "while",
            "class", "float", "native", "super",
            "continue", "for", "new", "switch"
    };

    // Node class representing a node in the cross-reference linked list
    static class Node {
        String identifier;  // Identifier name
        String lineNumbers;  // Line numbers where the identifier appears
        Node next;  // Reference to the next node in the linked list

        // Constructor to initialize a node with an identifier and its line number
        public Node(String identifier, int lineNumber) {
            this.identifier = identifier;
            this.lineNumbers = lineNumber + ", ";  // Initialize with the current line number
            this.next = null;
        }

        // Method to append additional line numbers to the existing ones
        public void appendLineNumber(int lineNumber) {
            lineNumbers += lineNumber + " ";  // Append the new line number
        }
    }

    // Method to tokenize a Java statement into words
    private static String[] tokenize(String javaStatement) {
        return javaStatement.split(DELIMITER);
    }

    // Method to check if a word is a reserved Java keyword
    private static boolean isReserved(String word) {
        for (String reservedWord : RESERVED_WORDS) {
            if (reservedWord.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // Hash function to calculate the index in the cross-reference array
    private static int hash(String word, int arraySize) {
        int hash = 0;
        for (char c : word.toCharArray()) {
            hash += (int) c;
        }
        return hash % arraySize;
    }

    // Method to update the cross-reference with a new identifier
    private static void updateCrossReference(Node[] crossReference, String word, int lineNumber) {
        int index = hash(word, crossReference.length);
        if (crossReference[index] == null) {
            crossReference[index] = new Node(word, lineNumber);  // Create a new node if the index is empty
        } else {
            Node node = crossReference[index];
            while (node != null) {
                if (node.identifier.equals(word)) {
                    node.appendLineNumber(lineNumber);  // If the identifier already exists, append the line number
                    return;
                }
                if (node.next == null) break;
                node = node.next;
            }
            node.next = new Node(word, lineNumber);  // If not found, add a new node to the end of the linked list
        }
    }

   // Method to print the cross-reference sorted alphabetically
private static void printCrossReference(Node[] crossReference) {
    TreeMap<String, String> sortedCrossReference = new TreeMap<>();

    // Collect identifiers and line numbers into TreeMap
    for (Node node : crossReference) {
        while (node != null) {
            String[] lineNumbers = node.lineNumbers.split(", ");
            Arrays.sort(lineNumbers);  // Sort line numbers
            StringBuilder sortedLineNumbers = new StringBuilder();
            for (String lineNumber : lineNumbers) {
                sortedLineNumbers.append(lineNumber).append(", ");
            }
            sortedCrossReference.put(node.identifier, sortedLineNumbers.toString().replaceAll("[,\\s]+$", ""));
            node = node.next;
        }
    }

    // Print the sorted cross-reference
    for (Map.Entry<String, String> entry : sortedCrossReference.entrySet()) {
        System.out.print(entry.getKey() + ": [ ");
        System.out.print(entry.getValue());
        System.out.println(" ]");
    }
}



    // Main method to process the Java file and generate the cross-reference
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Please provide the file path.");
            }

            String filePath = args[0];
            processFile(filePath);  // Call the method to process the file
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to process the Java file
    private static void processFile(String filePath) {
        Node[] crossReference = new Node[999999];  // Array to store the cross-reference
        int totalIdentifiers = 0;  // Counter for total identifiers extracted

        try (Scanner scanner = new Scanner(new File(filePath), "UTF-8")) {
            int lineNumber = 1;  // Initialize line number counter

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();  // Read a line from the file
                String[] words = tokenize(line);  // Tokenize the line into words

                for (String word : words) {
                    if (!word.isEmpty() && !isReserved(word)) {
                        updateCrossReference(crossReference, word, lineNumber);  // Update the cross-reference
                        totalIdentifiers++;  // Increment the identifier counter
                    }
                }

                lineNumber++;  // Move to the next line
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());  // Handle file I/O exception
            return;  // Exit the method
        }

        // Print the cross-reference and total identifiers extracted
        System.out.println("Cross Reference: -");
        printCrossReference(crossReference);
        System.out.println("Total Identifiers Extracted: " + totalIdentifiers);
    }
}
/**
* Cross Reference Map
* 
* I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement
* and collusion. I am aware of the consequences if found to be involved in these misconducts. I hereby
* declare that the work submitted for the "ITP4510 Data Structures & Algorithms" is authentic record
* of my own work. 
*
* @Name : Wong Chak Yuen
* @StdID: 230486888
* @Class: IT114105/1A
* @2024-04-11
*/
