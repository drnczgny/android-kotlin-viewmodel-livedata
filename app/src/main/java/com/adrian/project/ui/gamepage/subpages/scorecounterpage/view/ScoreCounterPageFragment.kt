package com.adrian.project.ui.gamepage.subpages.scorecounterpage.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrian.project.R
import com.adrian.project.ui.gamepage.subpages.scorecounterpage.model.ScoreCounterPageModel
import com.adrian.project.ui.gamepage.subpages.scorecounterpage.viewmodel.ScoreCounterPageViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_score_counter.*
import javax.inject.Inject


class ScoreCounterPageFragment : Fragment(), ScoreCounterPageRouter {

    @Inject
    lateinit var scoreCounterPageModel: ScoreCounterPageModel

    lateinit var scoreCounterPageViewModel: ScoreCounterPageViewModel

    companion object {
        fun newInstance(): ScoreCounterPageFragment {
            val fragment = ScoreCounterPageFragment()

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_score_counter, container, false)
        scoreCounterPageViewModel = ViewModelProviders.of(this).get(ScoreCounterPageViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonListeners()
        displayPlayerAPoints()
        displayPlayerBPoints()
    }

    fun setupButtonListeners() {
        btnOnePointPlayerA.setOnClickListener() {
            addPointForPlayerA(1)
        }
        btnTwoPointPlayerA.setOnClickListener() {
            addPointForPlayerA(2)
        }
        btnOnePointPlayerB.setOnClickListener() {
            addPointForPlayerB(1)
        }
        btnTwoPointPlayerB.setOnClickListener() {
            addPointForPlayerB(2)
        }
    }

    private fun addPointForPlayerA(point: Int) {
        scoreCounterPageViewModel.scorePlayerA = scoreCounterPageViewModel.scorePlayerA + point
        displayPlayerAPoints()
    }

    private fun addPointForPlayerB(point: Int) {
        scoreCounterPageViewModel.scorePlayerB = scoreCounterPageViewModel.scorePlayerB + point
        displayPlayerBPoints()
    }

    private fun displayPlayerAPoints() {
        tvPlayerAScore.text = scoreCounterPageViewModel.scorePlayerA.toString()
    }

    private fun displayPlayerBPoints() {
        tvPlayerBScore.text = scoreCounterPageViewModel.scorePlayerB.toString()
    }
}
