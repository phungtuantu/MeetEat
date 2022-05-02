<template>
  <div>
    <NavbarLogin></NavbarLogin>
    <br/>
    <CreateAccount/>
    <br/>
  </div>
</template>

<script>
// @ is an alias to /src
import NavbarLogin from "@/components/SmartComponents/NavbarLogin.vue";
import CreateAccount from '@/components/SmartComponents/CreateAccount.vue'
export default {
  name: 'CreateAccountPage',
  components: {
    CreateAccount,
    NavbarLogin,
  }
}
</script>
