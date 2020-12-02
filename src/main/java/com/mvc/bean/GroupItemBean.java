package com.mvc.bean;

import javax.mail.FetchProfile;
import java.util.List;

public class GroupItemBean {
    private String groupName;
    private int GroupCode;

    public GroupItemBean(int i) { this.GroupCode = i; }
    public String getGroupName() {return this.groupName;}
    public int getGroupCode() {return this.GroupCode;}
    public void setGroupCode(int groupCode) { GroupCode = groupCode; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
}
