package com.living.bean;

import java.util.List;

/**
 * author Tobin on 2016/4/26 10:48
 */

public class NewsSearchBean extends NetModel {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"allNum":7995,"allPages":400,"contentlist":[{"channelId":"5572a109b3cdc86cf39001db","channelName":"国内最新","desc":"在众多已上市或者准备上市的团队中，马云做为幕后推手，低调的控制着这些团队的小半个命脉。","imageurls":[{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041024411.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041023428.png","width":169}],"link":"http://news.huaxi100.com/show-228-650467-1.html&mobile=yes","nid":"16717980784340357593","pubDate":"2015-07-06 16:27:30","source":"华西都市报","title":"蚂蚁金服、恒大淘宝足球等集资上市 马云做幕后推手"},{"channelId":"5572a108b3cdc86cf39001d4","channelName":"体育焦点","desc":"　　北京时间7月6日，CLPGA锦湖轮胎女子公开赛在威海锦湖韩亚球场落幕，中国一姐冯珊珊[微博]以三轮成绩210杆(69-73-68)拿下了该比赛的亚军。虽然没有夺冠，但她表示，自己很满意这个结果。　　周一中午，冯珊珊更新了自己的微博，对于2015年下半年第一场比赛的表现\u201c很开心\u201d....","link":"http://sports.sina.com.cn/golf/2015-07-06/16047649430.shtml","pubDate":"2015-07-06 16:04:30","source":"新浪","title":"冯珊珊告别锦湖轮胎备战大满贯 表示享受打球瞬间"}],"currentPage":1,"maxResult":20},"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * pagebean : {"allNum":7995,"allPages":400,"contentlist":[{"channelId":"5572a109b3cdc86cf39001db","channelName":"国内最新","desc":"在众多已上市或者准备上市的团队中，马云做为幕后推手，低调的控制着这些团队的小半个命脉。","imageurls":[{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041024411.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041023428.png","width":169}],"link":"http://news.huaxi100.com/show-228-650467-1.html&mobile=yes","nid":"16717980784340357593","pubDate":"2015-07-06 16:27:30","source":"华西都市报","title":"蚂蚁金服、恒大淘宝足球等集资上市 马云做幕后推手"},{"channelId":"5572a108b3cdc86cf39001d4","channelName":"体育焦点","desc":"　　北京时间7月6日，CLPGA锦湖轮胎女子公开赛在威海锦湖韩亚球场落幕，中国一姐冯珊珊[微博]以三轮成绩210杆(69-73-68)拿下了该比赛的亚军。虽然没有夺冠，但她表示，自己很满意这个结果。　　周一中午，冯珊珊更新了自己的微博，对于2015年下半年第一场比赛的表现\u201c很开心\u201d....","link":"http://sports.sina.com.cn/golf/2015-07-06/16047649430.shtml","pubDate":"2015-07-06 16:04:30","source":"新浪","title":"冯珊珊告别锦湖轮胎备战大满贯 表示享受打球瞬间"}],"currentPage":1,"maxResult":20}
     * ret_code : 0
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * allNum : 7995
         * allPages : 400
         * contentlist : [{"channelId":"5572a109b3cdc86cf39001db","channelName":"国内最新","desc":"在众多已上市或者准备上市的团队中，马云做为幕后推手，低调的控制着这些团队的小半个命脉。","imageurls":[{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041024411.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041023428.png","width":169}],"link":"http://news.huaxi100.com/show-228-650467-1.html&mobile=yes","nid":"16717980784340357593","pubDate":"2015-07-06 16:27:30","source":"华西都市报","title":"蚂蚁金服、恒大淘宝足球等集资上市 马云做幕后推手"},{"channelId":"5572a108b3cdc86cf39001d4","channelName":"体育焦点","desc":"　　北京时间7月6日，CLPGA锦湖轮胎女子公开赛在威海锦湖韩亚球场落幕，中国一姐冯珊珊[微博]以三轮成绩210杆(69-73-68)拿下了该比赛的亚军。虽然没有夺冠，但她表示，自己很满意这个结果。　　周一中午，冯珊珊更新了自己的微博，对于2015年下半年第一场比赛的表现\u201c很开心\u201d....","link":"http://sports.sina.com.cn/golf/2015-07-06/16047649430.shtml","pubDate":"2015-07-06 16:04:30","source":"新浪","title":"冯珊珊告别锦湖轮胎备战大满贯 表示享受打球瞬间"}]
         * currentPage : 1
         * maxResult : 20
         */

        private PagebeanBean pagebean;
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean {
            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            /**
             * channelId : 5572a109b3cdc86cf39001db
             * channelName : 国内最新
             * desc : 在众多已上市或者准备上市的团队中，马云做为幕后推手，低调的控制着这些团队的小半个命脉。
             * imageurls : [{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041024411.png","width":169},{"height":119,"url":"http://news.huaxi100.com/uploadfile/2015/0706/20150706041023428.png","width":169}]
             * link : http://news.huaxi100.com/show-228-650467-1.html&mobile=yes
             * nid : 16717980784340357593
             * pubDate : 2015-07-06 16:27:30
             * source : 华西都市报
             * title : 蚂蚁金服、恒大淘宝足球等集资上市 马云做幕后推手
             */

            private List<ContentlistBean> contentlist;

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean {
                private String channelId;
                private String channelName;
                private String desc;
                private String link;
                private String nid;
                private String pubDate;
                private String source;
                private String title;
                /**
                 * height : 119
                 * url : http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png
                 * width : 169
                 */

                private List<ImageurlsBean> imageurls;

                public String getChannelId() {
                    return channelId;
                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public String getChannelName() {
                    return channelName;
                }

                public void setChannelName(String channelName) {
                    this.channelName = channelName;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getNid() {
                    return nid;
                }

                public void setNid(String nid) {
                    this.nid = nid;
                }

                public String getPubDate() {
                    return pubDate;
                }

                public void setPubDate(String pubDate) {
                    this.pubDate = pubDate;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<ImageurlsBean> getImageurls() {
                    return imageurls;
                }

                public void setImageurls(List<ImageurlsBean> imageurls) {
                    this.imageurls = imageurls;
                }

                public static class ImageurlsBean {
                    private int height;
                    private String url;
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }
    }
}
