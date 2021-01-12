//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mvc.entities;

import java.util.ArrayList;
import java.util.List;

public class
ListItemEntity {
    private String listItemName;
    private NhomsanphamEntity groupItem;
    private SanphamEntity mainItem;
    private List<SanphamEntity> itemList = new ArrayList();

    public ListItemEntity(String listItemName, SanphamEntity mainItem, NhomsanphamEntity groupItem) {
        this.groupItem = groupItem;
        this.mainItem = mainItem;
        this.listItemName = listItemName;
    }

    public String getListItemName() {
        return this.listItemName;
    }

    public List<SanphamEntity> getItemList() {
        return this.itemList;
    }

    public void setListItemName(String listItemName) {
        this.listItemName = listItemName;
    }

    public void setItemList(List<SanphamEntity> itemList) {
        this.itemList = itemList;
    }

    public SanphamEntity getMainItem() {
        return this.mainItem;
    }

    public void setGroupItem(NhomsanphamEntity groupItem) {
        this.groupItem = groupItem;
    }

    public void setMainItem(SanphamEntity mainItem) {
        this.mainItem = mainItem;
    }

    public NhomsanphamEntity getGroupItem() {
        return this.groupItem;
    }

    public void addItemToList(SanphamEntity item) {
        this.itemList.add(item);
    }

    public void removeItemFromList(SanphamEntity item) {
        this.itemList.remove(item);
    }

    public int getNumberOfItem() {
        return this.itemList.size();
    }
}
