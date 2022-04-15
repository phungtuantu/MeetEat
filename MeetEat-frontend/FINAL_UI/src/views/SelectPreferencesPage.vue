<template>
  <div class="home">
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
    <SelectPreferences/>
    <br/>
    <Footer></Footer>
  </div>
</template>

<!-- 
Cocher par défault les diets préférences 
Obliger à décocher si je mange avec un ami
-->

<script>
// @ is an alias to /src
import NavbarReturnHome from '@/components/SmartComponents/NavbarReturnHome.vue'
import SelectPreferences from '@/components/SmartComponents/SelectPreferences.vue'
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'Home',
  components: {
    NavbarReturnHome,
    SelectPreferences,
    Footer,
  }
}
</script>
