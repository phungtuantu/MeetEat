<template>
  <div>
    <Navbar></Navbar>
    <br/>
    <Offer_PageMain_Content></Offer_PageMain_Content>
    <Footer></Footer>
  </div>
</template>

<script>
import Navbar from "@/components/SmartComponents/Navbar";
import Offer_PageMain_Content from "@/components/SmartComponents/Offer_PageMain_Content";
import Footer from "@/components/SmartComponents/Footer";

export default {
  name: "OfferPage",
  components: {Offer_PageMain_Content, Navbar, Footer}
}
</script>

<style scoped>

</style>