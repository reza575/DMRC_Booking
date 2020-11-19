package com.moeiny.reza.dmrc_booking

import com.moeiny.reza.dmrc_booking.model.Node
import java.util.*

class GraphProcessor(nodes: ArrayList<Node>, arcs: HashMap<Long, Long>) {
    private  var nodes: Array<Node>
    private  var nodesIndex: HashMap<Long, Int>
    private  var matrix: Array<IntArray>
    private var V = 0
    private  var prev: IntArray
    private  var dist: IntArray
    private  var sptSet: Array<Boolean?>

     init {
        V = nodes.size
        sptSet = arrayOfNulls(V)
        dist = IntArray(V)
        prev = IntArray(V)
        this.nodes = nodes.toTypedArray()
        nodesIndex = HashMap()
        matrix = Array(V) { IntArray(V) }
        for (i in 0 until V) {
            for (j in 0 until V) {
                matrix[i][j] = Int.MAX_VALUE
            }
        }
        var index = 0
        for (node in this.nodes) {
            nodesIndex[node.id] = index
            index++
        }
        for ((start, end) in arcs) {
            if(!nodesIndex.containsKey(start) || !nodesIndex.containsKey(end)){
                continue
            }
            val startIdx:Int = nodesIndex.getValue(start)
            val endIdx:Int = nodesIndex.getValue(end)
            matrix[startIdx][endIdx] = 1
            matrix[endIdx][startIdx] = 1
        }
    }

    private lateinit var path: ArrayList<Node>

    fun process(src: Long,dest: Long ): Array<Node> {
        path = ArrayList()
        if(!nodesIndex.containsKey(src) || !nodesIndex.containsKey(dest)){
            return path.toTypedArray()
        }
        val srcInt: Int = nodesIndex.getValue(src)
        val destInt: Int = nodesIndex.getValue(dest)
        dijkstra(srcInt)
        printPath(srcInt, destInt)
        return path.toTypedArray()
    }

    private fun printPath(src: Int, dest: Int) {
        if (dest == src) {
            //System.out.println(nodes[src].name);
            path.add(nodes[src])
            return
        }
        printPath(src, prev[dest])
        //System.out.println(nodes[dest].name);
        path!!.add(nodes[dest])
    }

    private fun dijkstra(src: Int) {
        for (i in 0 until V) {
            prev[i] = -1
            dist[i] = Int.MAX_VALUE
            sptSet[i] = false
        }
        prev[src] = src
        dist[src] = 0
        for (count in 0 until V - 1) {
            val u = minDistance(dist, sptSet)
            sptSet[u] = true
            for (v in 0 until V) {
                if (!sptSet[v]!! && matrix[u][v] != 0 && dist[u] != Int.MAX_VALUE && dist[u] + matrix[u][v] < dist[v]
                ) {
                    dist[v] = dist[u] + matrix[u][v]
                    prev[v] = u
                }
            }
        }
    }

    private fun minDistance( dist: IntArray, sptSet: Array<Boolean?>): Int {
        var min = Int.MAX_VALUE
        var min_index = -1
        for (v in 0 until V) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v]
                min_index = v
            }
        }
        return min_index
    }

}