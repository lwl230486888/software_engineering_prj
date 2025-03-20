Cross-Reference Map Generator
This Java program, CrossReference.java, generates a cross-reference map for a given Java source file. The program identifies all identifiers in the file, lists them in alphabetical order, and records the line numbers where each identifier appears. Additionally, it provides statistical data about the input Java file, such as the total number of identifiers found.

Features
Cross-Reference Map:

Lists all identifiers in the input Java file in alphabetical order.

Records the line numbers where each identifier appears.

Ignores Java keywords and reserved words.

Statistical Data:

Total number of identifiers found.

Additional statistics about the input Java file (e.g., number of lines, number of keywords, etc.).

Efficient Searching:

Uses binary search to check if a token is a Java keyword or an identifier.

Tokenizer:

Tokenizes the Java source file to extract identifiers and keywords.

Custom Exception Handling:

Handles illegal identifiers and other exceptions.

Output Format:

Displays the source code with line numbers.

Prints the cross-reference map and statistical data.

Requirements
Input
A Java source file (e.g., DSample.java).

Output
Source Code with Line Numbers:

Displays the input Java file with line numbers for reference.

Cross-Reference Map:

Lists identifiers in alphabetical order along with the line numbers where they appear.

Statistical Data:

Total number of identifiers.

Additional statistics (e.g., number of lines, number of keywords).

Implementation Details
Data Structures
Linked List:

Used to store identifiers in ASCII order (including those starting with $ or _).

Each node in the linked list has an associated linked list to store line numbers.

Array:

Holds Java keywords/reserved words in ascending or descending order.

Algorithms
Binary Search:

Efficiently checks if a token is a Java keyword or an identifier.

Tokenizer:

Extracts tokens from the Java source file.

Exception Handling:

Custom exceptions for illegal identifiers and other errors.

How to Use
Compilation
Compile the program using the following command:

javac CrossReference.java
Execution
Run the program with a Java source file as input:

java CrossReference DSample.java
Output
The program will display:

The source code with line numbers.

The cross-reference map.

Statistical data about the input file.

Sample Output
Source Code with Line Numbers

1: public class DSample {
2:     public static void main(String[] args) {
3:         int x = 10;
4:         int y = 20;
5:         System.out.println(x + y);
6:     }
7: }
Cross-Reference Map

args: 2
DSample: 1
main: 2
out: 5
println: 5
String: 2
System: 5
x: 3, 5
y: 4, 5
Statistical Data

Total Identifiers Found: 8
Total Lines: 7
Total Keywords: 5
Code Structure
Key Components
CrossReference.java:

Main class that handles file input, tokenization, and output generation.

LinkedList:

Custom implementation of a linked list to store identifiers and line numbers.

Tokenizer:

Extracts tokens from the Java source file.

KeywordChecker:

Uses binary search to determine if a token is a Java keyword.

Custom Exceptions:

Handles illegal identifiers and other errors.

Techniques Used
Linked List:

Stores identifiers and their line numbers efficiently.

Binary Search:

Ensures fast lookup of Java keywords.

Tokenizer:

Splits the source code into tokens for processing.

Exception Handling:

Improves robustness by handling errors gracefully.

ASCII Ordering:

Ensures identifiers are sorted correctly.