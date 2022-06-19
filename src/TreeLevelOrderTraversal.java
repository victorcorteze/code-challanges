import java.util.LinkedList;
import java.util.Scanner;

public class TreeLevelOrderTraversal {

    static class Node{
        Node left;
        Node right;
        int data;

        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void levelOrder(Node root) {
        LinkedList<Node> nodes = new LinkedList<>();

        nodes.add(root);

        while(!nodes.isEmpty())
        {
            if(nodes.peek().left != null)
                nodes.add(nodes.peek().left);

            if(nodes.peek().right != null)
                nodes.add(nodes.peek().right);

            System.out.print(nodes.poll().data + " ");
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        levelOrder(root);


    }

}
