package com.example.birthdayregister

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.app.Activity
import android.content.Intent

class MainActivity : Activity() {

    private lateinit var friendListView: ListView
    private lateinit var addFriendButton: Button
    private lateinit var friendsAdapter: ArrayAdapter<String>
    private val friendsList = mutableListOf<Friend>()

    companion object {
        private const val ADD_FRIEND_REQUEST = 1
        private const val EDIT_FRIEND_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        friendListView = findViewById(R.id.friendListView)
        addFriendButton = findViewById(R.id.addFriendButton)

        friendsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        friendListView.adapter = friendsAdapter

        addFriendButton.setOnClickListener {
            val intent = Intent(this, FriendFormActivity::class.java)
            startActivityForResult(intent, ADD_FRIEND_REQUEST)
        }

        friendListView.setOnItemClickListener { parent, view, position, id ->
            val friend = friendsList[position]
            val intent = Intent(this, FriendFormActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("name", friend.name)
            intent.putExtra("birthdate", friend.birthdate)
            startActivityForResult(intent, EDIT_FRIEND_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val name = data.getStringExtra("name") ?: ""
            val birthdate = data.getStringExtra("birthdate") ?: ""
            val position = data.getIntExtra("position", -1)

            if (requestCode == ADD_FRIEND_REQUEST) {
                val newFriend = Friend(name, birthdate)
                friendsList.add(newFriend)
                friendsAdapter.add("${newFriend.name} - ${newFriend.birthdate}")
            } else if (requestCode == EDIT_FRIEND_REQUEST && position != -1) {
                val updatedFriend = friendsList[position]
                updatedFriend.name = name
                updatedFriend.birthdate = birthdate
                friendsAdapter.remove(friendsAdapter.getItem(position))
                friendsAdapter.insert("${updatedFriend.name} - ${updatedFriend.birthdate}", position)
            }
            friendsAdapter.notifyDataSetChanged()
        }
    }
}