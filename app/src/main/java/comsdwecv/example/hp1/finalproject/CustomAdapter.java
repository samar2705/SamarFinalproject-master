package comsdwecv.example.hp1.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hp1 on 27/09/2017.
 */

public class CustomAdapter extends ArrayAdapter<Item> {

    private int resource;

    public CustomAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.resource = resource;

    }@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    LayoutInflater imageInflater = LayoutInflater.from(getContext());
    View cuView = imageInflater.inflate(resource, parent, false );

       Item item = getItem(position);

    TextView title = (TextView) cuView.findViewById(R.id.tvDescription);
      ImageView image = (ImageView) cuView.findViewById(R.id.imgSign);

       title.setText(item.getTitle());
      image.setImageResource(item.getImageId());

        return cuView;
    }
}
