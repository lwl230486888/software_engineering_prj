import java.util.Comparator;

class LineNumberComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        // Compare based on the first line number
        int lineNumber1 = Integer.parseInt(node1.lineNumbers.split(",")[0].trim());
        int lineNumber2 = Integer.parseInt(node2.lineNumbers.split(",")[0].trim());
        return Integer.compare(lineNumber1, lineNumber2);
    }
}
