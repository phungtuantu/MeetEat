<template>
  <div class="">
    <NavbarReturnHome/>
    <div class="ViewReviews">
      <ViewReviews/>
    </div>
    <br/>
    <br/>
    <Footer/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from '@/components/SmartComponents/NavbarReturnHome.vue'
import ViewReviews from '@/components/SmartComponents/ViewReviews.vue'
import Footer from '@/components/SmartComponents/Footer.vue'
export default {
  name: 'ViewReviewsPage',
  components: {
    NavbarReturnHome,
    ViewReviews,
    Footer,
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
