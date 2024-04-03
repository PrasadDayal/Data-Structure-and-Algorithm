import java.util.*;

class TreeNode {
    char data;
    TreeNode left, right;

    TreeNode(char data) {
        this.data = data;
        left = right = null;
    }
}

class ExpressionTree {
    TreeNode root;

    ExpressionTree() {
        root = null;
    }


    public void constructTree(String prefix) {
        Stack<TreeNode> stack = new Stack<>();

        StringBuilder reversedPrefix = new StringBuilder(prefix).reverse();

        for (int i = 0; i < reversedPrefix.length(); i++) {
            char c = reversedPrefix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(new TreeNode(c));
            } else {

                TreeNode left = stack.pop();
                TreeNode right = stack.pop();
                TreeNode newNode = new TreeNode(c);
                newNode.left = left;
                newNode.right = right;
                stack.push(newNode);
            }
        }
        root = stack.pop();
    }

    private void postorderTraversal(TreeNode node) {
        if (node == null)
            return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public void postorder() {
        postorderTraversal(root);
        System.out.println();
    }

    private void deleteTree(TreeNode node) {
        if (node == null)
            return;


        deleteTree(node.left);
        deleteTree(node.right);

        node = null;
    }

    public void deleteTree() {
        deleteTree(root);
        root = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionTree tree = new ExpressionTree();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter Prefix Expression Tree");
            System.out.println("2. Delete Tree");
            System.out.println("3. Postfix Traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Enter prefix expression: ");
                    String prefix = scanner.nextLine();
                    tree.constructTree(prefix);
                    System.out.println("Expression tree constructed successfully.");
                    break;
                case 2:
                    tree.deleteTree();
                    System.out.println("Tree deleted successfully.");
                    break;
                case 3:
                    if (tree.root == null) {
                        System.out.println("Tree is empty. Please enter an expression first.");
                    } else {
                        System.out.println("Postorder traversal of the expression tree:");
                        tree.postorder();
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
    }
}