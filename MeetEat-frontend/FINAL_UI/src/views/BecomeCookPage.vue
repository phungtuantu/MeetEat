<template>
  <div>
    <NavbarReturnHome></NavbarReturnHome>
    <br/>
      <BecomeCook/>
    <br/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarReturnHome from "@/components/SmartComponents/NavbarReturnHome.vue";
import BecomeCook from '@/components/SmartComponents/BecomeCook.vue'
export default {
  name: 'BecomeCookPage',
  components: {
    NavbarReturnHome,
    BecomeCook,
  }
}
</script>
