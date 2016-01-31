package com.usc.xiweiliu.myebaysearch;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import android.content.Context;

import java.util.List;


/**
 * Created by xiweiliu on 10/28/15.
 */
public class ItemAdapter extends BaseAdapter {

    Context context;
    List<Item> items;

    public ItemAdapter(Context context, DataService dataService) {
        this.context = context;
        items = dataService.items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_result_list_item, parent, false);
        }
        ImageView itemThumbnail = (ImageView) convertView.findViewById(R.id.item_thumbnail);
        TextView itemTitle = (TextView) convertView.findViewById(R.id.item_title);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.item_price);

        final Item i = items.get(position);

        // fill content
        itemTitle.setText(i.getTitle());
        itemPrice.setText(i.getWholePrice());
        if(!i.getGalleryURL().equals("")) {
            Picasso.with(context).load(i.getGalleryURL()).into(itemThumbnail);
        } else if(!i.getPictureURLSuperSize().equals("")) {
            Picasso.with(context).load(i.getPictureURLSuperSize()).into(itemThumbnail);
        }
        itemThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(i.getViewItemURL());
            }
        });

        return convertView;
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        context.startActivity(intent);
    }

}
