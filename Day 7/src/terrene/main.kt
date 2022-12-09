package terrene

import java.util.*

abstract class Node

class File(val fileName: String, val fileSize: Int) : Node()

class Dir(var parent: Dir?, val dirName: String, val children: MutableList<Node>, var dirSize: Int) : Node()

fun main(args: Array<String>) {
    println("Hello world")
    println("Answer 1 " + answer1())
    println("Answer 2 " + answer2())
}

fun answer1(): String {
    var output = ""

    var fileSystem = Dir(null, "/", ArrayList(), 0)
    fileSystem.parent = fileSystem

    var systemPointer = fileSystem


    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var data = line.split(" ")
            when (data[0]) {
                "$" -> if (data[1] == "cd") {
                    systemPointer = moveSystemPointer(data[2], systemPointer)
                }
                "dir" -> systemPointer.children.add(Dir(systemPointer, data[1], ArrayList(), 0))
                else -> {
                    systemPointer.children.add(File(data[1], data[0].toInt()))
                }
            }
        }
    }

    return "" + printTree(fileSystem)
}

fun answer2(): String {
    var output = ""

    var fileSystem = Dir(null, "/", ArrayList(), 0)
    fileSystem.parent = fileSystem

    var systemPointer = fileSystem


    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var data = line.split(" ")
            when (data[0]) {
                "$" -> if (data[1] == "cd") {
                    systemPointer = moveSystemPointer(data[2], systemPointer)
                }
                "dir" -> systemPointer.children.add(Dir(systemPointer, data[1], ArrayList(), 0))
                else -> {
                    systemPointer.children.add(File(data[1], data[0].toInt()))
                }
            }
        }
    }

    printTree(fileSystem)

    var freeUpSpace = 30000000 - (70000000 - fileSystem.dirSize)

    return "" + findFreeUpDir(fileSystem, freeUpSpace)
}

fun findFreeUpDir(fileSystem: Dir, freeUpSpace: Int): Int {
    var bestResult = 70000000
    fileSystem.children.forEach { node ->
        if (node is Dir) {
            if (node.dirSize in freeUpSpace until bestResult) {
                bestResult = node.dirSize
            }
            var childAnswer = findFreeUpDir(node, freeUpSpace)
            if (childAnswer < bestResult) {
                bestResult = childAnswer
            }
        }
    }

    return bestResult
}

fun printTree(fileSystem: Dir): Int {
    var output = 0
    fileSystem.children.forEach { node ->
        output += treeWalker(node as Dir, "  ")
        fileSystem.dirSize += node.dirSize
    }

    return output
}

fun treeWalker(nodes: Dir, extra: String): Int {
    var outputTotal = 0
//    println(extra + nodes.dirName)
    nodes.children.forEach { node ->
        if (node is Dir) {
            outputTotal += treeWalker(node, "$extra  ")
            nodes.dirSize += node.dirSize
        } else {
            nodes.dirSize += (node as File).fileSize
//            println(extra + extra + (node as File).fileName + " { " + (node as File).fileSize + " }")
        }
    }
//    println(extra + nodes.dirSize)
    if (nodes.dirSize <= 100000) {
        outputTotal += nodes.dirSize
    }
    return outputTotal
}

fun moveSystemPointer(dirName: String, systemPointer: Dir): Dir {
    var newDir: Dir
    if (dirName == ".." && systemPointer.parent != null) {
        newDir = systemPointer.parent!!
    } else {
        var answers = systemPointer.children.filter { node -> node is Dir && node.dirName == dirName }
        if (answers.isEmpty()) {
            newDir = Dir(systemPointer, dirName, ArrayList(), 0)
            systemPointer.children.add(newDir)
        } else {
            newDir = answers[0] as Dir
        }
    }

    return newDir
}
