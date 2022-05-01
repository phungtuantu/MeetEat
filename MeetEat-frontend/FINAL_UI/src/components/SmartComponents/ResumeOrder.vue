<template>
  <div>
    <div class="card">
      <img class="card-img-top"
           src="../../assets/lasagne.jpg"/>
      <div class="card-body" style="text-align: left;">
        <h5 class="card-title">Delicious Lasagna ! </h5>
        <p class="card-text">
          <b>Ordered : 01/01/2022 </b><br/>
          <b>Delivery : 03/01/2022 </b><br/>

          <b>At 150m from your position</b><br/>
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </p>
        <br/>


        <div class="input-group w-auto">
          <button class="btn btn-dark" type="button" @click="viewDetails()">View details</button>
        </div>
      </div>
    </div>


    <div class="card">
      <img class="card-img-top"
           src="https://codingyaar.com/wp-content/uploads/bootstrap-4-card-image-left-demo-image.jpg"/>
      <div class="card-body" style="text-align: left;">
        <h5 class="card-title">Couscous </h5>
        <p class="card-text">
          <b>Ordered : 04/01/2022 </b><br/>
          <b>Delivery : 05/01/2022 </b><br/>
          <b>At 200m from your position</b><br/>
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </p>
        <br/>

        <div class="input-group w-auto">
          <button class="btn btn-dark" type="button" @click="viewDetails()">View details</button>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "ResumeOrder",
  methods : {
    viewDetails : function ()
    {
      router.replace('/orderPage/1')
    }
  }
}
</script>

<style scoped>
.card {
  flex-direction: row;
  align-items: center;
  margin-bottom: 30px;
}

.card-title {
  font-weight: bold;
}

.card img {
  width: 30%;
  border-top-right-radius: 0;
  border-bottom-left-radius: calc(0.25rem - 1px);
}
</style>