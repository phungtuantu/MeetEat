<template>
<div class="">
    <NavbarReturnHome/>
    <div class="ReportGuest">
      <ReportGuest/>
    </div>
  <br/>
  <br/>
  <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from '@/components/SmartComponents/NavbarReturnHome.vue'
import ReportGuest from '@/components/SmartComponents/ReportGuest.vue'
import Footer from '@/components/SmartComponents/Footer.vue'
export default {
  name: 'ReportGuestPage',
  components: {
    NavbarReturnHome,
    ReportGuest,
    Footer,
  }
}
</script>

<style scoped>

.ReportGuest {
    margin-top: 50px;
    margin-left: 50px;
    border: none
}

</style>
