package indi.uhyils.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月23日 08时27分
 */
public class HuffmanCodeUtil {

    public static HashMap<String, Integer> getHuffmanCodeUtilOnFile(String dirPath) throws Exception {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            throw new Exception("参数指向文件不存在");
        }
        if (dir.exists() && dir.isFile()) {
            throw new Exception("参数不是文件夹");
        }
        HashMap<String, Integer> temp = new HashMap<>(16);
        Integer count = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".trn")) {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                br.readLine();
                br.readLine();
                // 取第三行 音素行
                String s = br.readLine();
                br.close();
                fileReader.close();
                String[] phonemes = s.split(" ");
                for (String phoneme : phonemes) {
                    if (!temp.containsKey(phoneme)) {
                        temp.put(phoneme, 1);
                    } else {
                        temp.put(phoneme, temp.get(phoneme) + 1);
                    }
                    count++;
                }
            }
        }
        List<Node> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue() * 1.0 / count));
        }
        Node node = create(list);
        HashMap<String, Integer> code = getCode(node, list.size());
        return code;
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> huffmanCodeUtilOnFile = getHuffmanCodeUtilOnFile("D:\\share\\ideaSrc\\speechRecognition\\data\\data_thchs30\\data");
        for (Map.Entry<String, Integer> entry : huffmanCodeUtilOnFile.entrySet()) {
            System.out.printf(entry.getKey() + " " + entry.getValue());
            System.out.printf(";");
        }
    }

    private static Node create(List<Node> nodes) {
        while (nodes.size() > 1) {
            Node n1;
            Node n2;
            if (nodes.get(0).getValue() > nodes.get(1).getValue()) {
                n1 = nodes.get(1);
                n2 = nodes.get(0);
            } else {
                n1 = nodes.get(0);
                n2 = nodes.get(1);
            }
            Double per1 = n1.getValue();
            Double per2 = n2.getValue();

            int second = 2;
            for (int i = second; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                if (node.getValue() < per1) {
                    n2 = n1;
                    per2 = per1;
                    n1 = node;
                    per1 = node.getValue();
                }
            }

            assert n1 != null && n2 != null;
            nodes.remove(n1);
            nodes.remove(n2);
            Node node = new Node(per1 + per2);
            node.left = n1;
            node.right = n2;
            nodes.add(node);
        }
        return nodes.get(0);
    }

    private static HashMap<String, Integer> getCode(Node root, Integer size) {
        HashMap<String, String> temp = new HashMap<>(size);
        ergodic(root, temp, "1");
        HashMap<String, Integer> result = new HashMap<>(size);
        for (Map.Entry<String, String> entry : temp.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int i = Integer.parseInt(value, 2);
            result.put(key, i);
        }
        return result;
    }

    private static void ergodic(Node root, HashMap<String, String> result, String parentStr) {
        // 如果是中间结点
        if (root.getPh()) {
            ergodic(root.left, result, parentStr + "0");
            ergodic(root.right, result, parentStr + "1");
        } else {
            result.put(root.getKey(), parentStr);
        }
    }

    private static class Node {

        private String key;

        private Double value;

        private Node left;

        private Node right;

        private Boolean ph;

        /**
         * 创建一个音素节点
         *
         * @param key
         * @param value
         */
        public Node(String key, Double value) {
            this.key = key;
            this.value = value;
            this.ph = false;
        }

        /**
         * 创建一个中间节点
         *
         * @param value
         */
        public Node(Double value) {
            this.value = value;
            this.ph = Boolean.TRUE;
        }

        public Node() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Boolean getPh() {
            return ph;
        }

        public void setPh(Boolean ph) {
            this.ph = ph;
        }
    }

}
