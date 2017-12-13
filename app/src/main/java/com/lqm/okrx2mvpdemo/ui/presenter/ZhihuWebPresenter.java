package com.lqm.okrx2mvpdemo.ui.presenter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.api.ZhihuService;
import com.lqm.okrx2mvpdemo.model.pojo.News;
import com.lqm.okrx2mvpdemo.ui.base.BasePresenter;
import com.lqm.okrx2mvpdemo.ui.view.IZhihuWebView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhihuWebPresenter extends BasePresenter<IZhihuWebView> {

    private Context context;

    public ZhihuWebPresenter(Context context) {
        this.context = context;
    }

    public void getDetailNews(String id){
        ZhihuService.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    setWebView(news);
                },this::loadError);

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    ImageView webImg;
    private void setWebView(News news){
        WebView webView = getView().getWebView();
        webImg = getView().getWebImg();
        TextView imgTitle = getView().getImgTitle();
        TextView imgSource = getView().getImgSource();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+news.getCss().get(0)+"\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html =head + news.getBody().replace(img," ");
        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        Glide.with(context).load(news.getImage()).centerCrop().into(webImg);

        imgTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
    }

    public void destroyImg(){
        if(webImg != null) {
            Glide.clear(webImg);
        }
    }
}
