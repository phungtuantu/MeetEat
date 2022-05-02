<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <h1 class="display-5">MeetEat</h1>
        <a href="http://localhost:8080/">
          <img src="../../assets/MeetEat_logo.jpg" width="35px">
        </a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        </ul>
      </div>
    </nav>
  </div>

</template>

<script>
export default {
  name: "Navbar",
  data() {
    return {

    }
  },
  methods: {

  },
}
</script>

<style scoped>
a{
  margin-left: 15px;
  margin-bottom: 10px;
}
</style>