<template>
  <div class="">
    <Navbar></Navbar>
    <div class="EvaluateMeal">
      <br/>
      <EvaluateMeal/>
    </div>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import EvaluateMeal from '@/components/SmartComponents/EvaluateMeal.vue'
import Navbar from '@/components/SmartComponents/Navbar.vue'
import Footer from '@/components/SmartComponents/Footer.vue'
export default {
  name: 'EvaluateMealPage',
  components: {
    Navbar,
    EvaluateMeal,
    Footer,
  }
}
</script>

<style scoped>

.EvaluateMeal {
    margin-top: 50px;
    margin-left: 50px;
    border: none
}

</style>
