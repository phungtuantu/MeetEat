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
import Login from '@/components/SmartComponents/Login.vue'
export default {
  name: 'Home',
  components: {
    Login
  }
}
</script>
