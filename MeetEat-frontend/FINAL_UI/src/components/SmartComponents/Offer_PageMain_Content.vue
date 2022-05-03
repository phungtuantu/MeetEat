<template>
  <div class="container">
    <div class="card mb-3">
      <img class="card-img-top" id="mainPicture"
           src="../../assets/lasagne.jpg"
           alt="Card image cap">
      <div class="card-body" style="text-align: left;">
        <h5 class="card-title">Delicious Lasagna ! </h5>
        <div class="row">
          <div class="col-4">
            <img src="../../assets/ithan.jpg" alt="Ithan" width="120px"> <!--  height="100px" -->
          </div>
          <div class="col-8">
            <div class="row">
              Ithan
            </div>
            <div class="row">
              <img src="../../assets/blackStar.png" alt="Ithan" width="20px" height="20px">
              <img src="../../assets/blackStar.png" alt="Ithan" width="20px" height="20px">
              <img src="../../assets/blackStar.png" alt="Ithan" width="20px" height="20px">
              <img src="../../assets/blackStar.png" alt="Ithan" width="20px" height="20px">
              <img src="../../assets/grayStar.png" alt="Ithan" width="20px" height="20px">

              4/5 (50 reviews)
            </div>
          </div>
        </div>

        <br/>
        <h5>List of ingredients : </h5>
        <ul class="list-group" style="padding-left: 30px;">
          <li class="">Ground meat</li>
          <li class="">Tomato sauce</li>
          <li class="">Pasta</li>
          <li class="">Bechamel</li>
          <li class="">Shredded cheese</li>
        </ul>
        <br/>
        <div class="row">
          <div class="col-sm">
            <h5>More specifications : </h5>
            <ul class="list-group" style="padding-left: 30px;">
              <li class="">French meat</li>
            </ul>
          </div>
          <div class="col-sm">
            <h5>Type of cooking : </h5>
            <ul class="list-group" style="padding-left: 30px;">
              <li class="">Italian food</li>
            </ul>
          </div>
        </div>

        <br/>

        <p class="card-text">If you have any food allergies or special dietary requirements, please notify Ithan
          directly before placing your order.</p>
        <p class="card-text"><small class="text-muted">4 portions left</small></p>
        <div class="input-group w-auto" id="counter">
          <input class="button-minus border rounded-circle  icon-shape icon-sm mx-1 " data-field="quantity" type="button"
                 value="-">
          <input class="quantity-field border-0 text-center w-25" max="10" min="1" name="quantity" step="1" type="number"
                 value="1">
          <input class="button-plus border rounded-circle icon-shape icon-sm lh-0" data-field="quantity" type="button"
                 value="+">
        </div>
        <br/>
        <button type="button" class="btn btn-dark">Add to basket </button>
      </div>


    </div>

  </div>
</template>

<script>
export default {
  name: "Offer_PageMain_Content"
}
</script>

<style scoped>

.card {
  align-items: center;
  margin-bottom: 30px;
}

.card #mainPicture {
  width: 60%;
  border-top-right-radius: 0;
  border-bottom-left-radius: calc(0.25rem - 1px);
}

@media only screen and (max-width: 768px) {

  .card-body {
    padding: 0.5em 1.2em;
  }

  .card-body .card-text {
    margin: 0;
  }

  .card #mainPicture {
    width: 60%;
  }
}

@media only screen and (max-width: 1200px) {
  .card #mainPicture {
    width: 60%;
  }
}

button, #counter {
  width: 100%;
}
</style>