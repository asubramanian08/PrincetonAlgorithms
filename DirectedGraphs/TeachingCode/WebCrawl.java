package DirectedGraphs.TeachingCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class WebCrawl {
    public void crawl() // didn't think of a better name
    {
        Queue<String> queue = new Queue<String>(); // queue of websites to crawl
        SET<String> marked = new SET<String>(); // set of marked websites

        // start crawling from root website
        String root = "http://www.princeton.edu";
        queue.enqueue(root);
        marked.add(root);

        while (!queue.isEmpty()) {
            // read in raw html from next website in queue
            String v = queue.dequeue();
            StdOut.println(v);
            In in = new In(v);
            String input = in.readAll();
            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String w = matcher.group();
                // if unmarked, mark it and puton the queue
                if (!marked.contains(w)) {
                    marked.add(w);
                    queue.enqueue(w);
                }
            }
        }
    }
}