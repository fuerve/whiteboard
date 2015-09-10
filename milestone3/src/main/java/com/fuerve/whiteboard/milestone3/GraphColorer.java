package com.fuerve.whiteboard.milestone3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph coloring implementation.
 */
public class GraphColorer {
    // The parameters of this problem are these:
    // 1. We have a set of nodes (in graph terminology they would be vertices, but the interview
    //    question used the toy problem of coloring counties on a map) that need to be assigned a
    //    color.  All nodes must end up with a color.
    // 2. These elements may be adjacent to other elements.
    // 3. No two adjacent elements may be assigned the same color.
    // 4. We have a collection of colors from which to choose.
    //   4a. There is a total ordering of precedence for the set of colors, such that
    //       the first available color will always be chosen upon assigning a color to
    //       a node.
    // 5. As input, we are given a collection of nodes and their adjacencies.
    //
    // Once the nodes are loaded up, it's a pretty straight shot to get them colored.
    // So the scope of this project is to perform the following functions:
    // 1. Load up a list of nodes and their adjacencies.
    // 2. Assign them colors.
    // 3. Output the whole thing in some reasonable data structure.
    
    private Map<String, List<String>> nodes;
    private List<String> colors;
    
    /**
     * Initializes a new instance of GraphColorer.
     */
    public GraphColorer() {
        nodes = new LinkedHashMap<String, List<String>>();
        colors = new ArrayList<String>();
    }
    
    /**
     * Adds a color to the list of colors from which to choose.  Note that the
     * order in which these are added will comprise the precedence ordering.
     * @param color The color to add.  Note that behaves like a Set, inasmuch
     * as a color that already exists in the list will not be added a second time.
     */
    public void addColor(final String color) {
        // I know that this is slower than using a Set, but Sets
        // don't allow for easy indexing and this is a load-time
        // activity.  If we need this during runtime, I'll worry
        // then about its performance.
        if (colors.contains(color)) {
            return;
        } else {
            colors.add(color);
        }
    }
    
    /**
     * Gets the full list of colors in precedence order.
     * @return The list of colors.
     */
    public List<String> getColors() {
        return colors;
    }
    
    /**
     * Removes a color from the list.
     * @param color The color to remove.
     */
    public void removeColor(final String color) {
        colors.remove(color);
    }
    
    /**
     * Adds a node and its adjacencies.
     * @param name The name of the node.
     * @param adjacencies The node's adjacencies.
     */
    public void addNode(final String name, final List<String> adjacencies) {
        nodes.put(name, adjacencies);
    }
    
    public List<ColoredNode> color() {
        final List<ColoredNode> result = new ArrayList<ColoredNode>();
        final Map<String, String> assignedColors = new LinkedHashMap<String, String>();
        
        for (final String node : nodes.keySet()) {
            final ColoredNode coloredNode = assignColor(node, assignedColors);
            if (coloredNode == null) {
                continue;
            } else {
                result.add(coloredNode);
            }
        }
        
        return result;
    }
    
    /**
     * Assigns a color to a node, based on its neighbors.
     * @param node The node to color.
     * @param assignedColors The current collection of colored nodes and their colors.
     * @return A ColoredNode instance.
     */
    private ColoredNode assignColor(final String node, final Map<String, String> assignedColors) {
        if (!nodes.containsKey(node) || nodes.get(node).size() == 0) {
            return null;
        }
        
        final List<String> tempColors = new ArrayList<String>(colors);
        for (final String adjacency : nodes.get(node)) {
            if (assignedColors.containsKey(adjacency)) {
                tempColors.remove(assignedColors.get(adjacency));
            }
        }
        if (tempColors.size() <= 0) {
            throw new IndexOutOfBoundsException();
        } else {
            final String color = tempColors.get(0);
            assignedColors.put(node, color);
            return new ColoredNode(node, color);
        }
    }
    
    /**
     * Container for a colored node.
     */
    public class ColoredNode {
        public String name;
        public String color;
        
        /**
         * Initializes a new instance of ColoredNode.
         * @param nname The name of the node.
         * @param ccolor The color of the node.
         */
        public ColoredNode(final String nname, final String ccolor) {
            name = nname;
            color = ccolor;
        }
    }
}
