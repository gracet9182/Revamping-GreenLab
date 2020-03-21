package com.example.green;

public class RecylableItem {
    private String name;
    private String type;
    private boolean recylable;

    public RecylableItem(String name, String type, boolean recylable) {
        this.name = name;
        this.type = type;
        this.recylable = recylable;
    }

    public RecylableItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRecylable() {
        return recylable;
    }

    public void setRecylable(boolean recylable) {
        this.recylable = recylable;
    }
}
