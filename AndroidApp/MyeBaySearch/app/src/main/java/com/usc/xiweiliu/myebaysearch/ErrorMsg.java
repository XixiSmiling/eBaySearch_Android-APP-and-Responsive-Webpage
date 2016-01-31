package com.usc.xiweiliu.myebaysearch;

import android.view.View;
import android.widget.TextView;

/**
 * Created by xiweiliu on 10/28/15.
 */
public class ErrorMsg {
    String[] errors = {"Please enter a keyword",
            "Price can only be a positive number",
            "Price To must not be less than Price From",
            "No Results Found"};
    TextView msg;

    ErrorMsg(TextView m) {
        msg = m;
    }

    boolean isValid(SearchInput input) {
        float priceFrom = input.getPriceFrom().equals("")? 0: Float.parseFloat(input.getPriceFrom());
        float priceTo = input.getPriceTo().equals("")? Float.MAX_VALUE: Float.parseFloat(input.getPriceTo());
        if(input.getKeyword().equals("")) {
            return postErrorMsg(0);
        } else if(priceFrom < 0 || priceTo < 0) {
            return postErrorMsg(1);
        } else if(priceFrom >= priceTo) {
            return postErrorMsg(2);
        }
        msg.setVisibility(View.GONE);
        return true;
    }

    boolean postErrorMsg(int index) {
        msg.setText(errors[index]);
        msg.setVisibility(View.VISIBLE);
        return false;
    }

}
