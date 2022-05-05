<template>
  <div class="">
    <Navbar></Navbar>
  <div class="EvaluateGuest">
    <EvaluateGuest/>
  </div>
  <br/>
  <br/>
  <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import EvaluateGuest from '@/components/SmartComponents/EvaluateGuest.vue';
import Navbar from "@/components/SmartComponents/NavbarReturnHome";
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'EvaluateGuestPage',
  components: {
    Navbar,
    EvaluateGuest,
    Footer,
  }
}
</script>

<style scoped>

.EvaluateGuest {
    margin-top: 50px;
    margin-left: 50px;
    border: none
}

</style>
