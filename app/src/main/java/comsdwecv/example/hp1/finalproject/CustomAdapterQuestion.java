package comsdwecv.example.hp1.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hp1 on 27/09/2017.
 */

public class CustomAdapterQuestion extends ArrayAdapter<Question> {

    private int resource;

    public CustomAdapterQuestion(Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View cuView = imageInflater.inflate(resource, parent, false );

        Question question = getItem(position);

        TextView title = (TextView) cuView.findViewById(R.id.tvQuestionTitle);

        title.setText(question.getQuestion());

        return cuView;
    }
}
