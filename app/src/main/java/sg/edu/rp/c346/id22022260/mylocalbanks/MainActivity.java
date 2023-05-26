package sg.edu.rp.c346.id22022260.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    Button dbsBtn, ocbcBtn, uobBtn;
    Bank dbsBank, ocbcBank, uobBank, currentBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbsBtn = findViewById(R.id.dbsButton);
        ocbcBtn = findViewById(R.id.ocbcButton);
        uobBtn = findViewById(R.id.uobButton);

        dbsBank = new Bank("https://www.dbs.com.sg", "18001111111", dbsBtn);
        ocbcBank = new Bank("https://www.ocbc.com", "18003633333", ocbcBtn);
        uobBank = new Bank("https://www.uob.com.sg", "18002222121", uobBtn);

        registerForContextMenu(dbsBtn);
        registerForContextMenu(ocbcBtn);
        registerForContextMenu(uobBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.translate_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.englishSelection) {
            dbsBtn.setText(R.string.dbs_english);
            ocbcBtn.setText(R.string.ocbc_english);
            uobBtn.setText(R.string.uob_english);

            return true;
        } else if (id == R.id.chineseSelection) {
            dbsBtn.setText(R.string.dbs_chinese);
            ocbcBtn.setText(R.string.ocbc_chinese);
            uobBtn.setText(R.string.uob_chinese);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == dbsBtn) {
            currentBank = dbsBank;
        } else if (v == ocbcBtn) {
            currentBank = ocbcBank;
        } else if (v == uobBtn) {
            currentBank = uobBank;
        }

        getMenuInflater().inflate(R.menu.bank_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (currentBank != null) {
            if (id == R.id.websiteSelection) {
                Intent intentWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(currentBank.website));
                startActivity(intentWebsite);

                return true;
            } else if (id == R.id.contactSelection) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentBank.contact));
                startActivity(intentCall);

                return true;
            } else if (id == R.id.favouriteSelection) {
                currentBank.toggleFavourite();

                return true;
            }
        }

        return super.onContextItemSelected(item);
    }
}