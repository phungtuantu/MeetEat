<template>
<form>
  <div class="form-row justify-content-md-left">
      <div class="left">
        <h1>Diet</h1>
      </div>
  </div>

    <section class="menu">
        <div class="left">
            <figure>
                <a href="">
                    <img src="../../assets/icons8-non-lactose-food-50.png" width="70px">
                </a>
                <figcaption>Dairy free</figcaption>
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-no-gluten-50.png" alt="no-gluten" width="70px">
                </a>
                <figcaption>Gluten free</figcaption>
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-no-pork-50.png" alt="no-pork" width="70px">
                </a>  
                <figcaption>No pork</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-vegan-mark-50.png" alt="vegan-mark" width="70px">
                </a>  
                <figcaption>Vegan</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-carrot-50.png" alt="vegetarian-carrot" width="70px">
                </a>  
                <figcaption>Vegetarian</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-whole-fish-50.png" alt="pesco-vegetarian" width="70px">
                </a>  
                <figcaption>Pesco-vegetarian</figcaption>      
            </figure>
        </div>
    </section>

    <div class="left">
        <div class="form-row justify-content-md-left">
            <h1>Preferences</h1>
        </div>
        <div class="form-row justify-content-md-left">
            <h4>Besides special diets, are there any ingredients you don't like ?</h4>
        </div>

        <div class="form-row justify-content-md-left">
            <select class="dropdown" name="dog-names" id="dog-names">
                <option value="rigatoni">Peanut</option>
                <option value="dave">Garlic</option>
                <option value="pumpernickel">Olives</option>
            </select>
            <button class="button_suppr" type="button">Delete</button>
        </div>

        <div class="form-row justify-content-md-left">
            <select class="dropdown" name="dog-names" id="dog-names">
                <option value="rigatoni">Peanut</option>
                <option value="dave">Garlic</option>
                <option value="pumpernickel">Olives</option>
            </select>
            <button class="button_suppr" type="button">Delete</button>
        </div>

        <div class="form-row justify-content-md-left">
            <select class="dropdown" name="dog-names" id="dog-names">
                <option value="rigatoni">Find an ingredient</option>
                <option value="rigatoni">Peanut</option>
                <option value="dave">Garlic</option>
                <option value="pumpernickel">Olives</option>
            </select>
            <button class="button_suppr" type="button">Add</button>
        </div>
        <div class="form-row justify-content-md-left">
            <button class="button_validate" type="button">Validate</button>
        </div>
    </div>
    <div class="form-row justify-content-md-left">
        <button class="button_save" type="button">Save</button>
    </div>

</form>
</template>

<script>
export default {
  name: "SelectPreferences" //même nom que le nom de fichier
}
</script>

<style scoped>

.left {
    text-align: left;
    margin-left: 20%;
    margin-top: 50px;
}

.menu figure {
    display: inline-block;
    margin-right: 5%;
}

.figcaption{
    text-align: center;
}

.dropdown {
    margin-top: 10px;
    width: 200px;
    background: white;
    text-align: center;
    border: 2px solid black;
    color: black;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_suppr{
    margin-left: 20px;
    margin-top: 10px;
    width: 100px;
    background-color: green;
    text-transform: none;
    border: 0;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_validate{
    margin-top: 10px;
    width: 200px;
    background-color: green;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_save{
    margin-left: 17%;
    margin-top: 30px;
    width: 800px;
    Height: 50px;
    background-color: green;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

</style>