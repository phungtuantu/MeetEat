<template>

  <div class="home">
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
      <ResumeOrder/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<script>
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome";
import ResumeOrder from "@/components/SmartComponents/ResumeOrder";
import Footer from "@/components/SmartComponents/Footer"

export default {
  name: "CookOfferHistoryPage",
  components: {NavbarReturnHome, Footer, ResumeOrder}
}
</script>

<style scoped>

h2{
    text-align: left;
    margin-left: 70px;
    margin-bottom: 25px;
    font-weight: bold;
}

.btn{
  width: 500px;
  margin-top: 50px;
  margin-bottom: 60px;
}

.line-color {
    width: 70%;
    margin-top: 50px;
    margin-right: 5%;
    height: 1px;
    background-color: gray;
}

.line-color2 {
    width: 100%;
    margin-top: 50px;
    margin-right: -10%;
    /*height: 1px;*/
    background-color: rgb(150, 150, 150);
}

</style>