package org.adoptopenjdk.jheappo;

/**
 * /*
 * Copyright (c) 2018 Kirk Pepperdine.
 * Licensed under https://github.com/AdoptOpenJDK/jheappo/blob/master/LICENSE
 * Instructions: https://github.com/AdoptOpenJDK/jheappo/wiki
 */

import org.adoptopenjdk.jheappo.io.HeapProfile;
import org.adoptopenjdk.jheappo.model.HeapGraph;
import org.adoptopenjdk.jheappo.model.JavaHeap;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Heappo {

    public static void main(String[] args) throws IOException {
        Path path = new File(args[0]).toPath();
        HeapProfile heapDump = new HeapProfile(path, new DataInputStream(new FileInputStream(path.toFile())));
        if (args.length > 1 && args[1].equalsIgnoreCase("graph")) {
            HeapGraph graph = new HeapGraph(new File("graph.db"));
            graph.populateFrom(heapDump);
        } else {
            JavaHeap heap = new JavaHeap(Paths.get("."));
            heap.populateFrom(heapDump);
            heap.writeTo(System.out);
        }
    }
}
