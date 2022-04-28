<template>
  <div>
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
    <ModificationAccount></ModificationAccount>
  </div>
</template>

<script>
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome";
import ModificationAccount from "@/components/SmartComponents/ModificationAccount"//;
//import Footer from "@/components/SmartComponents/Footer"

export default {
  name: "ModificationAccountPage",
  components: {NavbarReturnHome, ModificationAccount}
}
</script>

<style scoped>

</style>