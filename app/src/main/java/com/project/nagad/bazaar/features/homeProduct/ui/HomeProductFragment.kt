package com.project.nagad.bazaar.features.homeProduct.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.project.nagad.bazaar.R
import com.project.nagad.bazaar.base.BaseFragment
import com.project.nagad.bazaar.features.homeProduct.adapters.StoriesViewPagerAdapter
import com.project.nagad.presentation.factory.ViewModelFactory
import com.project.nagad.presentation.model.Status
import com.project.nagad.presentation.model.StoriesBanner
import com.project.nagad.presentation.viewmodels.HomePageVM
import kotlinx.android.synthetic.main.fragment_home_product.*
import timber.log.Timber
import javax.inject.Inject


class HomeProductFragment : BaseFragment() {

    companion object {

        fun newInstance(): HomeProductFragment {
            return HomeProductFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var homeViewModel: HomePageVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomePageVM::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(homeViewModel) {
            userInfoResource.observe(viewLifecycleOwner, Observer { resource ->

                Timber.d("Observed...")

                when (resource.status) {
                    Status.LOADING -> {
                        Timber.d("Loading...")
                    }
                    Status.ERROR -> {
                        Timber.e("Error...")
                    }
                    Status.SUCCESS -> {
                        println("Success...")
                        resource.data?.let {
                            Timber.d("Success in ui $it")

                            tvName.text = it.userName
                            tvEmail.text = it.userEmail
                            tvAddress.text = it.userAddress
                        }
                    }
                }
            })
        }
    }

    /**
     * Shows loading indicator and blurs other views
     */
    private fun showLoader() {

    }

    /**
     * Hides loading indicator
     */
    private fun hideLoader() {

    }


}

