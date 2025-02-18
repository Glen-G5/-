<template>
  <div id="app">
    <!-- 全局返回按钮 -->
    <div v-if="showBackButton" class="back-button" @click="handleBack">
      <i class="el-icon-back"></i>
      <span>返回</span>
    </div>
    
    <router-view></router-view>
  </div>
</template>

<script>



export default {
  name: 'App',
  computed: {
      showBackButton() {
        // 不在首页时显示返回按钮
        return this.$route.path !== "/Home" && this.$route.path !== "/";
      },
    },
   methods:{
    handleBack() {
          // 判断路由层级
          const pathSegments = this.$route.path.split("/").filter((p) => p);

          if (pathSegments.length > 1) {
            // 子页面返回父级路由（如/strength/toplist -> /strength）
            const parentPath = `/${pathSegments.slice(0, -1).join("/")}`;
            this.$router.push(parentPath);
          } else {
            // 一级路由返回首页
            this.$router.push("/Home");
          }
        },
    },
}


</script>

<style>
/* 全局返回按钮样式 */
.back-button {
  position: fixed;
  top: 20px;
  left: 20px;
  display: flex;
  align-items: center;
  padding: 8px 15px;
  background: linear-gradient(135deg, #42b983 0%, #35495e 100%);
  color: white !important;
  border-radius: 20px;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  z-index: 1000;
}

.back-button:hover {
  transform: translateX(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.back-button i {
  margin-right: 5px;
  font-size: 18px;
}

/* 调整原有样式 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 80px; /* 增加顶部间距 */
}
</style>