package br.com.gustavopmsilva.topredditsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import br.com.gustavopmsilva.topredditsreader.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (!findNavController(R.id.fcv).navigateUp()) {
                finish()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
