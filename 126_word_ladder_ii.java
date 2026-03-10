import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        
        if (!wordSet.contains(endWord)) return result;

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> level = new HashSet<>();
        level.add(beginWord);

        boolean found = false;

        while (!level.isEmpty() && !found) {
            Set<String> nextLevel = new HashSet<>();

            for (String word : level) {
                wordSet.remove(word);
            }

            for (String word : level) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);

                        if (!wordSet.contains(newWord)) continue;

                        nextLevel.add(newWord);

                        parents.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);

                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                    }

                    chars[i] = old;
                }
            }

            level = nextLevel;
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, parents, path, result);
        }

        return result;
    }

    private void dfs(String word, String beginWord, Map<String, List<String>> parents,
                     List<String> path, List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        if (!parents.containsKey(word)) return;

        for (String parent : parents.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}