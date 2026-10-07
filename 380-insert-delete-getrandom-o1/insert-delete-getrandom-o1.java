import java.util.*;

class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> valueToIndex;
    private Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        valueToIndex = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) return false;
        
        // Add to the end of the list and record its index
        valueToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) return false;
        
        // 1. Get the index of the element to remove
        int indexToRemove = valueToIndex.get(val);
        int lastElement = nums.get(nums.size() - 1);
        
        // 2. Move the last element to the spot of the element we're removing
        nums.set(indexToRemove, lastElement);
        valueToIndex.put(lastElement, indexToRemove);
        
        // 3. Remove the last element from both structures
        nums.remove(nums.size() - 1);
        valueToIndex.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        // Since elements are contiguous in the ArrayList, 
        // we can pick a random index in O(1)
        return nums.get(rand.nextInt(nums.size()));
    }
}