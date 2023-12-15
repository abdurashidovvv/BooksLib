package uz.abdurashidov.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.abdurashidov.bookapp.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

/*
https://www.figma.com/file/3iBJVs4YadN70d9ufPpdeE/BookRate?type=design&node-id=36-384&mode=design&t=qeSt97Mx5r6r7XVM-0
https://github.com/abdurashidovvv/NewsApp/blob/master/app/src/main/java/uz/ilhomjon/newsapp/view/ui/MainFragment.kt
*/