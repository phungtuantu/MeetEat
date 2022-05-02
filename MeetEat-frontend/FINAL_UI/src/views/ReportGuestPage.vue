<template>
  <div class="ReportGuest">
    <ReportGuest/>
  </div>
</template>

<script>
// @ is an alias to /src
import ReportGuest from '@/components/SmartComponents/ReportGuest.vue'
export default {
  name: 'ReportGuestPage',
  components: {
    ReportGuest,
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
