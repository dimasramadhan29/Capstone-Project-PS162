package com.c23_ps162.trade_net.ui.screen.makepost

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.c23_ps162.trade_net.databinding.FragmentMakePostBinding
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import com.github.dhaval2404.imagepicker.ImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakePostFragment : Fragment(), Bootstrap {
    private lateinit var binding: FragmentMakePostBinding
    private val vm: MakePostFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakePostBinding.inflate(
            inflater,
            container,
            false
        )
        initState()
        return binding.root
    }

    override fun initState() {
        setupImagePicker()
    }

    private fun setupImagePicker() {
        binding.btnChooseImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        collect(vm.imageUri.flow) { uri ->
            if (uri != null) {
                binding.image.setImageURI(uri)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val uri: Uri = data?.data!!
                vm.imageUri.set(uri)
            }

            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}