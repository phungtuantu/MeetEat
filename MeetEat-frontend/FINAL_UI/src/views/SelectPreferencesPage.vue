<template>
  <div class="home">
    <NavbarFullMenu></NavbarFullMenu>
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
import NavbarFullMenu from '@/components/SmartComponents/NavbarFullMenu.vue'
import SelectPreferences from '@/components/SmartComponents/SelectPreferences.vue'
import Footer from "@/components/SmartComponents/Footer";
export default {
  name: 'Home',
  components: {
    NavbarFullMenu,
    SelectPreferences,
    Footer,
  }
}
</script>
