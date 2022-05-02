<template>
  <div class="home">
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
    <VerificationCook/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<script>
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome";
import VerificationCook from "@/components/SmartComponents/VerificationCook";
import Footer from "@/components/SmartComponents/Footer"
export default {
  name: "VerificationCookPage",
  components: {VerificationCook, Footer, NavbarReturnHome}

}
</script>

<style scoped>

</style>