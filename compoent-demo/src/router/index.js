import VueRouter from "vue-router";
import Vue from "vue";

import TopList from "@/components/TopList.vue";
import PlayList from "@/components/PlayList.vue";
import Product from "@/components/Product.vue";

import Login from "@/views/Login.vue";
import Regiser from "@/views/Regiser.vue";
import Home from "@/views/Home.vue";
import Strength from "@/views/Strength.vue";
import Breathe from "@/views/Breathe.vue";
import Cwater from "@/views/Cwater.vue";
Vue.use(VueRouter)

const router =new VueRouter({
    routes:[
        {
            path:'/',
            name:'Login',
            component:Login
        },
        {
            path:'/Register',
            name:'Register',
            component: () => import("@/views/Regiser.vue"), // 懒加载
        },
        {
            path:'/Home',
            name:'Home',
            component:Home,
            meta: { requiresAuth: true }, // 需要登录
        },

        {path: '/strength', 
                component:Strength,
                meta: { requiresAuth: true }, // 需要登录
                children:[
                {
                    path: '',
                        // 修改为独立的子组件
                        component: () => import('@/components/StrengthMain.vue'),
                        name: 'StrengthMain'// 懒加载
                    },
                {path: 'toplist', 
                    component: () => import("@/components/TopList.vue"), // 懒加载
                },
                {path: 'playlist', 
                    component: () => import("@/components/PlayList.vue"), // 懒加载
                },
            ],
               
        },

        {path: '/breathe', 
                component:Breathe,
                meta: { requiresAuth: true }, // 需要登录
                children:[
                    {
                        path: '',
                        component: () => import("@/components/BreatheMain.vue"), // 懒加载 
                      },
                    {path:':id',
                        component: () => import("@/components/Product.vue"), // 懒加载
                    }
                ]
            },
        {path: "/cwater",
        component: Cwater,
        meta: { requiresAuth: true }, // 需要登录
            },
    ]
})
// 全局前置守卫
// 在原有路由守卫基础上增加注销处理
router.beforeEach((to, from, next) => {
    const isAuthenticated = localStorage.getItem("isAuthenticated");
    
    // 增加对注销路由的判断
    if (to.path === "/logout") {
      localStorage.removeItem("isAuthenticated");
      next("/");
      return;
    }
  
    // 原有逻辑保持不变
    if (to.meta.requiresAuth && !isAuthenticated) {
      next("/");
    } else if (to.path === "/" && isAuthenticated) {
      next("/Home");
    } else {
      next();
    }
  });

export default router