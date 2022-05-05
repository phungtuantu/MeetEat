<template>
  <div>
    <Navbar></Navbar>
    <br/>
    <ModificationOffer></ModificationOffer>
    <Footer></Footer>
  </div>
</template>

<script>
import Navbar from "@/components/SmartComponents/NavbarReturnHome";
import ModificationOffer from "@/components/SmartComponents/ModificationOffer";
import Footer from "@/components/SmartComponents/Footer";

export default {
  name: "ModificationOfferPage",
  components: {ModificationOffer, Navbar, Footer}

}
</script>

<style scoped>

</style>