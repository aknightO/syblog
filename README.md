# 1.syblog

这个项目是我想自己开发一个自己的个人博客！我觉得这样挺有意思的。

这个项目现在还没开发完（目前日期:2020/09/14),不过也快了，现在前端（100%），后端（75%），开发开发完后我就打算上线，放到自己的
云服务器去！（因为前端调试细节真的是太慢太慢了！）

开发这个项目当时是我在B站上看到一个视频说是开发一个个人博客很炫我就来了，嘿嘿嘿o(*￣▽￣*)o！

参考项目来自于另一个github用户：
## 2.说说技术栈吧！
前端：html，css,js,jquery,semantic ui
bootstrap(这个我用来做轮播图了！)
<!--    </ul>-->
    <div class="ui center aligned container">
        <!-- 轮播 -->
        <div class="carousel-inner">
            <div class="carousel-item active">

                <div class="ui basic m-margin-top center aligned segment" style="width:100%;height:500px">
                    <h class="ui teal header" >
                        <p style="font-size: 100px;"> SyBlog</p>
                    </h>
                    <br>
                    <p class="m-text1" style="font-size: 40px;">生活不止眼前的苟且，还有诗和远方！</p>
                    <div class="ui  basic segment">
                            <a href="#" class="item"><i class="blue large github icon"></i>Github</a>
                            <a href="#" class="item"><i class=" blue large clone icon" ></i>ZhiHu</a>
                            <a href="#" class="item"><i class="blue large weibo icon"></i> Weibo</a>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="ui basic m-margin-top center aligned segment" style="width:100%;height:500px">
                    <h class="ui teal header" >
                        <p style="font-size: 50px;">👑关于本博客</p>
                    </h>
                    <br>
                    <br>
                    <br>
                    <p class="m-text1" style="font-size: 20px;">
                        本博客使用springboot+jpa，同时整合各种插件，也是用有druid连接池.....
                    </p>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <a href="#" class="ui basic orange large  button"><i class="ui eye icon"></i>阅读更多</a>
                </div>
            </div>
主要插件(这里就只先列举js)：代码高亮(prism.js)，樱花飘落的动画(hm.js),二维码生成器(qrcode.min.js)，文字排版(typo.css),目录生成插件（tocbot.js）,滚动侦测（jquery.waypoints.min.js）

以及我最喜欢的音乐和插件（music.js），后端写博客的mardown编辑器(editormd.min.js)

后端：
主体框架：springboot

持久化层:Jpa;

数据库：mysql（数据库挂载到docker上面）

容器引擎：docker（这是我在刚开是了解云服务器时顺带学的一些，后来感觉还挺好用，我就把我的数据库放到docker上了，等下我截个图d=====(￣▽￣*)b）

数据库连接池:Druid

模板引擎:ThymeLeaf

日志框架:log4j

总体就这么多了：到了找工作的时间，这个项目我停，我会后续慢慢更新的，争取把他写完！（git的时候出问题了可以@我哦，有时间我会帮助你解答一些问题！）

## 3.项目截图：
### index:
![Image text](https://user-images.githubusercontent.com/52133282/93034813-ec077000-f66d-11ea-99c6-0337ee137187.png)
![image](https://user-images.githubusercontent.com/52133282/93035046-88317700-f66e-11ea-978c-0bb0ac8fc97c.png)

### 登录：
![image](https://user-images.githubusercontent.com/52133282/93035083-9b444700-f66e-11ea-903e-ceec46dd6d17.png)

### 管理界面:
![image](https://user-images.githubusercontent.com/52133282/93035115-b8791580-f66e-11ea-96bf-7674982d38b3.png)
![image](https://user-images.githubusercontent.com/52133282/93035160-d8103e00-f66e-11ea-9777-de59df70e0bf.png)
![image](https://user-images.githubusercontent.com/52133282/93035184-e52d2d00-f66e-11ea-9132-679041237210.png)
![image](https://user-images.githubusercontent.com/52133282/93035247-1574cb80-f66f-11ea-9dde-8beef636d84b.png)

### 

