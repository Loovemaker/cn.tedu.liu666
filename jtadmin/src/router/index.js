import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import ElementUI from '../components/ElementUI.vue'
import Home from '../components/Home.vue'
import user from '../components/user/user.vue'
import ItemCat from '../components/items/ItemCat.vue'
import Item from '../components/items/Item.vue'
import AddItem from '../components/items/addItem.vue'

//使用路由机制
Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  {path: '/elementUI', component: ElementUI},
  {path: '/home', component: Home, children: [
    {path: '/user', component: user},
    {path: '/itemCat', component: ItemCat},
    {path: '/item', component: Item},
    {path: '/item/addItem', component: AddItem},
  ]},
]

//路由导航守卫!!!!!!!

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if(to.path === '/login') return next()

  let token = window.sessionStorage.getItem('token')
  if(!token) return next('/login')

  return next()
})

export default router
