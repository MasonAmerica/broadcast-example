package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {
    val ACTION_PUSH_RECEIVE = "com.bymason.platform.core.action.PUSH_RECEIVE"
    val EXTRA_PUSH_COMMAND = "com.bymason.platform.core.extra.PUSH_COMMAND"
    val EXTRA_PUSH_ARGUMENTS = "com.bymason.platform.core.extra.PUSH_ARGS"
    val TAG = "MYAPP"

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent.action.equals(ACTION_PUSH_RECEIVE)) {
            val command = intent.getStringExtra(EXTRA_PUSH_COMMAND)
            var args = intent.getStringArrayExtra(EXTRA_PUSH_ARGUMENTS)
            args = args ?: arrayOf()
            // Handle your push logic here
            Log.i(
                TAG,
                String.format("Got a PUSH message, command(%s), %d args ", command, args.size)
            )
            Toast.makeText(context.applicationContext,command,Toast.LENGTH_LONG).show()
        }
    }
}
