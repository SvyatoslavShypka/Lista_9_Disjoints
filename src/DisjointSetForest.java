import java.util.HashMap;
import java.util.Map;

public class DisjointSetForest implements IDisjointSetStructure {
    private Map<Integer, Integer> parent = new HashMap<>();

    // perform MakeSet operation
    public void makeSet(int[] universe)
    {
        // create `n` disjoint sets (one for each item)
        for (int i: universe) {
            parent.put(i, i);
        }
    }
    public DisjointSetForest(int size) {
        int[] arraySet = new int[size];
        for (int i = 0; i < size; i++) {
            arraySet[i] = i;
        }
        makeSet(arraySet);
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= parent.size() || item < 0) {
            throw new ItemOutOfRangeException();
        }
        // if `k` is root
        if (parent.get(item) == item) {
            return item;
        }

        // recur for the parent until we find the root
        return findSet(parent.get(item));
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (item1 < 0 || item2 < 0 || item1 >= parent.size() || item2 >= parent.size()) {
            throw new ItemOutOfRangeException();
        }
        // find the root of the sets in which elements `x` and `y` belongs
        int x = findSet(item1);
        int y = findSet(item2);
        parent.put(x, y);
    }
}
