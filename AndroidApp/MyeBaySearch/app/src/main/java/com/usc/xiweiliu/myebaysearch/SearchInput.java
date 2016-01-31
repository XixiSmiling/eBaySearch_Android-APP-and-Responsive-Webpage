package com.usc.xiweiliu.myebaysearch;

import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by xiweiliu on 10/28/15.
 */
public class SearchInput {

    EditText keyword;
    EditText priceFrom;
    EditText priceTo;
    Spinner sortBy;

    SearchInput(EditText k, EditText pF, EditText pT, Spinner s) {
        keyword = k;
        priceFrom = pF;
        priceTo = pT;
        sortBy = s;
    }

    /*
     * Getters
     */
    String getKeyword() { return keyword.getText().toString().trim(); }

    String getPriceFrom() { return priceFrom.getText().toString().trim(); }

    String getPriceTo() { return priceTo.getText().toString().trim(); }

    String getSortBy() {
        String sortby = sortBy.getSelectedItem().toString();
        if(sortby.equals("Best Match")){
            return "BestMatch";
        }else if(sortby.equals( "Price: highest first" )){
            return "CurrentPriceHighest";
        }else if(sortby.equals( "Price + Shipping: highest first")){
            return "PricePlusShippingHighest";
        }else if(sortby.equals("Price + Shipping: lowest first")){
            return "PricePlusShippingLowest";
        }
        return  "BestMatch";
    }

}
