package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        return null;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateRight() {
        // You should implement right rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        // TODO
        return null;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        // TODO
        return null;
    }

    @Override
    public T findMin() {
        if (size() == 0) {
            return null;
            }if (size() == 1){
                return this._value;
            }else {
                AVLTree<T> parent = this;
                AVLTree<T> lChild = this._left;
                T returnValue = null;
                while (lChild._left != null) {
                    parent = lChild;
                    lChild = lChild._left;
                    returnValue = lChild.getValue();
                return returnValue;
            }
        }
        return null;
    }

    @Override
    public T findMax() {
        if (size() == 0) {
            return null;
        }if (size() == 1){
            return this._value;
        }else {
            AVLTree<T> parent = this;
            AVLTree<T> rChild = this._right;
            T returnValue = null;
            while (rChild._right != null) {
                parent = rChild;
                rChild = rChild._right;
                returnValue = rChild.getValue();
            return returnValue;
            }
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
    	if (size() == 0) {
            return false;
            }
        if(size () == 1){
            if(element.compareTo(this.getValue()) == 0){
                return true;
            }else{
                return false;
            }
        } else{
            AVLTree<T> parent = this;
            AVLTree<T> lChild = this._left;
            AVLTree<T> rChild = this._right;
            while(this.getValue() != null) {
                if(element.compareTo(parent.getValue()) == 0){
                    return true;
                }if(element.compareTo(parent.getValue()) < 0) {
                    parent = lChild;
                    lChild = parent._left;
                }
                if(element.compareTo(parent.getValue()) > 0){
                    parent = rChild;
                    rChild = parent._right;
                }
            }
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

}