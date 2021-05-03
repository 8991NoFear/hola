package vn.binhld.hola.model;

import java.util.List;

public class User {
    String aliasName;
    List<String> friendChatRooms;
    List<String> groupChatRooms;
    List<String> friends;

    public User(String aliasName, List<String> friendChatRooms, List<String> groupChatRooms, List<String> friends) {
        this.aliasName = aliasName;
        this.friendChatRooms = friendChatRooms;
        this.groupChatRooms = groupChatRooms;
        this.friends = friends;
    }

    public String getAliasName() {
        return aliasName;
    }

    public List<String> getFriendChatRooms() {
        return friendChatRooms;
    }

    public List<String> getGroupChatRooms() {
        return groupChatRooms;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public void setFriendChatRooms(List<String> friendChatRooms) {
        this.friendChatRooms = friendChatRooms;
    }

    public void setGroupChatRooms(List<String> groupChatRooms) {
        this.groupChatRooms = groupChatRooms;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
