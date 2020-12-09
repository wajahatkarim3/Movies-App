package com.wajahatkarim.movies.swvl.base

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim.movies.swvl.BuildConfig
import com.wajahatkarim.movies.swvl.data.local.database.SwvlDatabase
import com.wajahatkarim3.roomexplorer.RoomExplorer
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    protected val activityViewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    abstract fun getViewModel(): VM

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && BuildConfig.DEBUG) {
            // Open RoomExplorer to view Room databases inside app when Volume Down key is pressed.
            // This is opened only in debug builds
            RoomExplorer.show(this, SwvlDatabase::class.java, SwvlDatabase.DB_NAME)

        }
        return super.onKeyDown(keyCode, event)
    }
}