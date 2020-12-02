package com.mvc.bean;

import java.util.ArrayList;
import java.util.List;

public class ListItemBean
{
    private String listItemName;
    private GroupItemBean groupItem;
    private ItemBean mainItem;
    private List<ItemBean> itemList;

    public ListItemBean(String listItemName, ItemBean mainItem, GroupItemBean groupItem)
    {
        this.itemList = new ArrayList<ItemBean>();
        this.groupItem = groupItem;
        this.mainItem = mainItem;
        this.listItemName = listItemName;
    }
    public String getListItemName() { return listItemName; }
    public List<ItemBean> getItemList() { return itemList; }
    public void setListItemName(String listItemName) { this.listItemName = listItemName; }
    public void setItemList(List<ItemBean> itemList) { this.itemList = itemList; }
    public ItemBean getMainItem() { return mainItem; }
    public void setGroupItem(GroupItemBean groupItem) { this.groupItem = groupItem; }
    public void setMainItem(ItemBean mainItem) { this.mainItem = mainItem; }
    public GroupItemBean getGroupItem() { return groupItem; }
    public void addItemToList(ItemBean item) {this.itemList.add(item);}
    public void removeItemFromList(ItemBean item) {this.itemList.remove(item);}
    public int getNumberOfItem() {return itemList.size();}
}
