import Vue from 'vue'
import VueRouter from 'vue-router'
import "chart.js/auto";
import 'chart.js';
import axios from 'axios'
import HomePage from "@/views/HomePage";
import OrderPage from "@/views/OrderPage";
import OfferPage from "@/views/OfferPage";
import LoginPage from "@/views/LoginPage";
import CreateAccountPage from "@/views/CreateAccountPage";
import SelectPreferencesPage from "@/views/SelectPreferencesPage";
import EvaluateMealPage from "@/views/EvaluateMealPage";

axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("auth-token")}`;
axios.interceptors.response.use(response => response, error => {
  if (error.response || error.response.status == 401) {
    localStorage.removeItem('username');
    localStorage.removeItem('auth-token');
    router.replace("/");
  }
});

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },

  {
    path: '/orderPage',
    name: 'OrderPage',
    component: OrderPage
  },
  {
    path: '/orderPage/:id',
    name: 'OfferPage',
    component: OfferPage
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/signup',
    name: 'CreateAccountPage',
    component: CreateAccountPage
  },
  {
    path: '/selectpreferences',
    name: 'SelectPreferencesPage',
    component: SelectPreferencesPage
  },
  {
    path: '/evaluatemeal',
    name: 'EvaluateMealPage',
    component: EvaluateMealPage
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
