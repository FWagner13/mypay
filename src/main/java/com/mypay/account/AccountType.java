package com.mypay.account;

public enum AccountType {
    STANDARD, OUTLET;

    public Boolean isStandard() {
        return this == AccountType.STANDARD;
    }

    public Boolean isOutlet() {
        return this == AccountType.OUTLET;
    }
}
