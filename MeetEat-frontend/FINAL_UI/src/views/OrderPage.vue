<template>
  <div>
    <Navbar></Navbar>
    <br/>
    <Order_PageMain_Content></Order_PageMain_Content>
    <Footer></Footer>
  </div>
</template>

<script>
import Navbar from "@/components/SmartComponents/NavbarReturnHome";
import Order_PageMain_Content from "@/components/SmartComponents/Order_PageMain_Content";
import Footer from "@/components/SmartComponents/Footer";

export default {
  name: "OrderPage",
  components: {Order_PageMain_Content, Navbar, Footer}
}
</script>

<style scoped>

</style>