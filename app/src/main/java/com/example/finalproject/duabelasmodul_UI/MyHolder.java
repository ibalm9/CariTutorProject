package com.example.finalproject.duabelasmodul_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;

/**
 * Created by Oclemy on 6/6/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt,propelantTxt;
    ImageView img;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.spacecraftImage);
        propelantTxt = (TextView) itemView.findViewById(R.id.propellantTxtDetail);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick();
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
