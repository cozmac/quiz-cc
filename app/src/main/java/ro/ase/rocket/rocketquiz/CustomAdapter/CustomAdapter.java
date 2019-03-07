package ro.ase.rocket.rocketquiz.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ro.ase.rocket.rocketquiz.R;
import ro.ase.rocket.rocketquiz.model.Developer;

public class CustomAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Developer> developers;
    TextView tv_name;
    TextView tv_description;
    ImageView iv_photo;



    public CustomAdapter(Context context, ArrayList<Developer> developers) {
        this.developers = developers;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(developers != null)
        {
            return developers.size();
        }
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return developers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);

            vh = new ViewHolder();

            vh.iv_photo = convertView.findViewById(R.id.image_customPerson);
            vh.tv_name = convertView.findViewById(R.id.tv_customName);
            vh.tv_description = convertView.findViewById(R.id.tv_customDescription);

            convertView.setTag(vh);
        }

        vh = (ViewHolder)convertView.getTag();

        Developer developer = developers.get(position);
        vh.tv_name.setText(developer.getName());
        vh.tv_description.setText(developer.getDescription());
        vh.iv_photo.setImageBitmap(developer.getImage());

        return convertView;
    }

    static class ViewHolder
    {
        public TextView tv_name;
        public TextView tv_description;
        public ImageView iv_photo;
    }
}
