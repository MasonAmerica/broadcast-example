package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val ACTION_PUSH_RECEIVE = "com.bymason.platform.core.action.PUSH_RECEIVE"
    val EXTRA_PUSH_COMMAND = "com.bymason.platform.core.extra.PUSH_COMMAND"
    val EXTRA_PUSH_ARGUMENTS = "com.bymason.platform.core.extra.PUSH_ARGS"
    val TAG = "MYAPP"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        registerReceiver(mCallbackStatusReceiver, IntentFilter(ACTION_PUSH_RECEIVE))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(mCallbackStatusReceiver)
    }
    // mCallbackStatusReceiver: Listens to broadcast from Mason Platform Broadcast Notifications
    private val mCallbackStatusReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
            if (intent.action.equals(ACTION_PUSH_RECEIVE)) {
                val command = intent.getStringExtra(EXTRA_PUSH_COMMAND)
                var args = intent.getStringArrayExtra(EXTRA_PUSH_ARGUMENTS)
                args = args ?: arrayOf()
                // Handle your push logic here
                Log.i(TAG, String.format("Got a PUSH message, command(%s), %d args ", command, args.size))
                Toast.makeText(context.applicationContext,command, Toast.LENGTH_LONG).show()
            }
        }
    }
}
