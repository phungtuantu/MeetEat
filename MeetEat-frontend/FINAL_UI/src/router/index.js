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
import ReportCookPage from "@/views/ReportCookPage";
import EvaluateGuestPage from "@/views/EvaluateGuestPage";
import ReportGuestPage from "@/views/ReportGuestPage";
import ViewReviewsPage from "@/views/ViewReviewsPage";
import ModificationAccountPage from "@/views/ModificationAccountPage";
import CookOfferHistoryPage from "@/views/CookOfferHistoryPage";
import ModificationOfferPage from "@/views/ModificationOfferPage";
import ResumeOrderPage from "@/views/ResumeOrderPage";
import BecomeCookPage from "@/views/BecomeCookPage";

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
    path: '/modificationOffer/:id',
    name: 'ModificationOffer',
    component: ModificationOfferPage
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
  {
    path: '/reportcook',
    name: 'ReportCookPage',
    component: ReportCookPage
  },
  {
    path: '/evaluateguest',
    name: 'EvaluateGuestPage',
    component: EvaluateGuestPage
  },
  {
    path: '/reportguest',
    name: 'ReportGuestPage',
    component: ReportGuestPage
  },
  {
    path: '/viewreviews',
    name: 'ViewReviewsPage',
    component: ViewReviewsPage
  },
  {
    path: '/myBasket',
    name: 'OrderReview',
    component: ResumeOrderPage
  },
  {
    path: '/modificationAccount',
    name: 'ModificationAccountPage',
    component: ModificationAccountPage
  },
  {
    path: '/cookofferhistory',
    name: 'CookOfferHistoryPage',
    component: CookOfferHistoryPage
  },
  {
    path: '/becomeCook',
    name: 'BecomeCookPage',
    component: BecomeCookPage
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
