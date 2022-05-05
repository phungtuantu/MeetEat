<template>
  <div>
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
      <BecomeCook/>
    <br/>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome.vue";
import BecomeCook from '@/components/SmartComponents/BecomeCook.vue';
import Footer from '@/components/SmartComponents/Footer.vue'
export default {
  name: 'BecomeCookPage',
  components: {
    NavbarReturnHome,
    BecomeCook,
    Footer,
  }
}
</script>
