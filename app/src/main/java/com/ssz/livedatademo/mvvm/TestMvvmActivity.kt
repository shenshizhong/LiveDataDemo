package com.ssz.livedatademo.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ssz.livedatademo.R
import com.ssz.livedatademo.databinding.ActivityTestMvvmBinding

class TestMvvmActivity : AppCompatActivity() {
  private lateinit var binding: ActivityTestMvvmBinding
  private lateinit var viewModel: CounterViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_test_mvvm)
    viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    viewModel.counter.observe(this, {
          t -> Log.d("ssz", "值：$t")
      }
    )

    Log.d("ssz", "onCreate")
    //distinctUntilChanged 直到数据改变（只要你数据没改变，就不会触发）
//    Transformations.distinctUntilChanged(viewModel.counter).observe(this, {
//      Log.d("ssz","值：" + it)
//    })


    viewModel.snackbarText.observe(this, { text ->
      // 显示 Snackbar 或处理其他操作
      val rootView: View = findViewById(android.R.id.content)
      Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT).show()
      Log.d("ssz","点击了" + text) //因为是使用 SingleLiveEvent 所以旋转屏幕就不会重复打印了。
    })


  }

  override fun onResume() {
    super.onResume()

  }
}
