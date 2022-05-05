<template>
<div class="container">
    <div class="row">
        <div class="col-2">
            <a href="">
                <img src="https://i.imgur.com/xELPaag.jpg" width="100px" class="">
            </a>
        </div>
        <div class="col-6">
            <h4>Rosine</h4>
            <h6>22 years old</h6>
            20 avenue Albert Einstein, Villeurbanne, 69100, FRANCE
        </div>
        <div class="col-2">
            <button type="button" class="btn btn-dark">View Request</button>
        </div>
    </div>

</div>
</template>

<script>
//import router from "@/router";
export default {
  name: "PendingRequests",
  methods: {
  },
}
</script>

<style scoped>
.container {
    width: 70%;
}

.row{
   background-color: #e2e2e2;
   margin: 10px;
}

.row img{
  margin: 10px;
}

.col-6{
  text-align: left;
  margin-top: 10px;
}

.btn-dark{
    float: center;
    margin-top: 40px;
    border-radius: 15px;
    background-color: black;
}

</style>