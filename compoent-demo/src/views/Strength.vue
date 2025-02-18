<template>
    <div class="strength-container">
      <!-- 主标题 -->
      <h1 class="main-title">力量训练</h1>
      
      <!-- 子路由导航 -->
      <nav class="sub-nav-menu">
        <router-link 
          to="/strength/toplist" 
          class="sub-nav-link"
          active-class="active-link"
          exact-active-class="exact-active-link">
          <i class="icon icon-dumbbell"></i>
          举铁
        </router-link>
        <router-link 
          to="/strength/playlist" 
          class="sub-nav-link"
          active-class="active-link"
          exact-active-class="exact-active-link">
          <i class="icon icon-machine"></i>
          器械
        </router-link>
      </nav>
  
      <!-- 分隔线 -->
      <hr class="divider">
  
      <!-- 子路由视图 -->
      <router-view class="sub-router-view"></router-view>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Strength',
  };
  </script>
  
  <style scoped>
  /* 容器样式 */
  .strength-container {
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
  
  /* 子导航菜单容器 */
  .sub-nav-menu {
    display: flex;
    justify-content: center;
    gap: 40px; /* 按钮间距 */
    margin-bottom: 30px;
  }
  
  /* 子导航链接基础样式 */
  .sub-nav-link {
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
  .sub-nav-link:hover {
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
  
  /* 分隔线样式 */
  .divider {
    border: 0;
    height: 1px;
    background: linear-gradient(90deg, rgba(66,185,131,0.1) 0%, rgba(66,185,131,0.5) 50%, rgba(66,185,131,0.1) 100%);
    margin: 20px 0;
  }
  
  /* 子路由视图容器 */
  .sub-router-view {
    margin-top: 20px;
    padding: 25px;
    background: white;
    border-radius: 15px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  }
  </style>