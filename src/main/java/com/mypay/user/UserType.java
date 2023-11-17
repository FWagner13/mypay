package com.mypay.user;

public enum UserType {
    STANDARD, OUTLET;

    public Boolean isStandard() {
        return this == UserType.STANDARD;
    }

    public Boolean isOutlet() {
        return this == UserType.OUTLET;
    }
}
