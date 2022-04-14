<template>
  <div>
    <NavbarLogin></NavbarLogin>
    <br/>
    <Login/>
    <br/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarLogin from "@/components/SmartComponents/NavbarLogin.vue";
import Login from '@/components/SmartComponents/Login.vue'
export default {
  name: 'LoginPage',
  components: {
    Login,
    NavbarLogin,
  }
}
</script>
