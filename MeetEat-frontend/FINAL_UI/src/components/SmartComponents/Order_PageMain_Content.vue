<template>
  <div class="row">

    <div class="col-3 border-right " style="text-align: left; margin-left: 25px;">
      <h2>Sort</h2>

      <form>
      <div class="form-check">
        <input id="flexRadioDefault1" checked class="form-check-input" name="flexRadioDefault" type="radio">
        <label class="form-check-label" for="flexRadioDefault1">
          Most popular
        </label>
      </div>
      <div class="form-check">
        <input id="flexRadioDefault2" class="form-check-input" name="flexRadioDefault" type="radio">
        <label class="form-check-label" for="flexRadioDefault2">
          Rating
        </label>
      </div>
      </form>
      <br/>


      <div class="input-group">
        <input id="form1" class="form-control" placeholder="Keyword" type="search"/>
        <button class="btn btn-primary" type="button">
          Search
        </button>
      </div>

      <br/>

      <h4>Price Range</h4>

      <div class="row">
        <article class=" col-sm">
          <input id="price1" type="checkbox"/>
          <div>
      <span>
        $
      </span>
          </div>
        </article>

        <article class=" col-sm">
          <input id="price2" type="checkbox"/>
          <div>
      <span>
        $$
      </span>
          </div>
        </article>

        <article class=" col-sm">
          <input id="price3" type="checkbox"/>
          <div>
              <span>
                $$$
              </span>
          </div>
        </article>
      </div>


      <br/>

      <h4>Diets</h4>

      <div class="">
        <article class=" col-sm">
          <input id="diet1" type="checkbox"/>
          <div>
      <span>
        Vegeterian
      </span>
          </div>
        </article>


        <article class=" col-sm">
          <input id="diet2" type="checkbox"/>
          <div>
      <span>
        Vegan
      </span>
          </div>
        </article>

        <article class=" col-sm">
          <input id="diet3" type="checkbox"/>
          <div>
              <span>
                Pesco vegetarian
              </span>
          </div>
        </article>


        <article class=" col-sm">
          <input id="diet4" type="checkbox"/>
          <div>
              <span>
                Dairy-free
              </span>
          </div>
        </article>

        <article class=" col-sm">
          <input id="diet5" type="checkbox"/>
          <div>
              <span>
                Gluten free
              </span>
          </div>
        </article>

        <article class=" col-sm">
          <input id="diet6" type="checkbox"/>
          <div>
              <span>
                No pork
              </span>
          </div>
        </article>

      </div>



      <h4>Food type</h4>


      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="foodType1">
        <label class="form-check-label" for="foodType1">
          Asian food
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="foodType2">
        <label class="form-check-label" for="foodType2">
          Italian food
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="foodType3">
        <label class="form-check-label" for="foodType3">
          Fast food
        </label>
      </div>




    </div>


    <div class="col-8">
      <div class="card">
        <img class="card-img-top"
             src="https://codingyaar.com/wp-content/uploads/bootstrap-4-card-image-left-demo-image.jpg"/>
        <div class="card-body" style="text-align: left;">
          <h5 class="card-title">Delicious Lasagna ! </h5>
          <p class="card-text">
            <b>At 150m from your position</b><br/>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </p>
          <br/>


          <div class="input-group w-auto">
            <input class="quantity-field border-0 text-center w-25" disabled type="text" value="5$">
            <input class="button-minus border rounded-circle  icon-shape icon-sm mx-1 " data-field="quantity" type="button"
                   value="-">
            <input class="quantity-field border-0 text-center w-25" max="10" name="quantity" step="1" type="number"
                   value="1">
            <input class="button-plus border rounded-circle icon-shape icon-sm lh-0" data-field="quantity" type="button"
                   value="+">
            <button class="btn btn-dark" type="button">Add to basket</button>
          </div>
        </div>
      </div>


      <div class="card">
        <img class="card-img-top"
             src="https://codingyaar.com/wp-content/uploads/bootstrap-4-card-image-left-demo-image.jpg"/>
        <div class="card-body" style="text-align: left;">
          <h5 class="card-title">Couscous </h5>
          <p class="card-text">
            <b>At 200m from your position</b><br/>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </p>
          <br/>


          <div class="input-group w-auto">
            <input class="quantity-field border-0 text-center w-25" disabled type="text" value="5$">
            <input class="button-minus border rounded-circle  icon-shape icon-sm mx-1 " data-field="quantity" type="button"
                   value="-">
            <input class="quantity-field border-0 text-center w-25" max="10" name="quantity" step="1" type="number"
                   value="1">
            <input class="button-plus border rounded-circle icon-shape icon-sm lh-0" data-field="quantity" type="button"
                   value="+">
            <button class="btn btn-dark" type="button">Add to basket</button>
          </div>
        </div>
      </div>


      <div class="card">
        <img class="card-img-top"
             src="https://codingyaar.com/wp-content/uploads/bootstrap-4-card-image-left-demo-image.jpg"/>
        <div class="card-body" style="text-align: left;">
          <h5 class="card-title">Mexican Tacos</h5>
          <p class="card-text">
            <b>At 500m from your position</b><br/>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </p>
          <br/>


          <div class="input-group w-auto">
            <input class="quantity-field border-0 text-center w-25" disabled type="text" value="6$">
            <input class="button-minus border rounded-circle  icon-shape icon-sm mx-1 " data-field="quantity" type="button"
                   value="-">
            <input class="quantity-field border-0 text-center w-25" max="10" name="quantity" step="1" type="number"
                   value="1">
            <input class="button-plus border rounded-circle icon-shape icon-sm lh-0" data-field="quantity" type="button"
                   value="+">
            <button class="btn btn-dark" type="button">Add to basket</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Order_PageMain_Content"
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

@media only screen and (max-width: 768px) {

  .card-body {
    padding: 0.5em 1.2em;
  }

  .card-body .card-text {
    margin: 0;
  }

  .card img {
    width: 50%;
  }
}

@media only screen and (max-width: 1200px) {
  .card img {
    width: 40%;
  }
}


article {
  position: relative;

  margin: 5px;
  float: left;
  border: 2px solid black;
  box-sizing: border-box;
}

article div {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 25px;
  transition: .5s ease;
}

article input {
  position: absolute;
  top: 0;
  left: 0;
  width: 140px;
  height: 100px;
  opacity: 0;
  cursor: pointer;
}

input[type=checkbox]:checked ~ div {
  background-color: darkgrey;
}

.social i:before {
  width: 14px;
  height: 14px;
  position: fixed;
  color: #fff;
  background: #0077B5;
  padding: 10px;
  border-radius: 50%;
  top: 5px;
  right: 5px;
}

@keyframes slidein {
  from {
    margin-top: 100%;
    width: 300%;
  }

  to {
    margin: 0%;
    width: 100%;
  }
}
</style>