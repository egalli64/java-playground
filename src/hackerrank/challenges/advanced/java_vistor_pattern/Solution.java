/**
 * HackerRank - Java - Advanced - Java Visitor Pattern
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-vistor-pattern/problem
 * 
Sample input:
5
4 7 2 5 12
0 1 0 0 1
1 2
1 3
3 4
3 5

Output:
24
40
15
 */
package hackerrank.challenges.advanced.java_vistor_pattern;

import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

// modifiable below
class SumInLeavesVisitor extends TreeVis {
    private int result = 0;

    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
    }

    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long result = 1;
    private final int MODULO = 1_000_000_007;

    public int getResult() {
        return (int) result;
    }

    public void visitNode(TreeNode node) {
        if (node.getColor().equals(Color.RED)) {
            result = (result * node.getValue()) % MODULO;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.RED)) {
            result = (result * leaf.getValue()) % MODULO;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int nonLeavesEvenDepth = 0;
    private int greenLeaves = 0;

    public int getResult() {
        return Math.abs(nonLeavesEvenDepth - greenLeaves);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0)
            nonLeavesEvenDepth += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.GREEN))
            greenLeaves += leaf.getValue();
    }
}

public class Solution {
    private static int values[];
    private static Color colors[];
    private static Map<Integer, Set<Integer>> nodes = new HashMap<>();

    private static void push(TreeNode parent, Integer id) {
        Set<Integer> edges = nodes.get(id);

        if (edges == null || edges.isEmpty()) {
            parent.addChild(new TreeLeaf(values[id - 1], colors[id - 1], parent.getDepth() + 1));
            return;
        }

        TreeNode node = new TreeNode(values[id - 1], colors[id - 1], parent.getDepth() + 1);
        parent.addChild(node);
        for (Integer nid : edges) {
            nodes.get(nid).remove(id);
            push(node, nid);
        }
    }

    public static Tree solve() {
        try (Scanner scanner = new Scanner(System.in)) {
            values = new int[scanner.nextInt()];
            for (int i = 0; i < values.length; i++) {
                values[i] = scanner.nextInt();
            }

            colors = new Color[values.length];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = (scanner.nextInt() == 0) ? Color.RED : Color.GREEN;
            }

            if (values.length == 1) {
                return new TreeLeaf(values[0], colors[0], 0);
            }

            for (int i = 1; i < values.length; i++) {
                int lhs = scanner.nextInt();
                Set<Integer> edgesLhs = nodes.get(lhs);
                if (edgesLhs == null) {
                    edgesLhs = new HashSet<>();
                }

                int rhs = scanner.nextInt();
                edgesLhs.add(rhs);
                nodes.put(lhs, edgesLhs);

                Set<Integer> edgesRhs = nodes.get(rhs);
                if (edgesRhs == null) {
                    edgesRhs = new HashSet<>();
                }

                edgesRhs.add(lhs);
                nodes.put(rhs, edgesRhs);
            }

            TreeNode root = new TreeNode(values[0], colors[0], 0);
            for (Integer id : nodes.get(1)) {
                nodes.get(id).remove(1);
                push(root, id);
            }

            return root;
        }
    }
// modifiable above

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}