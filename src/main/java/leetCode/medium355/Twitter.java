package leetCode.medium355;

import java.util.*;

public class Twitter {
    // 人的关系，关系中包括自己，主要记录有没有
    private HashMap<Integer, HashMap<Integer, User>> relations;

    // 自增id，可以标记推文时间
    private int id;

    private int getId() {
        return id++;
    }

    /** Initialize your data structure here. */
    public Twitter() {
        this.relations = new HashMap<>();
        this.id = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        HashMap<Integer, User> releation = this.relations.get(userId);
        if (releation == null) {
            // 初始化这个用户
            releation = new HashMap<>();
            releation.put(userId, new User(tweetId, this.getId()));
            relations.put(userId, releation);
        } else {
            // 找到自己
            User user = releation.get(userId);
            // 添加推文
            user.add(tweetId, this.getId());
        }

    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        HashMap<Integer, User> userMap = this.relations.get(userId);
        if (userMap == null) {
            return Collections.emptyList();
        }

        ArrayList<Tweet> tweetList = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            User user = entry.getValue();
            Tweet tweet = user.head;
            if (tweet == null) continue;
            for (int i = 0; i < 10; i++) {
                tweetList.add(tweet);
                if (tweet.next == null) break;
                tweet = tweet.next;
            }
        }
        tweetList.sort(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return Integer.compare(o2.id, o1.id);
            }
        });
        ArrayList<Integer> list = new ArrayList<>(10);
        int i = 0;
        for (Tweet tweet : tweetList) {
            if (i++ == 10) break;
            list.add(tweet.tweetId);
        }
        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        // 关注者map
        HashMap<Integer, User> followerMap = this.relations.get(followerId);
        // 被关注者map
        HashMap<Integer, User> followeeMap = this.relations.get(followeeId);
        // 被关注者
        User followee;

        if (followeeMap == null) {
            // 初始化被关注者
            followeeMap = new HashMap<>();
            followee = new User();
            // 把自己放进去
            followeeMap.put(followeeId, followee);
            relations.put(followeeId, followeeMap);
        } else {
            followee = followeeMap.get(followeeId);
        }

        if (followerMap == null) {
            // 初始化这个关注者
            followerMap = new HashMap<>();
            // 把自己放进去
            followerMap.put(followerId, new User());
            relations.put(followerId, followerMap);
        }

        // 关注
        followerMap.put(followeeId, followee);

    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        // 不能取关自己
        if (followerId == followeeId) return;
        // 关注者map
        HashMap<Integer, User> followerMap = this.relations.get(followerId);
        if (followerMap != null) {
            // 关注者map存在且key存在
            followerMap.remove(followeeId);
        }
    }

    class Tweet {
        private int tweetId;

        // 一个自增的id，
        private int id;


        // 当前推文的下一条
        private Tweet next;

        private Tweet(int tweetId, int id, Tweet next) {
            this.tweetId = tweetId;
            this.id = id;
            this.next = next;
        }
    }

    class User {
        // 这个人发的最新一条推文
        private Tweet head;

        private User() {
            this.head = null;
        }

        private User(int tweetId, int id) {
            this.head = new Tweet(tweetId, id, null);
        }

        private void add(int tweetId, int id) {
            this.head = new Tweet(tweetId, id, head);
        }

    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(2, 5);
        twitter.follow(1, 2);
//        twitter.follow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
//        twitter.unfollow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));
    }
}
