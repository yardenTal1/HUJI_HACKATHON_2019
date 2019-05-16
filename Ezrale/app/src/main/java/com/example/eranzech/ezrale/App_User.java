package com.example.eranzech.ezrale;

public class App_User {
    public boolean isTv;
    public boolean isPc;
    public boolean isPhone;
    public boolean isAC;
    public boolean isWashingMachine;
    public boolean isOther;


    public App_User() {};

    public App_User(boolean isTv, boolean isPc, boolean isPhone, boolean isAC,
            boolean isWashingMachine, boolean isOther) {
        this.isTv = isTv;
        this.isPc = isPc;
        this.isPhone = isPhone;
        this.isAC = isAC;
        this.isWashingMachine = isWashingMachine;
        this.isOther = isOther;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof App_User)) {
            return false;
        }

        // typecast o to App_Usrt so that we can compare data members
        App_User user = (App_User) o;

        // Compare the data members and return accordingly
        if (user.isTv) {
            if (!this.isTv) {
                return false;
            }
        }
        if (user.isPc) {
            if (!this.isPc) {
                return false;
            }
        }
        if (user.isPhone) {
            if (!this.isPhone) {
                return false;
            }
        }
        if (user.isAC) {
            if (!this.isAC) {
                return false;
            }
        }
        if (user.isWashingMachine) {
            if (!this.isWashingMachine) {
                return false;
            }
        }
        if (user.isOther) {
            if (!this.isOther) {
                return false;
            }
        }
        return true;
    }
}
