package com.fuerve.whiteboard.milestone3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.fuerve.whiteboard.milestone3.GraphColorer.ColoredNode;

/**
 * Tests for the GraphColorer class.
 */
public class GraphColorerTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.GraphColorer#GraphColorer()}.
     */
    @Test
    public void testGraphColorer() {
        final GraphColorer target = new GraphColorer();
        for (final Entry<String, List<String>> node : generateNodes().entrySet()) {
            target.addNode(node.getKey(), node.getValue());
        }
        for (final String color : generateColors()) {
            target.addColor(color);
        }
        
        final List<ColoredNode> actual = target.color();
        for (final ColoredNode coloredNode : actual) {
            System.out.println("Node name: " + coloredNode.name + "  :  Node color: " + coloredNode.color);
        }
        
        assertEquals(8, actual.size());
        assertEquals("A", actual.get(0).name);
        assertEquals("RED", actual.get(0).color);
        
        assertEquals("B", actual.get(1).name);
        assertEquals("GREEN", actual.get(1).color);
        
        assertEquals("D", actual.get(3).name);
        assertEquals("BLUE", actual.get(3).color);
        
        assertEquals("G", actual.get(6).name);
        assertEquals("YELLOW", actual.get(6).color);
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.GraphColorer#getColors()}.
     */
    @Test
    public void testAddAndGetColors() {
        final GraphColorer target = new GraphColorer();
        for (final Entry<String, List<String>> node : generateNodes().entrySet()) {
            target.addNode(node.getKey(), node.getValue());
        }
        for (final String color : generateColors()) {
            target.addColor(color);
        }
        
        final List<String> colors = target.getColors();
        assertEquals(6, colors.size());
        assertEquals("RED", colors.get(0));
        assertEquals("GREEN", colors.get(1));
        assertEquals("BLUE", colors.get(2));
        assertEquals("YELLOW", colors.get(3));
        assertEquals("ORANGE", colors.get(4));
        assertEquals("PURPLE", colors.get(5));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.GraphColorer#removeColor(java.lang.String)}.
     */
    @Test
    public void testRemoveColor() {
        final GraphColorer target = new GraphColorer();
        for (final Entry<String, List<String>> node : generateNodes().entrySet()) {
            target.addNode(node.getKey(), node.getValue());
        }
        for (final String color : generateColors()) {
            target.addColor(color);
        }
        
        target.removeColor("BLUE");
        assertTrue(!target.getColors().contains("BLUE"));
    }

    /**
     * Generates a set of test nodes.  See TestGraph.png for an illustration of this graph.
     * @return The set of test nodes.
     */
    private Map<String, List<String>> generateNodes() {
        final Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
        
        final List<String> listA = new ArrayList<String>();
        listA.add("B");
        
        final List<String> listB = new ArrayList<String>();
        listB.add("A");
        listB.add("C");
        listB.add("D");
        
        final List<String> listC = new ArrayList<String>();
        listC.add("B");
        listC.add("D");
        listC.add("E");
        
        final List<String> listD = new ArrayList<String>();
        listD.add("B");
        listD.add("C");
        listD.add("E");
        listD.add("F");
        listD.add("G");
        
        final List<String> listE = new ArrayList<String>();
        listE.add("C");
        listE.add("D");
        listE.add("G");
        listE.add("H");
        
        final List<String> listF = new ArrayList<String>();
        listF.add("D");
        listF.add("G");
        
        final List<String> listG = new ArrayList<String>();
        listG.add("D");
        listG.add("E");
        listG.add("F");
        listG.add("H");
        
        final List<String> listH = new ArrayList<String>();
        listH.add("E");
        listH.add("G");
        
        result.put("A", listA);
        result.put("B", listB);
        result.put("C", listC);
        result.put("D", listD);
        result.put("E", listE);
        result.put("F", listF);
        result.put("G", listG);
        result.put("H", listH);
        
        return result;
    }
    
    /**
     * Generates a list of test colors.
     * @return The list of test colors.
     */
    final List<String> generateColors() {
        final List<String> result = new ArrayList<String>();
        result.add("RED");
        result.add("GREEN");
        result.add("BLUE");
        result.add("YELLOW");
        result.add("ORANGE");
        result.add("PURPLE");
        return result;
    }
}
