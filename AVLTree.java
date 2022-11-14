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
        AVLTree<T> lChild = this._left;
        AVLTree<T> rChild = this._right;

        if(element.compareTo(this.getValue()) > 0) {
            this._right = rChild.insert(element);
        }
        if(element.compareTo(this.getValue()) < 0) {
            this._left = lChild.insert(element);
        }
        if(element.compareTo(this.getValue()) == 0) {
            this._right = rChild.insert(element);
        }
        return null;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        AVLTree<T> lChild = this._left;
        AVLTree<T> rChild = this._right;
        if (this.isEmpty()) {
            return this;
        }
        // search first, then figure out number of children
        if(element.compareTo(this.getValue()) > 0){
            this._right = rChild.remove(element);
        }
        if(element.compareTo(this.getValue()) < 0){
            this._left = lChild.remove(element);
        }
        if(element.compareTo(this.getValue()) == 0) { // finally found node
            if (this._left.isEmpty() && this._right.isEmpty()) { // leaf condition
                return new SelfBalancingBST<>();
            }
            if (this._left.isEmpty() ^ this._right.isEmpty()){ // one child condition
                if (this._left.isEmpty()) {
                    return this.getRight();
                }
                else{
                    return this.getLeft();
                }
            }
            if (!this._left.isEmpty() && !this._right.isEmpty()) { // 2 children condition. replace with smallest on right
                AVLTree<T> rightNode = this.getRight();
                while(!rightNode.getLeft().isEmpty()){
                    rightNode = rightNode.getLeft();
                }
                this._element = rightNode.getElement();
                this._right = this._right.remove(this._element);
            }
        }
        return null;
    }

    @Override
    public T findMin() {
        if (size() == 0) {
            return null;
        }
        if (size() == 1) {
            return this.getValue();
        } else {
            AVLTree<T> parent = this;
            AVLTree<T> lChild = this._left;
            T returnValue = null;
            while (lChild.getValue() != null) {
                parent = lChild;
                lChild = parent._left;
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
        }
        if (size() == 1) {
            return this.getValue();
        } else {
            AVLTree<T> parent = this;
            AVLTree<T> rChild = this._right;
            T returnValue = null;
            while (rChild.getValue()!= null) {
                parent = rChild;
                rChild = parent._right;
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
        if (size() == 1) {
            if (element.compareTo(this.getValue()) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            AVLTree<T> parent = this;
            AVLTree<T> lChild = this._left;
            AVLTree<T> rChild = this._right;
            while (this.getValue() != null) {
                if (element.compareTo(parent.getValue()) == 0) {
                    return true;
                }
                if (element.compareTo(parent.getValue()) < 0) {
                    parent = lChild;
                    lChild = parent._left;
                }
                if (element.compareTo(parent.getValue()) > 0) {
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
        public SelfBalancingBST<T> getRight () {
            if (isEmpty()) {
                return null;
            }

            return _right;
        }
    }