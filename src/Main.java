public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        // testing ht is in TestHashTable class
        //
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");
        tree.put(1, "one");

        for (var key : tree.keys()) {
            System.out.println("Key is " + key + " and value is " + tree.get(key));
        }
    }
}