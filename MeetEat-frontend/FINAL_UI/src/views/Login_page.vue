<template>
  <div class="home">
    <Navbar></Navbar>
    <br/>
    <Login/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<script>
// @ is an alias to /src
import Navbar from '@/components/SmartComponents/Navbar.vue'
import Login from '@/components/SmartComponents/Login.vue'
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'Home',
  components: {
    Footer,
    Navbar,
    Login
  }
}
</script>
