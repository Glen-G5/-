<template>
    <div class="home">
      <!-- 注销按钮 -->
    <div class="logout-button">
      <el-button 
        type="danger" 
        @click="handleLogout"
        round
        icon="el-icon-switch-button">
        注销登录
      </el-button>
    </div>
    <!-- 主标题 -->
    <h1 class="main-title">3分练7分吃</h1>
    
    <!-- 导航菜单 -->
    <nav class="nav-menu">
      <router-link 
        to="/strength" 
        class="nav-link"
        active-class="active-link">
        力量训练
      </router-link>
      <router-link 
        to="/breathe" 
        class="nav-link"
        active-class="active-link">
        有氧训练
      </router-link>
      <router-link 
        to="/cwater" 
        class="nav-link"
        active-class="active-link">
        碳水循环
      </router-link>
    </nav>
    <!-- 路由视图 -->
    <router-view class="router-view"></router-view>
    <Hello></Hello>
 </div>
   
</template>
   
<script>
    
    import Hello from '@/components/Hello.vue';

   export default{
    name: 'Home',
    created:function(){
        
    },
    methods: {
      async handleLogout() {
      try {
        const response = await this.$http.post(
          "/logout",
          null,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json"
            }
          }
        );
        
        if (response.data.success) {
          localStorage.removeItem("isAuthenticated");
          this.$router.push("/");
          this.$message.success("已安全退出");
        }
      } catch (error) {
        console.error("注销失败:", error);
        this.$message.error("注销失败，请重试");
        // 强制清理本地状态
        localStorage.removeItem("isAuthenticated");
        this.$router.push("/");
      }
    }
  },
    mounted:function(){
      console.log("渲染完毕")
    },
    components: {
     Hello
    }
   }
   
</script>

<style scoped>
/* 新增注销按钮样式 */
.logout-button {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
}

/* 容器样式 */
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* 主标题样式 */
.main-title {
  font-family: 'Arial Rounded MT Bold', sans-serif;
  font-size: 2.8rem;
  color: #2c3e50;
  text-align: center;
  margin: 30px 0;
  padding-bottom: 15px;
  background: linear-gradient(90deg, #42b983 0%, #35495e 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

/* 导航菜单容器 */
.nav-menu {
  display: flex;
  justify-content: center;
  gap: 40px; /* 按钮间距 */
  margin-bottom: 40px;
}

/* 导航链接基础样式 */
.nav-link {
  padding: 12px 25px;
  border-radius: 25px;
  font-size: 1.1rem;
  font-weight: 500;
  text-decoration: none;
  color: #2c3e50;
  background-color: #f8f9fa;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  border: 2px solid transparent;
}

/* 悬停效果 */
.nav-link:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  background-color: #ffffff;
  color: #42b983;
}

/* 激活状态样式 */
.active-link {
  background: linear-gradient(135deg, #42b983 0%, #35495e 100%);
  color: white !important;
  border-color: #42b983;
}

/* 路由视图容器 */
.router-view {
  margin-top: 30px;
  padding: 25px;
  background: white;
  border-radius: 15px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);
}
</style>