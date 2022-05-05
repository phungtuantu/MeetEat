<template>
  <div class="">
    <NavbarReturnHome/>
    <div class="ReportCook">
      <ReportCook/>
    </div>
    <br/>
    <br/>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from '@/components/SmartComponents/NavbarReturnHome.vue'
import ReportCook from '@/components/SmartComponents/ReportCook.vue'
import Footer from '@/components/SmartComponents/Footer.vue'
export default {
  name: 'ReportCookPage',
  components: {
    NavbarReturnHome,
    ReportCook,
    Footer,
  }
}
</script>

<style scoped>

.ReportCook {
    margin-top: 50px;
    margin-left: 50px;
    border: none
}

</style>
