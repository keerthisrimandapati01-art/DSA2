class Node {
    int price, height;
    Node left, right;

    Node(int price) {
        this.price = price;
        height = 1;
    }
}

public class AmazonPriceAVL {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int price) {

        if (node == null)
            return new Node(price);

        if (price < node.price)
            node.left = insert(node.left, price);
        else if (price > node.price)
            node.right = insert(node.right, price);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && price < node.left.price) {
            System.out.println("LL Rotation at pivot " + node.price);
            return rightRotate(node);
        }

        if (balance < -1 && price > node.right.price) {
            System.out.println("RR Rotation at pivot " + node.price);
            return leftRotate(node);
        }

        if (balance > 1 && price > node.left.price) {
            System.out.println("LR Rotation at pivot " + node.price);
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && price < node.right.price) {
            System.out.println("RL Rotation at pivot " + node.price);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.price + " ");
            inorder(node.right);
        }
    }

    Node minValue(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    Node maxValue(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    boolean search(Node node, int key) {
        if (node == null)
            return false;

        if (node.price == key)
            return true;

        if (key < node.price)
            return search(node.left, key);

        return search(node.right, key);
    }

    public static void main(String[] args) {

        AmazonPriceAVL tree = new AmazonPriceAVL();

        int[] prices = {2400, 1800, 3600, 2500, 4200, 1500, 3000, 5000};

        System.out.println("======================================");
        System.out.println(" AMAZON PRODUCT PRICE INDEX USING AVL ");
        System.out.println("======================================");

        System.out.println("\nInsertion Order:");

        for (int p : prices) {
            System.out.print(p + " ");
            tree.root = tree.insert(tree.root, p);
        }

        System.out.println("\n\nFINAL AVL TREE (Ascending Prices)");
        tree.inorder(tree.root);

        System.out.println("\n");
        System.out.println("Minimum Product Price : " +
                tree.minValue(tree.root).price);

        System.out.println("Maximum Product Price : " +
                tree.maxValue(tree.root).price);

        int searchPrice = 3000;

        System.out.println("\nSearching for Product Price " +
                searchPrice + "...");

        if (tree.search(tree.root, searchPrice))
            System.out.println("Price Found!");
        else
            System.out.println("Price Not Found!");

        System.out.println("\nBST vs AVL Comparison");
        System.out.println("BST Search Time (Worst Case) : O(n)");
        System.out.println("AVL Search Time             : O(log n)");

        System.out.println("\nComplexity Analysis:");
        System.out.println("Insert   : O(log n)");
        System.out.println("Search   : O(log n)");
        System.out.println("Delete   : O(log n)");
        System.out.println("Traversal: O(n)");

        System.out.println("\nResult:");
        System.out.println("AVL Tree successfully maintained balanced product price data.");
    }
}