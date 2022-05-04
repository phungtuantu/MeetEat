<template>

  <div class="container">
    <div style="text-align: center">
      <h1>Create an Offer</h1>
    </div>
    <form>
      <div class="form-group">
        <img class="" id="mainPicture" src="../../assets/grey-square.png" height="400px" width="60%" >
      </div>

      <div class="form-group">
        <input type="file" id="csv" name="profile_pic"
               accept=".jpg">
      </div>

      <div class="form-group row">
        <label for="title" class="col-sm-2 col-form-label">Title</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="title" v-model="title">
        </div>
      </div>

      <div class="form-group row">
        <label for="ingredients" class="col-sm-2 col-form-label">Ingredients</label>
        <div class="col-sm">
            <div class="row">
              <div class="col-sm"  id="listIngredients">
                <input type="text" class="form-control" id="ingredients" v-model="ingredient">
              </div>
              <div class="col-sm"  id="listDeleteButton">
                <button type="button" class="btn btn-success" @click='deleteIngredients(ingredient, numberOfIngredients)'>Delete</button>
              </div>
            </div>
            <br/>
              <button type="button" class="btn btn-success" @click='addIngredients'>Add an ingredient</button>

        </div>
        <div class="col-sm">
        </div>
      </div>

      <div class="form-group row">
        <label for="description" class="col-sm-2 col-form-label">Description</label>
        <div class="col-sm">
          <textarea id="description" class="form-control" rows="3" v-model="description"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">More specifications :</label>
        <div class="col-sm-10">
          <textarea id="specification" class="form-control" rows="3" v-model="specification"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="typeOfCuisine" class="col-sm-2 col-form-label">Type of cuisine</label>
        <div class="col-sm">
          <select name="line" id="typeOfCuisine" class="form-select form-select-sm" aria-label=".form-select-sm" v-model="typeOfCuisine">
            <option value="american">American</option>
            <option value="cantoneese">Cantoneese</option>
            <option value="chineese">Chineese</option>
            <option value="french">French</option>
            <option value="italian">Italian</option>
            <option value="mexican">Mexican</option>
            <option value="spanish">Spanish</option>
          </select>
        </div>
      </div>


      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">Diet </label>
        <div class="col-sm-10">
          <div class="row">
            <article class=" col-sm">
              <input id="dairyFree" type="checkbox"/>
              <div>
                <span>
                  Dairy free
                </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="glutenFree" type="checkbox"/>
              <div>
                <span>
                  Gluten free
                </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="noPork" type="checkbox"/>
              <div>
                <span>
                  No pork
                </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="vegan" type="checkbox"/>
              <div>
                <span>
                  Vegan
                </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="Vegetarian" type="checkbox"/>
              <div>
                <span>
                  Vegetarian
                </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="Pesco-vegetarian" type="checkbox"/>
              <div>
                <span>
                  Pesco-vegetarian
                </span>
              </div>
            </article>

          </div>

        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Number of servings</label>
        <div class="col-sm-10">
          <input type="number" class="form-control-plaintext" id="portions" v-model="portions">
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Sale date</label>
        <div class="col-sm-10">
          <input type="date" class="form-control-plaintext" id="portions" v-model="portions">
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Sales address</label>
        <input type="text" name="" placeholder="Address">
        </div>

        <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Zip code </label>
        <input type="text" name="" placeholder="Zip code">
        </div>

        <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">City </label>
            <input type="text" name="" placeholder="City">
        </div>

        <div class="row">
        <label for="portions" class="col-sm-2 col-form-label">Check one or both </label>
            <article class="col-4">
              <input type="checkbox"/>
              <div>
                <span>
                  To take away
                </span>
              </div>
            </article>
            <article class="col-4">
              <input type="checkbox"/>
              <div>
                <span>
                  On site
                </span>
              </div>
            </article>
        </div>
        <br>

    </form>

    <div class="row">
        <div class="col-sm" id="validate">
            <button type="button" class="btn btn-success2" @click='validateInformation()'>Next step</button>
        </div>
    </div>


    <template v-if="show === 1">
      <form>
        <div class="form-group row">
          <label for="suggestedPrice" class="col-sm-2 col-form-label">Price suggestion</label>
          <div class="col-sm-10">
            <input type="number" disabled readonly class="form-control-plaintext" id="suggestedPrice" v-model="suggestedPrice">
          </div>
        </div>

        <div class="form-group row">
          <label for="sellingPrice" class="col-sm-2 col-form-label">Sale price</label>
          <div class="col-sm-10">
            <input type="number" step="0.01" class="form-control-plaintext" id="sellingPrice" v-model="sellingPrice">
          </div>
        </div>

      </form>

      <button type="button" class="btn btn-success2" id="saveBtn" @click='save()'>Publish</button>
    </template>

    <template v-if="show === 2">
        <div class="row">
            <label class="successPublish">Your application has been published successfully</label>
            <button type="button" class="btn btn-info" id="okHome" @click='backHome()'> OK </button>
        </div>
    </template>

    <br/>
    <br/>


    <div class="row">
      <div class="col-sm">
        <br>
        <button type="button" class="btn btn-dark  btn-lg btn-block" id="cancelBtn" @click='backHome()'>Cancel</button>
      </div>
    <!--
      <div class="col-sm">
        <button type="button" class="btn btn-danger  btn-lg btn-block" @click='deleteOffer()'>Delete</button>
      </div>-->
    </div>

    <br/>
    <br/>
    <br/>
    <br/>

  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "ModificationOffer",
  data() {
    return {
      title : '',
      description : '',
      specification : '',
      typeOfCuisine : '',
      portions : 0,
      suggestedPrice : 5.50,
      sellingPrice : 5.99,
      ingredient : null,
      show : 0,
      date : null,
      numberOfIngredients : 1,

    }
  },
  methods: {
    addIngredients : function () {
      console.log('add');
      this.numberOfIngredients ++;
      var newInput = document.createElement("input");
      newInput.setAttribute("class", "form-control");
      newInput.setAttribute("id", "ingredients"+this.numberOfIngredients);
      newInput.setAttribute("type", "text");

      document.getElementById("listIngredients").appendChild(newInput);

      var br = document.createElement("br");

      var newButton = document.createElement("button");
      newButton.setAttribute("type", "button");
      newButton.setAttribute("class", "btn btn-success");
      newButton.innerText = "Delete";
      newButton.onclick = this.deleteIngredients("ingredients"+this.numberOfIngredients, this.numberOfIngredients);
      document.getElementById("listDeleteButton").appendChild(br);
      document.getElementById("listDeleteButton").appendChild(newButton);

    },

    deleteIngredients : function (ingredient, index)
    {
      console.log(ingredient + ' ' + index);
      return 0;
    },

    validateInformation : function ()
    {
        document.getElementById("validate").style.display = "none";
      this.show = 1;
    },

    backHome : function () {
      router.replace('/');

    },

    deleteOffer : function () {
      console.log('delete');
    },

    save : function () {
      console.log('save');
      document.getElementById("saveBtn").style.display = "none";
      document.getElementById("cancelBtn").style.display = "none";
      this.show = 2;
    },

  },
}
</script>

<style scoped>

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
  background-color: green;
}

.btn-dark, .btn-success2{
    width: 50%;
    height: 50px;
    float: center;
    font-size: 20px;
}

.btn-success2{
    background-color: green;
    color: white;
}

.btn-dark{
    margin-left: 25%;
}

.btn-info{
    background-color: grey;
    margin-left: 20px;
}

.successPublish{
    color: gray;
    float: center;
    margin-left: 100px;
    margin-top: 30px;
    font-size: 20px;
}

input[type="checkbox"]{
    width: 100%;
    height: 100%;
}


</style>