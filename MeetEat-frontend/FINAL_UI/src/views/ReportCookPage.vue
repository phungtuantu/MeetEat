<template>
  <div class="ReportCook">
    <ReportCook/>
  </div>
</template>

<script>
// @ is an alias to /src
import ReportCook from '@/components/SmartComponents/ReportCook.vue'
export default {
  name: 'ReportCookPage',
  components: {
    ReportCook,
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
