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
        <div class="form-inline my-2 my-lg-0">
          <a href="http://localhost:8080/login">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="button">Sign In</button>
          </a>
          <a href="http://localhost:8080/signup">
            <button class="btn btn-dark my-2 my-sm-0" type="button">Sign Up</button>
          </a>
        </div>
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
.btn{
  margin-left: 15px;
}

a{
  margin-left: 15px;
  margin-bottom: 10px;
}

</style>