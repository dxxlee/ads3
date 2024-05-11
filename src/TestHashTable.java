import java.lang.reflect.Field;
import java.util.Random;

class MyTestingClass {
    private final int id;
    private final String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31; //prime number and my birthday)
        int result = 1;
        result = prime * result + id;
        /*    This line combines the hash code of the id field with the current
        value of result. It does this by multiplying the current result by the prime number 31
        and then adding the hash code of the id field. This operation helps incorporate the id
        field into the overall hash code    */
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        /*   Similar to the previous line. This line combines the hash code of the name field
        with the current value of result. If the name is null, it uses 0 in the calculation.
        Otherwise, it adds the hash code of the name field, ensuring that the name contributes
        to the overall hash code    */
        return result;
    }
    /* in general, this method is used to generate a hash code for an object,
    which is an integer representation of its state.
    This method is important for various data alg and strcs, such as hash tables,
    hash sets, and hash maps, as it allows efficient storage and retrieval
    of objects based on their hash codes.
    * */
}

public class TestHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random random = new Random();

        // adding random 10000 elements to the ht
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(1000), "Name" + i); // random id and name
            table.put(key, i);
        }
        printBucketSizes(table);
    }

    private static void printBucketSizes(MyHashTable<MyTestingClass, Integer> table) {
        try {
            Field capacityField = MyHashTable.class.getDeclaredField("capacity");
            capacityField.setAccessible(true);
            int capacity = (int) capacityField.get(table);

            Field chainArrayField = MyHashTable.class.getDeclaredField("chainArray");
            chainArrayField.setAccessible(true);
            MyHashTable.HashNode<MyTestingClass, Integer>[] chainArray = (MyHashTable.HashNode<MyTestingClass, Integer>[]) chainArrayField.get(table);

            int[] bucketSizes = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                MyHashTable.HashNode<MyTestingClass, Integer> node = chainArray[i];
                while (node != null) {
                    bucketSizes[i]++;
                    node = node.next;
                }
            }


            for (int i = 0; i < capacity; i++) {
                System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
