<template>
  <div class="EvaluateMeal">
    <EvaluateMeal/>
  </div>
</template>

<script>
// @ is an alias to /src
import EvaluateMeal from '@/components/SmartComponents/EvaluateMeal.vue'
export default {
  name: 'EvaluateMealPage',
  components: {
    EvaluateMeal,
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
