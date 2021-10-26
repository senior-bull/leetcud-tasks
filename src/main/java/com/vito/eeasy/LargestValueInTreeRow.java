package com.vito.eeasy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargestValueInTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> currentLevel = Collections.singletonList(root);
        List<Integer> result = new ArrayList<>();

        while (!currentLevel.isEmpty()) {

            int maxValue = currentLevel.stream().mapToInt(node -> node.val).max().orElse(0);
            result.add(maxValue);

            currentLevel = currentLevel.stream()
                    .flatMap(node -> Stream.of(node.left, node.right))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return result;
    }
}
