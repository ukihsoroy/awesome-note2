package io.ukihsoroy;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Test3 {

    @Test
    public void test1() {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.next();
        String[] split = line1.split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        Map<String, Integer> value = new HashMap<>();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            String[] s = line.split(" ");
            Node node = new Node();
            node.setStart(s[0]);
            node.setEnd(s[1]);
            node.setMs(Integer.parseInt(s[2]));
            nodes.add(node);
        }

        String lineEnd = sc.next();
        String[] s = lineEnd.split(" ");
        String start = s[0];
        String end = s[1];
        List<Node> process1 = process(start, nodes);

        List<Integer> collect = process1.stream().mapToInt(node -> node.getMs()).boxed().collect(Collectors.toList());
        Collections.sort(collect);
        System.out.println(collect.get(0));

    }

    private List<Node> process(String start, List<Node> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        List<Node> collect1 = nodes.stream().filter(node -> start.equals(node.getStart())).collect(Collectors.toList());
        List<Node> collect2 = nodes.stream().filter(node -> !start.equals(node.getStart())).collect(Collectors.toList());
        collect1.forEach(node -> {
            List<Node> p1 = process(node.getEnd(), collect2);
            node.setNexts(p1);
            if (p1 != null) {
                List<Integer> numbers = p1.stream().mapToInt(n -> n.getMs() + node.getMs()).boxed().collect(Collectors.toList());
                node.setScores(numbers);
            } else {
                List<Integer> numbers = new ArrayList<>();
                numbers.add(node.getMs());
                node.setScores(numbers);
            }
        });
        return collect1;
    }

    class Node {
        private String start;

        private String end;

        private Integer ms;

        private List<Node> nexts;

        private List<Integer> scores;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public Integer getMs() {
            return ms;
        }

        public void setMs(Integer ms) {
            this.ms = ms;
        }

        public List<Node> getNexts() {
            return nexts;
        }

        public void setNexts(List<Node> nexts) {
            this.nexts = nexts;
        }

        public List<Integer> getScores() {
            return scores;
        }

        public void setScores(List<Integer> scores) {
            this.scores = scores;
        }
    }

}
