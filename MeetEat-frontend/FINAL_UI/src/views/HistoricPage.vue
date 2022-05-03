<template>
  <div>
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
    <Historic/>
    <br/>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome.vue";
import Historic from '@/components/SmartComponents/Historic.vue';
import Footer from "@/components/SmartComponents/Footer.vue";
export default {
  name: 'CreateAccountPage',
  components: {
    Historic,
    Footer,
    NavbarReturnHome,
  }
}
</script>
