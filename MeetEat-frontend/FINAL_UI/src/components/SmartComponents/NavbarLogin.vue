<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <h1 class="display-5">MeetEat</h1>
          <a><img src="../../assets/MeetEat_logo.jpg" width="35px" @click="goHome()"></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        </ul>
      </div>
    </nav>
  </div>

</template>

<script>
import router from "@/router";
export default {
  name: "Navbar",
  data() {
    return {

    }
  },
  methods: {
    goHome : function(){
      router.replace('/');
    }
  },
}
</script>

<style scoped>
a{
  margin-left: 15px;
  margin-bottom: 10px;
  cursor: pointer;
}
</style>