<template>
  <div class="home">
    <Navbar></Navbar>
    <br/>
    <HomePage/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<script>
// @ is an alias to /src
import Navbar from '@/components/SmartComponents/Navbar.vue'
import HomePage from '@/components/SmartComponents/HomePage.vue'
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'Home',
  components: {
    Footer,
    Navbar,
    HomePage
  }
}
</script>
