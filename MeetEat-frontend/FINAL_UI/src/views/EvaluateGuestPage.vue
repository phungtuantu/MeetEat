<template>
  <div class="EvaluateGuest">
    <EvaluateGuest/>
  </div>
</template>

<script>
// @ is an alias to /src
import EvaluateGuest from '@/components/SmartComponents/EvaluateGuest.vue'
export default {
  name: 'EvaluateGuestPage',
  components: {
    EvaluateGuest,
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
