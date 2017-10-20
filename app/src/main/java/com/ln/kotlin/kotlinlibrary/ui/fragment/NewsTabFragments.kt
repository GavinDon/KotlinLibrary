package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.kotlinlibrary.adapter.NewsAdapter
import com.ln.kotlin.kotlinlibrary.bean.NewsBean
import com.ln.kotlin.kotlinlibrary.https.APIS
import com.ln.kotlin.kotlinlibrary.widget.DividerItemDecoration
import com.ln.kotlin.mylibrary.BaseFragment
import com.ln.kotlin.mylibrary.https.RetrofitClient
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_tab_fragments.*
import org.jetbrains.anko.bundleOf

/**
 * description: 资讯分类页面
 * Created by liNan on 2017/7/4 9:52

 */
class NewsTabFragments : BaseFragment() {
    private var mContext: Context? = null
    private var newsType: String = "头条"
    private var mAdapter = NewsAdapter(NewsBean())

    companion object {

        private val PARAMS = "params"
        private val JUHE_NEWS_KEY = "bc05474dd386731c3ffc5946620ec1a6"
        /**
         * 控件是否初始化完成
         */
        private var isViewCreated: Boolean = false

        fun instance(value: String): NewsTabFragments {
            val newsTabFragments = NewsTabFragments()
            newsTabFragments.arguments = bundleOf(PARAMS to value)
            return newsTabFragments
        }
    }

    /**
     * Fragment和Activity相关联时调用
     * 强转context 获取activity
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != arguments) {
            newsType = arguments.get(PARAMS).toString()

        }
    }

    /**
     * 当Activity完成onCreate()时调用。
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        if (userVisibleHint &&   isViewCreated) {
//            newsRq()
//        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mContext = container?.context
        val mView = inflater?.inflate(R.layout.news_tab_fragments, null) as View
        isViewCreated = true
        return mView
    }

    /**
     * OnCreateView执行完成会立刻执行此方法
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    /**
     * 此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
//        if (userVisibleHint &&   isViewCreated) {
//            newsRq()
//        }
    }

    /**
     * 初始化RV
     */
    private fun initRecyclerView() {
        val lm = LinearLayoutManager(mContext)
        newsRv.apply {
            setHasFixedSize(true)
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST))
            adapter = mAdapter
        }
        swipeRefresh.isEnabled = true
        //下拉刷新 新数据
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            newsRq()
        }
        newsRq()
    }


    private var mDisposable: Disposable? = null
    /**
     * 资讯请求
     */
    private fun newsRq() {
        val apis = RetrofitClient.getInstance(this.activity).create(APIS::class.java)
        apis.news(JUHE_NEWS_KEY, newsType)
                .compose(toTransformer())
                .subscribe(object : Observer<NewsBean> {
                    override fun onNext(t: NewsBean) {
                        mAdapter.updateData(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        mDisposable = d
                    }

                    override fun onComplete() {
                        swipeRefresh.isRefreshing = false
                    }

                    override fun onError(e: Throwable) {
                        swipeRefresh.isRefreshing = false
                    }

                })

    }

    /**
     * compose
     * @param upstream 是一个Observable
     * @return  ObservableSource
     */
    fun <T> toTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mDisposable?.dispose()
    }

}