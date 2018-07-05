# afine
新项目框架主要围绕四点来设计

1.快速开发

2.易于维护

3.高效的性能

4.提高质量

1.ui框架（Activity 层）


1整个app只需启动一个activity，activity的layout有两层，
上层显示splash等悬浮fragment，这样设计的优点是在加载splash的同时就可用初始化主页面的fragment了，实现了一个预加载的功能

下层显示逻辑页面，包括登录，主页面，二级页面，等等

 

2.ui框架（Fragment层）
Vu接口类定义了一些处理ui的接口

BaseVu一个抽象类，其实现了Vu，并且处理了mRootLayout，mToolBarLayout,mContentLayout,mStateLayout这几个模块的关系，每个页面都需要创建一个Vu类，并且需要继承BaseVu



BaseVu将页面分为三块，分别为1标题Layout，2状态Layout，3内容Layout

3.ui框架（View层）
1状态页面的显示定位
通过getRootStateLayout 方法来确定，

2显示状态的ui
有一套默认ui，错误状态，空状态

3替换状态ui
在Vu实现类中initStateLayout方法中实现状态ui的初始化，调用api 

setErrorView(View errorView)
setEmptyView(View emptyView)
来替换自定义状态View
