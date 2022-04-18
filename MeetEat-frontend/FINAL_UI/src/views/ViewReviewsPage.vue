<template>
  <div class="ViewReviews">
    <ViewReviews/>
  </div>
</template>

<script>
// @ is an alias to /src
import ViewReviews from '@/components/SmartComponents/ViewReviews.vue'
export default {
  name: 'ViewReviewsPage',
  components: {
    ViewReviews,
  }
}
</script>

<style scoped>

.ViewReviews {
    margin-top: 50px;
    margin-left: 50px;
    border: none
}

</style>
