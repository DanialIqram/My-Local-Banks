package sg.edu.rp.c346.id22022260.mylocalbanks;

import android.graphics.Color;
import android.widget.Button;

public class Bank {
    public String website, contact;
    private Button button;
    private boolean isFav;

    public Bank(String website, String contact, Button button) {
        this.website = website;
        this.contact = contact;
        this.button = button;
        this.isFav = false;
    }

    public void toggleFavourite() {
        int color;

        if (isFav) {
            color = Color.rgb(0, 0, 0);
        } else {
            color = Color.rgb(255, 0, 0);
        }

        isFav = !isFav;
        button.setTextColor(color);
    }
}
