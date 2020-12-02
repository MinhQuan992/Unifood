package com.mvc.bean;

public class ItemBean
{
    private String itemCode;
    private String itemName;
    private String itemImage;
    private String itemStorage;
    private String itemUnit;
    private int itemPrice;
    private int maximumQuantity;
    private int itemQuantity;
    private int itemGroup;
    private String itemDescription;

    public String getItemUnit() {return this.itemUnit;}
    public String getItemStorage() {return this.itemStorage;}
    public int getItemGroup() {return this.itemGroup;}
    public int getItemQuantity() {return this.itemQuantity;}
    public String getItemName() {return this.itemName;}
    public String getItemCode() {return this.itemCode;}
    public String getItemImage() {return this.itemImage;}
    public int getItemPrice() {return this.itemPrice;}
    public int getMaximumQuantity() {return this.maximumQuantity;}
    public String getItemDescription() {return this.itemDescription;}

    public void setItemUnit(String value) {this.itemUnit = value;}
    public void setItemStorage(String value) {this.itemStorage = value;}
    public void setItemGroup(int value) {this.itemGroup = value;}
    public void setItemQuantity(int value) {this.itemQuantity = value;}
    public void setItemName(String value) {this.itemName = value;}
    public void setItemCode(String value) {this.itemCode = value;}
    public void setItemImage(String value) {this.itemImage = value;};
    public void setItemPrice(int value) {this.itemPrice = value;}
    public void setMaximumQuantity(int value) {this.maximumQuantity = value;}
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }

    public ItemBean(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public ItemBean()
    {
        this.itemCode = "";
        this.itemUnit = "";
        this.itemGroup = 0;
        this.itemStorage = "";
        this.itemPrice = 0;
        this.itemQuantity = 0;
        this.maximumQuantity = 0;
        this.itemName = "";
        this.itemImage = "";
    }

    public boolean checkIfRightQuantity()
    {
        return (itemQuantity<=maximumQuantity);
    }

    public int totalCost()
    {
        return (itemQuantity*itemPrice);
    }
}
