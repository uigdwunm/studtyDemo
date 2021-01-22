package leetCode.medium721;

import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Set<List<String>> accountSet = new HashSet<>();
        Map<String, MergeSet> account2user = new HashMap<>();
        for (List<String> account : accounts) {
            accountSet.add(account);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                account2user.compute(email, (k, ov) -> {
                    if (ov == null) {
                        ov = new MergeSet();
                        ov.accounts = new ArrayList<>();
                    }
                    ov.accounts.add(account);
                    return ov;
                });
            }
        }

        for (Map.Entry<String, MergeSet> entry : account2user.entrySet()) {
            MergeSet mergeSet = entry.getValue();
            if (mergeSet.root == null) {
                mergeSet.root = mergeSet.accounts.get(0);
                this.dfs(account2user, mergeSet);
            }
        }

        Set<String> repectSet = new HashSet<>();
        for (MergeSet mergeSet : account2user.values()) {
            List<String> root = mergeSet.root;
            for (int i = 1; i < root.size(); i++) {
                String email = root.get(i);
                if (!repectSet.add(email)) {
                    root.remove(i);
                }
            }
            for (List<String> account : mergeSet.accounts) {
                if (root == account) {
                    continue;
                }
                for (int i = 1; i < account.size(); i++) {
                    String email = account.get(i);
                    if (repectSet.add(email)) {
                        root.add(email);
                    }
                }
                accountSet.remove(account);
            }
        }

        return new ArrayList<>(accountSet);
    }

    /**
     * 传入的mergetSet必须有root
     **/
    private void dfs(Map<String, MergeSet> account2user, MergeSet mergeSet) {
        List<String> root = mergeSet.root;
        for (List<String> account : mergeSet.accounts) {
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                MergeSet dfsMergeSet = account2user.get(email);
                if (dfsMergeSet.root == null) {
                    dfsMergeSet.root = root;
                    this.dfs(account2user, dfsMergeSet);
                }
            }
        }
    }
    class SortSet {

    }

    class MergeSet {
        // 合并的
        private List<String> root;
        // 被合并的
        private List<List<String>> accounts;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        List<List<String>> data = new LinkedList<>();
        data.add(new ArrayList<String>(){{
           add("John");
           add("johnaaa111@mail.com");
           add("johnbbb@mail.com");
        }});
        data.add(new ArrayList<String>(){{
            add("John");
            add("johnaaa111@mail.com");
            add("john00@mail.com");
            add("jobn11@mail.com");
        }});
        data.add(new ArrayList<String>(){{
            add("Mary");
            add("mary@mail.com");
        }});
        data.add(new ArrayList<String>(){{
            add("John");
            add("johnnybravo@mail.com");
        }});
        List<List<String>> lists = solution.accountsMerge(data);
        System.out.println(lists);
    }
}
