<template>
  <div>
    <NavbarLogin></NavbarLogin>
    <br/>
      <BecomeCook/>
    <br/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarLogin from "@/components/SmartComponents/NavbarLogin.vue";
import BecomeCook from '@/components/SmartComponents/BecomeCook.vue'
export default {
  name: 'BecomeCookPage',
  components: {
    NavbarLogin,
    BecomeCook,
  }
}
</script>
