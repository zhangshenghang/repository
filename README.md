## 本地IDEA开发
### 初始化Hexo环境
1. 安装hexo
```shell
npm install -g hexo-cli
```
2. 初始化hexo
进入项目根目录
```shell
hexo init docs
```
3. 安装主题
```shell
git clone https://github.com/wujun234/hexo-theme-tree.git  docs/themes/tree
```
5. 删除无用.git
```shell
rm -rf docs/themes/tree/.git
```
6. 修改主题
修改`docs/_config.yml`文件
```yaml
theme: tree
```
7. 在docs目录下创建`_config.tree.yml`
```yaml
########## The following are all optional #########
####### footer bar #####
# footer, author name
author: your name
# footer, author name link
website: /
# footer, copyright start year, default this year
#siteStartYear: 2018
# favicon, replaceable
favicon: /favicon.ico

####### header bar #####
# before set to true, please maker sure you've created hexo tags\categories\about pages
tags: false
categories: true
about: false
links:
  # GitHub links，start with http、https or //
  github: https://github.com/zhangshenghang/all-in-one
  # normal links，support multiple
  custom:
    - name: 百度翻译
      URL: https://fanyi.baidu.com

####### sidebar #####
sidebar:
  # name for left sidebar post list. true: title name; false: file name
  usePostTitle: true

####### plug-in #####
# optional, giscus comment
# get generated configurations from https://giscus.app/
# when enable is true, full out your repo、repo_id、category_id
giscus:
  enable: false
  repo:
  repo_id:
  category_id:
  reactions_enabled: "1"
  emit_metadata: "0"
  input_position: "top"
  theme: "preferred_color_scheme"
  lang: "en"
# optional, valine
valine:
  enableComment: false
  enableCounter: false
  # valine appID
  appID:
  # valine appKey
  appKey:
  placeholder: Please enter a comment
  avatar: retro
#optional, busuanzi, statistics of visits
busuanzi: false

####### Off-page search #####
# If the site is [included by a search engine]
# searchEngine：default google, can replace with other searchEngine
#searchEngine: https://www.baidu.com/s?wd=
searchEngine: https://www.google.com/search?q=
# homeHost：target domain, default current page domain
homeHost: localhost

```