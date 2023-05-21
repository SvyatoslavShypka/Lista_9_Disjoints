import java.util.HashMap;
import java.util.Map;

public class DisjointSetLinkedList implements IDisjointSetStructure {

    // to represent Node of linked list. Every
    // node has a pointer to representative
    class Node {
        int val;
        Node next;
        Item itemPtr;
    }

    // A list has a pointer to head and tail
    class Item {
        Node hd, tl;
    }

    public DisjointSetLinkedList(int size) {
        for (int i = 0; i < size; i++) {
            makeset(i);
//            System.out.println("i = " + i + " hashcode: " + find(i).hd.val);
        }

    }

    // Hash to store addresses of set representatives
    // for given values. It is made global for ease of
    // implementation. And second part of hash is actually
    // address of Nodes. We typecast addresses to long
    // before storing them.
    private Map<Integer, Node> nodeAddress
            = new HashMap<>();

    // To make a set with one object
    // with its representative
    public void makeset(int a) {
        // Create a new Set
        Item newSet = new Item();

        // Create a new linked list node
        // to store given key
        newSet.hd = new Node();

        // Initialize head and tail
        newSet.tl = newSet.hd;
        nodeAddress.put(a, newSet.hd);

        // Create a new set
        newSet.hd.val = a;
        newSet.hd.itemPtr = newSet;
        newSet.hd.next = null;
    }

    // To find representative address of a
    // key
    public Item find(int key)
    {
        Node ptr = nodeAddress.get(key);
        return ptr.itemPtr;
    }
    // union function for joining two subsets
    // of a universe. Merges set2 into set1
    // and deletes set1.
    public void unionItems(Item set1, Item set2)
    {
        Node cur = set2.hd;
        while (cur != null) {
            cur.itemPtr = set1;
            cur = cur.next;
        }

        // Join the tail of the set to head
        // of the input set
        set1.tl.next = set2.hd;
        set1.tl = set2.tl;
        set2 = null;
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= nodeAddress.size() || item < 0) {
            throw new ItemOutOfRangeException();
        }
        return find(item).hd.val;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (item1 < 0 || item2 < 0 || item1 >= nodeAddress.size() || item2 >= nodeAddress.size()) {
            throw new ItemOutOfRangeException();
        }
        unionItems(find(item1), find(item2));
    }
}
