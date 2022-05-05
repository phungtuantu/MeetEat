<template>
  <div class="home">
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
    <Home/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from '@/components/SmartComponents/NavbarReturnHome.vue'
import Home from '@/components/SmartComponents/Home.vue'
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'HomePage',
  components: {
    Footer,
    NavbarReturnHome,
    Home
  }
}
</script>
