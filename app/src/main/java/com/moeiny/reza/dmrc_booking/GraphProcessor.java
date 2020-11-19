/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moeiny.reza.dmrc_booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphProcessor {

    private final Node[] nodes;
    private final HashMap<Long, Integer> nodesIndex;
    private byte[][] matrix;
    private final int V;
    private int prev[];
    private int dist[];
    private Boolean sptSet[];

    public GraphProcessor(ArrayList<Node> nodes, HashMap<Long, Long> arcs) {
        V = nodes.size();
        this.sptSet = new Boolean[V];
        this.dist = new int[V];
        this.prev = new int[V];
        this.nodes = new Node[V];
        nodesIndex = new HashMap<>();
        matrix = new byte[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = Byte.MAX_VALUE;
            }
        }
        int index = 0;
        for (Node node : nodes) {
            this.nodes[index] = node;
            nodesIndex.put(node.id, index);
            index++;
        }
        for (Map.Entry<Long, Long> item : arcs.entrySet()) {
            long start = item.getKey();
            long end = item.getValue();
            int startIdx = nodesIndex.get(start);
            int endIdx = nodesIndex.get(end);
            matrix[startIdx][endIdx] = 1;
            matrix[endIdx][startIdx] = 1;
        }
    }

    private ArrayList<Node> path;

    public Node[] process(long src, long dest) {
        path = new ArrayList<>();
        int srcInt = nodesIndex.get(src);
        int destInt = nodesIndex.get(dest);
        dijkstra(srcInt);
        printPath(srcInt, destInt);
        return path.toArray(new Node[path.size()]);
    }

    private void printPath(int src, int dest) {
        if (dest == src) {
            //System.out.println(nodes[src].name);
            path.add(nodes[src]);
            return;
        }
        printPath(src, prev[dest]);
        //System.out.println(nodes[dest].name);
        path.add(nodes[dest]);
    }

    private void dijkstra(int src) {

        for (int i = 0; i < V; i++) {
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        prev[src] = src;
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && matrix[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + matrix[u][v];
                    prev[v] = u;
                }
            }
        }
    }

    private int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

}

