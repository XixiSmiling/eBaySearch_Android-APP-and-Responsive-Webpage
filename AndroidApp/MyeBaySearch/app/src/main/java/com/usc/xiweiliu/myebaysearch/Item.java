package com.usc.xiweiliu.myebaysearch;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class for a result item, which contains all information of a result item.
 */
public class Item implements Parcelable {
    /*
     * All data for a result item
     */
    // basicInfo
    private String title;
    private String viewItemURL;
    private String galleryURL;
    private String pictureURLSuperSize;
    private String convertedCurrentPrice;
    private String shippingServiceCost;
    private String conditionDisplayName;
    private String listingType;
    private String location;
    private String categoryName;
    private boolean topRatedListing;
    private String wholePrice;

    // sellerInfo
    private String sellerUserName;
    private String feedbackScore;
    private String positiveFeedbackPercent;
    private String feedbackRatingStar;
    private boolean topRatedSeller;
    private String sellerStoreName;
    private String sellerStoreURL;

    // shippingInfo
    private String shippingType;
    private String shipToLocations;
    private boolean expeditedShipping;
    private boolean oneDayShippingAvailable;
    private boolean returnsAccepted;
    private String handlingTime;

    /*
     * Constructor
     *
     * @param title title of the result item
     */
    public Item(JSONObject item) {
        try {
            JSONObject basicInfo = item.getJSONObject("basicInfo");
            JSONObject sellerInfo = item.getJSONObject("sellerInfo");
            JSONObject shippingInfo = item.getJSONObject("shippingInfo");

            title = basicInfo.getString("title");
            viewItemURL = basicInfo.getString("viewItemURL");
            galleryURL = basicInfo.getString("gallerURL");
            pictureURLSuperSize = basicInfo.getString("pictureURLSuperSize");
            convertedCurrentPrice = basicInfo.getString("convertedCurrentPrice");
            shippingServiceCost = basicInfo.getString("shippingServiceCost");
            conditionDisplayName = basicInfo.getString("conditionDisplayName");
            listingType = basicInfo.getString("listingType");
            location = basicInfo.getString("location");
            categoryName = basicInfo.getString("categoryName");
            topRatedListing = Boolean.parseBoolean(basicInfo.getString("topRatedListing"));

            sellerUserName = sellerInfo.getString("sellerUserName");
            feedbackScore = sellerInfo.getString("feedbackScore");
            positiveFeedbackPercent = sellerInfo.getString("positiveFeedbackPercent");
            feedbackRatingStar = sellerInfo.getString("feedbackRatingStar");
            topRatedSeller = Boolean.parseBoolean(sellerInfo.getString("topRatedSeller"));
            sellerStoreName = sellerInfo.getString("sellerStoreName");
            sellerStoreURL = sellerInfo.getString("sellerStoreURL");

            shippingType = shippingInfo.getString("shippingType");
            shipToLocations = shippingInfo.getString("shipToLocations");
            expeditedShipping = Boolean.parseBoolean(shippingInfo.getString("expeditedShipping"));
            oneDayShippingAvailable = Boolean.parseBoolean(shippingInfo.getString("oneDayShippingAvailable"));
            returnsAccepted = Boolean.parseBoolean(shippingInfo.getString("returnsAccepted"));
            handlingTime = shippingInfo.getString("handlingTime");

            String shippingPrice = "";
            if(shippingServiceCost.equals("") || shippingServiceCost.equals("0") || shippingServiceCost.equals("0.0")) {
                shippingPrice = "Free";
            } else {
                shippingPrice = "+ $" + shippingServiceCost;
            }
            wholePrice = "Price: $" + convertedCurrentPrice + " (" + shippingPrice + " Shipping)";

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString(){
        return title;
    }

    protected Item(Parcel in) {
        title = in.readString();
        viewItemURL = in.readString();
        galleryURL = in.readString();
        pictureURLSuperSize = in.readString();
        convertedCurrentPrice = in.readString();
        shippingServiceCost = in.readString();
        conditionDisplayName = in.readString();
        listingType = in.readString();
        location = in.readString();
        categoryName = in.readString();
        topRatedListing = in.readByte() != 0;
        sellerUserName = in.readString();
        feedbackScore = in.readString();
        positiveFeedbackPercent = in.readString();
        feedbackRatingStar = in.readString();
        topRatedSeller = in.readByte() != 0;
        sellerStoreName = in.readString();
        sellerStoreURL = in.readString();
        shippingType = in.readString();
        shipToLocations = in.readString();
        expeditedShipping = in.readByte() != 0;
        oneDayShippingAvailable = in.readByte() != 0;
        returnsAccepted = in.readByte() != 0;
        handlingTime = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    /*
         * Getters for private attributes of result item class
         */
    public String getTitle() { return this.title; }

    public String getViewItemURL() { return this.viewItemURL; }

    public String getGalleryURL() { return this.galleryURL; }

    public String getPictureURLSuperSize() { return this.pictureURLSuperSize; }

    public String getConvertedCurrentPrice() { return this.convertedCurrentPrice; }

    public String getShippingServiceCost() { return this.shippingServiceCost; }

    public String getConditionDisplayName() { return this.conditionDisplayName; }

    public String getListingType() { return this.listingType; }

    public String getLocation() { return this.location; }

    public String getCategoryName() { return this.categoryName; }

    public boolean isTopRatedListing() { return this.topRatedListing; }

    public String getSellerUserName() { return this.sellerUserName; }

    public String getFeedbackScore() { return this.feedbackScore; }

    public String getPositiveFeedbackPercent() { return this.positiveFeedbackPercent; }

    public String getFeedbackRatingStar() { return this.feedbackRatingStar; }

    public boolean isTopRatedSeller() { return this.topRatedSeller; }

    public String getSellerStoreName() { return this.sellerStoreName; }

    public String getSellerStoreURL() { return this.sellerStoreURL; }

    public String getShippingType() { return this.shippingType; }

    public String getShipToLocations() { return this.shipToLocations; }

    public boolean isExpeditedShipping() { return this.expeditedShipping; }

    public boolean isOneDayShippingAvailable() { return this.oneDayShippingAvailable; }

    public boolean isReturnsAccepted() { return this.returnsAccepted; }

    public String getHandlingTime() { return this.handlingTime; }

    public String getWholePrice() { return this.wholePrice; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(viewItemURL);
        dest.writeString(galleryURL);
        dest.writeString(pictureURLSuperSize);
        dest.writeString(convertedCurrentPrice);
        dest.writeString(shippingServiceCost);
        dest.writeString(conditionDisplayName);
        dest.writeString(listingType);
        dest.writeString(location);
        dest.writeString(categoryName);
        dest.writeByte((byte) (topRatedListing ? 1 : 0));
        dest.writeString(sellerUserName);
        dest.writeString(feedbackScore);
        dest.writeString(positiveFeedbackPercent);
        dest.writeString(feedbackRatingStar);
        dest.writeByte((byte) (topRatedSeller ? 1 : 0));
        dest.writeString(sellerStoreName);
        dest.writeString(sellerStoreURL);
        dest.writeString(shippingType);
        dest.writeString(shipToLocations);
        dest.writeByte((byte) (expeditedShipping ? 1 : 0));
        dest.writeByte((byte) (oneDayShippingAvailable ? 1 : 0));
        dest.writeByte((byte) (returnsAccepted ? 1 : 0));
        dest.writeString(handlingTime);
    }
}
