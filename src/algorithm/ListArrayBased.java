package algorithm;

// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
public class ListArrayBased implements ListInterface {

    private static final int MAX_LIST = 50;
    private Object[] items;  // an array of list items
    private int numItems;  // number of items in list

    public ListArrayBased() {
        items = new Object[MAX_LIST];
        numItems = 0;
    }  // end default constructor

    public boolean isEmpty() {
        return (numItems == 0);
    } // end isEmpty

    public int size() {
        return numItems;
    }  // end size

    public void removeAll() {
        // Creates a new array; marks old array for
        // garbage collection.
        items = new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll

    public void add(int index, Object item)
            throws ListIndexOutOfBoundsException {
        if (numItems > MAX_LIST) {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 1 && index <= numItems + 1) {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems; pos >= index; pos--) {
                items[translate(pos + 1)] = items[translate(pos)];
            } // end for
            // insert new item
            items[translate(index)] = item;
            numItems++;
        } else {  // index out of range
            throw new ListIndexOutOfBoundsException(
                    "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

    public Object get(int index)
            throws ListIndexOutOfBoundsException {
        if (index >= 1 && index <= numItems) {
            return items[translate(index)];
        } else {  // index out of range
            throw new ListIndexOutOfBoundsException(
                    "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get

    public void remove(int index)
            throws ListIndexOutOfBoundsException {
        if (index >= 1 && index <= numItems) {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index + 1; pos <= size(); pos++) {
                items[translate(pos - 1)] = items[translate(pos)];
            }  // end for
            numItems--;
        } else {  // index out of range
            throw new ListIndexOutOfBoundsException(
                    "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove

    private int translate(int position) {
        return position - 1;
    }  // end translate

    // Bubble sort method from online edited to apply to this program
    public void bubbleSort() {
        int arysize = size();
        for (int i = 0; i < arysize - 1; i++) {
            for (int j = 0; j < arysize - 1; j++) {
                // Variables for the if statement to convert variable types of the nodes
                // Tree nodes
                TreeNode nodesZero = ((TreeNode) items[j]);
                TreeNode nodesOne = ((TreeNode) items[j + 1]);

                // Huff item nodes
                HuffItem itemsZero = ((HuffItem) (nodesZero).getItem());
                HuffItem itemsOne = ((HuffItem) (nodesOne).getItem());

                // Object to store the data
                Object temp;

                if (itemsZero.getFreq() > itemsOne.getFreq()) {
                    // initialise to the top frequency of the list array
                    temp = items[j];

                    // Add the frequency to the next frequency on the list array
                    items[j] = items[j + 1];

                    // Repeat the process on the list array
                    items[j + 1] = temp;

                }
            }
        }
    }
}  // end ListArrayBased
