package com.usc.xiweiliu.myebaysearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public final static String ITEM = "MY_ITEM";

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            item = bundle.getParcelable(ITEM);
        }

        // set up all info and UI
        ImageView detailImg = (ImageView) findViewById(R.id.detail_image);
        if(!item.getPictureURLSuperSize().equals("")){
            Picasso.with(this).load(item.getPictureURLSuperSize()).into(detailImg);
        } else if(!item.getGalleryURL().equals("")) {
            Picasso.with(this).load(item.getGalleryURL()).into(detailImg);
        }

        TextView detailTitle = (TextView) findViewById(R.id.detail_title);
        detailTitle.setText(item.getTitle());
        TextView detailPrice = (TextView) findViewById(R.id.detail_price);
        detailPrice.setText(item.getWholePrice());
        TextView detailLocation = (TextView) findViewById(R.id.detail_location);
        detailLocation.setText(item.getLocation());

        if(!item.isTopRatedListing()) {
            ImageView detailTopRating = (ImageView) findViewById(R.id.detail_toprating);
            detailTopRating.setVisibility(View.GONE);
        }
        ImageView detailBuyNow = (ImageView) findViewById(R.id.detail_buynow);
        detailBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(item.getViewItemURL());
            }
        });

        final RelativeLayout basicInfo = (RelativeLayout) findViewById(R.id.detail_basic_info);
        final RelativeLayout sellerInfo = (RelativeLayout) findViewById(R.id.detail_seller_info);
        final RelativeLayout shippingInfo = (RelativeLayout) findViewById(R.id.detail_shipping_info);
        sellerInfo.setVisibility(View.GONE);
        shippingInfo.setVisibility(View.GONE);

        // set basic info
        if(!item.getCategoryName().equals("")) {
            TextView category = (TextView) findViewById(R.id.category_value);
            category.setText(item.getCategoryName());
        }
        if(!item.getConditionDisplayName().equals("")) {
            TextView condition = (TextView) findViewById(R.id.condition_value);
            condition.setText(item.getConditionDisplayName());
        }
        if(!item.getListingType().equals("")) {
            TextView buying = (TextView) findViewById(R.id.buying_value);
            buying.setText(item.getListingType());
        }

        // set seller info
        if(!item.getSellerUserName().equals("")){
            TextView userName = (TextView) findViewById(R.id.username_value);
            userName.setText(item.getSellerUserName());
        }
        if(!item.getFeedbackScore().equals("")) {
            TextView feedbackScore = (TextView) findViewById(R.id.feedback_rating_value);
            feedbackScore.setText(item.getFeedbackScore());
        }
        if(!item.getPositiveFeedbackPercent().equals("")) {
            TextView positiveFeedback = (TextView) findViewById(R.id.positive_feedback_value);
            positiveFeedback.setText(item.getPositiveFeedbackPercent());
        }
        if(!item.getFeedbackRatingStar().equals("")) {
            TextView feedbackRating = (TextView) findViewById(R.id.feedback_rating_value);
            feedbackRating.setText(item.getFeedbackRatingStar());
        }
        if(item.isTopRatedSeller()) {
            ImageView topRated = (ImageView) findViewById(R.id.top_rated_value);
            topRated.setImageResource(R.drawable.checkyes);
        }
        if(!item.getSellerStoreName().equals("")) {
            TextView store = (TextView) findViewById(R.id.store_value);
            store.setText(item.getSellerStoreName());
        }

        // set shipping info
        if(!item.getShippingType().equals("")) {
            TextView shippingType = (TextView) findViewById(R.id.shipping_type_value);
            shippingType.setText(item.getShippingType());
        }
        if(!item.getHandlingTime().equals("")) {
            TextView handlingTime = (TextView) findViewById(R.id.handling_time_value);
            handlingTime.setText(item.getHandlingTime());
        }
        if(!item.getShipToLocations().equals("")) {
            TextView shippingLocation = (TextView) findViewById(R.id.shipping_locations_value);
            shippingLocation.setText(item.getShipToLocations());
        }
        if(item.isExpeditedShipping()) {
            ImageView expeditedShipping = (ImageView) findViewById(R.id.expedited_shipping_value);
            expeditedShipping.setImageResource(R.drawable.checkyes);
        }
        if(item.isOneDayShippingAvailable()) {
            ImageView oneDayShipping = (ImageView) findViewById(R.id.one_day_shipping_value);
            oneDayShipping.setImageResource(R.drawable.checkyes);
        }
        if(item.isReturnsAccepted()) {
            ImageView returnsAccepted = (ImageView) findViewById(R.id.returns_accepted_value);
            returnsAccepted.setImageResource(R.drawable.checkyes);
        }

        // handle buttons of basic info, seller info, shipping info
        final Button btnBasic = (Button) findViewById(R.id.detail_basic_btn);
        final Button btnSeller = (Button) findViewById(R.id.detail_seller_btn);
        final Button btnShipping = (Button) findViewById(R.id.detail_shipping_btn);
        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBasic.setBackgroundResource(R.drawable.btn_bluebackground);
                btnSeller.setBackgroundResource(R.drawable.btn_greybackground);
                btnShipping.setBackgroundResource(R.drawable.btn_greybackground);
                basicInfo.setVisibility(View.VISIBLE);
                sellerInfo.setVisibility(View.GONE);
                shippingInfo.setVisibility(View.GONE);
            }
        });
        btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBasic.setBackgroundResource(R.drawable.btn_greybackground);
                btnSeller.setBackgroundResource(R.drawable.btn_bluebackground);
                btnShipping.setBackgroundResource(R.drawable.btn_greybackground);
                basicInfo.setVisibility(View.GONE);
                sellerInfo.setVisibility(View.VISIBLE);
                shippingInfo.setVisibility(View.GONE);
            }
        });
        btnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBasic.setBackgroundResource(R.drawable.btn_greybackground);
                btnSeller.setBackgroundResource(R.drawable.btn_greybackground);
                btnShipping.setBackgroundResource(R.drawable.btn_bluebackground);
                basicInfo.setVisibility(View.GONE);
                sellerInfo.setVisibility(View.GONE);
                shippingInfo.setVisibility(View.VISIBLE);
            }
        });



    }
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        this.startActivity(intent);
    }
}
