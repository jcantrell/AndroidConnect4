package com.TeamAwesome.ConnectFour.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.TeamAwesome.ConnectFour.R;
import com.TeamAwesome.ConnectFour.controller.GamePlayController2;
import com.TeamAwesome.ConnectFour.rules.GameRules;
import com.TeamAwesome.ConnectFour.view.BoardView2;

public class GamePlayActivity2 extends AppCompatActivity {

    private GamePlayController2 mGameController;
    private final GameRules mGameRules = new GameRules();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        BoardView2 boardView2 = (BoardView2) findViewById(R.id.gameView2);
        mGameRules.importFrom(getIntent().getExtras());
        mGameController = new GamePlayController2(this, boardView2, mGameRules);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlert(R.string.back);
                break;
            case R.id.restart:
                showAlert(R.string.reset_game);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(final int msgId) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(msgId)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (msgId == R.string.back) {
                            mGameController.exitGame();
                        } else {
                            mGameController.restartGame();
                        }
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        showAlert(R.string.back);
    }
}
