<template>
  <div class="app-container home">
    <el-row :gutter="30" class="main-row">
      <!-- 欢迎信息区域 -->
      <el-col :sm="24" :lg="8" class="welcome-col">
        <h2>evas图书管理系统</h2>
        <p class="welcome-text">
           <span style=" font-weight: bold;background-color:#D8FFFF;">
             欢迎登陆eva's图书管理系统QAQ
           </span>
          <br>
          然后是感谢ruoyi和bilibili，让我完成我的实验课作业
          <br>
          感恩...
          <br>
          对的---我在ruoyi架构上二次开发了这个图书管理系统
          <br>
          因为自己一直也是在自学javaweb的内容，虽然节奏比较慢了，但也还是有自己的计划的，主要是学java后端了，
          所以做这个vue+springboot的web作业真的没有什么时间也挺有难度的。
          <br>
          但是在二次开发这个项目的过程中也是对没有接触过的vue框架有了一些了解，弄清了哪些包放哪些文件啥的。
          然后是后端也是相当符合springboot的web开发框架，更加熟悉了。
          最后是数据库，自己创建了图书表、用户表、借阅表...温习了sql语句的编写和表与表之间的联系。
          <br>
          总之，这次实验的一切都很有意义！！！
          <hr>
          (碎碎念:之前竟然还想用黑马的苍穹外卖来2次开发...但是真的太大了，实验报告一点也不好写。。。
            前端也是被打成jar包不知从何改起。
          <br>
            我也不是毫无所获，我想试着只保留苍穹外卖一个员工管理模块，
            试的过程中发现我竟然还没安装nginx...然后我就顺理成章地又下了一个虚拟机Vmware，
            学了一些linux指令，远程操控CRT啥的...我真是个肯钻研的人啊Q.Q...
            额，不过我还没下好nginx，
            我会继续的...相信不久就能2次开发苍穹外卖了。。。吧，反正学到东西了)
          <hr>
          <span style=" font-weight: bold;background-color: #fff3cd;">
            希望程序一切顺利，老师给个高分！
          </span>
          自己一定会持续学习下去的。
        </p>
        <p class="welcome-tag">
          <el-tag type="danger">欢迎登陆</el-tag>
        </p>
      </el-col>

      <!-- 技术选型区域 -->
      <el-col :sm="24" :lg="16" class="tech-col">
        <el-row class="tech-title-row">
          <el-col :span="24">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row class="tech-content-row">
          <el-col :sm="12" :md="6" class="tech-column">
            <h4>后端技术</h4>
            <ul class="tech-list">
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :sm="12" :md="6" class="tech-column">
            <h4>前端技术</h4>
            <ul class="tech-list">
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.9.0"
    }
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank")
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  width: 100%;
  min-height: 100%;
  padding: 20px;
  box-sizing: border-box;

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 14px;
  overflow-x: hidden;

  .main-row {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
  }

  .welcome-col {
    padding: 20px;
    border-radius: 8px;
    height: 740px;
  }

  .tech-col {
    padding: 20px;
    border-radius: 8px;
    height: 500px;
  }

  .welcome-text {
    line-height: 1.8;
    margin: 15px 0;
    text-align: justify;
  }


  .welcome-tag {
    margin-top: 20px;
  }

  .tech-title-row {
    margin-bottom: 20px;
  }

  .tech-column {
    padding: 10px 15px;
  }

  .tech-list {
    list-style-type: none;
    padding: 0;
    margin: 10px 0 0 0;
  }

  .tech-list li {
    padding: 6px 0;
    position: relative;
    padding-left: 18px;
  }

  .tech-list li:before {
    content: "•";
    color: #409EFF;
    position: absolute;
    left: 0;
  }

  h2 {
    margin-top: 0;
    margin-bottom: 15px;
    font-size: 34px;
    font-weight: 600;
    color: #333;
    border-bottom: 1px solid #eee;
    padding-bottom: 10px;
  }

  h4 {
    margin-top: 0;
    margin-bottom: 10px;
    font-size: 26px;
    color: #444;
    font-weight: 500;
  }

  @media (max-width: 992px) {
    .welcome-col, .tech-col {
      margin-bottom: 20px;
    }

    h2 {
      font-size: 32px;
    }
  }

  @media (max-width: 768px) {
    padding: 15px;

    .tech-column {
      margin-bottom: 15px;
    }
  }
}
</style>
