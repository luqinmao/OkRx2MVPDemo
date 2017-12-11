package com.lqm.okrx2mvpdemo.model.pojoVO;


import com.lqm.okrx2mvpdemo.model.pojo.ArticleAlbumsBean;

import java.io.Serializable;
import java.util.List;

/**
 * user：lqm
 * desc：
 */

public class ArticleAlbumVO implements Serializable {

        private List<ArticleAlbumsBean> articleAlbums;

        public List<ArticleAlbumsBean> getArticleAlbums() {
            return articleAlbums;
        }

        public void setArticleAlbums(List<ArticleAlbumsBean> articleAlbums) {
            this.articleAlbums = articleAlbums;
        }


}
