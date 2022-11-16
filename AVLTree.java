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
        AVLTree<T> parent = this; // old root
        AVLTree<T> rChild = this._right;
        AVLTree<T> baby = rChild._left;
        rChild._left = parent; // new root
        parent._right = baby;
        // update the height
        parent._height = Math.max(parent._left._height, parent._right._height) + 1;
        parent._size = parent._left._size + parent._right._size + 1;
        rChild._height = Math.max(rChild._left._height, rChild._right._height) + 1;
        rChild._size = rChild._left._size + rChild._right._size + 1;
        return rChild;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateRight() {
        AVLTree<T> parent = this; // old root
        AVLTree<T> lChild = this._left;
        AVLTree<T> baby = lChild._right;
        lChild._right = parent; // new root
        parent._left = baby;
        // update the height
        parent._height = Math.max(parent._left._height, parent._right._height) + 1;
        parent._size = parent._left._size + parent._right._size + 1;
        lChild._height = Math.max(lChild._left._height, lChild._right._height) + 1;
        lChild._size = lChild._left._size + lChild._right._size + 1;
        return lChild;
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
        if (this.size() == 0) {
            this._value = element;
            this._left = new AVLTree<>();
            this._right = new AVLTree<>();
            this._height += 1;
            this._size += 1;
            return this;
        }
        AVLTree<T> parent = this;
        AVLTree<T> lChild = this._left;
        AVLTree<T> rChild = this._right;
        if (element.compareTo(parent.getValue()) > 0) {
            this._right = (AVLTree<T>) rChild.insert(element);
        } else if (element.compareTo(parent.getValue()) < 0) {
            this._left = (AVLTree<T>) lChild.insert(element);
        } else {
            this._right = (AVLTree<T>) rChild.insert(element);
        }

        //Change the height
        this._height = Math.max(this._left._height, this._right._height) + 1;
        this._size = this._left._size + this._right._size + 1;

        //do the rotation
        if (this.getBalance() >= 2) {
            if (this._left.getBalance() <= -1) {
                this._left = this._left.rotateLeft();
            }
            return this.rotateRight();
        }

        if (this.getBalance() <= -2) {
            if (this._right.getBalance() >= 1) {
                this._right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) { // change the size
        if (this.contains(element)) { // searching for node
            if (element.compareTo(this.getValue()) > 0) {
                this._right = (AVLTree<T>) this._right.remove(element);
            } else if (element.compareTo(this.getValue()) < 0) {
                this._left = (AVLTree<T>) this._left.remove(element);
            } else { // finally found node
                if (this._left.isEmpty() && this._right.isEmpty()) { // leaf condition
                    return new AVLTree<>();
                } else if (this._left.isEmpty() ^ this._right.isEmpty()) { // one child condition
                    if (this._left.isEmpty()) {
                        return this.getRight();
                    } else {
                        return this.getLeft();
                    }
                } else { // 2 children condition. Replace with min on right tree
                    T replace = this._right.findMin();
                    this._value = replace;
                    this._right = (AVLTree<T>) this._right.remove(this._value);
                }
            }
            this._height = Math.max(this._left._height, this._right._height) + 1;
            this._size = this._left._size + this._right._size + 1;

            // do the rotation
            if (this.getBalance() >= 2) {
                if (this._left.getBalance() <= -1) {
                    this._left = this._left.rotateLeft();
                }
                return this.rotateRight();
            }

            if (this.getBalance() <= -2) {
                if (this._right.getBalance() >= 1) {
                    this._right = this._right.rotateRight();
                }
                return this.rotateLeft();
            }
        }
        return this;
    }

    @Override
    public T findMin() {
        if (size() == 0) {
            return null;
        }
        if (this._left.isEmpty()) {
            return this.getValue();
        } else {
            return this._left.findMin();
        }
    }

    @Override
    public T findMax() {
        AVLTree<T> parent = this;
        AVLTree<T> rChild = this._right;
        T max = null;
        if (size() == 0) {
            return null;
        }
        if (size() == 1) {
            return this.getValue();
        }
        if (size() >= 2) {
            while (!parent.isEmpty()) {
                if (!rChild.isEmpty()) {
                    max = rChild.getValue();
                }
                parent = rChild;
                rChild = parent._right;
            }
        }
        return max;
    }

    @Override
    public boolean contains(T element) {
        if (size() == 0) {
            return false;
        }
//        else if (size() == 1) {
//            if (element.compareTo(this.getValue()) == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
        else {
            if (element.compareTo(this.getValue()) == 0) {
                return true;
            } else if (element.compareTo(this.getValue()) < 0) {
                return this._left.contains(element);
            } else {
                return this._right.contains(element);
            }
        }
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

    int getBalance() {
        if (this.isEmpty()) {
            return 0;
        }
        return this._left.height() - this._right.height();
    }
}
