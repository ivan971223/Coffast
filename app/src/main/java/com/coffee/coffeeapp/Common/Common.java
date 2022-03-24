package com.coffee.coffeeapp.Common;
import com.coffee.coffeeapp.Model.User;

public class Common {
    public static User currentUser;
    public static final String UPDATE ="Update";
    public static final String DELETE ="Delete";

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "Shipping";
        else
            return "Shipped";
    }

}
