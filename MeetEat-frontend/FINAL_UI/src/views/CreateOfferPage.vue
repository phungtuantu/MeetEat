<template>
  <div>
    <Navbar></Navbar>
    <br/>
    <CreateOffer></CreateOffer>
    <Footer></Footer>
  </div>
</template>

<script>
import Navbar from "@/components/SmartComponents/NavbarReturnHome";
import CreateOffer from "@/components/SmartComponents/CreateOffer";
import Footer from "@/components/SmartComponents/Footer";

export default {
  name: "CreateOfferPage",
  components: {Navbar, CreateOffer, Footer}

}
</script>

<style scoped>

</style>