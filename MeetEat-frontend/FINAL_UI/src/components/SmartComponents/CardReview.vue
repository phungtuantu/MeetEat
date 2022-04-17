<template>
    <div class="card">
        <div class="row d-flex">
            <div class=""> <img class="profile-pic" src="https://i.imgur.com/V3ICjlm.jpg"> </div>
            <div class="">
                <h3 class="mt-2 mb-0">Mukesh Kumar</h3>
                    <div class="row">
                    <div class="col-2">
                        <h4 class="text-note">4</h4>
                    </div>
                    <div class="col-10">
                        <label class="star-active" for="1">★</label> 
                        <label class="star-active" for="2">★</label> 
                        <label class="star-active" for="3">★</label> 
                        <label class="star-active" for="4">★</label>
                        <label class="star-inactive" for="5">☆</label>
                    </div>
                    </div>
            </div>
            <div class="ml-auto">
                <p class="text-muted pt-5 pt-sm-3">10 Sept</p>
            </div>
        </div>
        <div class="row text-left">
            <h4 class="blue-text mt-3">Excellent</h4>
            <p class="content">Rosine is a superb girl ! She's very kind and she likes disovering new type of food.</p>
        </div>
    </div>
</template>

<script>
export default {
  name: "CardReview" //même nom que le nom de fichier
}
</script>

<style scoped>

.star-active {
    color: #FBC02D;
    font-size: 40px;
    margin: 0;
    padding: 0;
}

.star-inactive {
    color: #CFD8DC;
    margin: 0;
    padding: 0;
    font-size: 40px;
}

.blue-text {
    color: #0091EA;
    /*padding: 0;*/
}

.content {
    font-size: 18px
}

.profile-pic {
    width: 90px;
    height: 90px;
    border-radius: 100%;
    margin-right: 30px
}

div.col-10, div.col-2{
  padding: 0;
}

h4.text-note{
  margin-top: 15px;
}

</style>